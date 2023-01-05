package dev.warrant;

import java.util.HashMap;
import java.util.Map;

public class QueryWarrantsParams {

    private String selectClause = "warrants";
    private String forClause;
    private String whereClause;

    public void setSelectClause(String selectClause) {
        this.selectClause = selectClause;
    }

    public void setForClause(String forClause) {
        this.forClause = forClause;
    }

    public void setWhereClause(String whereClause) {
        this.whereClause = whereClause;
    }

    public Map<String, Object> asMap() {
        Map<String, Object> params = new HashMap<>();
        if (selectClause != null) {
            params.put("select", selectClause);
        }
        if (forClause != null) {
            params.put("for", forClause);
        }
        if (whereClause != null) {
            params.put("where", whereClause);
        }
        return params;
    }
}
