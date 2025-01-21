package com.example.demo.api;

import com.example.demo.entity.Movie;

import java.util.List;

public class ApiMovieUse {
    public static void main(String[] args) {
        // REST API 연결한 후 parser 결과를 DB에 넣고 싶음.
        ApiMovieParser parser = new ApiMovieParser();

        List<Movie> list = parser.movie();
        System.out.println("----- 받은 데이터 -----");
        System.out.println(list);
        System.out.println("----- DB에 넣을 예정. JPA 이용 -----");
    }
}
