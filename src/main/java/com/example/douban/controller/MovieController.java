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
            ArrayList<Movie> movies = movieService.findMovieByKeyWords(movieData.get("keyword"), movieData.get("limit"), movieData.get("offset"));
            return ResponseEntity.ok(movies);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(null);
    }

    @PostMapping("/count2")
    public ResponseEntity<Integer> handleCount2Page(@RequestBody Map<String, String> movieData) {
        try {
            Integer cnt = movieService.countMovieByKeywords(movieData.get("keyword"));
            return ResponseEntity.ok(cnt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(null);
    }

    @PostMapping("/classify")
    public ResponseEntity<ArrayList<Movie>> handleClassifyPage(@RequestBody Map<String, String> movieData) {
        try {
            ArrayList<Movie> movies = movieService.findMovieByTag(
                    movieData.get("tag1"), movieData.get("tag2"), movieData.get("tag3"), movieData.get("limit"), movieData.get("offset"));
            return ResponseEntity.ok(movies);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(null);
    }

    @PostMapping("/count")
    public ResponseEntity<Integer> handleCountPage(@RequestBody Map<String, String> movieData) {
        try {
            Integer cnt = movieService.countMovieByTag(
                    movieData.get("tag1"), movieData.get("tag2"), movieData.get("tag3"));
            return ResponseEntity.ok(cnt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(null);
    }
}
