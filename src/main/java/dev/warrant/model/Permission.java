package dev.warrant.model;

public class Permission implements WarrantObject {
    private String permissionId;
    private String name;
    private String description;

    public Permission() {
        // For json serialization
    }

    public Permission(String permissionId, String name, String description) {
        this.permissionId = permissionId;
        this.name = name;
        this.description = description;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String id() {
        return permissionId;
    }
}
