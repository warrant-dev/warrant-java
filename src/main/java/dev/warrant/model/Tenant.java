package dev.warrant.model;

public class Tenant implements WarrantObject {
    private String tenantId;
    private String name;

    public Tenant() {
        // For json serialization
    }

    public Tenant(String tenantId) {
        this.tenantId = tenantId;
    }

    public Tenant(String tenantId, String name) {
        this.tenantId = tenantId;
        this.name = name;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String id() {
        return tenantId;
    }

    
}
