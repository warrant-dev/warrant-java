package dev.warrant.model;

public class QueryResultSet {

    private QueryResult[] results;
    private String prevCursor;
    private String nextCursor;

    public QueryResultSet() {
        // For json serialization
    }

    public QueryResultSet(QueryResult[] results) {
        this.results = results;
    }

    public QueryResultSet(QueryResult[] results, String prevCursor, String nextCursor) {
        this.results = results;
        this.prevCursor = prevCursor;
        this.nextCursor = nextCursor;
    }

    public QueryResult[] getResults() {
        return this.results;
    }

    public String getPrevCursor() {
        return prevCursor;
    }

    public void setPrevCursor(String prevCursor) {
        this.prevCursor = prevCursor;
    }

    public String getNextCursor() {
        return nextCursor;
    }

    public void setNextCursor(String nextCursor) {
        this.nextCursor = nextCursor;
    }
}
