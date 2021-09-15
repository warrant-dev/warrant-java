package dev.warrant.model;

public class User {
    private String userId;

    public User() {
        // For json serialization
    }

    public User(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
