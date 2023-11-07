package com.example.douban.service;

import com.example.douban.mapper.MovieMapper;
import com.example.douban.pojo.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MovieService {
    private final MovieMapper movieMapper;

    @Autowired
    public MovieService(MovieMapper movieMapper) {
        this.movieMapper = movieMapper;
    }

    public Movie findMovieById(String id) {
        return movieMapper.findMovieById(id);
    }

    public ArrayList<Movie> findMovieByTag(String tag) {
        return movieMapper.findMovieByTag(tag);
    }

    public ArrayList<Movie> findMovieByKeyWords(String keyword) {
        return movieMapper.findMovieByKeywords(keyword);
    }
}