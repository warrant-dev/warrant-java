package dev.warrant;

public class WarrantConfig {
    private static final String API_URL_BASE = "https://api.warrant.dev";
    private static final String SELF_SERVICE_DASHBOARD_URL_BASE = "https://self-serve.warrant.dev";

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

    public String getBaseUrl() {
        return API_URL_BASE;
    }

    public String getSelfServiceDashboardBaseUrl() {
        return SELF_SERVICE_DASHBOARD_URL_BASE;
    }
}
