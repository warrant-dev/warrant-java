package dev.warrant.model;

public class QueryResult {

    private String objectType;
    private String objectId;
    private Warrant warrant;
    private Boolean isImplicit;

    public QueryResult() {
        // For json serialization
    }

    public QueryResult(String objectType, String objectId, Warrant warrant, Boolean isImplicit) {
        this.objectType = objectType;
        this.objectId = objectId;
        this.warrant = warrant;
        this.isImplicit = isImplicit;
    }

    public String getObjectType() {
        return this.objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getObjectId() {
        return this.objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public Warrant getWarrant() {
        return this.warrant;
    }

    public void setWarrant(Warrant warrant) {
        this.warrant = warrant;
    }

    public Boolean isImplicit() {
        return this.isImplicit;
    }

    public void setIsImplicit(Boolean isImplicit) {
        this.isImplicit = isImplicit;
    }
}
