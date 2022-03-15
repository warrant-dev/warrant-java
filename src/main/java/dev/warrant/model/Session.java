package dev.warrant.model;

public class Session {

    private String type;
    private String userId;
    private String tenantId;
    private String redirectUrl;

    public static Session newSelfServiceSession(String userId, String redirectUrl) {
        return new Session("ssdash", userId, redirectUrl);
    }

    public static Session newSelfServiceSession(String userId, String tenantId, String redirectUrl) {
        return new Session("ssdash", userId, tenantId, redirectUrl);
    }

    public Session(String type, String userId, String redirectUrl) {
        this.type = type;
        this.userId = userId;
        this.redirectUrl = redirectUrl;
    }

    public Session(String type, String userId, String tenantId, String redirectUrl) {
        this.type = type;
        this.userId = userId;
        this.tenantId = tenantId;
        this.redirectUrl = redirectUrl;
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

    public String getRedirectUrl() {
        return redirectUrl;
    }

}
