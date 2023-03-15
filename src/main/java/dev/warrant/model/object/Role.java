package dev.warrant.model.object;

public class Role implements WarrantObject {
    private String roleId;
    private String name;
    private String description;

    public Role() {
        // For json serialization
    }

    public Role(String roleId) {
        this.roleId = roleId;
    }

    public Role(String roleId, String name, String description) {
        this.roleId = roleId;
        this.name = name;
        this.description = description;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
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
        return roleId;
    }

    @Override
    public String type() {
        return "role";
    }
}
