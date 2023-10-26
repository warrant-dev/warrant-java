package dev.warrant.model.object;

import java.util.Map;

public class Feature extends BaseWarrantObject {
    public static final String OBJECT_TYPE = "feature";

    public Feature() {
        // For json serialization
        super();
    }

    public Feature(String featureId) {
        super(OBJECT_TYPE, featureId);
    }

    public Feature(String featureId, Map<String, Object> meta) {
        super(OBJECT_TYPE, featureId, meta);
    }

    public String getFeatureId() {
        return objectId;
    }

    public void setFeatureId(String featureId) {
        this.objectId = featureId;
    }

    @Override
    public String id() {
        return objectId;
    }

    @Override
    public String type() {
        return OBJECT_TYPE;
    }
}
