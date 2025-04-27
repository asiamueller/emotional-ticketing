package com.example.ticketingsystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    // Simple in-memory storage for demo purposes
    private static final Map<String, Map<String, Object>> users = new HashMap<>();
    
    static {
        // Add a default admin user
        String adminId = UUID.randomUUID().toString();
        Map<String, Object> adminUser = new HashMap<>();
        adminUser.put("id", adminId);
        adminUser.put("username", "admin");
        adminUser.put("password", "password"); // In a real app, hash this!
        adminUser.put("email", "admin@example.com");
        adminUser.put("role", "admin");
        users.put(adminId, adminUser);
    }
    
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerUser(@RequestBody Map<String, Object> request) {
        try {
            System.out.println("Register request: " + request);
            
            String username = (String) request.get("username");
            String email = (String) request.get("email");
            String password = (String) request.get("password");
            String role = (String) request.get("role");
            
            // Validation
            if (username == null || username.trim().isEmpty() ||
                email == null || email.trim().isEmpty() ||
                password == null || password.trim().isEmpty()) {
                
                Map<String, Object> response = new HashMap<>();
                response.put("status", "error");
                response.put("message", "Username, email, and password are required");
                return ResponseEntity.badRequest().body(response);
            }
            
            // Check if username already exists
            boolean usernameExists = users.values().stream()
                .anyMatch(u -> username.equals(u.get("username")));
            
            if (usernameExists) {
                Map<String, Object> response = new HashMap<>();
                response.put("status", "error");
                response.put("message", "Username already exists");
                return ResponseEntity.badRequest().body(response);
            }
            
            // Create user
            String userId = UUID.randomUUID().toString();
            Map<String, Object> user = new HashMap<>();
            user.put("id", userId);
            user.put("username", username);
            user.put("email", email);
            user.put("password", password); // In a real app, hash this!
            user.put("role", role != null ? role : "student");
            
            users.put(userId, user);
            
            System.out.println("User registered: " + username);
            
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("id", userId);
            response.put("username", username);
            response.put("role", user.get("role"));
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            
            Map<String, Object> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", "Error during registration: " + e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody Map<String, Object> request) {
        try {
            System.out.println("Login request: " + request);
            
            String username = (String) request.get("username");
            String password = (String) request.get("password");
            
            // Validation
            if (username == null || username.trim().isEmpty() ||
                password == null || password.trim().isEmpty()) {
                
                Map<String, Object> response = new HashMap<>();
                response.put("status", "error");
                response.put("message", "Username and password are required");
                return ResponseEntity.badRequest().body(response);
            }
            
            // Find user by username
            Map<String, Object> user = null;
            for (Map<String, Object> u : users.values()) {
                if (username.equals(u.get("username"))) {
                    user = u;
                    break;
                }
            }
            
            if (user == null || !password.equals(user.get("password"))) {
                Map<String, Object> response = new HashMap<>();
                response.put("status", "error");
                response.put("message", "Invalid credentials");
                return ResponseEntity.status(401).body(response);
            }
            
            System.out.println("User logged in: " + username);
            
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("userId", user.get("id"));
            response.put("username", user.get("username"));
            response.put("role", user.get("role"));
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            
            Map<String, Object> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", "Error during login: " + e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }
}