package dev.warrant.model.object;

public class ListResult<T> {
    private T[] results;
    private String prevCursor;
    private String nextCursor;

    public ListResult() {
        // For json serialization
    }

    public ListResult(T[] results) {
        this.results = results;
    }

    public ListResult(T[] results, String prevCursor, String nextCursor) {
        this.results = results;
        this.prevCursor = prevCursor;
        this.nextCursor = nextCursor;
    }

    public T[] getResults() {
        return results;
    }

    public void setResults(T[] results) {
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
