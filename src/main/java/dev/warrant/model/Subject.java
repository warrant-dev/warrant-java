package dev.warrant.model;

public class Subject {
    private String objectType;
    private String objectId;

    public Subject() {
        // For json serialization
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
}
