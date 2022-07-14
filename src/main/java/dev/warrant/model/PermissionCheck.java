package dev.warrant.model;

public class PermissionCheck {
    private String permissionId;
    private String userId;

    public PermissionCheck() {
        // For json serialization
    }

    public PermissionCheck(String permissionId, String userId) {
        this.permissionId = permissionId;
        this.userId = userId;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
