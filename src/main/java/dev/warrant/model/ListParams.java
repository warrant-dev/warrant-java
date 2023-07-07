package dev.warrant.model;

import java.util.HashMap;
import java.util.Map;

public class ListParams {
    private int page;
    private int limit;
    private String beforeId;
    private String beforeValue;
    private String afterId;
    private String afterValue;
    private String sortBy;
    private String sortOrder;

    public ListParams() {}

    public ListParams withPage(int page) {
        this.page = page;
        return this;
    }

    public ListParams withLimit(int limit) {
        this.limit = limit;
        return this;
    }

    public ListParams withBeforeId(String beforeId) {
        this.beforeId = beforeId;
        return this;
    }

    public ListParams withBeforeValue(String beforeValue) {
        this.beforeValue = beforeValue;
        return this;
    }

    public ListParams withAfterId(String afterId) {
        this.afterId = afterId;
        return this;
    }

    public ListParams withAfterValue(String afterValue) {
        this.afterValue = afterValue;
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
        if (this.page != 0) {
            params.put("page", page);
        }
        if (this.limit != 0) {
            params.put("limit", limit);
        }
        if (this.beforeId != null) {
            params.put("beforeId", beforeId);
        }
        if (this.beforeValue != null) {
            params.put("beforeValue", beforeValue);
        }
        if (this.afterId != null) {
            params.put("afterId", afterId);
        }
        if (this.afterValue != null) {
            params.put("afterValue", afterValue);
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
