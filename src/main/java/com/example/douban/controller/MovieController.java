package com.example.douban.controller;

import com.example.douban.pojo.Movie;
import com.example.douban.service.MovieService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping(value = "/movie")
public class MovieController {
    @Resource
    MovieService movieService;

    @PostMapping("/recommend")
    public ResponseEntity<ArrayList<Movie>> handleOpenPage(@RequestBody Map<String, String> userData) {
        try {
            ArrayList<Movie> movies = movieService.findAllMovies();
            return ResponseEntity.ok(movies);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(null);
    }

    @PostMapping("/detail")
    public ResponseEntity<Movie> handleDetailPage(@RequestBody Map<String, String> movieData) {
        try {
            Movie movie = movieService.findMovieById(movieData.get("movie"));
            return ResponseEntity.ok(movie);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(null);
    }

    @PostMapping("/search")
    public ResponseEntity<ArrayList<Movie>> handleSearchPage(@RequestBody Map<String, String> movieData) {
        try {
            ArrayList<Movie> movies = movieService.findMovieByKeyWords(movieData.get("content"));
            return ResponseEntity.ok(movies);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(null);
    }

    @PostMapping("/classify")
    public ResponseEntity<ArrayList<Movie>> handleClassifyPage(@RequestBody Map<String, String> movieData) {
        try {
            System.out.println(movieData);
            ArrayList<Movie> movies = movieService.findMovieByTag(movieData.get("tag1"), movieData.get("tag2"), movieData.get("tag3"));
            return ResponseEntity.ok(movies);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(null);
    }
}
