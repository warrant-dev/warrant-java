package dev.warrant.model.object;

import java.util.Map;

public class Role extends BaseWarrantObject {
    public static final String OBJECT_TYPE = "role";
    static final String NAME_KEY = "name";
    static final String DESCRIPTION_KEY = "description";

    public Role() {
        // For json serialization
        super();
    }

    public Role(String roleId) {
        super(OBJECT_TYPE, roleId);
    }

    public Role(String roleId, String name) {
        super(OBJECT_TYPE, roleId);
        this.meta.put(NAME_KEY, name);
    }

    public Role(String roleId, String name, String description) {
        super(OBJECT_TYPE, roleId);
        this.meta.put(NAME_KEY, name);
        this.meta.put(DESCRIPTION_KEY, description);
    }

    public Role(String roleId, Map<String, Object> meta) {
        super(OBJECT_TYPE, roleId, meta);
    }

    public String getRoleId() {
        return objectId;
    }

    public void setRoleId(String roleId) {
        this.objectId = roleId;
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

    // @Override
    // public Map<String, Object> meta() {
    //     return null;
    // }
}
