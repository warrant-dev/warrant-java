package dev.warrant.model;

public class UserSessionSpec {

    private String type;
    private String userId;
    private String tenantId;

    public static UserSessionSpec newAuthorizationSessionSpec(String userId) {
        return new UserSessionSpec("sess", userId);
    }

    public static UserSessionSpec newSelfServiceDashboardSessionSpec(String userId, String tenantId) {
        return new UserSessionSpec("ssdash", userId, tenantId);
    }

    private UserSessionSpec(String type, String userId) {
        this.type = type;
        this.userId = userId;
    }

    private UserSessionSpec(String type, String userId, String tenantId) {
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
