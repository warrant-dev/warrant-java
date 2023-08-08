package dev.warrant;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.warrant.exception.WarrantException;
import dev.warrant.model.WarrantSubject;
import dev.warrant.model.UserSession;
import dev.warrant.model.UserSessionSpec;
import dev.warrant.model.Warrant;
import dev.warrant.model.WarrantCheckSpec;
import dev.warrant.model.WarrantSpec;
import dev.warrant.model.WarrantCheck;
import dev.warrant.model.object.WarrantObject;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

public class WarrantBaseClient {
    public static final String SDK_VERSION = "3.1.0";
    public static final String USER_AGENT = "warrant-java/" + SDK_VERSION;

    final HttpClient client;
    final WarrantConfig config;
    final ObjectMapper mapper;

    WarrantBaseClient(WarrantConfig config, HttpClient client) {
        this.config = config;
        this.client = client;
        this.mapper = new ObjectMapper();
        // Only pojo declared attributes should be deserialized
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public WarrantBaseClient(WarrantConfig config) {
        this(config, HttpClient.newHttpClient());
    }

    public Warrant createWarrant(WarrantObject object, String relation, WarrantSubject subject)
            throws WarrantException {
        Warrant toCreate = new Warrant(object.type(), object.id(), relation, subject);
        return makePostRequest("/v1/warrants", toCreate, Warrant.class);
    }

    public Warrant createWarrant(WarrantObject object, String relation, WarrantSubject subject, String policy)
            throws WarrantException {
        Warrant toCreate = new Warrant(object.type(), object.id(), relation, subject, policy);
        return makePostRequest("/v1/warrants", toCreate, Warrant.class);
    }

    public void deleteWarrant(WarrantObject object, String relation, WarrantSubject subject) throws WarrantException {
        try {
            Warrant toDelete = new Warrant(object.type(), object.id(), relation, subject);
            String payload = mapper.writeValueAsString(toDelete);
            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create(config.getBaseUrl() + "/v1/warrants"))
                    .method("DELETE", HttpRequest.BodyPublishers.ofString(payload))
                    .header("Authorization", "ApiKey " + config.getApiKey())
                    .header("User-Agent", USER_AGENT)
                    .build();
            HttpResponse<String> resp = client.send(req, BodyHandlers.ofString());
            int statusCode = resp.statusCode();
            if (statusCode != Response.Status.OK.getStatusCode()) {
                throw new WarrantException("Warrant request failed: HTTP " + statusCode + " " + resp.body());
            }
        } catch (IOException | InterruptedException e) {
            throw new WarrantException(e);
        }
    }

