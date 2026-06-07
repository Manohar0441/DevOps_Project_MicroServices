package com.microos.order.controller;

import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@CrossOrigin(origins = "*")
public class OrderController {

    private final List<Map<String, Object>> orders = new CopyOnWriteArrayList<>();

    @GetMapping("/health")
    public Map<String, String> health() {
        Map<String, String> response = new LinkedHashMap<>();
        response.put("status", "OK");
        return response;
    }

    @GetMapping("/")
    public String root() {
        return "Order Service Running";
    }

    @GetMapping("/api/orders")
    public List<Map<String, Object>> getOrders() {
        return orders;
    }

    @PostMapping("/api/orders")
    public Map<String, Object> createOrder(@RequestBody Map<String, Object> order) {
        orders.add(order);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Order created");
        response.put("order", order);
        return response;
    }
}
