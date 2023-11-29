package com.example.douban.controller;

import com.example.douban.pojo.Account;
import com.example.douban.pojo.Movie;
import com.example.douban.service.ChartService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping(value = "/chart")
public class ChartController {
    @Resource
    ChartService chartService;

    @PostMapping("/chart1")
    public ResponseEntity<ArrayList<Movie>> handleChart1(@RequestBody Map<String, String> data) {
        try {
            ArrayList<String> result = chartService.getMovieYears();
            ArrayList<Movie> movies = new ArrayList<>();
            for (String res : result) {
                movies.add(chartService.getMostPopularMovie(res));
            }
            return ResponseEntity.ok(movies);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(null);
    }
}
