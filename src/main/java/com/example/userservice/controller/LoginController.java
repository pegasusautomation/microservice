package com.example.userservice.controller;

import com.example.userservice.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class LoginController {

    private final JwtUtil jwtUtil;

    public LoginController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        if ("admin".equals(username) && "password".equals(password)) { // Replace with real authentication logics
            String token = jwtUtil.generateToken(username);
            return ResponseEntity.ok(Map.of("token", token));
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}
