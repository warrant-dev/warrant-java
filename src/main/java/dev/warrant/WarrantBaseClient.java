package dev.warrant;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.warrant.exception.WarrantException;
import dev.warrant.model.WarrantSubject;
import dev.warrant.model.QueryResultSet;
import dev.warrant.model.UserSession;
import dev.warrant.model.UserSessionSpec;
import dev.warrant.model.Warrant;
import dev.warrant.model.WarrantCheckSpec;
import dev.warrant.model.WarrantSpec;
import dev.warrant.model.WarrantCheck;
import dev.warrant.model.object.WarrantObject;
import dev.warrant.model.object.BaseWarrantObject;
import dev.warrant.model.object.BaseWarrantObjectListResult;
import dev.warrant.model.object.WarrantListResult;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

public class WarrantBaseClient {
    public static final String SDK_VERSION = "4.1.0";
    public static final String USER_AGENT = "warrant-java/" + SDK_VERSION;
    public static final Integer MAX_RETRIES = 2;
    public static final Duration MINIMUM_SLEEP_TIME = Duration.ofMillis(500);
    public static final Double BACKOFF_MULTIPLIER = 1.5;

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
        return createWarrant(object, relation, subject, "", new RequestOptions());
    }

    public Warrant createWarrant(WarrantObject object, String relation, WarrantSubject subject, RequestOptions requestOptions)
            throws WarrantException {
        return createWarrant(object, relation, subject, "", requestOptions);
    }

    public Warrant createWarrant(WarrantObject object, String relation, WarrantSubject subject, String policy)
            throws WarrantException {
        return createWarrant(object, relation, subject, policy, new RequestOptions());
    }

    public Warrant createWarrant(WarrantObject object, String relation, WarrantSubject subject, String policy, RequestOptions requestOptions)
            throws WarrantException {
        Warrant toCreate = new Warrant(object.type(), object.id(), relation, subject, policy);
        return makePostRequest("/v2/warrants", toCreate, Warrant.class, requestOptions.asMap());
    }

    public Warrant[] createWarrants(Warrant[] warrants) throws WarrantException {
        return createWarrants(warrants, new RequestOptions());
    }

    public Warrant[] createWarrants(Warrant[] warrants, RequestOptions requestOptions) throws WarrantException {
        return makePostRequest("/v2/warrants", warrants, Warrant[].class, requestOptions.asMap());
    }

    public String deleteWarrant(WarrantObject object, String relation, WarrantSubject subject) throws WarrantException {
        return deleteWarrant(object, relation, subject, "", new RequestOptions());
    }

    public String deleteWarrant(WarrantObject object, String relation, WarrantSubject subject, RequestOptions requestOptions) throws WarrantException {
        return deleteWarrant(object, relation, subject, "", requestOptions);
    }

    public String deleteWarrant(WarrantObject object, String relation, WarrantSubject subject, String policy) throws WarrantException {
        return deleteWarrant(object, relation, subject, policy, new RequestOptions());
    }

    public String deleteWarrant(WarrantObject object, String relation, WarrantSubject subject, String policy, RequestOptions requestOptions) throws WarrantException {
        Warrant toDelete = new Warrant(object.type(), object.id(), relation, subject, policy);
        return makeDeleteRequest("/v2/warrants", toDelete, requestOptions.asMap());
    }

    public String deleteWarrants(Warrant[] warrants) throws WarrantException {
        return deleteWarrants(warrants, new RequestOptions());
    }

    public String deleteWarrants(Warrant[] warrants, RequestOptions requestOptions) throws WarrantException {
        return makeDeleteRequest("/v2/warrants", warrants, requestOptions.asMap());
    }

    public WarrantListResult listWarrants(WarrantFilters filters, ListParams listParams) throws WarrantException {
        return listWarrants(filters, listParams, new RequestOptions());
    }

    public WarrantListResult listWarrants(WarrantFilters filters, ListParams listParams, RequestOptions requestOptions)
            throws WarrantException {
        Map<String, Object> queryParams = filters.asMap();
        queryParams.putAll(listParams.asMap());
        return makeGetRequest("/v2/warrants", queryParams, WarrantListResult.class, requestOptions.asMap());
    }

    public QueryResultSet query(String query, ListParams listParams) throws WarrantException {
        return query(query, listParams, new RequestOptions());
    }

    public QueryResultSet query(String query, ListParams listParams, RequestOptions requestOptions)
            throws WarrantException {
        Map<String, Object> queryParams = listParams.asMap();
        queryParams.put("q", query);
        return makeGetRequest("/v2/query", queryParams, QueryResultSet.class, requestOptions.asMap());
    }

    public boolean check(WarrantObject object, String relation, WarrantSubject subject) throws WarrantException {
        return check(object, relation, subject, Collections.emptyMap(), new RequestOptions());
    }

    public boolean check(WarrantObject object, String relation, WarrantSubject subject, RequestOptions requestOptions) throws WarrantException {
        return check(object, relation, subject, Collections.emptyMap(), requestOptions);
    }

    public boolean check(WarrantObject object, String relation, WarrantSubject subject, Map<String, Object> context) throws WarrantException {
        return check(object, relation, subject, context, new RequestOptions());
    }

    public boolean check(WarrantObject object, String relation, WarrantSubject subject, Map<String, Object> context, RequestOptions requestOptions) throws WarrantException {
        WarrantCheckSpec toCheck = new WarrantCheckSpec(
                Arrays.asList(new WarrantSpec(object.type(), object.id(), relation, subject, context)));
        WarrantCheck result = makeCheckRequest(toCheck, requestOptions.asMap());
        if (result.getCode().intValue() == 200 && "Authorized".equals(result.getResult())) {
            return true;
        }
        return false;
    }

    public List<WarrantCheck> checkBatch(List<WarrantSpec> warrants) throws WarrantException {
        return checkBatch(warrants, new RequestOptions());
    }

    public List<WarrantCheck> checkBatch(List<WarrantSpec> warrants, RequestOptions requestOptions) throws WarrantException {
        WarrantCheck[] results = checkWithOp(warrants, "batch", WarrantCheck[].class, requestOptions);
        return Arrays.asList(results);
    }

    public WarrantCheck checkAnyOf(List<WarrantSpec> warrants) throws WarrantException {
        return checkAnyOf(warrants, new RequestOptions());
    }

    public WarrantCheck checkAnyOf(List<WarrantSpec> warrants, RequestOptions requestOptions) throws WarrantException {
        return checkWithOp(warrants, "anyOf", WarrantCheck.class, requestOptions);
    }

    public WarrantCheck checkAllOf(List<WarrantSpec> warrants) throws WarrantException {
        return checkAllOf(warrants, new RequestOptions());
    }

    public WarrantCheck checkAllOf(List<WarrantSpec> warrants, RequestOptions requestOptions) throws WarrantException {
        return checkWithOp(warrants, "allOf", WarrantCheck.class, new RequestOptions());
    }

    public BaseWarrantObject createObject(BaseWarrantObject object) throws WarrantException {
        return createObject(object, new RequestOptions());
    }

    public BaseWarrantObject createObject(BaseWarrantObject object, RequestOptions requestOptions) throws WarrantException {
        return createObject(object.type(), object.id(), Collections.emptyMap(), requestOptions);
    }

    public BaseWarrantObject createObject(BaseWarrantObject object, Map<String, Object> meta) throws WarrantException {
        return createObject(object, meta, new RequestOptions());
    }

    public BaseWarrantObject createObject(BaseWarrantObject object, Map<String, Object> meta, RequestOptions requestOptions) throws WarrantException {
        return createObject(object.type(), object.id(), meta, requestOptions);
    }

    public <T extends WarrantObject> T createObject(T object, Class<T> resultType) throws WarrantException {
        return createObject(object.type(), object.id(), Collections.emptyMap(), resultType, new RequestOptions());
    }

    public <T extends WarrantObject> T createObject(T object, Class<T> resultType, RequestOptions requestOptions) throws WarrantException {
        return createObject(object.type(), object.id(), Collections.emptyMap(), resultType, requestOptions);
    }

    public <T extends WarrantObject> T createObject(T object, Map<String, Object> meta, Class<T> resultType) throws WarrantException {
        return createObject(object.type(), object.id(), meta, resultType, new RequestOptions());
    }

    public <T extends WarrantObject> T createObject(T object, Map<String, Object> meta, Class<T> resultType, RequestOptions requestOptions) throws WarrantException {
        return createObject(object.type(), object.id(), meta, resultType, requestOptions);
    }

    public BaseWarrantObject createObject(String objectType) throws WarrantException {
        return createObject(objectType, null, Collections.emptyMap(), BaseWarrantObject.class, new RequestOptions());
    }

    public BaseWarrantObject createObject(String objectType, RequestOptions requestOptions) throws WarrantException {
        return createObject(objectType, null, Collections.emptyMap(), BaseWarrantObject.class, requestOptions);
    }

    public <T extends WarrantObject> T createObject(String objectType, Class<T> resultType) throws WarrantException {
        return createObject(objectType, null, Collections.emptyMap(), resultType, new RequestOptions());
    }

    public <T extends WarrantObject> T createObject(String objectType, Class<T> resultType, RequestOptions requestOptions) throws WarrantException {
        return createObject(objectType, null, Collections.emptyMap(), resultType, requestOptions);
    }

    public BaseWarrantObject createObject(String objectType, Map<String, Object> meta) throws WarrantException {
        return createObject(objectType, null, meta, BaseWarrantObject.class, new RequestOptions());
    }

    public BaseWarrantObject createObject(String objectType, Map<String, Object> meta, RequestOptions requestOptions) throws WarrantException {
        return createObject(objectType, null, meta, BaseWarrantObject.class, requestOptions);
    }

    public <T extends WarrantObject> T createObject(String objectType, Map<String, Object> meta, Class<T> resultType) throws WarrantException {
        return createObject(objectType, null, meta, resultType, new RequestOptions());
    }

    public <T extends WarrantObject> T createObject(String objectType, Map<String, Object> meta, Class<T> resultType, RequestOptions requestOptions) throws WarrantException {
        return createObject(objectType, null, meta, resultType, requestOptions);
    }

    public BaseWarrantObject createObject(String objectType, String objectId) throws WarrantException {
        return createObject(objectType, objectId, Collections.emptyMap(), BaseWarrantObject.class, new RequestOptions());
    }

    public BaseWarrantObject createObject(String objectType, String objectId, RequestOptions requestOptions) throws WarrantException {
        return createObject(objectType, objectId, Collections.emptyMap(), BaseWarrantObject.class, requestOptions);
    }

    public <T extends WarrantObject> T createObject(String objectType, String objectId, Class<T> resultType) throws WarrantException {
        return createObject(objectType, objectId, Collections.emptyMap(), resultType, new RequestOptions());
    }

    public <T extends WarrantObject> T createObject(String objectType, String objectId, Class<T> resultType, RequestOptions requestOptions) throws WarrantException {
        return createObject(objectType, objectId, Collections.emptyMap(), resultType, requestOptions);
    }

    public BaseWarrantObject createObject(String objectType, String objectId, Map<String, Object> meta) throws WarrantException {
        return createObject(objectType, objectId, meta, BaseWarrantObject.class, new RequestOptions());
    }

    public BaseWarrantObject createObject(String objectType, String objectId, Map<String, Object> meta, RequestOptions requestOptions) throws WarrantException {
        return createObject(objectType, objectId, meta, BaseWarrantObject.class, requestOptions);
    }

    public <T extends WarrantObject> T createObject(String objectType, String objectId, Map<String, Object> meta, Class<T> resultType) throws WarrantException {
        return createObject(objectType, objectId, meta, resultType, new RequestOptions());
    }

    public <T extends WarrantObject> T createObject(String objectType, String objectId, Map<String, Object> meta, Class<T> resultType, RequestOptions requestOptions) throws WarrantException {
        BaseWarrantObject obj = new BaseWarrantObject(objectType, objectId, meta);
        return makePostRequest("/v2/objects", obj, resultType, requestOptions.asMap());
    }

    public BaseWarrantObject[] createObjects(BaseWarrantObject[] objects) throws WarrantException {
        return createObjects(objects, new RequestOptions());
    }

    public BaseWarrantObject[] createObjects(BaseWarrantObject[] objects, RequestOptions requestOptions) throws WarrantException {
        return createObjects(objects, BaseWarrantObject[].class, requestOptions);
    }

    public <T extends WarrantObject> T[] createObjects(T[] objects, Class<T[]> resultType) throws WarrantException {
        return createObjects(objects, resultType, new RequestOptions());
    }

    public <T extends WarrantObject> T[] createObjects(T[] objects, Class<T[]> resultType, RequestOptions requestOptions) throws WarrantException {
        BaseWarrantObject[] baseObjects = Arrays.stream(objects).map(object -> new BaseWarrantObject(object.type(), object.id(), object.meta())).toArray(BaseWarrantObject[]::new);
        return makePostRequest("/v2/objects", baseObjects, resultType, requestOptions.asMap());
    }

    public BaseWarrantObject getObject(String objectType, String objectId) throws WarrantException {
        return getObject(objectType, objectId, BaseWarrantObject.class, new RequestOptions());
    }

    public BaseWarrantObject getObject(String objectType, String objectId, RequestOptions requestOptions) throws WarrantException {
        return getObject(objectType, objectId, BaseWarrantObject.class, requestOptions);
    }

    public <T extends WarrantObject> T getObject(String objectType, String objectId, Class<T> resultType) throws WarrantException {
        return getObject(objectType, objectId, resultType, new RequestOptions());
    }

    public <T extends WarrantObject> T getObject(String objectType, String objectId, Class<T> resultType, RequestOptions requestOptions) throws WarrantException {
        return makeGetRequest("/v2/objects/" + objectType + "/" + objectId, resultType, requestOptions.asMap());
    }

    public BaseWarrantObject updateObject(String objectType, String objectId, Map<String, Object> meta) throws WarrantException {
        return updateObject(objectType, objectId, meta, BaseWarrantObject.class, new RequestOptions());
    }

    public BaseWarrantObject updateObject(String objectType, String objectId, Map<String, Object> meta, RequestOptions requestOptions) throws WarrantException {
        return updateObject(objectType, objectId, meta, BaseWarrantObject.class, requestOptions);
    }

    public <T extends WarrantObject> T updateObject(String objectType, String objectId, Map<String, Object> meta, Class<T> resultType) throws WarrantException {
        return updateObject(objectType, objectId, meta, resultType, new RequestOptions());
    }

    public <T extends WarrantObject> T updateObject(String objectType, String objectId, Map<String, Object> meta, Class<T> resultType, RequestOptions requestOptions) throws WarrantException {
        BaseWarrantObject obj = new BaseWarrantObject(objectType, objectId, meta);
        return makePutRequest("/v2/objects/" + objectType + "/" + objectId, obj, resultType, requestOptions.asMap());
    }

    public String deleteObject(WarrantObject obj) throws WarrantException {
        return deleteObject(obj.type(), obj.id(), new RequestOptions());
    }

    public String deleteObject(WarrantObject obj, RequestOptions requestOptions) throws WarrantException {
        return deleteObject(obj.type(), obj.id(), requestOptions);
    }

    public String deleteObject(String objectType, String objectId) throws WarrantException {
        return deleteObject(objectType, objectId, new RequestOptions());
    }

    public String deleteObject(String objectType, String objectId, RequestOptions requestOptions) throws WarrantException {
        return makeDeleteRequest("/v2/objects/" + objectType + "/" + objectId, requestOptions.asMap());
    }

    public String deleteObjects(BaseWarrantObject[] objects) throws WarrantException {
        return deleteObjects(objects, new RequestOptions());
    }

    public String deleteObjects(BaseWarrantObject[] objects, RequestOptions requestOptions) throws WarrantException {
        return makeDeleteRequest("/v2/objects", objects, requestOptions.asMap());
    }

    public <T extends WarrantObject> String deleteObjects(T[] objects) throws WarrantException {
        return deleteObjects(objects, new RequestOptions());
    }

    public <T extends WarrantObject> String deleteObjects(T[] objects, RequestOptions requestOptions) throws WarrantException {
        BaseWarrantObject[] baseObjects = Arrays.stream(objects).map(object -> new BaseWarrantObject(object.type(), object.id())).toArray(BaseWarrantObject[]::new);
        return makeDeleteRequest("/v2/objects", baseObjects, requestOptions.asMap());
    }

    public BaseWarrantObjectListResult listObjects(ObjectFilters filters, ListParams listParams) throws WarrantException {
        return listObjects(filters, listParams, new RequestOptions());
    }

    public BaseWarrantObjectListResult listObjects(ObjectFilters filters, ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        Map<String, Object> queryParams = filters.asMap();
        queryParams.putAll(listParams.asMap());
        return makeGetRequest("/v2/objects", queryParams, BaseWarrantObjectListResult.class, requestOptions.asMap());
    }

    public String createUserAuthzSession(String userId) throws WarrantException {
        return createUserAuthzSession(userId, new RequestOptions());
    }

    public String createUserAuthzSession(String userId, RequestOptions requestOptions) throws WarrantException {
        UserSession sess = makePostRequest("/v2/sessions", UserSessionSpec.newAuthorizationSessionSpec(userId),
                UserSession.class, requestOptions.asMap());
        return sess.getToken();
    }

    public String createUserSelfServiceDashboardUrl(String userId, String tenantId, String selfServiceStrategy, String redirectUrl)
            throws WarrantException {
        return createUserSelfServiceDashboardUrl(userId, tenantId, selfServiceStrategy, redirectUrl, new RequestOptions());
    }

    public String createUserSelfServiceDashboardUrl(String userId, String tenantId, String selfServiceStrategy, String redirectUrl, RequestOptions requestOptions)
            throws WarrantException {
        UserSession ssdash = makePostRequest("/v2/sessions",
                UserSessionSpec.newSelfServiceDashboardSessionSpec(userId, tenantId, selfServiceStrategy),
                UserSession.class, requestOptions.asMap());
        return config.getSelfServiceDashboardBaseUrl() + "/" + ssdash.getToken() + "?redirectUrl=" + redirectUrl;
    }

    WarrantCheck makeCheckRequest(WarrantCheckSpec toCheck) throws WarrantException {
        return makeCheckRequest(toCheck, Collections.emptyMap());
    }

    WarrantCheck makeCheckRequest(WarrantCheckSpec toCheck, Map<String, Object> requestOptions) throws WarrantException {
        try {
            String payload = mapper.writeValueAsString(toCheck);
            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(URI.create(config.getCheckUrl() + "/v2/check"))
                    .POST(HttpRequest.BodyPublishers.ofString(payload))
                    .header("User-Agent", USER_AGENT);

            if (!config.getApiKey().isEmpty()) {
                requestBuilder.header("Authorization", "ApiKey " + config.getApiKey());
            }

            for (Map.Entry<String, Object> requestOption : requestOptions.entrySet()) {
                requestBuilder.header(requestOption.getKey(), requestOption.getValue().toString());
            }

            HttpRequest req = requestBuilder.build();
            HttpResponse<String> resp = makeRequestWithRetry(req, BodyHandlers.ofString());
            int statusCode = resp.statusCode();
            if (statusCode >= Response.Status.OK.getStatusCode() && statusCode < 300) {
                return mapper.readValue(resp.body(), WarrantCheck.class);
            } else {
                throw new WarrantException("Warrant request failed: HTTP " + statusCode + " " + resp.body());
            }
        } catch (IOException e) {
            throw new WarrantException(e);
        }
    }

    <T> T checkWithOp(List<WarrantSpec> warrants, String op, Class<T> type, RequestOptions requestOptions) throws WarrantException {
        try {
            WarrantCheckSpec toCheck = new WarrantCheckSpec(warrants, op);
            String payload = mapper.writeValueAsString(toCheck);
            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(URI.create(config.getCheckUrl() + "/v2/check"))
                    .POST(HttpRequest.BodyPublishers.ofString(payload))
                    .header("User-Agent", USER_AGENT);

            if (!config.getApiKey().isEmpty()) {
                requestBuilder.header("Authorization", "ApiKey " + config.getApiKey());
            }

            for (Map.Entry<String, Object> requestOption : requestOptions.asMap().entrySet()) {
                requestBuilder.header(requestOption.getKey(), requestOption.getValue().toString());
            }

            HttpRequest req = requestBuilder.build();
            HttpResponse<String> resp = makeRequestWithRetry(req, BodyHandlers.ofString());
            int statusCode = resp.statusCode();
            if (statusCode >= Response.Status.OK.getStatusCode() && statusCode < 300) {
                return mapper.readValue(resp.body(), type);
            } else {
                throw new WarrantException("Warrant request failed: HTTP " + statusCode + " " + resp.body());
            }
        } catch (IOException e) {
            throw new WarrantException(e);
        }
    }

    <T> T makePostRequest(String uri, Object reqPayload, Class<T> type) throws WarrantException {
        try {
            HttpResponse<String> resp = makePostRequest(uri, reqPayload);
            if (type.isArray()) {
                Map<String, Object>[] responseBody = mapper.readValue(resp.body(), new TypeReference<Map<String,Object>[]>(){});
                Optional<String> warrantToken = resp.headers().firstValue("warrant-token");
                for (Map<String, Object> map : responseBody) {
                    warrantToken.ifPresent(token -> map.put("warrantToken", token));
                }
                return mapper.readValue(mapper.writeValueAsString(responseBody), type);
            }
            Map<String, Object> responseBody = mapper.readValue(resp.body(), new TypeReference<Map<String,Object>>(){});
            Optional<String> warrantToken = resp.headers().firstValue("warrant-token");
            warrantToken.ifPresent(token -> responseBody.put("warrantToken", token));
            return mapper.readValue(mapper.writeValueAsString(responseBody), type);
        } catch (IOException e) {
            throw new WarrantException(e);
        }
    }

    <T> T makePostRequest(String uri, Object reqPayload, Class<T> type, Map<String, Object> requestOptions) throws WarrantException {
        try {
            HttpResponse<String> resp = makePostRequest(uri, reqPayload);
            if (type.isArray()) {
                Map<String, Object>[] responseBody = mapper.readValue(resp.body(), new TypeReference<Map<String,Object>[]>(){});
                Optional<String> warrantToken = resp.headers().firstValue("warrant-token");
                for (Map<String, Object> map : responseBody) {
                    warrantToken.ifPresent(token -> map.put("warrantToken", token));
                }
                return mapper.readValue(mapper.writeValueAsString(responseBody), type);
            }
            Map<String, Object> responseBody = mapper.readValue(resp.body(), new TypeReference<Map<String,Object>>(){});
            Optional<String> warrantToken = resp.headers().firstValue("warrant-token");
            warrantToken.ifPresent(token -> responseBody.put("warrantToken", token));
            return mapper.readValue(mapper.writeValueAsString(responseBody), type);
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
            HttpResponse<String> resp = makeRequestWithRetry(req, BodyHandlers.ofString());
            int statusCode = resp.statusCode();
            if (statusCode >= Response.Status.OK.getStatusCode() && statusCode < 300) {
                return resp;
            } else {
                throw new WarrantException("Warrant request failed: HTTP " + statusCode + " " + resp.body());
            }
        } catch (IOException e) {
            throw new WarrantException(e);
        }
    }

    HttpResponse<String> makePostRequest(String uri, Object reqPayload, Map<String, Object> requestOptions) throws WarrantException {
        try {
            String payload = mapper.writeValueAsString(reqPayload);
            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(URI.create(config.getBaseUrl() + uri))
                    .POST(HttpRequest.BodyPublishers.ofString(payload))
                    .header("User-Agent", USER_AGENT);

            for (Map.Entry<String, Object> requestOption : requestOptions.entrySet()) {
                requestBuilder.header(requestOption.getKey(), requestOption.getValue().toString());
            }

            if (!config.getApiKey().isEmpty()) {
                requestBuilder.header("Authorization", "ApiKey " + config.getApiKey());
            }
            HttpRequest req = requestBuilder.build();
            HttpResponse<String> resp = makeRequestWithRetry(req, BodyHandlers.ofString());
            int statusCode = resp.statusCode();
            if (statusCode >= Response.Status.OK.getStatusCode() && statusCode < 300) {
                return resp;
            } else {
                throw new WarrantException("Warrant request failed: HTTP " + statusCode + " " + resp.body());
            }
        } catch (IOException e) {
            throw new WarrantException(e);
        }
    }

    <T> T makePutRequest(String uri, Object reqPayload, Class<T> type) throws WarrantException {
        try {
            HttpResponse<String> resp = makePutRequest(uri, reqPayload);
            if (type.isArray()) {
                Map<String, Object>[] responseBody = mapper.readValue(resp.body(), new TypeReference<Map<String,Object>[]>(){});
                Optional<String> warrantToken = resp.headers().firstValue("warrant-token");
                for (Map<String, Object> map : responseBody) {
                    warrantToken.ifPresent(token -> map.put("warrantToken", token));
                }
                return mapper.readValue(mapper.writeValueAsString(responseBody), type);
            }
            Map<String, Object> responseBody = mapper.readValue(resp.body(), new TypeReference<Map<String,Object>>(){});
            Optional<String> warrantToken = resp.headers().firstValue("warrant-token");
            warrantToken.ifPresent(token -> responseBody.put("warrantToken", token));
            return mapper.readValue(mapper.writeValueAsString(responseBody), type);
        } catch (IOException e) {
            throw new WarrantException(e);
        }
    }

    <T> T makePutRequest(String uri, Object reqPayload, Class<T> type, Map<String, Object> requestOptions) throws WarrantException {
        try {
            HttpResponse<String> resp = makePutRequest(uri, reqPayload);
            if (type.isArray()) {
                Map<String, Object>[] responseBody = mapper.readValue(resp.body(), new TypeReference<Map<String,Object>[]>(){});
                Optional<String> warrantToken = resp.headers().firstValue("warrant-token");
                for (Map<String, Object> map : responseBody) {
                    warrantToken.ifPresent(token -> map.put("warrantToken", token));
                }
                return mapper.readValue(mapper.writeValueAsString(responseBody), type);
            }
            Map<String, Object> responseBody = mapper.readValue(resp.body(), new TypeReference<Map<String,Object>>(){});
            Optional<String> warrantToken = resp.headers().firstValue("warrant-token");
            warrantToken.ifPresent(token -> responseBody.put("warrantToken", token));
            return mapper.readValue(mapper.writeValueAsString(responseBody), type);
        } catch (IOException e) {
            throw new WarrantException(e);
        }
    }

    private HttpResponse<String> makePutRequest(String uri, Object reqPayload) throws WarrantException {
        return makePutRequest(uri, reqPayload, Collections.emptyMap());
    }

    private HttpResponse<String> makePutRequest(String uri, Object reqPayload, Map<String, Object> requestOptions) throws WarrantException {
        try {
            String payload = mapper.writeValueAsString(reqPayload);
            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(URI.create(config.getBaseUrl() + uri))
                    .PUT(HttpRequest.BodyPublishers.ofString(payload))
                    .header("User-Agent", USER_AGENT);

            for (Map.Entry<String, Object> requestOption : requestOptions.entrySet()) {
                requestBuilder.header(requestOption.getKey(), requestOption.getValue().toString());
            }

            if (!config.getApiKey().isEmpty()) {
                requestBuilder.header("Authorization", "ApiKey " + config.getApiKey());
            }
            HttpRequest req = requestBuilder.build();
            HttpResponse<String> resp = makeRequestWithRetry(req, BodyHandlers.ofString());
            int statusCode = resp.statusCode();
            if (statusCode >= Response.Status.OK.getStatusCode() && statusCode < 300) {
                return resp;
            } else {
                throw new WarrantException("Warrant request failed: HTTP " + statusCode + " " + resp.body());
            }
        } catch (IOException e) {
            throw new WarrantException(e);
        }
    }

    String makeDeleteRequest(String uri) throws WarrantException {
        return makeDeleteRequest(uri, null, Collections.emptyMap());
    }

    String makeDeleteRequest(String uri, Object reqPayload) throws WarrantException {
        return makeDeleteRequest(uri, reqPayload, Collections.emptyMap());
    }

    String makeDeleteRequest(String uri, Map<String, Object> requestOptions) throws WarrantException {
        return makeDeleteRequest(uri, null, requestOptions);
    }

    String makeDeleteRequest(String uri, Object reqPayload, Map<String, Object> requestOptions) throws WarrantException {
        try {
            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(URI.create(config.getBaseUrl() + uri))
                    .header("User-Agent", USER_AGENT);

            if (reqPayload != null) {
                String payload = mapper.writeValueAsString(reqPayload);
                requestBuilder.method("DELETE", HttpRequest.BodyPublishers.ofString(payload));
            } else {
                requestBuilder.DELETE();
            }

            for (Map.Entry<String, Object> requestOption : requestOptions.entrySet()) {
                requestBuilder.header(requestOption.getKey(), requestOption.getValue().toString());
            }

            if (!config.getApiKey().isEmpty()) {
                requestBuilder.header("Authorization", "ApiKey " + config.getApiKey());
            }
            HttpRequest req = requestBuilder.build();
            HttpResponse<String> resp = makeRequestWithRetry(req, BodyHandlers.ofString());
            int statusCode = resp.statusCode();
            if (statusCode >= Response.Status.OK.getStatusCode() && statusCode < 300) {
                Optional<String> warrantToken = resp.headers().firstValue("warrant-token");
                if (warrantToken.isPresent()) {
                    return warrantToken.toString();
                } else {
                    return "";
                }
            } else {
                throw new WarrantException("Warrant request failed: HTTP " + statusCode + " " + resp.body());
            }
        } catch (IOException e) {
            throw new WarrantException(e);
        }
    }

    <T> T makeGetRequest(String uri, Class<T> type) throws WarrantException {
        try {
            HttpResponse<String> resp = makeGetRequest(uri, Collections.emptyMap());
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

    <T> T makeGetRequest(String uri, Class<T> type, Map<String, Object> requestOptions) throws WarrantException {
        try {
            HttpResponse<String> resp = makeGetRequest(uri, Collections.emptyMap(), requestOptions);
            return mapper.readValue(resp.body(), type);
        } catch (IOException e) {
            throw new WarrantException(e);
        }
    }

    <T> T makeGetRequest(String uri, Map<String, Object> queryParams, Class<T> type, Map<String, Object> requestOptions) throws WarrantException {
        try {
            HttpResponse<String> resp = makeGetRequest(uri, queryParams, requestOptions);
            return mapper.readValue(resp.body(), type);
        } catch (IOException e) {
            throw new WarrantException(e);
        }
    }

    private HttpResponse<String> makeGetRequest(String uri, Map<String, Object> queryParams) throws WarrantException {
        return makeGetRequest(uri, queryParams, Collections.emptyMap());
    }

    private HttpResponse<String> makeGetRequest(String uri, Map<String, Object> queryParams, Map<String, Object> requestOptions) throws WarrantException {
        UriBuilder builder = UriBuilder.fromPath(config.getBaseUrl() + uri);
        for (Map.Entry<String, Object> entry : queryParams.entrySet()) {
            builder.queryParam(entry.getKey(), entry.getValue());
        }

        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(builder.build())
                .GET()
                .header("User-Agent", USER_AGENT);

        for (Map.Entry<String, Object> requestOption : requestOptions.entrySet()) {
            requestBuilder.header(requestOption.getKey(), requestOption.getValue().toString());
        }

        if (!config.getApiKey().isEmpty()) {
            requestBuilder.header("Authorization", "ApiKey " + config.getApiKey());
        }
        HttpRequest req = requestBuilder.build();
        HttpResponse<String> resp = makeRequestWithRetry(req, BodyHandlers.ofString());
        int statusCode = resp.statusCode();
        if (statusCode >= Response.Status.OK.getStatusCode() && statusCode < 300) {
            return resp;
        } else {
            throw new WarrantException("Warrant request failed: HTTP " + statusCode + " " + resp.body());
        }
    }

    static final Map<String, Object> getPaginationParams(int limit, int page) {
        Map<String, Object> params = new HashMap<>();
        params.put("limit", limit);
        params.put("page", page);
        return params;
    }

    private HttpResponse<String> makeRequestWithRetry(HttpRequest request, BodyHandler<String> respBodyHandler) throws WarrantException {
        WarrantException requestException = null;
        HttpResponse<String> response = null;
        int retryAttempts = 0;

        while (true) {
            requestException = null;

            try {
                response = client.send(request, respBodyHandler);
            } catch (IOException | InterruptedException e) {
                requestException = new WarrantException(e);
            }

            if (!shouldRetryRequest(response, retryAttempts, requestException)) {
                break;
            }

            retryAttempts += 1;

            try {
                Thread.sleep(getSleepTime(retryAttempts).toMillis());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        if (requestException != null) {
            throw requestException;
        }

        return response;
    }

    private boolean shouldRetryRequest(HttpResponse<String> response, int retryAttempts, WarrantException requestException) {
        if (retryAttempts > MAX_RETRIES) {
            return false;
        }

        if ((requestException != null) && (requestException.getCause() != null)
            && (requestException.getCause() instanceof ConnectException || requestException.getCause() instanceof SocketTimeoutException)) {
            return true;
        }

        if ((requestException != null) && (requestException.getCause() != null) && (requestException.getCause() instanceof IOException) && (requestException.getMessage().contains("GOAWAY"))) {
            return true;
        }

        if (response != null && response.statusCode() == 502) {
            return true;
        }

        return false;
    }

    private Duration getSleepTime(int retryAttempt) {
        Duration sleepTime = Duration.ofNanos((long) (MINIMUM_SLEEP_TIME.toNanos() * Math.pow(BACKOFF_MULTIPLIER, retryAttempt + 1)));

        double jitter = ThreadLocalRandom.current().nextDouble(0.5, 1.5);
        sleepTime = Duration.ofNanos((long) (sleepTime.toNanos() * jitter));

        return sleepTime;
    }
}
