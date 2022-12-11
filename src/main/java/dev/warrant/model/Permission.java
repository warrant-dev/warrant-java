package dev.warrant.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "createdAt", "updatedAt" })
public class Permission {
    private String permissionId;
    private String name;
    private String description;

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
}
