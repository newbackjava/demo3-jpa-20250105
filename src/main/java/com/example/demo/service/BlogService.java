package com.example.demo.service;

import com.example.demo.dto.BlogDto;
import com.example.demo.entity.Blog;
import com.example.demo.naver.APIExamTranslate3EN;
import com.example.demo.naver.APIExamTranslate3KO;
import com.example.demo.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;

    public Blog insert(BlogDto blogDto) {
        Blog blog = Blog.builder()
                        .img(blogDto.getImg())
                        .mail(blogDto.getMail())
                        .name(blogDto.getName())
                        .content(blogDto.getContent())
                        .build();
        OCRGeneralAPIDemo2 ocr = new OCRGeneralAPIDemo2();
        String result = ocr.oce(blog.getImg());
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("result: " + result);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        blog.setContent2(result);
        return blogRepository.save(blog);
    }

    public String trans (String word) {
        APIExamTranslate3KO ko = new APIExamTranslate3KO();
        APIExamTranslate3EN en = new APIExamTranslate3EN();

        if(word.matches(".*[가-힣]+.*")) {
            return ko.trans(word);
        } else {
            return en.trans(word);
        }
    }
//    public String trans(String word){
//        APIExamTranslate3KO en = new APIExamTranslate3KO();
//        return en.trans(word);
//    }
}
