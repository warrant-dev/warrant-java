package dev.warrant.model.object;

import java.util.Map;

public class User implements WarrantObject {
    public static final String OBJECT_TYPE = "user";

    private String userId;
    private String email;

    public User() {
        // For json serialization
    }

    public User(String userId) {
        this.userId = userId;
    }

    public User(String userId, String email) {
        this.userId = userId;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String id() {
        return userId;
    }

    @Override
    public String type() {
        return "user";
    }

    @Override
    public Map<String, Object> meta() {
        return null;
    }
}
