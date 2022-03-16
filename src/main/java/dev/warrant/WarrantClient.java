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
import dev.warrant.model.Permission;
import dev.warrant.model.Role;
import dev.warrant.model.Session;
import dev.warrant.model.Tenant;
import dev.warrant.model.User;
import dev.warrant.model.UsersetWarrant;
import dev.warrant.model.Warrant;

public class WarrantClient {

    private final HttpClient client;
    private final WarrantConfig config;
    private final ObjectMapper mapper;

    WarrantClient(WarrantConfig config, HttpClient client) {
        this.config = config;
        this.client = client;
        this.mapper = new ObjectMapper();
    }

    public WarrantClient(WarrantConfig config) {
        this(config, HttpClient.newHttpClient());
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

    public Role createRole(String roleId) throws WarrantException {
        Role role = new Role(roleId);
        HttpResponse<String> resp = makePostRequest("/roles", role);
        try {
            Role newRole = mapper.readValue(resp.body(), Role.class);
            return newRole;
        } catch (IOException e) {
            throw new WarrantException(e);
        }
    }

    public void deleteRole(String roleId) throws WarrantException {
        makeDeleteRequest("/roles/" + roleId);
    }

    public Role assignRoleToUser(String userId, String roleId) throws WarrantException {
        HttpResponse<String> resp = makePostRequest("/users/" + userId + "/roles/" + roleId, Collections.EMPTY_MAP);
        try {
            Role newRole = mapper.readValue(resp.body(), Role.class);
            return newRole;
        } catch (IOException e) {
            throw new WarrantException(e);
        }
    }

    public void removeRoleFromUser(String userId, String roleId) throws WarrantException {
        makeDeleteRequest("/users/" + userId + "/roles/" + roleId);
    }

    public Permission createPermission(String permissionId) throws WarrantException {
        Permission permission = new Permission(permissionId);
        HttpResponse<String> resp = makePostRequest("/permissions", permission);
        try {
            Permission newPermission = mapper.readValue(resp.body(), Permission.class);
            return newPermission;
        } catch (IOException e) {
            throw new WarrantException(e);
        }
    }

    public void deletePermission(String permissionId) throws WarrantException {
        makeDeleteRequest("/permissions/" + permissionId);
    }

    public Permission assignPermissionToUser(String userId, String permissionId) throws WarrantException {
        HttpResponse<String> resp = makePostRequest("/users/" + userId + "/permissions/" + permissionId,
                Collections.EMPTY_MAP);
        try {
            Permission newPermission = mapper.readValue(resp.body(), Permission.class);
            return newPermission;
        } catch (IOException e) {
            throw new WarrantException(e);
        }
    }

    public void removePermissionFromUser(String userId, String permissionId) throws WarrantException {
        makeDeleteRequest("/users/" + userId + "/permissions/" + permissionId);
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

    public String createSelfServiceSession(Session session) throws WarrantException {
        HttpResponse<String> resp = makePostRequest("/sessions", session);
        try {
            JsonNode respBody = mapper.readTree(resp.body());
            return respBody.get("url").asText();
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

    public boolean hasPermission(String permissionId, String userId) throws WarrantException {
        return isAuthorized(Warrant.newUserWarrant("permission", permissionId, "member", userId));
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

    private HttpResponse<String> makeDeleteRequest(String uri) throws WarrantException {
        try {
            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create(config.getUrl() + uri))
                    .DELETE()
                    .header("Authorization", "ApiKey" + config.getApiKey())
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
