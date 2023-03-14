package dev.warrant.model;

public class UserSessionSpec {

    private String type;
    private String userId;
    private String tenantId;
    private String selfServiceStrategy;

    public static UserSessionSpec newAuthorizationSessionSpec(String userId) {
        return new UserSessionSpec("sess", userId);
    }

    public static UserSessionSpec newSelfServiceDashboardSessionSpec(String userId, String tenantId, String selfServiceStrategy) {
        return new UserSessionSpec("ssdash", userId, tenantId, selfServiceStrategy);
    }

    private UserSessionSpec(String type, String userId) {
        this.type = type;
        this.userId = userId;
    }

    private UserSessionSpec(String type, String userId, String tenantId, String selfServiceStrategy) {
        this.type = type;
        this.userId = userId;
        this.tenantId = tenantId;
        this.selfServiceStrategy = selfServiceStrategy;
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

    public String getSelfServiceStrategy() {
        return selfServiceStrategy;
    }
}
