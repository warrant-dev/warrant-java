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
import dev.warrant.model.User;

public class WarrantClientTest {

    private HttpClient httpClient;
    private HttpResponse<String> httpResponse;

    @BeforeEach
    public void init() throws IOException, InterruptedException {
        httpClient = Mockito.mock(HttpClient.class);
        httpResponse = Mockito.mock(HttpResponse.class);
        Mockito.when(httpClient.send(Mockito.any(HttpRequest.class), Mockito.any(BodyHandler.class))).thenReturn(httpResponse);
    }

    @Test
    public void testCreateUser() throws WarrantException {
        Mockito.when(httpResponse.statusCode()).thenReturn(200);
        Mockito.when(httpResponse.body()).thenReturn("{\"userId\":\"sdf872934sdf\"}");

        WarrantClient warrantClient = new WarrantClient(WarrantConfig.withApiKey("sample_key"), httpClient);
        User newUser = warrantClient.createUser();
        Assertions.assertEquals("sdf872934sdf", newUser.getUserId());
    }
}
