package dev.warrant;

public class WarrantConfig {
    private static final String API_URL_BASE = "https://api.warrant.dev";
    private static final String API_VERSION = "/v1";

    private final String apiKey;

    public static WarrantConfig withApiKey(String apiKey) {
        return new WarrantConfig(apiKey);
    }

    public WarrantConfig(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getUrl() {
        return API_URL_BASE + API_VERSION;
    }
}
