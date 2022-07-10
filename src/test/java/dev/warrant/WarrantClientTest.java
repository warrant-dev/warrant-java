package dev.warrant;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import dev.warrant.exception.WarrantException;
import dev.warrant.model.Tenant;
import dev.warrant.model.User;

public class WarrantClientTest {

    private HttpClient httpClient;
    private HttpResponse<String> httpResponse;

    @BeforeEach
    public void init() throws IOException, InterruptedException {
        httpClient = Mockito.mock(HttpClient.class);
        httpResponse = Mockito.mock(HttpResponse.class);
        Mockito.when(httpClient.send(Mockito.any(HttpRequest.class), Mockito.any(BodyHandler.class)))
                .thenReturn(httpResponse);
    }

    @Test
    public void testCreateUser() throws WarrantException {
        Mockito.when(httpResponse.statusCode()).thenReturn(200);
        Mockito.when(httpResponse.body())
                .thenReturn("{\"userId\":\"sdf872934sdf\", \"createdAt\": \"2022-05-14T02:40:25Z\"}");

        WarrantClient warrantClient = new WarrantClient(WarrantConfig.withApiKey("sample_key"), httpClient);
        User newUser = warrantClient.createUser();
        Assertions.assertEquals("sdf872934sdf", newUser.getUserId());
        Assertions.assertNull(newUser.getEmail());
    }

    @Test
    public void testCreateUserWithId() throws WarrantException {
        Mockito.when(httpResponse.statusCode()).thenReturn(200);
        Mockito.when(httpResponse.body())
                .thenReturn("{\"userId\":\"sdf872935sdf\", \"createdAt\": \"2022-05-14T02:40:25Z\"}");

        WarrantClient warrantClient = new WarrantClient(WarrantConfig.withApiKey("sample_key"), httpClient);
        User newUser = warrantClient.createUser(new User("sdf872935sdf"));
        Assertions.assertEquals("sdf872935sdf", newUser.getUserId());
        Assertions.assertNull(newUser.getEmail());
    }

    @Test
    public void testCreateUserWithIdAndEmail() throws WarrantException {
        Mockito.when(httpResponse.statusCode()).thenReturn(200);
        Mockito.when(httpResponse.body()).thenReturn(
                "{\"userId\":\"sdf872934sdf\", \"email\": \"test@test.com\", \"createdAt\": \"2022-05-14T02:40:25Z\"}");

        WarrantClient warrantClient = new WarrantClient(WarrantConfig.withApiKey("sample_key"), httpClient);
        User newUser = warrantClient.createUser(new User("sdf882934sdf", "test@test.com"));
        Assertions.assertEquals("sdf872934sdf", newUser.getUserId());
        Assertions.assertEquals("test@test.com", newUser.getEmail());
    }

    @Test
    public void testCreateTenant() throws WarrantException {
        Mockito.when(httpResponse.statusCode()).thenReturn(200);
        Mockito.when(httpResponse.body()).thenReturn(
                "{\"tenantId\": \"913241cf-740\", \"name\": null, \"createdAt\": \"2022-05-16T04:33:39Z\" }");

        WarrantClient warrantClient = new WarrantClient(WarrantConfig.withApiKey("sample_key"), httpClient);
        Tenant newTenant = warrantClient.createTenant();
        Assertions.assertEquals("913241cf-740", newTenant.getTenantId());
        Assertions.assertNull(newTenant.getName());
    }

    @Test
    public void testCreateTenantWithId() throws WarrantException {
        Mockito.when(httpResponse.statusCode()).thenReturn(200);
        Mockito.when(httpResponse.body())
                .thenReturn("{\"tenantId\": \"913241cf\", \"name\": null, \"createdAt\": \"2022-05-16T04:33:39Z\" }");

        WarrantClient warrantClient = new WarrantClient(WarrantConfig.withApiKey("sample_key"), httpClient);
        Tenant newTenant = warrantClient.createTenant(new Tenant("913241cf"));
        Assertions.assertEquals("913241cf", newTenant.getTenantId());
        Assertions.assertNull(newTenant.getName());
    }
}
