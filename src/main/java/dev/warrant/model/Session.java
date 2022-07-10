package dev.warrant.model;

public class Session {

    private String type;
    private String userId;
    private String tenantId;

    public static Session newAuthorizationSession(String userId) {
        return new Session("sess", userId);
    }

    public static Session newSelfServiceSession(String userId, String tenantId) {
        return new Session("ssdash", userId, tenantId);
    }

    private Session(String type, String userId) {
        this.type = type;
        this.userId = userId;
    }

    private Session(String type, String userId, String tenantId) {
        this.type = type;
        this.userId = userId;
        this.tenantId = tenantId;
    }

    public String getType() {
        return type;
    }

    public String getUserId() {
        return userId;
    }

    public String getTenantId() {
        return tenantId;
    }
}
