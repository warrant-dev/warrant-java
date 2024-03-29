package dev.warrant;

public class WarrantConfig {
    private static final String SELF_SERVICE_DASHBOARD_URL_BASE = "https://self-serve.warrant.dev";
    private static final String API_BASE = "https://api.warrant.dev";

    private final String baseUrl;
    private final String checkUrl;
    private final String apiKey;

    public static WarrantConfig withApiKey(String apiKey) {
        return new WarrantConfig(apiKey, API_BASE, API_BASE);
    }

    public WarrantConfig(String apiKey, String baseUrl, String checkUrl) {
        this.apiKey = apiKey;
        this.baseUrl = baseUrl;
        this.checkUrl = checkUrl;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getCheckUrl() {
        return checkUrl;
    }

    public String getSelfServiceDashboardBaseUrl() {
        return SELF_SERVICE_DASHBOARD_URL_BASE;
    }
}
