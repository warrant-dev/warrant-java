package dev.warrant.model;

public class UserSession {
    private String userId;
    private String token;

    public UserSession() {
        // For json serialization
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
