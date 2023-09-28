package dev.warrant.model;

public class QueryResultSet {

    private QueryResult[] results;
    private String lastId;

    public QueryResultSet() {
        // For json serialization
    }

    public QueryResultSet(QueryResult[] results, String lastId) {
        this.results = results;
        this.lastId = lastId;
    }

    public QueryResult[] getResults() {
        return this.results;
    }

    public String getLastId() {
        return this.lastId;
    }
}
