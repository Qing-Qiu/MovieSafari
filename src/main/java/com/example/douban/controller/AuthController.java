package com.example.douban.controller;

import com.example.douban.pojo.Account;
import com.example.douban.service.AccountService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping(value = "/auth")
public class AuthController {
    @Resource
    AccountService accountService;

    @PostMapping("/login")
    public ResponseEntity<String> handleLoginFormSubmission(@RequestBody Map<String, String> formData) {
        try {
            String username = formData.get("username");
            String password = formData.get("password");
            Account account = accountService.findAccountByUsername(username);
            if (account == null) return ResponseEntity.ok("failed");
            if (Objects.equals(account.getPassword(), password)) {
                return ResponseEntity.ok("success");
            } else return ResponseEntity.ok("failed");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("failed");
    }

    @PostMapping("/register")
    public ResponseEntity<String> handleRegisterFormSubmission(@RequestBody Map<String, String> formData) {
        try {
            String username = formData.get("username");
            String password = formData.get("password");
            Account account = accountService.findAccountByUsername(username);
            if (account != null) return ResponseEntity.ok("existed");
            Account account1 = new Account(username, password);
            int cnt = accountService.insertAccount(account1);
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("failed");
    }
}
