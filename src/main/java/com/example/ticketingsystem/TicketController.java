package com.example.ticketingsystem;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Map;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    
    // POST request to submit a ticket
    @PostMapping("/submit")
    public ResponseEntity<Map<String, String>> submitTicket(@RequestBody Map<String, Object> request) {
        try {
            // Log the incoming request
            System.out.println("Received ticket request: " + request);
            
            // Extract fields from the JSON request
            String content = (String) request.get("content");
            boolean anonymous = request.containsKey("anonymous") ? (boolean) request.get("anonymous") : false;
            String userId = request.containsKey("userId") ? (String) request.get("userId") : null;
            
            // Validate content
            if (content == null || content.trim().isEmpty()) {
                Map<String, String> response = Map.of("status", "error", "message", "Content cannot be empty");
                return ResponseEntity.badRequest().body(response);
            }
            
            // Simulate saving to database
            boolean isSaved = saveTicket(content, anonymous, userId);
            
            if (!isSaved) {
                Map<String, String> response = Map.of("status", "error", "message", "Server error while saving ticket");
                return ResponseEntity.internalServerError().body(response);
            }
            
            // Return success response
            Map<String, String> response = Map.of("status", "success", "message", "Ticket submitted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, String> response = Map.of("status", "error", "message", "Error: " + e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }
    
    // Simulate saving the ticket request
    private boolean saveTicket(String content, boolean anonymous, String userId) {
        try {
            // For now, just log the data (you'll implement database saving later)
            System.out.println("Saving ticket: Content=" + content 
                + ", Anonymous=" + anonymous 
                + ", UserId=" + (anonymous ? "null" : userId));
            
            // Simulate successful save
            return true;
        } catch (Exception e) {
            System.err.println("Error saving ticket: " + e.getMessage());
            return false;
        }
    }
}