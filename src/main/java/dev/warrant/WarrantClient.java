package dev.warrant;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Collections;
import java.util.Map;

import javax.ws.rs.core.UriBuilder;

import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.warrant.exception.WarrantException;
import dev.warrant.model.Warrant;
import dev.warrant.model.User;
import dev.warrant.model.UsersetWarrant;
import dev.warrant.model.Tenant;

public class WarrantClient {

    private final HttpClient client;
    private final WarrantConfig config;
    private final ObjectMapper mapper;

    public WarrantClient(WarrantConfig config) {
        client = HttpClient.newHttpClient();
        this.config = config;
        this.mapper = new ObjectMapper();
    }

    public User createUser() throws WarrantException {
        HttpResponse<String> resp = makePostRequest("/users", Collections.EMPTY_MAP);
        try {
            User newUser = mapper.readValue(resp.body(), User.class);
            return newUser;
        } catch (IOException e) {
            throw new WarrantException(e);
        }
    }

    public User createUser(String userId) throws WarrantException {
        User user = new User(userId);
        HttpResponse<String> resp = makePostRequest("/users", user);
        try {
            User newUser = mapper.readValue(resp.body(), User.class);
            return newUser;
        } catch (IOException e) {
            throw new WarrantException(e);
        }
    }

    public User createUser(String userId, String email) throws WarrantException {
        User user = new User(userId, email);
        HttpResponse<String> resp = makePostRequest("/users", user);
        try {
            User newUser = mapper.readValue(resp.body(), User.class);
            return newUser;
        } catch (IOException e) {
            throw new WarrantException(e);
        }
    }

    public Tenant createTenant() throws WarrantException {
        HttpResponse<String> resp = makePostRequest("/tenants", Collections.EMPTY_MAP);
        try {
            Tenant newTenant = mapper.readValue(resp.body(), Tenant.class);
            return newTenant;
        } catch (IOException e) {
            throw new WarrantException(e);
        }
    }

    public Tenant createTenant(String tenantId) throws WarrantException {
        Tenant tenant = new Tenant(tenantId);
        HttpResponse<String> resp = makePostRequest("/tenants", tenant);
        try {
            Tenant newTenant = mapper.readValue(resp.body(), Tenant.class);
            return newTenant;
        } catch (IOException e) {
            throw new WarrantException(e);
        }
    }

    public boolean isAuthorized(Warrant toCheck) throws WarrantException {
        HttpResponse<String> resp = makePostRequest("/authorize", toCheck);
        if (resp.statusCode() == Response.Status.OK.getStatusCode()) {
            return true;
        }
        return false;
    }

    public void createWarrant(Warrant toCreate) throws WarrantException {
        HttpResponse<String> resp = makePostRequest("/warrants", toCreate);
        if (resp.statusCode() != Response.Status.OK.getStatusCode()) {
            throw new WarrantException("Warrant request failed: HTTP " + resp.statusCode() + " " + resp.body());
        }
    }

    public UsersetWarrant[] listWarrants(Map<String, Object> filters) throws WarrantException {
        HttpResponse<String> resp = makeGetRequest("/warrants", filters);
        try {
            UsersetWarrant[] warrants = mapper.readValue(resp.body(), UsersetWarrant[].class);
            
            return warrants;
        } catch (IOException e) {
            throw new WarrantException(e);
        }
    } 

    public String createSession(String userId) throws WarrantException {
        HttpResponse<String> resp = makePostRequest("/users/" + userId + "/sessions", Collections.EMPTY_MAP);
        try {
            JsonNode respBody = mapper.readTree(resp.body());
            return respBody.get("token").asText();
        } catch (IOException e) {
            throw new WarrantException(e);
        }
    }

    private HttpResponse<String> makePostRequest(String uri, Object reqPayload) throws WarrantException {
        try {
            String payload = mapper.writeValueAsString(reqPayload);
            HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(config.getUrl() + uri))
                .POST(HttpRequest.BodyPublishers.ofString(payload))
                .header("Authorization", "ApiKey " + config.getApiKey())
                .build();
            HttpResponse<String> resp = client.send(req, BodyHandlers.ofString());
            int statusCode = resp.statusCode();
            if ((statusCode >= Response.Status.OK.getStatusCode() && statusCode < Response.Status.BAD_REQUEST.getStatusCode())
                || statusCode == Response.Status.UNAUTHORIZED.getStatusCode()) {
                return resp;
            } else {
                throw new WarrantException("Warrant request failed: HTTP " + statusCode + " " + resp.body());
            }
        } catch(IOException|InterruptedException e) {
            throw new WarrantException(e);
        }
    }

    private HttpResponse<String> makeGetRequest(String uri, Map<String, Object> params) throws WarrantException {
        try {
            UriBuilder builder = UriBuilder.fromPath(config.getUrl() + uri);
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                builder.queryParam(entry.getKey(), entry.getValue());
            }

            HttpRequest req = HttpRequest.newBuilder()
                .uri(builder.build())
                .GET()
                .header("Authorization", "ApiKey " + config.getApiKey())
                .build();
            HttpResponse<String> resp = client.send(req, BodyHandlers.ofString());
            int statusCode = resp.statusCode();
            if ((statusCode >= Response.Status.OK.getStatusCode() && statusCode < Response.Status.BAD_REQUEST.getStatusCode())
                || statusCode == Response.Status.UNAUTHORIZED.getStatusCode()) {
                return resp;
            } else {
                throw new WarrantException("Warrant request failed: HTTP " + statusCode + " " + resp.body());
            }
        } catch(IOException|InterruptedException e) {
            throw new WarrantException(e);
        }
    }
}
