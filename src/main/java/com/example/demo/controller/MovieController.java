package com.example.demo.controller;

import com.example.demo.entity.Movie;
import com.example.demo.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/insert")
    @ResponseBody
    public String insert(){
        System.out.println("insert movie");
        movieService.insert();
        return "ok";
    }

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<List<Movie>> all(){
        System.out.println("all young");
        List<Movie> list = movieService.findAll();
        return ResponseEntity.ok(list);
    }
}