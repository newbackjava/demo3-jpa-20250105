package com.example.demo.service;

import com.example.demo.api.ApiMovieParser;
import com.example.demo.entity.Movie;
import com.example.demo.entity.Naver;
import com.example.demo.repository.MovieRepository;
import com.example.demo.repository.NaverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NaverService {

    private final NaverRepository naverRepository;

    public void insert(Naver naver) {
      naverRepository.save(naver);
    }
}

