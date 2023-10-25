package dev.warrant.model.object;

import java.util.Map;

public class Tenant extends BaseWarrantObject {
    public static final String OBJECT_TYPE = "tenant";
    static final String NAME_KEY = "name";

    public Tenant() {
        // For json serialization
        super();
    }

    public Tenant(String tenantId) {
        super(OBJECT_TYPE, tenantId);
    }

    public Tenant(String tenantId, String name) {
        super(OBJECT_TYPE, tenantId);
        this.meta.put(NAME_KEY, name);
    }

    public Tenant(String tenantId, Map<String, Object> meta) {
        super(OBJECT_TYPE, tenantId, meta);
    }

    public String getTenantId() {
        return objectId;
    }

    public void setTenantId(String tenantId) {
        this.objectId = tenantId;
    }

    public String getName() {
        if (meta != null) {
            if (meta.containsKey(NAME_KEY)) {
                return meta.get(NAME_KEY).toString();
            } else {
                return "";
            }
        } else {
            return "";
        }
    }

    public void setName(String name) {
        meta.put(NAME_KEY, name);
    }

    @Override
    public String id() {
        return objectId;
    }

    @Override
    public String type() {
        return OBJECT_TYPE;
    }

    // @Override
    // public Map<String, Object> meta() {
    //     return null;
    // }
}
