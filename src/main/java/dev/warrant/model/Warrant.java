package dev.warrant.model;

public class Warrant {

    private String objectType;
    private String objectId;
    private String relation;
    private WarrantSubject subject;
    private String policy;

    public Warrant() {
        // For json serialization
    }

    public Warrant(String objectType, String objectId, String relation, WarrantSubject subject) {
        this.objectType = objectType;
        this.objectId = objectId;
        this.relation = relation;
        this.subject = subject;
    }

    public Warrant(String objectType, String objectId, String relation, WarrantSubject subject, String policy) {
        this.objectType = objectType;
        this.objectId = objectId;
        this.relation = relation;
        this.subject = subject;
        this.policy = policy;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public WarrantSubject getSubject() {
        return subject;
    }

    public void setSubject(WarrantSubject subject) {
        this.subject = subject;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }
}
