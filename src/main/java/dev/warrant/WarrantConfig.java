package dev.warrant;

public class WarrantConfig {
    private static final String SELF_SERVICE_DASHBOARD_URL_BASE = "https://self-serve.warrant.dev";

    private final String baseUrl;
    private final String apiKey;

    public static WarrantConfig withApiKey(String apiKey) {
        return new WarrantConfig(apiKey);
    }

    public WarrantConfig(String apiKey) {
        this.apiKey = apiKey;
        this.baseUrl = "https://api.warrant.dev";
    }

    public WarrantConfig(String apiKey, String baseUrl) {
        this.apiKey = apiKey;
        this.baseUrl = baseUrl;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getSelfServiceDashboardBaseUrl() {
        return SELF_SERVICE_DASHBOARD_URL_BASE;
    }
}
