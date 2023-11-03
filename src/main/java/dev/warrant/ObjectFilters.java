package dev.warrant;

import java.util.HashMap;
import java.util.Map;

public class ObjectFilters {

    private String objectType = "";
    private String query = "";

    public ObjectFilters() {
    }

    public ObjectFilters withObjectType(String objectType) {
        this.objectType = objectType;
        return this;
    }

    public ObjectFilters withQuery(String query) {
        this.query = query;
        return this;
    }

    public Map<String, Object> asMap() {
        Map<String, Object> params = new HashMap<>();
        if (!this.objectType.isEmpty()) {
            params.put("objectType", objectType);
        }
        if (!this.query.isEmpty()) {
            params.put("q", query);
        }
        return params;
    }
}
