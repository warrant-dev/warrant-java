package dev.warrant.model;

import java.util.Map;

public class WarrantSpec {

    private String objectType;
    private String objectId;
    private String relation;
    private WarrantSubject subject;
    private Map<String, Object> context;

    public WarrantSpec() {
        // For json serialization
    }

    public WarrantSpec(String objectType, String objectId, String relation, WarrantSubject subject) {
        this.objectType = objectType;
        this.objectId = objectId;
        this.relation = relation;
        this.subject = subject;
    }

    public WarrantSpec(String objectType, String objectId, String relation, WarrantSubject subject, Map<String, Object> context) {
        this.objectType = objectType;
        this.objectId = objectId;
        this.relation = relation;
        this.subject = subject;
        this.context = context;
    }

    public String getObjectType() {
        return objectType;
    }

    public String getObjectId() {
        return objectId;
    }

    public String getRelation() {
        return relation;
    }

    public WarrantSubject getSubject() {
        return subject;
    }

    public Map<String, Object> getContext() {
        return context;
    }
}
