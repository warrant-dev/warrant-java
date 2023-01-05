package dev.warrant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Query {

    private final String selectClause;
    private final List<String> forClauses;
    private final List<String> whereClauses;

    public static Query selectWarrants() {
        return new Query("warrants");
    }

    public static Query selectExplicitWarrants() {
        return new Query("explicit warrants");
    }

    private Query(String select) {
        this.selectClause = select;
        this.forClauses = new ArrayList<>();
        this.whereClauses = new ArrayList<>();
    }

    public Query forClause(String forClause) {
        this.forClauses.add(forClause);
        return this;
    }

    public Query where(String where) {
        this.whereClauses.add(where);
        return this;
    }

    public Map<String, Object> asMap() {
        Map<String, Object> params = new HashMap<>();
        params.put("select", selectClause);

        if (forClauses.size() > 0) {
            StringBuilder b = new StringBuilder();
            for (String f : forClauses) {
                b.append(f).append(",");
            }
            b.delete(b.length() - 1, b.length());
            params.put("for", b.toString());
        }

        if (whereClauses.size() > 0) {
            StringBuilder b = new StringBuilder();
            for (String f : whereClauses) {
                b.append(f).append(",");
            }
            b.delete(b.length() - 1, b.length());
            params.put("where", b.toString());
        }

        return params;
    }
}
