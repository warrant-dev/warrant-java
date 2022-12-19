package dev.warrant;

import java.util.HashMap;
import java.util.Map;

import dev.warrant.model.WarrantSubject;

public class QueryWarrantsFilters {

    private String objectType;
    private String relation;
    private WarrantSubject subject;

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public void setSubject(WarrantSubject subject) {
        this.subject = subject;
    }

    public Map<String, Object> asMap() {
        Map<String, Object> filters = new HashMap<>();
        if (objectType != null) {
            filters.put("objectType", objectType);
        }
        if (relation != null) {
            filters.put("relation", relation);
        }
        if (subject != null) {
            filters.put("subject", subject.toString());
        }
        return filters;
    }
}
