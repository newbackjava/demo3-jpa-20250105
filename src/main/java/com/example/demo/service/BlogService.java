package com.example.demo.service;

import com.example.demo.naver.APIExamTranslate3EN;
import com.example.demo.naver.APIExamTranslate3KO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlogService {

    public String trans(String word){
        APIExamTranslate3KO en = new APIExamTranslate3KO();
        return en.trans(word);
    }
}
