package dev.warrant.model.object;

import java.util.Map;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BaseWarrantObject implements WarrantObject {
    protected String objectType;
    protected String objectId;
    protected Map<String, Object> meta;

    public BaseWarrantObject() {
        // For json serialization
    }

    public BaseWarrantObject(String objectType) {
        this.objectType = objectType;
        this.meta = new HashMap<String, Object>();
    }

    public BaseWarrantObject(String objectType, String objectId) {
        this.objectType = objectType;
        this.objectId = objectId;
        this.meta = new HashMap<String, Object>();
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

    public String id() {
        return objectId;
    }

    public String type() {
        return objectType;
    }

    public Map<String, Object> meta() {
        return meta;
    }
}
