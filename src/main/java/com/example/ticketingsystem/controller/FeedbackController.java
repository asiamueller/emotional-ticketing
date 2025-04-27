package com.example.ticketingsystem.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {
    
    @PostMapping("/submit")
    public ResponseEntity<Map<String, String>> submitFeedback(@RequestBody Map<String, Object> request) {
        try {
            // Log the incoming request
            System.out.println("Received feedback: " + request);
            
            // Extract fields from the JSON request
            String content = (String) request.get("content");
            int rating = request.containsKey("rating") ? ((Number) request.get("rating")).intValue() : 0;
            boolean anonymous = request.containsKey("anonymous") ? (boolean) request.get("anonymous") : false;
            String userId = request.containsKey("userId") ? (String) request.get("userId") : null;
            
            // Validate content and rating
            if (content == null || content.trim().isEmpty()) {
                Map<String, String> response = new HashMap<>();
                response.put("status", "error");
                response.put("message", "Feedback content cannot be empty");
                return ResponseEntity.badRequest().body(response);
            }
            
            if (rating < 1 || rating > 5) {
                Map<String, String> response = new HashMap<>();
                response.put("status", "error");
                response.put("message", "Rating must be between 1 and 5");
                return ResponseEntity.badRequest().body(response);
            }
            
            // For now, just simulate success (you would save to database here)
            System.out.println("Feedback submitted: " + content + " with rating " + rating);
            
            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Feedback submitted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            
            Map<String, String> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", "Error processing feedback: " + e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }
}