package com.example.ticketingsystem.model;

/**
 * Model class for user feedback
 */
public class Feedback {
    private Long id;
    private String content;
    private int rating; // 1-5 star rating
    private String userId; // Will be null for anonymous feedback
    private boolean anonymous;
    private long timestamp;

    public Feedback() {
        this.timestamp = System.currentTimeMillis();
    }

    public Feedback(String content, int rating, boolean anonymous, String userId) {
        this();
        this.content = content;
        this.rating = rating;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isAnonymous() {
        return anonymous;
    }

    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}