package com.example.demo.service;

import com.example.demo.api.ApiMovieParser;
import com.example.demo.entity.Movie;
import com.example.demo.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public void insert() {
        ApiMovieParser parser = new ApiMovieParser();
        List<Movie> list = parser.movie();

        int index = 1;
        for (Movie movie : list) {
            movieRepository.save(movie);
            System.out.println(index + ">> ");
            index++;
        }
    }

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }
}