package dev.warrant;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Collections;
import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.warrant.exception.WarrantException;
import dev.warrant.model.UserSession;
import dev.warrant.model.UserSessionSpec;
import dev.warrant.model.Warrant;
import dev.warrant.model.WarrantCheck;
import dev.warrant.model.WarrantCheckResult;

public class WarrantBaseClient {
    public static final String SDK_VERSION = "0.3.0";
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

    public Warrant createWarrant(Warrant toCreate) throws WarrantException {
        HttpResponse<String> resp = makePostRequest("/v1/warrants", toCreate);
        if (resp.statusCode() != Response.Status.OK.getStatusCode()) {
            throw new WarrantException("Warrant request failed: HTTP " + resp.statusCode() + " " + resp.body());
        }
        return null;
    }

    public void deleteWarrant(Warrant toDelete) throws WarrantException {
        try {
            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create(config.getBaseUrl() + "/v1/warrants"))
                    .DELETE()
                    .header("Authorization", "ApiKey " + config.getApiKey())
                    .header("User-Agent", USER_AGENT)
                    .build();
            HttpResponse<String> resp = client.send(req, BodyHandlers.ofString());
            int statusCode = resp.statusCode();
            if (statusCode != Response.Status.OK.getStatusCode()) {
                throw new WarrantException("Failed to DELETE warrant: HTTP " + statusCode + " " + resp.body());
            }
        } catch (IOException | InterruptedException e) {
            throw new WarrantException(e);
        }
    }

    // TODO: support pagination?
    public Warrant[] queryWarrants(Map<String, Object> filters) throws WarrantException {
        if (filters == null) {
            throw new WarrantException("Must pass map of filters");
        }
        HttpResponse<String> resp = makeGetRequest("/v1/query", filters);
        try {
            Warrant[] warrants = mapper.readValue(resp.body(), Warrant[].class);
            return warrants;
        } catch (IOException e) {
            throw new WarrantException(e);
        }
    }

    // TODO: check multiple?
    public WarrantCheckResult check(WarrantCheck toCheck) throws WarrantException {
        HttpResponse<String> resp = makePostRequest("/v2/authorize", toCheck);
        if (resp.statusCode() != Response.Status.OK.getStatusCode()) {
            throw new WarrantException("API error: " + resp.statusCode());
        }
        try {
            WarrantCheckResult result = mapper.readValue(resp.body(), WarrantCheckResult.class);
            return result;
        } catch (IOException e) {
            throw new WarrantException(e);
        }
    }

    public String createUserAuthzSession(String userId) throws WarrantException {
        UserSession sess = makePostRequest("/v1/sessions", UserSessionSpec.newAuthorizationSession(userId),
                UserSession.class);
        return sess.getToken();
    }

    public String createUserSelfServiceDashboardUrl(String userId, String tenantId, String redirectUrl)
            throws WarrantException {
        UserSession ssdash = makePostRequest("/v1/sessions", UserSessionSpec.newSelfServiceSession(userId, tenantId),
                UserSession.class);
        return config.getSelfServiceDashboardBaseUrl() + "/" + ssdash.getToken() + "?redirectUrl=" + redirectUrl;
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
            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create(config.getBaseUrl() + uri))
                    .POST(HttpRequest.BodyPublishers.ofString(payload))
                    .header("Authorization", "ApiKey " + config.getApiKey())
                    .header("User-Agent", USER_AGENT)
                    .build();
            HttpResponse<String> resp = client.send(req, BodyHandlers.ofString());
            int statusCode = resp.statusCode();
            if ((statusCode >= Response.Status.OK.getStatusCode()
                    && statusCode < Response.Status.BAD_REQUEST.getStatusCode())
                    || statusCode == Response.Status.UNAUTHORIZED.getStatusCode()) {
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

    HttpResponse<String> makePutRequest(String uri, Object reqPayload) throws WarrantException {
        try {
            String payload = mapper.writeValueAsString(reqPayload);
            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create(config.getBaseUrl() + uri))
                    .PUT(HttpRequest.BodyPublishers.ofString(payload))
                    .header("Authorization", "ApiKey " + config.getApiKey())
                    .header("User-Agent", USER_AGENT)
                    .build();
            HttpResponse<String> resp = client.send(req, BodyHandlers.ofString());
            int statusCode = resp.statusCode();
            if ((statusCode >= Response.Status.OK.getStatusCode()
                    && statusCode < Response.Status.BAD_REQUEST.getStatusCode())
                    || statusCode == Response.Status.UNAUTHORIZED.getStatusCode()) {
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
            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create(config.getBaseUrl() + uri))
                    .DELETE()
                    .header("Authorization", "ApiKey " + config.getApiKey())
                    .header("User-Agent", USER_AGENT)
                    .build();
            HttpResponse<String> resp = client.send(req, BodyHandlers.ofString());
            int statusCode = resp.statusCode();
            if ((statusCode >= Response.Status.OK.getStatusCode()
                    && statusCode < Response.Status.BAD_REQUEST.getStatusCode())
                    || statusCode == Response.Status.UNAUTHORIZED.getStatusCode()) {
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

    private HttpResponse<String> makeGetRequest(String uri, Map<String, Object> params) throws WarrantException {
        try {
            UriBuilder builder = UriBuilder.fromPath(config.getBaseUrl() + uri);
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                builder.queryParam(entry.getKey(), entry.getValue());
            }

            HttpRequest req = HttpRequest.newBuilder()
                    .uri(builder.build())
                    .GET()
                    .header("Authorization", "ApiKey " + config.getApiKey())
                    .header("User-Agent", USER_AGENT)
                    .build();
            HttpResponse<String> resp = client.send(req, BodyHandlers.ofString());
            int statusCode = resp.statusCode();
            if ((statusCode >= Response.Status.OK.getStatusCode()
                    && statusCode < Response.Status.BAD_REQUEST.getStatusCode())
                    || statusCode == Response.Status.UNAUTHORIZED.getStatusCode()) {
                return resp;
            } else {
                throw new WarrantException("Warrant request failed: HTTP " + statusCode + " " + resp.body());
            }
        } catch (IOException | InterruptedException e) {
            throw new WarrantException(e);
        }
    }
}
