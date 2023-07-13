package dev.warrant.model;

public class Warrant {

    private String objectType;
    private String objectId;
    private String relation;
    private WarrantSubject subject;
    private String policy;
    private boolean isImplicit;

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

    public Warrant(String objectType, String objectId, String relation, WarrantSubject subject, boolean isImplicit) {
        this.objectType = objectType;
        this.objectId = objectId;
        this.relation = relation;
        this.subject = subject;
        this.isImplicit = isImplicit;
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

    public String getPolicy() {
        return policy;
    }

    public boolean getIsImplicit() {
        return isImplicit;
    }
}
