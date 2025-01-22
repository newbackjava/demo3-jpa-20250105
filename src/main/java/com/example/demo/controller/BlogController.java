package com.example.demo.controller;

import com.example.demo.dto.BlogDto;
import com.example.demo.entity.Blog;
import com.example.demo.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("blog")
public class BlogController {

    private final BlogService blogService;


    @Value("${upload.path}")
    private String uploadPath;


    @GetMapping("blog")
    public String blog() {
        return "blog/blog";
    }

    @PostMapping("blog-result")
    public String blog_result(String content, String contentId, Model model) {
        System.out.println("content: " + content);
        System.out.println("contentId:" + contentId);

        String result = blogService.trans(content);
        System.out.println("result: " + result);
        model.addAttribute("result", result);
        model.addAttribute("contentId", contentId);
        return "blog/blog-result";
    }

    @GetMapping("blog-details")
    public String blog_details() {
        return "blog/blog-details";
    }


    @PostMapping("upload")
    public String createBoard(BlogDto blogDto,
                              @RequestParam("file") MultipartFile file,
                              Model model) {

        System.out.println("------------------ " + blogDto);
        try {
            // 파일 업로드 처리
            if (!file.isEmpty()) {

                String originalFileName = file.getOriginalFilename();
                //이 프로젝트에서 유일한 값을 생성해줌. --> 유일한 파일명을 생성해줌.
                String uuid = UUID.randomUUID().toString();
                String savedFileName = uuid + "_" + originalFileName;
                System.out.println(uploadPath);
                File uploadDir = new File(uploadPath);
                System.out.println(uploadDir.getAbsolutePath());
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                file.transferTo(new File(uploadPath + "/" + savedFileName));
                blogDto.setImg(savedFileName); // DB에 저장할 파일 이름
                System.out.println("------------------ " + blogDto.getImg());
            }

            // 줄바꿈 처리
            blogDto.setContent(blogDto.getContent().replace("\n", "<br>"));

            // 게시글 저장
            Blog blog = blogService.insert(blogDto);
            System.out.println("================================================================");
            System.out.println("result blog: " + blog);
            System.out.println("================================================================");
            model.addAttribute("blog", blog);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "blog/blog-upload";
    } //upload

    @GetMapping("/total")
    @ResponseBody
    public ResponseEntity<Long> getTotalCount() {
        return ResponseEntity.ok(blogService.totalCount());
    }

    @GetMapping("/up3")
    @ResponseBody
    public ResponseEntity<Long> up3() {
        return ResponseEntity.ok(blogService.up3());
    }

    @GetMapping("/happy")
    @ResponseBody
    public ResponseEntity<Integer>happy() {
        return ResponseEntity.ok(blogService.findHappy().size());
    }
}
