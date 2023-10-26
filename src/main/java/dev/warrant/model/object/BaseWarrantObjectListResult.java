package dev.warrant.model.object;

public class BaseWarrantObjectListResult {
    private BaseWarrantObject[] results;
    private String prevCursor;
    private String nextCursor;

    public BaseWarrantObjectListResult() {
        // For json serialization
    }

    public BaseWarrantObjectListResult(BaseWarrantObject[] results) {
        this.results = results;
    }

    public BaseWarrantObjectListResult(BaseWarrantObject[] results, String prevCursor, String nextCursor) {
        this.results = results;
        this.prevCursor = prevCursor;
        this.nextCursor = nextCursor;
    }

    public BaseWarrantObject[] getResults() {
        return results;
    }

    public void setResults(BaseWarrantObject[] results) {
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
