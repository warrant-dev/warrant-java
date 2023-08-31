package dev.warrant;

import java.util.HashMap;
import java.util.Map;

public class WarrantFilters {

    private String objectType = "";
    private String objectId = "";
    private String relation = "";
    private String subjectType = "";
    private String subjectId = "";
    private String subjectRelation = "";

    public WarrantFilters() {
    }

    public WarrantFilters withObjectType(String objectType) {
        this.objectType = objectType;
        return this;
    }

    public WarrantFilters withObjectId(String objectId) {
        this.objectId = objectId;
        return this;
    }

    public WarrantFilters withRelation(String relation) {
        this.relation = relation;
        return this;
    }

    public WarrantFilters withSubjectType(String subjectType) {
        this.subjectType = subjectType;
        return this;
    }

    public WarrantFilters withSubjectId(String subjectId) {
        this.subjectId = subjectId;
        return this;
    }

    public WarrantFilters withSubjectRelation(String subjectRelation) {
        this.subjectRelation = subjectRelation;
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
        if (!this.relation.isEmpty()) {
            params.put("relation", relation);
        }
        if (!this.subjectType.isEmpty()) {
            params.put("subjectType", subjectType);
        }
        if (!this.subjectId.isEmpty()) {
            params.put("subjectId", subjectId);
        }
        if (!this.subjectRelation.isEmpty()) {
            params.put("subjectRelation", subjectRelation);
        }
        return params;
    }
}
