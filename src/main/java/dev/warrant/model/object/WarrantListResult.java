package dev.warrant.model.object;

import dev.warrant.model.Warrant;

public class WarrantListResult {
    private Warrant[] results;
    private String prevCursor;
    private String nextCursor;

    public WarrantListResult() {
        // For json serialization
    }

    public WarrantListResult(Warrant[] results) {
        this.results = results;
    }

    public WarrantListResult(Warrant[] results, String prevCursor, String nextCursor) {
        this.results = results;
        this.prevCursor = prevCursor;
        this.nextCursor = nextCursor;
    }

    public Warrant[] getResults() {
        return results;
    }

    public void setResults(Warrant[] results) {
        this.results = results;
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
