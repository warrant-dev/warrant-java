package dev.warrant.model.object;

public class User implements WarrantObject {
    private String email;
    private String userId;

    public User() {
        // For json serialization
    }

    public User(String userId) {
        this.email = "";
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
}
