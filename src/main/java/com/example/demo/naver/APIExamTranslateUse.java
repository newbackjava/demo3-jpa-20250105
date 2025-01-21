package com.example.demo.naver;

public class APIExamTranslateUse {
    public static void main(String[] args) {
        APIExamTranslate3KO naver = new APIExamTranslate3KO();
        String ko = "와~~ 수요일이야!! 신나!";
        String en = naver.trans(ko);
        System.out.println("번역 결과>> " + en);
    }
}
