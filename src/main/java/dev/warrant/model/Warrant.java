package dev.warrant.model;

public class Warrant {

    private Integer id;
    private String objectType;
    private String objectId;
    private String relation;
    private Subject subject;

    public Warrant() {
        // For json serialization
    }

    public Warrant(String objectType, String objectId, String relation, Subject subject) {
        this.objectType = objectType;
        this.objectId = objectId;
        this.relation = relation;
        this.subject = subject;
    }

    public Integer getId() {
        return id;
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

    public Subject getSubject() {
        return subject;
    }
}
