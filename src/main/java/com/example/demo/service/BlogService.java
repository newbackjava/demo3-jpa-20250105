package com.example.demo.service;

import com.example.demo.naver.APIExamTranslate3EN;
import com.example.demo.naver.APIExamTranslate3KO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlogService {


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
