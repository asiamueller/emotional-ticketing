package com.example.ticketingsystem.model;

/**
 * Model class for tickets submitted in the system
 */
public class Ticket {
    private Long id;
    private String content;
    private boolean anonymous;
    private String userId; // Will be null for anonymous tickets
    private String status; // e.g., "pending", "in_progress", "resolved"
    private long timestamp;

    public Ticket() {
        this.timestamp = System.currentTimeMillis();
        this.status = "pending";
    }

    public Ticket(String content, boolean anonymous, String userId) {
        this();
        this.content = content;
        this.anonymous = anonymous;
        this.userId = anonymous ? null : userId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isAnonymous() {
        return anonymous;
    }

    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}