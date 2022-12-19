package dev.warrant.model;

public class WarrantSubject {
    private String objectType;
    private String objectId;
    private String relation;

    public WarrantSubject() {
        // For json serialization
    }

    public WarrantSubject(String objectType, String objectId, String relation) {
        this.objectType = objectType;
        this.objectId = objectId;
        this.relation = relation;
    }

    public WarrantSubject(String objectType, String objectId) {
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
        StringBuilder s = new StringBuilder();
        s.append(objectType).append(':').append(objectId);
        if (relation != null) {
            s.append('#').append(relation);
        }
        return s.toString();
    }
}
