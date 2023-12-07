package com.example.douban.controller;

import com.example.douban.pojo.Account;
import com.example.douban.pojo.Movie;
import com.example.douban.service.ChartService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

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
            for (String year : result) {
                movies.add(chartService.getMostPopularMovie(year));
            }
            return ResponseEntity.ok(movies);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(null);
    }

    @PostMapping("/chart2")
    public ResponseEntity<ArrayList<Movie>> handleChart2(@RequestBody Map<String, String> data) {
        try {
            ArrayList<String> result = chartService.getMovieYears();
            ArrayList<Movie> res = new ArrayList<>();
            for (String year : result) {
                Movie movie = chartService.getMovieCountByTag(year, data.get("tag"));
                if (movie.getGenre().equals("0")) {
                    movie.setYear(year);
                }
                res.add(movie);
            }
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(null);
    }

    @PostMapping("/year")
    public ResponseEntity<ArrayList<String>> handleYear(@RequestBody Map<String, String> data) {
        try {
            ArrayList<String> result = chartService.getMovieYears();
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(null);
    }

    @PostMapping("/chart3")
    public ResponseEntity<ArrayList<Movie>> handleChart3(@RequestBody Map<String, String> data) {
        try {
            String[] types = new String[]{"动作", "动画", "喜剧", "犯罪"
                    , "科幻", "历史", "音乐", "爱情", "悬疑", "惊悚"};
            ArrayList<Movie> res = new ArrayList<>();
            for (String type : types) {
                Movie movie = chartService.getMovieCountByTag(data.get("year"), type);
//                if (movie.getGenre().equals("0")) {
//                    movie.setYear(year);
//                }
                res.add(movie);
            }
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(null);
    }
}
