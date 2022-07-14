package dev.warrant;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import javax.ws.rs.core.UriBuilder;

import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.warrant.exception.WarrantException;
import dev.warrant.model.Permission;
import dev.warrant.model.PermissionCheck;
import dev.warrant.model.Role;
import dev.warrant.model.Session;
import dev.warrant.model.Subject;
import dev.warrant.model.Tenant;
import dev.warrant.model.User;
import dev.warrant.model.Warrant;
import dev.warrant.model.WarrantCheck;
import dev.warrant.model.WarrantCheckResult;

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
        HttpResponse<String> resp = makePostRequest("/v1/users", Collections.EMPTY_MAP);
        try {
            User newUser = mapper.readValue(resp.body(), User.class);
            return newUser;
        } catch (IOException e) {
            throw new WarrantException(e);
        }
    }

    public User createUser(User user) throws WarrantException {
        HttpResponse<String> resp = makePostRequest("/v1/users", user);
        try {
            User newUser = mapper.readValue(resp.body(), User.class);
            return newUser;
        } catch (IOException e) {
            throw new WarrantException(e);
        }
    }

    public Tenant createTenant() throws WarrantException {
        HttpResponse<String> resp = makePostRequest("/v1/tenants", Collections.EMPTY_MAP);
        try {
            Tenant newTenant = mapper.readValue(resp.body(), Tenant.class);
            return newTenant;
        } catch (IOException e) {
            throw new WarrantException(e);
        }
    }

    public Tenant createTenant(Tenant tenant) throws WarrantException {
        HttpResponse<String> resp = makePostRequest("/v1/tenants", tenant);
        try {
            Tenant newTenant = mapper.readValue(resp.body(), Tenant.class);
            return newTenant;
        } catch (IOException e) {
            throw new WarrantException(e);
        }
    }

    public Role createRole(Role role) throws WarrantException {
        HttpResponse<String> resp = makePostRequest("/v1/roles", role);
        try {
            Role newRole = mapper.readValue(resp.body(), Role.class);
            return newRole;
        } catch (IOException e) {
            throw new WarrantException(e);
        }
    }

    public void deleteRole(String roleId) throws WarrantException {
        makeDeleteRequest("/v1/roles/" + roleId);
    }

    public Role assignRoleToUser(String userId, String roleId) throws WarrantException {
        HttpResponse<String> resp = makePostRequest("/v1/users/" + userId + "/roles/" + roleId, Collections.EMPTY_MAP);
        try {
            Role newRole = mapper.readValue(resp.body(), Role.class);
            return newRole;
        } catch (IOException e) {
            throw new WarrantException(e);
        }
    }

    public void removeRoleFromUser(String userId, String roleId) throws WarrantException {
        makeDeleteRequest("/v1/users/" + userId + "/roles/" + roleId);
    }

    public Permission createPermission(Permission permission) throws WarrantException {
        HttpResponse<String> resp = makePostRequest("/v1/permissions", permission);
        try {
            Permission newPermission = mapper.readValue(resp.body(), Permission.class);
            return newPermission;
        } catch (IOException e) {
            throw new WarrantException(e);
        }
    }

    public void deletePermission(String permissionId) throws WarrantException {
        makeDeleteRequest("/v1/permissions/" + permissionId);
    }

    public Permission assignPermissionToUser(String userId, String permissionId) throws WarrantException {
        HttpResponse<String> resp = makePostRequest("/v1/users/" + userId + "/permissions/" + permissionId,
                Collections.EMPTY_MAP);
        try {
            Permission newPermission = mapper.readValue(resp.body(), Permission.class);
            return newPermission;
        } catch (IOException e) {
            throw new WarrantException(e);
        }
    }

    public void removePermissionFromUser(String userId, String permissionId) throws WarrantException {
        makeDeleteRequest("/v1/users/" + userId + "/permissions/" + permissionId);
    }

    public Permission assignPermissionToRole(String roleId, String permissionId) throws WarrantException {
        HttpResponse<String> resp = makePostRequest("/v1/roles/" + roleId + "/permissions/" + permissionId,
                Collections.EMPTY_MAP);
        try {
            Permission newPermission = mapper.readValue(resp.body(), Permission.class);
            return newPermission;
        } catch (IOException e) {
            throw new WarrantException(e);
        }
    }

    public void removePermissionFromRole(String roleId, String permissionId) throws WarrantException {
        makeDeleteRequest("/v1/roles/" + roleId + "/permissions/" + permissionId);
    }

    public void createWarrant(Warrant toCreate) throws WarrantException {
        HttpResponse<String> resp = makePostRequest("/v1/warrants", toCreate);
        if (resp.statusCode() != Response.Status.OK.getStatusCode()) {
            throw new WarrantException("Warrant request failed: HTTP " + resp.statusCode() + " " + resp.body());
        }
    }

    public Warrant[] listWarrants(Map<String, Object> filters) throws WarrantException {
        HttpResponse<String> resp = makeGetRequest("/v1/warrants", filters);
        try {
            Warrant[] warrants = mapper.readValue(resp.body(), Warrant[].class);
            return warrants;
        } catch (IOException e) {
            throw new WarrantException(e);
        }
    }

    public String createAuthorizationSession(Session session) throws WarrantException {
        session.SetType("sess");
        HttpResponse<String> resp = makePostRequest("/v1/sessions/", session);
        try {
            JsonNode respBody = mapper.readTree(resp.body());
            return respBody.get("token").asText();
        } catch (IOException e) {
            throw new WarrantException(e);
        }
    }

    public String createSelfServiceSession(Session session, String redirectUrl) throws WarrantException {
        session.SetType("ssdash");
        HttpResponse<String> resp = makePostRequest("/v1/sessions/", session);
        try {
            JsonNode respBody = mapper.readTree(resp.body());
            String sessionToken = respBody.get("token").asText();
            return config.getSelfServiceDashboardBaseUrl() + "/" + sessionToken + "?redirectUrl=" + redirectUrl;
        } catch (IOException e) {
            throw new WarrantException(e);
        }
    }

    public WarrantCheckResult isAuthorized(WarrantCheck toCheck) throws WarrantException {
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

    public boolean hasPermission(PermissionCheck permissionCheck) throws WarrantException {
        Subject userSubject = new Subject("user", permissionCheck.getUserId());
        Warrant permissionWarrant = new Warrant("permission", permissionCheck.getPermissionId(), "member", userSubject);
        WarrantCheck warrantCheck = new WarrantCheck(Arrays.asList(permissionWarrant));
        WarrantCheckResult result = isAuthorized(warrantCheck);
        return result.getCode() == 200;
    }

    private HttpResponse<String> makePostRequest(String uri, Object reqPayload) throws WarrantException {
        try {
            String payload = mapper.writeValueAsString(reqPayload);
            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create(config.getBaseUrl() + uri))
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
            UriBuilder builder = UriBuilder.fromPath(config.getBaseUrl() + uri);
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

    private HttpResponse<String> makeDeleteRequest(String uri) throws WarrantException {
        try {
            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create(config.getBaseUrl() + uri))
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
