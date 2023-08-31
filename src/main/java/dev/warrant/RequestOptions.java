package dev.warrant;

import java.util.HashMap;
import java.util.Map;

public class RequestOptions {
    private String warrantToken;

    public RequestOptions() {}

    public RequestOptions withWarrantToken(String warrantToken) {
        this.warrantToken = warrantToken;
        return this;
    }

    public Map<String, Object> asMap() {
        Map<String, Object> options = new HashMap<>();

        if (this.warrantToken != null) {
            options.put("Warrant-Token", warrantToken);
        }

        return options;
    }
}
