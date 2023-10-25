package dev.warrant.model.object;

import java.util.Map;

public class User extends BaseWarrantObject {
    public static final String OBJECT_TYPE = "user";
    static final String EMAIL_KEY = "email";

    public User() {
        // For json serialization
        super();
    }

    public User(String userId) {
        super(OBJECT_TYPE, userId);
    }

    public User(String userId, String email) {
        super(OBJECT_TYPE, userId);
        this.meta.put(EMAIL_KEY, email);
    }

    public User(String userId, Map<String, Object> meta) {
        super(OBJECT_TYPE, userId, meta);
    }

    public String getUserId() {
        return objectId;
    }

    public void setUserId(String userId) {
        this.objectId = userId;
    }

    public String getEmail() {
        if (meta != null) {
            if (meta.containsKey(EMAIL_KEY)) {
                return meta.get(EMAIL_KEY).toString();
            } else {
                return "";
            }
        } else {
            return "";
        }
    }

    public void setEmail(String email) {
        meta.put(EMAIL_KEY, email);
    }

    @Override
    public String id() {
        return objectId;
    }

    @Override
    public String type() {
        return OBJECT_TYPE;
    }

    // @Override
    // public Map<String, Object> meta() {
    //     return null;
    // }
}
