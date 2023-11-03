package dev.warrant.model.object;

import java.util.Map;

public class Permission extends BaseWarrantObject {
    public static final String OBJECT_TYPE = "permission";
    static final String NAME_KEY = "name";
    static final String DESCRIPTION_KEY = "description";

    public Permission() {
        // For json serialization
        super();
    }

    public Permission(String permissionId) {
        super(OBJECT_TYPE, permissionId);
    }

    public Permission(String permissionId, String name, String description) {
        super(OBJECT_TYPE, permissionId);
        this.meta.put(NAME_KEY, name);
        this.meta.put(DESCRIPTION_KEY, description);
    }

    public Permission(String permissionId, Map<String, Object> meta) {
        super(OBJECT_TYPE, permissionId, meta);
    }

    public String getPermissionId() {
        return objectId;
    }

    public void setPermissionId(String permissionId) {
        this.objectId = permissionId;
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

    public String getDescription() {
        if (meta != null) {
            if (meta.containsKey(DESCRIPTION_KEY)) {
                return meta.get(DESCRIPTION_KEY).toString();
            } else {
                return "";
            }
        } else {
            return "";
        }
    }

    public void setDescription(String description) {
        meta.put(DESCRIPTION_KEY, description);
    }

    @Override
    public String id() {
        return objectId;
    }

    @Override
    public String type() {
        return OBJECT_TYPE;
    }
}
