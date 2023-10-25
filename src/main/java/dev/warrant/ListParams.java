package dev.warrant;

import java.util.HashMap;
import java.util.Map;

public class ListParams {
    private int limit = 0;
    private String prevCursor;
    private String nextCursor;
    private String sortBy;
    private String sortOrder;

    public ListParams() {
    }

    public ListParams withLimit(int limit) {
        this.limit = limit;
        return this;
    }

    public ListParams withPrevCursor(String prevCursor) {
        this.prevCursor = prevCursor;
        return this;
    }

    public ListParams withNextCursor(String nextCursor) {
        this.nextCursor = nextCursor;
        return this;
    }

    public ListParams withSortBy(String sortBy) {
        this.sortBy = sortBy;
        return this;
    }

    public ListParams withSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
        return this;
    }

    public Map<String, Object> asMap() {
        Map<String, Object> params = new HashMap<>();
        if (this.limit != 0) {
            params.put("limit", limit);
        }
        if (this.prevCursor != null) {
            params.put("prevCursor", prevCursor);
        }
        if (this.nextCursor != null) {
            params.put("nextCursor", nextCursor);
        }
        if (this.sortBy != null) {
            params.put("sortBy", sortBy);
        }
        if (this.sortOrder != null) {
            params.put("sortOrder", sortOrder);
        }
        return params;
    }
}
