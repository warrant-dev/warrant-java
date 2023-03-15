package dev.warrant.model.object;

public class Feature implements WarrantObject {
    static final String OBJECT_TYPE = "feature";

    private String featureId;

    public Feature() {
        // For json serialization
    }

    public Feature(String featureId) {
        this.featureId = featureId;
    }

    public String getFeatureId() {
        return featureId;
    }

    public void setFeatureId(String featureId) {
        this.featureId = featureId;
    }

    @Override
    public String id() {
        return featureId;
    }

    @Override
    public String type() {
        return "feature";
    }
}
