package dev.warrant.model;

public abstract class Warrant {

    private String objectType;
    private String objectId;
    private String relation;

    public static Warrant newUserWarrant(String objectType, String objectId, String relation, String userId) {
        return new UserWarrant(objectType, objectId, relation, userId);
    }

    public static Warrant newUsersetWarrant(String objectType, String objectId, String relation, Userset user) {
        return new UsersetWarrant(objectType, objectId, relation, user);
    }

    public Warrant(String objectType, String objectId, String relation) {
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
