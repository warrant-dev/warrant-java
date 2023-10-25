package dev.warrant.model.object;

public class BaseListResult {
    private BaseWarrantObject[] results;
    private String prevCursor;
    private String nextCursor;

    public BaseListResult() {
        // For json serialization
    }

    public BaseListResult(BaseWarrantObject[] results) {
        this.results = results;
    }

    public BaseListResult(BaseWarrantObject[] results, String prevCursor, String nextCursor) {
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
