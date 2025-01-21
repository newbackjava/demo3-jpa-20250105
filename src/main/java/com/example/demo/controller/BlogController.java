package com.example.demo.controller;

import com.example.demo.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("blog")
public class BlogController {

    private final BlogService blogService;

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

}