    public void deleteWarrant(WarrantObject object, String relation, WarrantSubject subject, String policy) throws WarrantException {
        try {
            Warrant toDelete = new Warrant(object.type(), object.id(), relation, subject, policy);
            String payload = mapper.writeValueAsString(toDelete);
            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create(config.getBaseUrl() + "/v1/warrants"))
                    .method("DELETE", HttpRequest.BodyPublishers.ofString(payload))
                    .header("Authorization", "ApiKey " + config.getApiKey())
                    .header("User-Agent", USER_AGENT)
                    .build();
            HttpResponse<String> resp = client.send(req, BodyHandlers.ofString());
            int statusCode = resp.statusCode();
            if (statusCode != Response.Status.OK.getStatusCode()) {
                throw new WarrantException("Warrant request failed: HTTP " + statusCode + " " + resp.body());
            }
        } catch (IOException | InterruptedException e) {
            throw new WarrantException(e);
        }
    }

    public Warrant[] queryWarrants(Query query, int limit, int page) throws WarrantException {
        Map<String, Object> queryParams = query.asMap();
        queryParams.put("limit", limit);
        queryParams.put("page", page);
        return makeGetRequest("/v1/query", queryParams, Warrant[].class);
    }

    public boolean check(WarrantObject object, String relation, WarrantSubject subject) throws WarrantException {
        WarrantCheckSpec toCheck = new WarrantCheckSpec(
                Arrays.asList(new WarrantSpec(object.type(), object.id(), relation, subject)));
        WarrantCheck result = makeCheckRequest(toCheck);
        if (result.getCode().intValue() == 200 && "Authorized".equals(result.getResult())) {
            return true;
        }
        return false;
    }

    public boolean check(WarrantObject object, String relation, WarrantSubject subject, Map<String, Object> context) throws WarrantException {
        WarrantCheckSpec toCheck = new WarrantCheckSpec(
                Arrays.asList(new WarrantSpec(object.type(), object.id(), relation, subject, context)));
        WarrantCheck result = makeCheckRequest(toCheck);
        if (result.getCode().intValue() == 200 && "Authorized".equals(result.getResult())) {
            return true;
        }
        return false;
    }

    public String createUserAuthzSession(String userId) throws WarrantException {
        UserSession sess = makePostRequest("/v1/sessions", UserSessionSpec.newAuthorizationSessionSpec(userId),
                UserSession.class);
        return sess.getToken();
    }

    public String createUserSelfServiceDashboardUrl(String userId, String tenantId, String selfServiceStrategy, String redirectUrl)
            throws WarrantException {
        UserSession ssdash = makePostRequest("/v1/sessions",
                UserSessionSpec.newSelfServiceDashboardSessionSpec(userId, tenantId, selfServiceStrategy),
                UserSession.class);
        return config.getSelfServiceDashboardBaseUrl() + "/" + ssdash.getToken() + "?redirectUrl=" + redirectUrl;
    }

    WarrantCheck makeCheckRequest(WarrantCheckSpec toCheck) throws WarrantException {
        try {
            String payload = mapper.writeValueAsString(toCheck);
            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(URI.create(config.getCheckUrl() + "/v2/authorize"))
                    .POST(HttpRequest.BodyPublishers.ofString(payload))
                    .header("User-Agent", USER_AGENT);

            if (!config.getApiKey().isEmpty()) {
                requestBuilder.header("Authorization", "ApiKey " + config.getApiKey());
            }
            HttpRequest req = requestBuilder.build();
            HttpResponse<String> resp = client.send(req, BodyHandlers.ofString());
            int statusCode = resp.statusCode();
            if (statusCode >= Response.Status.OK.getStatusCode() && statusCode < 300) {
                return mapper.readValue(resp.body(), WarrantCheck.class);
            } else {
                throw new WarrantException("Warrant request failed: HTTP " + statusCode + " " + resp.body());
            }
        } catch (IOException | InterruptedException e) {
            throw new WarrantException(e);
        }
    }

    <T> T makePostRequest(String uri, Object reqPayload, Class<T> type) throws WarrantException {
        try {
            HttpResponse<String> resp = makePostRequest(uri, reqPayload);
            return mapper.readValue(resp.body(), type);
        } catch (IOException e) {
            throw new WarrantException(e);
        }
    }

    HttpResponse<String> makePostRequest(String uri, Object reqPayload) throws WarrantException {
        try {
            String payload = mapper.writeValueAsString(reqPayload);
            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(URI.create(config.getBaseUrl() + uri))
                    .POST(HttpRequest.BodyPublishers.ofString(payload))
                    .header("User-Agent", USER_AGENT);

            if (!config.getApiKey().isEmpty()) {
                requestBuilder.header("Authorization", "ApiKey " + config.getApiKey());
            }
            HttpRequest req = requestBuilder.build();
            HttpResponse<String> resp = client.send(req, BodyHandlers.ofString());
            int statusCode = resp.statusCode();
            if (statusCode >= Response.Status.OK.getStatusCode() && statusCode < 300) {
                return resp;
            } else {
                throw new WarrantException("Warrant request failed: HTTP " + statusCode + " " + resp.body());
            }
        } catch (IOException | InterruptedException e) {
            throw new WarrantException(e);
        }
    }

    <T> T makePutRequest(String uri, Object reqPayload, Class<T> type) throws WarrantException {
        try {
            HttpResponse<String> resp = makePutRequest(uri, reqPayload);
            return mapper.readValue(resp.body(), type);
        } catch (IOException e) {
            throw new WarrantException(e);
        }
    }

    private HttpResponse<String> makePutRequest(String uri, Object reqPayload) throws WarrantException {
        try {
            String payload = mapper.writeValueAsString(reqPayload);
            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(URI.create(config.getBaseUrl() + uri))
                    .PUT(HttpRequest.BodyPublishers.ofString(payload))
                    .header("User-Agent", USER_AGENT);

            if (!config.getApiKey().isEmpty()) {
                requestBuilder.header("Authorization", "ApiKey " + config.getApiKey());
            }
            HttpRequest req = requestBuilder.build();
            HttpResponse<String> resp = client.send(req, BodyHandlers.ofString());
            int statusCode = resp.statusCode();
            if (statusCode >= Response.Status.OK.getStatusCode() && statusCode < 300) {
                return resp;
            } else {
                throw new WarrantException("Warrant request failed: HTTP " + statusCode + " " + resp.body());
            }
        } catch (IOException | InterruptedException e) {
            throw new WarrantException(e);
        }
    }

    HttpResponse<String> makeDeleteRequest(String uri) throws WarrantException {
        try {
            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(URI.create(config.getBaseUrl() + uri))
                    .DELETE()
                    .header("User-Agent", USER_AGENT);

            if (!config.getApiKey().isEmpty()) {
                requestBuilder.header("Authorization", "ApiKey " + config.getApiKey());
            }
            HttpRequest req = requestBuilder.build();
            HttpResponse<String> resp = client.send(req, BodyHandlers.ofString());
            int statusCode = resp.statusCode();
            if (statusCode >= Response.Status.OK.getStatusCode() && statusCode < 300) {
                return resp;
            } else {
                throw new WarrantException("Warrant request failed: HTTP " + statusCode + " " + resp.body());
            }
        } catch (IOException | InterruptedException e) {
            throw new WarrantException(e);
        }
    }

    <T> T makeGetRequest(String uri, Class<T> type) throws WarrantException {
        try {
            HttpResponse<String> resp = makeGetRequest(uri, Collections.EMPTY_MAP);
            return mapper.readValue(resp.body(), type);
        } catch (IOException e) {
            throw new WarrantException(e);
        }
    }

    <T> T makeGetRequest(String uri, Map<String, Object> queryParams, Class<T> type) throws WarrantException {
        try {
            HttpResponse<String> resp = makeGetRequest(uri, queryParams);
            return mapper.readValue(resp.body(), type);
        } catch (IOException e) {
            throw new WarrantException(e);
        }
    }

    private HttpResponse<String> makeGetRequest(String uri, Map<String, Object> queryParams) throws WarrantException {
        try {
            UriBuilder builder = UriBuilder.fromPath(config.getBaseUrl() + uri);
            for (Map.Entry<String, Object> entry : queryParams.entrySet()) {
                builder.queryParam(entry.getKey(), entry.getValue());
            }

            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(builder.build())
                    .GET()
                    .header("User-Agent", USER_AGENT);

            if (!config.getApiKey().isEmpty()) {
                requestBuilder.header("Authorization", "ApiKey " + config.getApiKey());
            }
            HttpRequest req = requestBuilder.build();
            HttpResponse<String> resp = client.send(req, BodyHandlers.ofString());
            int statusCode = resp.statusCode();
            if (statusCode >= Response.Status.OK.getStatusCode() && statusCode < 300) {
                return resp;
            } else {
                throw new WarrantException("Warrant request failed: HTTP " + statusCode + " " + resp.body());
            }
        } catch (IOException | InterruptedException e) {
            throw new WarrantException(e);
        }
    }

    static final Map<String, Object> getPaginationParams(int limit, int page) {
        Map<String, Object> params = new HashMap<>();
        params.put("limit", limit);
        params.put("page", page);
        return params;
    }
}
