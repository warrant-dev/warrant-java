package dev.warrant.model;

public class UserWarrant extends Warrant {
    private User user;

    public UserWarrant(String objectType, String objectId, String relation, String userId) {
        super(objectType, objectId, relation);
        this.user = new User(userId); 
    }

    public User getUser() {
        return user;
    }
}
