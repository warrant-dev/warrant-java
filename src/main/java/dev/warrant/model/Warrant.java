package dev.warrant.model;

public class Warrant {

    private String objectType;
    private String objectId;
    private String relation;
    private WarrantSubject subject;
    private boolean isDirectMatch;

    public Warrant() {
        // For json serialization
    }

    public Warrant(String objectType, String objectId, String relation, WarrantSubject subject) {
        this.objectType = objectType;
        this.objectId = objectId;
        this.relation = relation;
        this.subject = subject;
    }

    public Warrant(String objectType, String objectId, String relation, WarrantSubject subject, boolean isDirectMatch) {
        this.objectType = objectType;
        this.objectId = objectId;
        this.relation = relation;
        this.subject = subject;
        this.isDirectMatch = isDirectMatch;
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

    public boolean getIsDirectMatch() {
        return isDirectMatch;
    }
}
