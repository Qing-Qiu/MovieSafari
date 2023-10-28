package com.example.douban.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping(value = "/api")
public class SearchController {
    @PostMapping("/hi")
    public ResponseEntity<String> handleFormSubmission(@RequestBody Map<String, String> formData) {
        try {
            String content = formData.get("content");
            System.out.println(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("成功接收并处理数据");
    }
}
