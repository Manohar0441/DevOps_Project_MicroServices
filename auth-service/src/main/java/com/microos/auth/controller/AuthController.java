package com.microos.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {

    @GetMapping("/health")
    public Map<String, String> health() {
        Map<String, String> response = new LinkedHashMap<>();
        response.put("status", "OK");
        return response;
    }

    @GetMapping("/")
    public String root() {
        return "Auth Service Running";
    }

    @PostMapping("/api/auth/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");

        if ("admin".equals(username) && "1234".equals(password)) {
            Map<String, String> success = new LinkedHashMap<>();
            success.put("message", "Login Successful");
            success.put("token", "abc123");
            return ResponseEntity.ok(success);
        }

        Map<String, String> failure = new LinkedHashMap<>();
        failure.put("message", "Invalid Credentials");
        return ResponseEntity.status(401).body(failure);
    }
}
