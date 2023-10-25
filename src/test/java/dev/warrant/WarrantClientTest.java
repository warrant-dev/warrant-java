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
import dev.warrant.model.WarrantSubject;
import dev.warrant.model.QueryResult;
import dev.warrant.model.QueryResultSet;
import dev.warrant.model.Warrant;
import dev.warrant.model.object.Tenant;
import dev.warrant.model.object.User;

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
                .thenReturn("{\"objectType\":\"user\", \"objectId\":\"sdf872934sdf\", \"createdAt\": \"2022-05-14T02:40:25Z\"}");

        WarrantClient warrantClient = new WarrantClient(WarrantConfig.withApiKey("sample_key"), httpClient);
        User newUser = warrantClient.createUser();
        Assertions.assertEquals("sdf872934sdf", newUser.getUserId());
        Assertions.assertEquals("", newUser.getEmail());
    }

    @Test
    public void testCreateUserWithId() throws WarrantException {
        Mockito.when(httpResponse.statusCode()).thenReturn(200);
        Mockito.when(httpResponse.body())
                .thenReturn("{\"objectType\":\"user\", \"objectId\":\"sdf872935sdf\", \"createdAt\": \"2022-05-14T02:40:25Z\"}");

        WarrantClient warrantClient = new WarrantClient(WarrantConfig.withApiKey("sample_key"), httpClient);
        User newUser = warrantClient.createUser(new User("sdf872935sdf"));
        Assertions.assertEquals("sdf872935sdf", newUser.getUserId());
        Assertions.assertEquals("", newUser.getEmail());
    }

    @Test
    public void testCreateUserWithIdAndEmail() throws WarrantException {
        Mockito.when(httpResponse.statusCode()).thenReturn(200);
        Mockito.when(httpResponse.body()).thenReturn(
                "{\"objectType\":\"user\", \"objectId\":\"sdf872934sdf\", \"meta\": {\"email\": \"test@test.com\"}, \"createdAt\": \"2022-05-14T02:40:25Z\"}");

        WarrantClient warrantClient = new WarrantClient(WarrantConfig.withApiKey("sample_key"), httpClient);
        User newUser = warrantClient.createUser(new User("sdf882934sdf", "test@test.com"));
        Assertions.assertEquals("sdf872934sdf", newUser.getUserId());
        Assertions.assertEquals("test@test.com", newUser.getEmail());
    }

    @Test
    public void testCreateTenant() throws WarrantException {
        Mockito.when(httpResponse.statusCode()).thenReturn(200);
        Mockito.when(httpResponse.body()).thenReturn(
                "{\"objectType\":\"tenant\", \"objectId\": \"913241cf-740\", \"createdAt\": \"2022-05-16T04:33:39Z\" }");

        WarrantClient warrantClient = new WarrantClient(WarrantConfig.withApiKey("sample_key"), httpClient);
        Tenant newTenant = warrantClient.createTenant();
        Assertions.assertEquals("913241cf-740", newTenant.getTenantId());
        Assertions.assertEquals("", newTenant.getName());
    }

    @Test
    public void testCreateTenantWithId() throws WarrantException {
        Mockito.when(httpResponse.statusCode()).thenReturn(200);
        Mockito.when(httpResponse.body())
                .thenReturn("{\"objectType\":\"tenant\", \"objectId\": \"913241cf\", \"createdAt\": \"2022-05-16T04:33:39Z\" }");

        WarrantClient warrantClient = new WarrantClient(WarrantConfig.withApiKey("sample_key"), httpClient);
        Tenant newTenant = warrantClient.createTenant(new Tenant("913241cf"));
        Assertions.assertEquals("913241cf", newTenant.getTenantId());
        Assertions.assertEquals("", newTenant.getName());
    }

    @Test
    public void testQuery() throws WarrantException {
        Mockito.when(httpResponse.statusCode()).thenReturn(200);
        Mockito.when(httpResponse.body())
                .thenReturn(
                        "{\"results\":[{\"objectType\":\"role\",\"objectId\":\"admin\",\"warrant\":{\"objectType\":\"role\",\"objectId\":\"admin\",\"relation\":\"member\",\"subject\":{\"objectType\":\"user\",\"objectId\":\"6\"}},\"isImplicit\":false,\"meta\":{\"name\":\"Admin\"}},{\"objectType\":\"role\",\"objectId\":\"manager\",\"warrant\":{\"objectType\":\"role\",\"objectId\":\"manager\",\"relation\":\"member\",\"subject\":{\"objectType\":\"role\",\"objectId\":\"admin\"}},\"isImplicit\":true,\"meta\":{\"name\":\"Manager\"}}]}\n");

        WarrantClient warrantClient = new WarrantClient(WarrantConfig.withApiKey("sample_key"), httpClient);
        QueryResultSet queryResultSet = warrantClient.query("select role where user:6 is member", new ListParams(),
                new RequestOptions());
        QueryResult[] expectedQueryResults = {
                new QueryResult("role", "admin",
                        new Warrant("role", "admin", "member", new WarrantSubject("user", "6")), false),
                new QueryResult("role", "manager",
                        new Warrant("role", "manager", "member", new WarrantSubject("role", "admin")), true)
        };
        QueryResultSet expectedQueryResultSet = new QueryResultSet(expectedQueryResults, "", "");

        Assertions.assertEquals(expectedQueryResultSet.getResults().length, queryResultSet.getResults().length);
        Assertions.assertEquals(expectedQueryResultSet.getResults()[0].getObjectType(),
                queryResultSet.getResults()[0].getObjectType());
        Assertions.assertEquals(expectedQueryResultSet.getResults()[0].getObjectId(),
                queryResultSet.getResults()[0].getObjectId());
        Assertions.assertEquals(expectedQueryResultSet.getResults()[0].getWarrant().getObjectType(),
                queryResultSet.getResults()[0].getWarrant().getObjectType());
        Assertions.assertEquals(expectedQueryResultSet.getResults()[0].getWarrant().getObjectId(),
                queryResultSet.getResults()[0].getWarrant().getObjectId());
        Assertions.assertEquals(expectedQueryResultSet.getResults()[0].getWarrant().getRelation(),
                queryResultSet.getResults()[0].getWarrant().getRelation());
        Assertions.assertEquals(expectedQueryResultSet.getResults()[0].getWarrant().getSubject().getObjectType(),
                queryResultSet.getResults()[0].getWarrant().getSubject().getObjectType());
        Assertions.assertEquals(expectedQueryResultSet.getResults()[0].getWarrant().getSubject().getObjectId(),
                queryResultSet.getResults()[0].getWarrant().getSubject().getObjectId());
        Assertions.assertEquals(expectedQueryResultSet.getResults()[0].isImplicit(),
                queryResultSet.getResults()[0].isImplicit());
        Assertions.assertEquals(expectedQueryResultSet.getResults()[1].getObjectType(),
                queryResultSet.getResults()[1].getObjectType());
        Assertions.assertEquals(expectedQueryResultSet.getResults()[1].getObjectId(),
                queryResultSet.getResults()[1].getObjectId());
        Assertions.assertEquals(expectedQueryResultSet.getResults()[1].getWarrant().getObjectType(),
                queryResultSet.getResults()[1].getWarrant().getObjectType());
        Assertions.assertEquals(expectedQueryResultSet.getResults()[1].getWarrant().getObjectId(),
                queryResultSet.getResults()[1].getWarrant().getObjectId());
        Assertions.assertEquals(expectedQueryResultSet.getResults()[1].getWarrant().getRelation(),
                queryResultSet.getResults()[1].getWarrant().getRelation());
        Assertions.assertEquals(expectedQueryResultSet.getResults()[1].getWarrant().getSubject().getObjectType(),
                queryResultSet.getResults()[1].getWarrant().getSubject().getObjectType());
        Assertions.assertEquals(expectedQueryResultSet.getResults()[1].getWarrant().getSubject().getObjectId(),
                queryResultSet.getResults()[1].getWarrant().getSubject().getObjectId());
        Assertions.assertEquals(expectedQueryResultSet.getResults()[1].isImplicit(),
                queryResultSet.getResults()[1].isImplicit());
    }
}
