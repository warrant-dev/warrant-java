package dev.warrant.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "createdAt", "updatedAt" })
public class UsersetWarrant extends Warrant {

    private Userset user;

    public UsersetWarrant() {
        // For json serialization
    }

    public UsersetWarrant(String objectType, String objectId, String relation, Userset user) {
        super(objectType, objectId, relation);
        this.user = user;
    }

    public Userset getUser() {
        return user;
    }
}
