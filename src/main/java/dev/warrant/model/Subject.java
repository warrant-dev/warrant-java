package dev.warrant.model;

public class Subject {
    private String objectType;
    private String objectId;
    private String relation;

    public Subject() {
        // For json serialization
    }

    public Subject(String objectType, String objectId, String relation) {
        this.objectType = objectType;
        this.objectId = objectId;
        this.relation = relation;
    }

    public Subject(String objectType, String objectId) {
        this.objectType = objectType;
        this.objectId = objectId;
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

    @Override
    public String toString() {
        return this.objectType + ":" + this.objectId;
    }
}
