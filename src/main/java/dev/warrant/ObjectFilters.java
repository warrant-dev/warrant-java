package dev.warrant;

import java.util.HashMap;
import java.util.Map;

public class ObjectFilters {

    private String objectType = "";
    private String objectId = "";

    public ObjectFilters() {
    }

    public ObjectFilters withObjectType(String objectType) {
        this.objectType = objectType;
        return this;
    }

    public ObjectFilters withObjectId(String objectId) {
        this.objectId = objectId;
        return this;
    }

    public Map<String, Object> asMap() {
        Map<String, Object> params = new HashMap<>();
        if (!this.objectType.isEmpty()) {
            params.put("objectType", objectType);
        }
        if (!this.objectId.isEmpty()) {
            params.put("objectId", objectId);
        }
        return params;
    }
}
