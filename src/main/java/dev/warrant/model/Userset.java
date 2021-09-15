package dev.warrant.model;

public class Userset {
    
    private String objectType;
    private String objectId;
    private String relation;

    public Userset(String objectType, String objectId, String relation) {
        this.objectType = objectType;
        this.objectId = objectId;
        this.relation = relation;
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
    
}
