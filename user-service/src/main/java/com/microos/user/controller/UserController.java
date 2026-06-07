package com.microos.user.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    private final List<Map<String, Object>> users = new CopyOnWriteArrayList<>();

    public UserController() {
        Map<String, Object> seed = new LinkedHashMap<>();
        seed.put("id", 1);
        seed.put("name", "Manohar");
        users.add(seed);
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        Map<String, String> response = new LinkedHashMap<>();
        response.put("status", "OK");
        return response;
    }

    @GetMapping("/")
    public String root() {
        return "User Service Running";
    }

    @GetMapping("/api/users")
    public List<Map<String, Object>> getUsers() {
        return users;
    }

    @PostMapping("/api/users")
    public Map<String, Object> addUser(@RequestBody Map<String, Object> user) {
        users.add(user);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "User added");
        response.put("user", user);
        return response;
    }
}
