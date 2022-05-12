package dev.warrant.model;

public class Role {
    private String roleId;

    public Role() {
        // For json serialization
    }

    public Role(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
