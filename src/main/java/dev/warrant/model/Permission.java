package dev.warrant.model;

public class Permission {
    private String permissionId;

    public Permission() {
        // For json serialization
    }

    public Permission(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }
}
