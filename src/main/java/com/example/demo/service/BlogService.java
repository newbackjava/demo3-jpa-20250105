package com.example.demo.service;

import com.example.demo.dto.BlogDto;
import com.example.demo.entity.Blog;
import com.example.demo.naver.APIExamTranslate3EN;
import com.example.demo.naver.APIExamTranslate3KO;
import com.example.demo.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;


    /**
     * Blog를 저장하는 메서드
     */
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

    /**
     * 입력된 문자열을 번역하는 메서드
     * 한국어가 포함되어 있으면 KO 번역기 사용, 그렇지 않으면 EN 번역기 사용
     */
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

    /**
     * 전체 Blog의 수를 반환
     */
    public long totalCount() {
        return blogRepository.totalCount();
    }

    /**
     * blogId가 3 이상인 Blog의 수를 반환
     */
    public long up3() {
        return blogRepository.up3();
    }

    /**
     * '행복'이라는 단어가 포함된 Blog를 반환
     */
    public List<Blog> findHappy() {
        return blogRepository.findHappy();
    }
}
