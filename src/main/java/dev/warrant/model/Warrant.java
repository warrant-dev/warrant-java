package dev.warrant.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "createdAt", "updatedAt" })
public class Warrant {

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
