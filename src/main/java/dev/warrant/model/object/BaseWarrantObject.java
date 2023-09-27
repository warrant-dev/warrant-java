package dev.warrant.model.object;

import java.util.Map;

public class BaseWarrantObject implements WarrantObject {
    private String objectType;
    private String objectId;
    private Map<String, Object> meta;

    public BaseWarrantObject() {
        // For json serialization
    }

    public BaseWarrantObject(String objectType, String objectId, Map<String, Object> meta) {
        this.objectType = objectType;
        this.objectId = objectId;
        this.meta = meta;
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

    public Map<String, Object> getMeta() {
        return meta;
    }

    public void setMeta(Map<String, Object> meta) {
        this.meta = meta;
    }

    @Override
    public String id() {
        return objectId;
    }

    @Override
    public String type() {
        return objectType;
    }

    @Override
    public Map<String, Object> meta() {
        return meta;
    }
}
