package dev.warrant.model;

public class UsersetWarrant extends Warrant {

    private Userset user;

    public UsersetWarrant(String objectType, String objectId, String relation, Userset user) {
        super(objectType, objectId, relation);
        this.user = user;
    }

    public Userset getUser() {
        return user;
    }
}
