package dev.warrant;

import java.net.http.HttpClient;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import dev.warrant.exception.WarrantException;
import dev.warrant.model.WarrantSubject;
import dev.warrant.model.object.BaseWarrantObject;
import dev.warrant.model.object.Feature;
import dev.warrant.model.object.BaseWarrantObjectListResult;
import dev.warrant.model.object.ListResult;
import dev.warrant.model.object.Permission;
import dev.warrant.model.object.PricingTier;
import dev.warrant.model.object.Role;
import dev.warrant.model.object.Tenant;
import dev.warrant.model.object.User;
import dev.warrant.model.QueryResult;
import dev.warrant.model.QueryResultSet;
import dev.warrant.model.Warrant;

public class WarrantClient extends WarrantBaseClient {

    WarrantClient(WarrantConfig config, HttpClient client) {
        super(config, client);
    }

    public WarrantClient(WarrantConfig config) {
        super(config);
    }

    // Users
    public User createUser() throws WarrantException {
        return createUser(new RequestOptions());
    }

    public User createUser(RequestOptions requestOptions) throws WarrantException {
        return createObject(User.OBJECT_TYPE, User.class, requestOptions);
    }

    public User createUser(User user) throws WarrantException {
        return createUser(user, new RequestOptions());
    }

    public User createUser(User user, RequestOptions requestOptions) throws WarrantException {
        return createObject(User.OBJECT_TYPE, user.getUserId(), user.getMeta(), User.class, requestOptions);
    }

    public User[] createUsers(User[] users) throws WarrantException {
        return createUsers(users, new RequestOptions());
    }

    public User[] createUsers(User[] users, RequestOptions requestOptions) throws WarrantException {
        return createObjects(users, User[].class, requestOptions);
    }

    public User updateUser(String userId, User toUpdate) throws WarrantException {
        return updateUser(userId, toUpdate, new RequestOptions());
    }

    public User updateUser(String userId, User toUpdate, RequestOptions requestOptions) throws WarrantException {
        return updateObject(User.OBJECT_TYPE, userId, toUpdate.getMeta(), User.class, requestOptions);
    }

    public String deleteUser(User user) throws WarrantException {
        return deleteUser(user.getUserId(), new RequestOptions());
    }

    public String deleteUser(String userId) throws WarrantException {
        return deleteUser(userId, new RequestOptions());
    }

    public String deleteUser(String userId, RequestOptions requestOptions) throws WarrantException {
        return deleteObject(User.OBJECT_TYPE, userId, requestOptions);
    }

    public String deleteUsers(User[] users) throws WarrantException {
        return deleteObjects(users);
    }

    public String deleteUsers(User[] users, RequestOptions requestOptions) throws WarrantException {
        return deleteObjects(users, requestOptions);
    }

    public User getUser(String userId) throws WarrantException {
        return getUser(userId, new RequestOptions());
    }

    public User getUser(String userId, RequestOptions requestOptions) throws WarrantException {
        return getObject(User.OBJECT_TYPE, userId, User.class, requestOptions);
    }

    public ListResult<User> listUsers(int limit) throws WarrantException {
        return listUsers(new ListParams().withLimit(limit), new RequestOptions());
    }

    public ListResult<User> listUsers(int limit, RequestOptions requestOptions) throws WarrantException {
        return listUsers(new ListParams().withLimit(limit), requestOptions);
    }

    public ListResult<User> listUsers(ListParams listParams) throws WarrantException {
        return listUsers(listParams, new RequestOptions());
    }

    public ListResult<User> listUsers(ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        BaseWarrantObjectListResult userObjects = listObjects(new ObjectFilters().withObjectType(User.OBJECT_TYPE), new ListParams().withLimit(10), requestOptions);
        User[] users = Arrays.stream(userObjects.getResults()).map(result -> new User(result.getObjectId(), result.getMeta())).toArray(User[]::new);
        return new ListResult<User>(users, userObjects.getPrevCursor(), userObjects.getNextCursor());
    }

    public ListResult<User> listUsersForTenant(Tenant tenant, int limit) throws WarrantException {
        return listUsersForTenant(tenant.getTenantId(), new ListParams().withLimit(limit), new RequestOptions());
    }

    public ListResult<User> listUsersForTenant(Tenant tenant, int limit, RequestOptions requestOptions) throws WarrantException {
        return listUsersForTenant(tenant.getTenantId(), new ListParams().withLimit(limit), requestOptions);
    }

    public ListResult<User> listUsersForTenant(Tenant tenant, ListParams listParams) throws WarrantException {
        return listUsersForTenant(tenant.getTenantId(), listParams, new RequestOptions());
    }

    public ListResult<User> listUsersForTenant(Tenant tenant, ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        return listUsersForTenant(tenant.getTenantId(), listParams, requestOptions);
    }

    public ListResult<User> listUsersForTenant(String tenantId, int limit) throws WarrantException {
        return listUsersForTenant(tenantId, new ListParams().withLimit(limit), new RequestOptions());
    }

    public ListResult<User> listUsersForTenant(String tenantId, int limit, RequestOptions requestOptions) throws WarrantException {
        return listUsersForTenant(tenantId, new ListParams().withLimit(limit), requestOptions);
    }

    public ListResult<User> listUsersForTenant(String tenantId, ListParams listParams) throws WarrantException {
        return listUsersForTenant(tenantId, listParams, new RequestOptions());
    }

    public ListResult<User> listUsersForTenant(String tenantId, ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        QueryResultSet queryResultSet = query("select * of type user for tenant:" + tenantId, listParams, requestOptions);
        User[] users = Arrays.stream(queryResultSet.getResults()).map(result -> new User(result.getObjectId(), result.getMeta())).toArray(User[]::new);
        return new ListResult<User>(users, queryResultSet.getPrevCursor(), queryResultSet.getNextCursor());
    }

    // Tenants
    public Tenant createTenant() throws WarrantException {
        return createTenant(new Tenant(), new RequestOptions());
    }

    public Tenant createTenant(RequestOptions requestOptions) throws WarrantException {
        return createTenant(new Tenant(), requestOptions);
    }

    public Tenant createTenant(Tenant tenant) throws WarrantException {
        return createTenant(tenant, new RequestOptions());
    }

    public Tenant createTenant(Tenant tenant, RequestOptions requestOptions) throws WarrantException {
        return createObject(Tenant.OBJECT_TYPE, tenant.getTenantId(), tenant.getMeta(), Tenant.class, requestOptions);
    }

    public Tenant[] createTenants(Tenant[] tenants) throws WarrantException {
        return createTenants(tenants, new RequestOptions());
    }

    public Tenant[] createTenants(Tenant[] tenants, RequestOptions requestOptions) throws WarrantException {
        return createObjects(tenants, Tenant[].class, requestOptions);
    }

    public Tenant updateTenant(String tenantId, Tenant toUpdate) throws WarrantException {
        return updateTenant(tenantId, toUpdate, new RequestOptions());
    }

    public Tenant updateTenant(String tenantId, Tenant toUpdate, RequestOptions requestOptions) throws WarrantException {
        return updateObject(Tenant.OBJECT_TYPE, tenantId, toUpdate.getMeta(), Tenant.class, requestOptions);
    }

    public String deleteTenant(Tenant tenant) throws WarrantException {
        return deleteTenant(tenant.getTenantId(), new RequestOptions());
    }

    public String deleteTenant(Tenant tenant, RequestOptions requestOptions) throws WarrantException {
        return deleteTenant(tenant.getTenantId(), requestOptions);
    }

    public String deleteTenant(String tenantId) throws WarrantException {
        return deleteTenant(tenantId, new RequestOptions());
    }

    public String deleteTenant(String tenantId, RequestOptions requestOptions) throws WarrantException {
        return deleteObject(Tenant.OBJECT_TYPE, tenantId, requestOptions);
    }

    public String deleteTenants(Tenant[] tenants) throws WarrantException {
        return deleteTenants(tenants, new RequestOptions());
    }

    public String deleteTenants(Tenant[] tenants, RequestOptions requestOptions) throws WarrantException {
        return deleteObjects(tenants, requestOptions);
    }

    public Tenant getTenant(String tenantId) throws WarrantException {
        return getTenant(tenantId, new RequestOptions());
    }

    public Tenant getTenant(String tenantId, RequestOptions requestOptions) throws WarrantException {
        return getObject(Tenant.OBJECT_TYPE, tenantId, Tenant.class, requestOptions);
    }

    public ListResult<Tenant> listTenants(int limit) throws WarrantException {
        return listTenants(new ListParams().withLimit(limit), new RequestOptions());
    }

    public ListResult<Tenant> listTenants(int limit, RequestOptions requestOptions) throws WarrantException {
        return listTenants(new ListParams().withLimit(limit), requestOptions);
    }

    public ListResult<Tenant> listTenants(ListParams listParams) throws WarrantException {
        return listTenants(listParams, new RequestOptions());
    }

    public ListResult<Tenant> listTenants(ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        BaseWarrantObjectListResult tenantObjects = listObjects(new ObjectFilters().withObjectType(Tenant.OBJECT_TYPE), new ListParams().withLimit(10), requestOptions);
        Tenant[] tenants = Arrays.stream(tenantObjects.getResults()).map(result -> new Tenant(result.getObjectId(), result.getMeta())).toArray(Tenant[]::new);
        return new ListResult<Tenant>(tenants, tenantObjects.getPrevCursor(), tenantObjects.getNextCursor());
    }

    public ListResult<Tenant> listTenantsForUser(User user, int limit) throws WarrantException {
        return listTenantsForUser(user.getUserId(), new ListParams().withLimit(limit), new RequestOptions());
    }

    public ListResult<Tenant> listTenantsForUser(User user, int limit, RequestOptions requestOptions) throws WarrantException {
        return listTenantsForUser(user.getUserId(), new ListParams().withLimit(limit), requestOptions);
    }

    public ListResult<Tenant> listTenantsForUser(User user, ListParams listParams) throws WarrantException {
        return listTenantsForUser(user.getUserId(), listParams, new RequestOptions());
    }

    public ListResult<Tenant> listTenantsForUser(User user, ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        return listTenantsForUser(user.getUserId(), listParams, requestOptions);
    }

    public ListResult<Tenant> listTenantsForUser(String userId, int limit) throws WarrantException {
        return listTenantsForUser(userId, new ListParams().withLimit(limit), new RequestOptions());
    }

    public ListResult<Tenant> listTenantsForUser(String userId, int limit, RequestOptions requestOptions) throws WarrantException {
        return listTenantsForUser(userId, new ListParams().withLimit(limit), requestOptions);
    }

    public ListResult<Tenant> listTenantsForUser(String userId, ListParams listParams) throws WarrantException {
        return listTenantsForUser(userId, listParams, new RequestOptions());
    }

    public ListResult<Tenant> listTenantsForUser(String userId, ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        QueryResultSet queryResultSet = query("select tenant where user:" + userId + " is *", listParams, requestOptions);
        Tenant[] tenants = Arrays.stream(queryResultSet.getResults()).map(result -> new Tenant(result.getObjectId(), result.getMeta())).toArray(Tenant[]::new);
        return new ListResult<Tenant>(tenants, queryResultSet.getPrevCursor(), queryResultSet.getNextCursor());
    }

    // Roles
    public Role createRole() throws WarrantException {
        return createRole(new RequestOptions());
    }

    public Role createRole(RequestOptions requestOptions) throws WarrantException {
        return createObject(Role.OBJECT_TYPE, Role.class, requestOptions);
    }

    public Role createRole(Role role) throws WarrantException {
        return createRole(role, new RequestOptions());
    }

    public Role createRole(Role role, RequestOptions requestOptions) throws WarrantException {
        return createObject(Role.OBJECT_TYPE, role.getRoleId(), role.getMeta(), Role.class, requestOptions);
    }

    public Role updateRole(String roleId, Role toUpdate) throws WarrantException {
        return updateRole(roleId, toUpdate, new RequestOptions());
    }

    public Role updateRole(String roleId, Role toUpdate, RequestOptions requestOptions) throws WarrantException {
        return updateObject(Role.OBJECT_TYPE, roleId, toUpdate.getMeta(), Role.class, requestOptions);
    }

    public String deleteRole(Role role) throws WarrantException {
        return deleteRole(role.getRoleId(), new RequestOptions());
    }

    public String deleteRole(Role role, RequestOptions requestOptions) throws WarrantException {
        return deleteRole(role.getRoleId(), requestOptions);
    }

    public String deleteRole(String roleId) throws WarrantException {
        return deleteRole(roleId, new RequestOptions());
    }

    public String deleteRole(String roleId, RequestOptions requestOptions) throws WarrantException {
        return deleteObject(Role.OBJECT_TYPE, roleId, requestOptions);
    }

    public Role getRole(String roleId) throws WarrantException {
        return getRole(roleId, new RequestOptions());
    }

    public Role getRole(String roleId, RequestOptions requestOptions) throws WarrantException {
        return getObject(Role.OBJECT_TYPE, roleId, Role.class, requestOptions);
    }

    public ListResult<Role> listRoles(int limit) throws WarrantException {
        return listRoles(new ListParams().withLimit(limit), new RequestOptions());
    }

    public ListResult<Role> listRoles(int limit, RequestOptions requestOptions) throws WarrantException {
        return listRoles(new ListParams().withLimit(limit), requestOptions);
    }

    public ListResult<Role> listRoles(ListParams listParams) throws WarrantException {
        return listRoles(listParams, new RequestOptions());
    }

    public ListResult<Role> listRoles(ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        BaseWarrantObjectListResult roleObjects = listObjects(new ObjectFilters().withObjectType(Role.OBJECT_TYPE), new ListParams().withLimit(10), requestOptions);
        Role[] roles = Arrays.stream(roleObjects.getResults()).map(result -> new Role(result.getObjectId(), result.getMeta())).toArray(Role[]::new);
        return new ListResult<Role>(roles, roleObjects.getPrevCursor(), roleObjects.getNextCursor());
    }

    public ListResult<Role> listRolesForUser(User user, int limit) throws WarrantException {
        return listRolesForUser(user.getUserId(), new ListParams().withLimit(limit), new RequestOptions());
    }

    public ListResult<Role> listRolesForUser(User user, int limit, RequestOptions requestOptions) throws WarrantException {
        return listRolesForUser(user.getUserId(), new ListParams().withLimit(limit), requestOptions);
    }

    public ListResult<Role> listRolesForUser(User user, ListParams listParams) throws WarrantException {
        return listRolesForUser(user.getUserId(), listParams, new RequestOptions());
    }

    public ListResult<Role> listRolesForUser(User user, ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        return listRolesForUser(user.getUserId(), listParams, requestOptions);
    }

    public ListResult<Role> listRolesForUser(String userId, int limit) throws WarrantException {
        return listRolesForUser(userId, new ListParams().withLimit(limit), new RequestOptions());
    }

    public ListResult<Role> listRolesForUser(String userId, int limit, RequestOptions requestOptions) throws WarrantException {
        return listRolesForUser(userId, new ListParams().withLimit(limit), requestOptions);
    }

    public ListResult<Role> listRolesForUser(String userId, ListParams listParams) throws WarrantException {
        return listRolesForUser(userId, listParams, new RequestOptions());
    }

    public ListResult<Role> listRolesForUser(String userId, ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        QueryResultSet queryResultSet = query("select role where user:" + userId + " is *", listParams, requestOptions);
        Role[] roles = Arrays.stream(queryResultSet.getResults()).map(result -> new Role(result.getObjectId(), result.getMeta())).toArray(Role[]::new);
        return new ListResult<Role>(roles, queryResultSet.getPrevCursor(), queryResultSet.getNextCursor());
    }

    // Permissions
    public Permission createPermission() throws WarrantException {
        return createPermission(new RequestOptions());
    }

    public Permission createPermission(RequestOptions requestOptions) throws WarrantException {
        return createObject(Permission.OBJECT_TYPE, Permission.class, requestOptions);
    }

    public Permission createPermission(Permission permission) throws WarrantException {
        return createPermission(permission, new RequestOptions());
    }

    public Permission createPermission(Permission permission, RequestOptions requestOptions) throws WarrantException {
        return createObject(Permission.OBJECT_TYPE, permission.getPermissionId(), permission.getMeta(), Permission.class, requestOptions);
    }

    public Permission updatePermission(String permissionId, Permission toUpdate) throws WarrantException {
        return updatePermission(permissionId, toUpdate, new RequestOptions());
    }

    public Permission updatePermission(String permissionId, Permission toUpdate, RequestOptions requestOptions) throws WarrantException {
        return updateObject(Permission.OBJECT_TYPE, permissionId, toUpdate.getMeta(), Permission.class, requestOptions);
    }

    public String deletePermission(Permission permission) throws WarrantException {
        return deletePermission(permission.getPermissionId(), new RequestOptions());
    }

    public String deletePermission(Permission permission, RequestOptions requestOptions) throws WarrantException {
        return deletePermission(permission.getPermissionId(), requestOptions);
    }

    public String deletePermission(String permissionId) throws WarrantException {
        return deletePermission(permissionId, new RequestOptions());
    }

    public String deletePermission(String permissionId, RequestOptions requestOptions) throws WarrantException {
        return deleteObject(Permission.OBJECT_TYPE, permissionId, requestOptions);
    }

    public Permission getPermission(String permissionId) throws WarrantException {
        return getPermission(permissionId, new RequestOptions());
    }

    public Permission getPermission(String permissionId, RequestOptions requestOptions) throws WarrantException {
        return getObject(Permission.OBJECT_TYPE, permissionId, Permission.class, requestOptions);
    }

    public ListResult<Permission> listPermissions(int limit) throws WarrantException {
        return listPermissions(new ListParams().withLimit(limit), new RequestOptions());
    }

    public ListResult<Permission> listPermissions(int limit, RequestOptions requestOptions) throws WarrantException {
        return listPermissions(new ListParams().withLimit(limit), requestOptions);
    }

    public ListResult<Permission> listPermissions(ListParams listParams) throws WarrantException {
        return listPermissions(listParams, new RequestOptions());
    }

    public ListResult<Permission> listPermissions(ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        BaseWarrantObjectListResult permissionObjects = listObjects(new ObjectFilters().withObjectType(Permission.OBJECT_TYPE), new ListParams().withLimit(10), requestOptions);
        Permission[] permissions = Arrays.stream(permissionObjects.getResults()).map(result -> new Permission(result.getObjectId(), result.getMeta())).toArray(Permission[]::new);
        return new ListResult<Permission>(permissions, permissionObjects.getPrevCursor(), permissionObjects.getNextCursor());
    }

    public ListResult<Permission> listPermissionsForUser(User user, int limit) throws WarrantException {
        return listPermissionsForUser(user.getUserId(), new ListParams().withLimit(limit), new RequestOptions());
    }

    public ListResult<Permission> listPermissionsForUser(User user, int limit, RequestOptions requestOptions) throws WarrantException {
        return listPermissionsForUser(user.getUserId(), new ListParams().withLimit(limit), requestOptions);
    }

    public ListResult<Permission> listPermissionsForUser(User user, ListParams listParams) throws WarrantException {
        return listPermissionsForUser(user.getUserId(), listParams, new RequestOptions());
    }

    public ListResult<Permission> listPermissionsForUser(User user, ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        return listPermissionsForUser(user.getUserId(), listParams, requestOptions);
    }

    public ListResult<Permission> listPermissionsForUser(String userId, int limit) throws WarrantException {
        return listPermissionsForUser(userId, new ListParams().withLimit(limit), new RequestOptions());
    }

    public ListResult<Permission> listPermissionsForUser(String userId, int limit, RequestOptions requestOptions) throws WarrantException {
        return listPermissionsForUser(userId, new ListParams().withLimit(limit), requestOptions);
    }

    public ListResult<Permission> listPermissionsForUser(String userId, ListParams listParams) throws WarrantException {
        return listPermissionsForUser(userId, listParams, new RequestOptions());
    }

    public ListResult<Permission> listPermissionsForUser(String userId, ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        QueryResultSet queryResultSet = query("select permission where user:" + userId + " is *", listParams, requestOptions);
        Permission[] permissions = Arrays.stream(queryResultSet.getResults()).map(result -> new Permission(result.getObjectId(), result.getMeta())).toArray(Permission[]::new);
        return new ListResult<Permission>(permissions, queryResultSet.getPrevCursor(), queryResultSet.getNextCursor());
    }

    public ListResult<Permission> listPermissionsForRole(Role role, int limit) throws WarrantException {
        return listPermissionsForRole(role.getRoleId(), new ListParams().withLimit(limit), new RequestOptions());
    }

    public ListResult<Permission> listPermissionsForRole(Role role, int limit, RequestOptions requestOptions) throws WarrantException {
        return listPermissionsForRole(role.getRoleId(), new ListParams().withLimit(limit), requestOptions);
    }

    public ListResult<Permission> listPermissionsForRole(Role role, ListParams listParams) throws WarrantException {
        return listPermissionsForRole(role.getRoleId(), listParams, new RequestOptions());
    }

    public ListResult<Permission> listPermissionsForRole(Role role, ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        return listPermissionsForRole(role.getRoleId(), listParams, requestOptions);
    }

    public ListResult<Permission> listPermissionsForRole(String roleId, int limit) throws WarrantException {
        return listPermissionsForRole(roleId, new ListParams().withLimit(limit), new RequestOptions());
    }

    public ListResult<Permission> listPermissionsForRole(String roleId, int limit, RequestOptions requestOptions) throws WarrantException {
        return listPermissionsForRole(roleId, new ListParams().withLimit(limit), requestOptions);
    }

    public ListResult<Permission> listPermissionsForRole(String roleId, ListParams listParams) throws WarrantException {
        return listPermissionsForRole(roleId, listParams, new RequestOptions());
    }

    public ListResult<Permission> listPermissionsForRole(String roleId, ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        QueryResultSet queryResultSet = query("select permission where role:" + roleId + " is *", listParams, requestOptions);
        Permission[] permissions = Arrays.stream(queryResultSet.getResults()).map(result -> new Permission(result.getObjectId(), result.getMeta())).toArray(Permission[]::new);
        return new ListResult<Permission>(permissions, queryResultSet.getPrevCursor(), queryResultSet.getNextCursor());
    }

    // Features
    public Feature createFeature() throws WarrantException {
        return createFeature(new RequestOptions());
    }

    public Feature createFeature(RequestOptions requestOptions) throws WarrantException {
        return createObject(Feature.OBJECT_TYPE, Feature.class, requestOptions);
    }

    public Feature createFeature(Feature feature) throws WarrantException {
        return createFeature(feature, new RequestOptions());
    }

    public Feature createFeature(Feature feature, RequestOptions requestOptions) throws WarrantException {
        return createObject(Feature.OBJECT_TYPE, feature.getFeatureId(), feature.getMeta(), Feature.class, requestOptions);
    }

    public String deleteFeature(Feature feature) throws WarrantException {
        return deleteFeature(feature.getFeatureId(), new RequestOptions());
    }

    public String deleteFeature(Feature feature, RequestOptions requestOptions) throws WarrantException {
        return deleteFeature(feature.getFeatureId(), requestOptions);
    }

    public String deleteFeature(String featureId) throws WarrantException {
        return deleteFeature(featureId, new RequestOptions());
    }

    public String deleteFeature(String featureId, RequestOptions requestOptions) throws WarrantException {
        return deleteObject(Feature.OBJECT_TYPE, featureId, requestOptions);
    }

    public Feature getFeature(String featureId) throws WarrantException {
        return getFeature(featureId, new RequestOptions());
    }

    public Feature getFeature(String featureId, RequestOptions requestOptions) throws WarrantException {
        return getObject(Feature.OBJECT_TYPE, featureId, Feature.class, requestOptions);
    }

    public ListResult<Feature> listFeatures(int limit) throws WarrantException {
        return listFeatures(new ListParams().withLimit(limit), new RequestOptions());
    }

    public ListResult<Feature> listFeatures(int limit, RequestOptions requestOptions) throws WarrantException {
        return listFeatures(new ListParams().withLimit(limit), requestOptions);
    }

    public ListResult<Feature> listFeatures(ListParams listParams) throws WarrantException {
        return listFeatures(listParams, new RequestOptions());
    }

    public ListResult<Feature> listFeatures(ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        BaseWarrantObjectListResult featureObjects = listObjects(new ObjectFilters().withObjectType(Feature.OBJECT_TYPE), new ListParams().withLimit(10), requestOptions);
        Feature[] features = Arrays.stream(featureObjects.getResults()).map(result -> new Feature(result.getObjectId(), result.getMeta())).toArray(Feature[]::new);
        return new ListResult<Feature>(features, featureObjects.getPrevCursor(), featureObjects.getNextCursor());
    }

    public ListResult<Feature> listFeaturesForUser(User user, int limit) throws WarrantException {
        return listFeaturesForUser(user.getUserId(), new ListParams().withLimit(limit), new RequestOptions());
    }

    public ListResult<Feature> listFeaturesForUser(User user, int limit, RequestOptions requestOptions) throws WarrantException {
        return listFeaturesForUser(user.getUserId(), new ListParams().withLimit(limit), requestOptions);
    }

    public ListResult<Feature> listFeaturesForUser(User user, ListParams listParams) throws WarrantException {
        return listFeaturesForUser(user.getUserId(), listParams, new RequestOptions());
    }

    public ListResult<Feature> listFeaturesForUser(User user, ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        return listFeaturesForUser(user.getUserId(), listParams, requestOptions);
    }

    public ListResult<Feature> listFeaturesForUser(String userId, int limit) throws WarrantException {
        return listFeaturesForUser(userId, new ListParams().withLimit(limit), new RequestOptions());
    }

    public ListResult<Feature> listFeaturesForUser(String userId, int limit, RequestOptions requestOptions) throws WarrantException {
        return listFeaturesForUser(userId, new ListParams().withLimit(limit), requestOptions);
    }

    public ListResult<Feature> listFeaturesForUser(String userId, ListParams listParams) throws WarrantException {
        return listFeaturesForUser(userId, listParams, new RequestOptions());
    }

    public ListResult<Feature> listFeaturesForUser(String userId, ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        QueryResultSet queryResultSet = query("select feature where user:" + userId + " is *", listParams, requestOptions);
        Feature[] features = Arrays.stream(queryResultSet.getResults()).map(result -> new Feature(result.getObjectId(), result.getMeta())).toArray(Feature[]::new);
        return new ListResult<Feature>(features, queryResultSet.getPrevCursor(), queryResultSet.getNextCursor());
    }

    public ListResult<Feature> listFeaturesForTenant(Tenant tenant, int limit) throws WarrantException {
        return listFeaturesForTenant(tenant.getTenantId(), new ListParams().withLimit(limit), new RequestOptions());
    }

    public ListResult<Feature> listFeaturesForTenant(Tenant tenant, int limit, RequestOptions requestOptions) throws WarrantException {
        return listFeaturesForTenant(tenant.getTenantId(), new ListParams().withLimit(limit), requestOptions);
    }

    public ListResult<Feature> listFeaturesForTenant(Tenant tenant, ListParams listParams) throws WarrantException {
        return listFeaturesForTenant(tenant.getTenantId(), listParams, new RequestOptions());
    }

    public ListResult<Feature> listFeaturesForTenant(Tenant tenant, ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        return listFeaturesForTenant(tenant.getTenantId(), listParams, requestOptions);
    }

    public ListResult<Feature> listFeaturesForTenant(String tenantId, int limit) throws WarrantException {
        return listFeaturesForTenant(tenantId, new ListParams().withLimit(limit), new RequestOptions());
    }

    public ListResult<Feature> listFeaturesForTenant(String tenantId, int limit, RequestOptions requestOptions) throws WarrantException {
        return listFeaturesForTenant(tenantId, new ListParams().withLimit(limit), requestOptions);
    }

    public ListResult<Feature> listFeaturesForTenant(String tenantId, ListParams listParams) throws WarrantException {
        return listFeaturesForTenant(tenantId, listParams, new RequestOptions());
    }

    public ListResult<Feature> listFeaturesForTenant(String tenantId, ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        QueryResultSet queryResultSet = query("select feature where tenant:" + tenantId + " is *", listParams, requestOptions);
        Feature[] features = Arrays.stream(queryResultSet.getResults()).map(result -> new Feature(result.getObjectId(), result.getMeta())).toArray(Feature[]::new);
        return new ListResult<Feature>(features, queryResultSet.getPrevCursor(), queryResultSet.getNextCursor());
    }

    public ListResult<Feature> listFeaturesForPricingTier(PricingTier pricingTier, int limit) throws WarrantException {
        return listFeaturesForPricingTier(pricingTier.getPricingTierId(), new ListParams().withLimit(limit), new RequestOptions());
    }

    public ListResult<Feature> listFeaturesForPricingTier(PricingTier pricingTier, int limit, RequestOptions requestOptions) throws WarrantException {
        return listFeaturesForPricingTier(pricingTier.getPricingTierId(), new ListParams().withLimit(limit), requestOptions);
    }

    public ListResult<Feature> listFeaturesForPricingTier(PricingTier pricingTier, ListParams listParams) throws WarrantException {
        return listFeaturesForPricingTier(pricingTier.getPricingTierId(), listParams, new RequestOptions());
    }

    public ListResult<Feature> listFeaturesForPricingTier(PricingTier pricingTier, ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        return listFeaturesForPricingTier(pricingTier.getPricingTierId(), listParams, requestOptions);
    }

    public ListResult<Feature> listFeaturesForPricingTier(String pricingTierId, int limit) throws WarrantException {
        return listFeaturesForPricingTier(pricingTierId, new ListParams().withLimit(limit), new RequestOptions());
    }

    public ListResult<Feature> listFeaturesForPricingTier(String pricingTierId, int limit, RequestOptions requestOptions) throws WarrantException {
        return listFeaturesForPricingTier(pricingTierId, new ListParams().withLimit(limit), requestOptions);
    }

    public ListResult<Feature> listFeaturesForPricingTier(String pricingTierId, ListParams listParams) throws WarrantException {
        return listFeaturesForPricingTier(pricingTierId, listParams, new RequestOptions());
    }

    public ListResult<Feature> listFeaturesForPricingTier(String pricingTierId, ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        QueryResultSet queryResultSet = query("select feature where pricing-tier:" + pricingTierId + " is *", listParams, requestOptions);
        Feature[] features = Arrays.stream(queryResultSet.getResults()).map(result -> new Feature(result.getObjectId(), result.getMeta())).toArray(Feature[]::new);
        return new ListResult<Feature>(features, queryResultSet.getPrevCursor(), queryResultSet.getNextCursor());
    }

    // Pricing Tiers
    public PricingTier createPricingTier() throws WarrantException {
        return createPricingTier(new RequestOptions());
    }

    public PricingTier createPricingTier(RequestOptions requestOptions) throws WarrantException {
        return createObject(PricingTier.OBJECT_TYPE, PricingTier.class, requestOptions);
    }

    public PricingTier createPricingTier(PricingTier pricingTier) throws WarrantException {
        return createPricingTier(pricingTier, new RequestOptions());
    }

    public PricingTier createPricingTier(PricingTier pricingTier, RequestOptions requestOptions) throws WarrantException {
        return createObject(PricingTier.OBJECT_TYPE, pricingTier.getPricingTierId(), pricingTier.getMeta(), PricingTier.class, requestOptions);
    }

    public String deletePricingTier(PricingTier pricingTier) throws WarrantException {
        return deletePricingTier(pricingTier.getPricingTierId(), new RequestOptions());
    }

    public String deletePricingTier(PricingTier pricingTier, RequestOptions requestOptions) throws WarrantException {
        return deletePricingTier(pricingTier.getPricingTierId(), requestOptions);
    }

    public String deletePricingTier(String pricingTierId) throws WarrantException {
        return deletePricingTier(pricingTierId, new RequestOptions());
    }

    public String deletePricingTier(String pricingTierId, RequestOptions requestOptions) throws WarrantException {
        return deleteObject(PricingTier.OBJECT_TYPE, pricingTierId, requestOptions);
    }

    public PricingTier getPricingTier(String pricingTierId) throws WarrantException {
        return getPricingTier(pricingTierId, new RequestOptions());
    }

    public PricingTier getPricingTier(String pricingTierId, RequestOptions requestOptions) throws WarrantException {
        return getObject(PricingTier.OBJECT_TYPE, pricingTierId, PricingTier.class, requestOptions);
    }

    public ListResult<PricingTier> listPricingTiers(int limit) throws WarrantException {
        return listPricingTiers(new ListParams().withLimit(limit), new RequestOptions());
    }

    public ListResult<PricingTier> listPricingTiers(int limit, RequestOptions requestOptions) throws WarrantException {
        return listPricingTiers(new ListParams().withLimit(limit), requestOptions);
    }

    public ListResult<PricingTier> listPricingTiers(ListParams listParams) throws WarrantException {
        return listPricingTiers(listParams, new RequestOptions());
    }

    public ListResult<PricingTier> listPricingTiers(ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        BaseWarrantObjectListResult pricingTierObjects = listObjects(new ObjectFilters().withObjectType(PricingTier.OBJECT_TYPE), new ListParams().withLimit(10), requestOptions);
        PricingTier[] pricingTiers = Arrays.stream(pricingTierObjects.getResults()).map(result -> new PricingTier(result.getObjectId(), result.getMeta())).toArray(PricingTier[]::new);
        return new ListResult<PricingTier>(pricingTiers, pricingTierObjects.getPrevCursor(), pricingTierObjects.getNextCursor());
    }

    public ListResult<PricingTier> listPricingTiersForTenant(Tenant tenant, int limit) throws WarrantException {
        return listPricingTiersForTenant(tenant.getTenantId(), new ListParams().withLimit(limit), new RequestOptions());
    }

    public ListResult<PricingTier> listPricingTiersForTenant(Tenant tenant, int limit, RequestOptions requestOptions) throws WarrantException {
        return listPricingTiersForTenant(tenant.getTenantId(), new ListParams().withLimit(limit), requestOptions);
    }

    public ListResult<PricingTier> listPricingTiersForTenant(Tenant tenant, ListParams listParams) throws WarrantException {
        return listPricingTiersForTenant(tenant.getTenantId(), listParams, new RequestOptions());
    }

    public ListResult<PricingTier> listPricingTiersForTenant(Tenant tenant, ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        return listPricingTiersForTenant(tenant.getTenantId(), listParams, requestOptions);
    }

    public ListResult<PricingTier> listPricingTiersForTenant(String tenantId, int limit) throws WarrantException {
        return listPricingTiersForTenant(tenantId, new ListParams().withLimit(limit), new RequestOptions());
    }

    public ListResult<PricingTier> listPricingTiersForTenant(String tenantId, int limit, RequestOptions requestOptions) throws WarrantException {
        return listPricingTiersForTenant(tenantId, new ListParams().withLimit(limit), requestOptions);
    }

    public ListResult<PricingTier> listPricingTiersForTenant(String tenantId, ListParams listParams) throws WarrantException {
        return listPricingTiersForTenant(tenantId, listParams, new RequestOptions());
    }

    public ListResult<PricingTier> listPricingTiersForTenant(String tenantId, ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        QueryResultSet queryResultSet = query("select pricing-tier where tenant:" + tenantId + " is *", listParams, requestOptions);
        PricingTier[] pricingTiers = Arrays.stream(queryResultSet.getResults()).map(result -> new PricingTier(result.getObjectId(), result.getMeta())).toArray(PricingTier[]::new);
        return new ListResult<PricingTier>(pricingTiers, queryResultSet.getPrevCursor(), queryResultSet.getNextCursor());
    }

    public ListResult<PricingTier> listPricingTiersForUser(User user, int limit) throws WarrantException {
        return listPricingTiersForUser(user.getUserId(), new ListParams().withLimit(limit), new RequestOptions());
    }

    public ListResult<PricingTier> listPricingTiersForUser(User user, int limit, RequestOptions requestOptions) throws WarrantException {
        return listPricingTiersForUser(user.getUserId(), new ListParams().withLimit(limit), requestOptions);
    }

    public ListResult<PricingTier> listPricingTiersForUser(User user, ListParams listParams) throws WarrantException {
        return listPricingTiersForUser(user.getUserId(), listParams, new RequestOptions());
    }

    public ListResult<PricingTier> listPricingTiersForUser(User user, ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        return listPricingTiersForUser(user.getUserId(), listParams, requestOptions);
    }

    public ListResult<PricingTier> listPricingTiersForUser(String userId, int limit) throws WarrantException {
        return listPricingTiersForUser(userId, new ListParams().withLimit(limit), new RequestOptions());
    }

    public ListResult<PricingTier> listPricingTiersForUser(String userId, int limit, RequestOptions requestOptions) throws WarrantException {
        return listPricingTiersForUser(userId, new ListParams().withLimit(limit), requestOptions);
    }

    public ListResult<PricingTier> listPricingTiersForUser(String userId, ListParams listParams) throws WarrantException {
        return listPricingTiersForUser(userId, listParams, new RequestOptions());
    }

    public ListResult<PricingTier> listPricingTiersForUser(String userId, ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        QueryResultSet queryResultSet = query("select pricing-tier where user:" + userId + " is *", listParams, requestOptions);
        PricingTier[] pricingTiers = Arrays.stream(queryResultSet.getResults()).map(result -> new PricingTier(result.getObjectId(), result.getMeta())).toArray(PricingTier[]::new);
        return new ListResult<PricingTier>(pricingTiers, queryResultSet.getPrevCursor(), queryResultSet.getNextCursor());
    }

    // Assign
    public Warrant assignRoleToUser(Role role, User user) throws WarrantException {
        return assignRoleToUser(role.getRoleId(), user.getUserId(), new RequestOptions());
    }

    public Warrant assignRoleToUser(Role role, User user, RequestOptions requestOptions) throws WarrantException {
        return assignRoleToUser(role.getRoleId(), user.getUserId(), requestOptions);
    }

    public Warrant assignRoleToUser(String roleId, String userId) throws WarrantException {
        return assignRoleToUser(roleId, userId, new RequestOptions());
    }

    public Warrant assignRoleToUser(String roleId, String userId, RequestOptions requestOptions) throws WarrantException {
        return createWarrant(new Role(roleId), "member", new WarrantSubject(User.OBJECT_TYPE, userId), requestOptions);
    }

    public Warrant assignPermissionToUser(Permission permission, User user) throws WarrantException {
        return assignPermissionToUser(permission.getPermissionId(), user.getUserId(), new RequestOptions());
    }

    public Warrant assignPermissionToUser(Permission permission, User user, RequestOptions requestOptions) throws WarrantException {
        return assignPermissionToUser(permission.getPermissionId(), user.getUserId(), requestOptions);
    }

    public Warrant assignPermissionToUser(String permissionId, String userId) throws WarrantException {
        return assignPermissionToUser(permissionId, userId, new RequestOptions());
    }

    public Warrant assignPermissionToUser(String permissionId, String userId, RequestOptions requestOptions) throws WarrantException {
        return createWarrant(new Permission(permissionId), "member", new WarrantSubject(User.OBJECT_TYPE, userId), requestOptions);
    }

    public Warrant assignPricingTierToUser(PricingTier pricingTier, User user) throws WarrantException {
        return assignPricingTierToUser(pricingTier.getPricingTierId(), user.getUserId(), new RequestOptions());
    }

    public Warrant assignPricingTierToUser(PricingTier pricingTier, User user, RequestOptions requestOptions) throws WarrantException {
        return assignPricingTierToUser(pricingTier.getPricingTierId(), user.getUserId(), requestOptions);
    }

    public Warrant assignPricingTierToUser(String pricingTierId, String userId) throws WarrantException {
        return assignPricingTierToUser(pricingTierId, userId, new RequestOptions());
    }

    public Warrant assignPricingTierToUser(String pricingTierId, String userId, RequestOptions requestOptions) throws WarrantException {
        return createWarrant(new PricingTier(pricingTierId), "member", new WarrantSubject(User.OBJECT_TYPE, userId), requestOptions);
    }

    public Warrant assignFeatureToUser(Feature feature, User user) throws WarrantException {
        return assignFeatureToUser(feature.getFeatureId(), user.getUserId(), new RequestOptions());
    }

    public Warrant assignFeatureToUser(Feature feature, User user, RequestOptions requestOptions) throws WarrantException {
        return assignFeatureToUser(feature.getFeatureId(), user.getUserId(), requestOptions);
    }

    public Warrant assignFeatureToUser(String featureId, String userId) throws WarrantException {
        return assignFeatureToUser(featureId, userId, new RequestOptions());
    }

    public Warrant assignFeatureToUser(String featureId, String userId, RequestOptions requestOptions) throws WarrantException {
        return createWarrant(new Feature(featureId), "member", new WarrantSubject(User.OBJECT_TYPE, userId), requestOptions);
    }

    public Warrant assignUserToTenant(User user, Tenant tenant) throws WarrantException {
        return assignUserToTenant(user.getUserId(), tenant.getTenantId(), new RequestOptions());
    }

    public Warrant assignUserToTenant(User user, Tenant tenant, RequestOptions requestOptions) throws WarrantException {
        return assignUserToTenant(user.getUserId(), tenant.getTenantId(), requestOptions);
    }

    public Warrant assignUserToTenant(String userId, String tenantId) throws WarrantException {
        return assignUserToTenant(userId, tenantId, new RequestOptions());
    }

    public Warrant assignUserToTenant(String userId, String tenantId, RequestOptions requestOptions) throws WarrantException {
        return createWarrant(new Tenant(tenantId), "member", new WarrantSubject(User.OBJECT_TYPE, userId), requestOptions);
    }

    public Warrant assignPricingTierToTenant(PricingTier pricingTier, Tenant tenant) throws WarrantException {
        return assignPricingTierToTenant(pricingTier.getPricingTierId(), tenant.getTenantId(), new RequestOptions());
    }

    public Warrant assignPricingTierToTenant(PricingTier pricingTier, Tenant tenant, RequestOptions requestOptions) throws WarrantException {
        return assignPricingTierToTenant(pricingTier.getPricingTierId(), tenant.getTenantId(), requestOptions);
    }

    public Warrant assignPricingTierToTenant(String pricingTierId, String tenantId) throws WarrantException {
        return assignPricingTierToTenant(pricingTierId, tenantId, new RequestOptions());
    }

    public Warrant assignPricingTierToTenant(String pricingTierId, String tenantId, RequestOptions requestOptions) throws WarrantException {
        return createWarrant(new PricingTier(pricingTierId), "member", new WarrantSubject(Tenant.OBJECT_TYPE, tenantId), requestOptions);
    }

    public Warrant assignFeatureToTenant(Feature feature, Tenant tenant) throws WarrantException {
        return assignFeatureToTenant(feature.getFeatureId(), tenant.getTenantId(), new RequestOptions());
    }

    public Warrant assignFeatureToTenant(Feature feature, Tenant tenant, RequestOptions requestOptions) throws WarrantException {
        return assignFeatureToTenant(feature.getFeatureId(), tenant.getTenantId(), requestOptions);
    }

    public Warrant assignFeatureToTenant(String featureId, String tenantId) throws WarrantException {
        return assignFeatureToTenant(featureId, tenantId, new RequestOptions());
    }

    public Warrant assignFeatureToTenant(String featureId, String tenantId, RequestOptions requestOptions) throws WarrantException {
        return createWarrant(new Feature(featureId), "member", new WarrantSubject(Tenant.OBJECT_TYPE, tenantId), requestOptions);
    }

    public Warrant assignFeatureToPricingTier(Feature feature, PricingTier pricingTier) throws WarrantException {
        return assignFeatureToPricingTier(feature.getFeatureId(), pricingTier.getPricingTierId(), new RequestOptions());
    }

    public Warrant assignFeatureToPricingTier(Feature feature, PricingTier pricingTier, RequestOptions requestOptions) throws WarrantException {
        return assignFeatureToPricingTier(feature.getFeatureId(), pricingTier.getPricingTierId(), requestOptions);
    }

    public Warrant assignFeatureToPricingTier(String featureId, String pricingTierId) throws WarrantException {
        return assignFeatureToPricingTier(featureId, pricingTierId, new RequestOptions());
    }

    public Warrant assignFeatureToPricingTier(String featureId, String pricingTierId, RequestOptions requestOptions) throws WarrantException {
        return createWarrant(new Feature(featureId), "member", new WarrantSubject(PricingTier.OBJECT_TYPE, pricingTierId), requestOptions);
    }

    public Warrant assignPermissionToRole(Permission permission, Role role) throws WarrantException {
        return assignPermissionToRole(permission.getPermissionId(), role.getRoleId(), new RequestOptions());
    }

    public Warrant assignPermissionToRole(Permission permission, Role role, RequestOptions requestOptions) throws WarrantException {
        return assignPermissionToRole(permission.getPermissionId(), role.getRoleId(), requestOptions);
    }

    public Warrant assignPermissionToRole(String permissionId, String roleId) throws WarrantException {
        return assignPermissionToRole(permissionId, roleId, new RequestOptions());
    }

    public Warrant assignPermissionToRole(String permissionId, String roleId, RequestOptions requestOptions) throws WarrantException {
        return createWarrant(new Permission(permissionId), "member", new WarrantSubject(Role.OBJECT_TYPE, roleId), requestOptions);
    }

    // Remove associations
    public void removeRoleFromUser(Role role, User user) throws WarrantException {
        removeRoleFromUser(role.getRoleId(), user.getUserId(), new RequestOptions());
    }

    public void removeRoleFromUser(Role role, User user, RequestOptions requestOptions) throws WarrantException {
        removeRoleFromUser(role.getRoleId(), user.getUserId(), requestOptions);
    }

    public void removeRoleFromUser(String roleId, String userId) throws WarrantException {
        removeRoleFromUser(roleId, userId, new RequestOptions());
    }

    public void removeRoleFromUser(String roleId, String userId, RequestOptions requestOptions) throws WarrantException {
        deleteWarrant(new Role(roleId), "member", new WarrantSubject(User.OBJECT_TYPE, userId), requestOptions);
    }

    public void removePermissionFromUser(Permission permission, User user) throws WarrantException {
        removePermissionFromUser(permission.getPermissionId(), user.getUserId(), new RequestOptions());
    }

    public void removePermissionFromUser(Permission permission, User user, RequestOptions requestOptions) throws WarrantException {
        removePermissionFromUser(permission.getPermissionId(), user.getUserId(), requestOptions);
    }

    public void removePermissionFromUser(String permissionId, String userId) throws WarrantException {
        removePermissionFromUser(permissionId, userId, new RequestOptions());
    }

    public void removePermissionFromUser(String permissionId, String userId, RequestOptions requestOptions) throws WarrantException {
        deleteWarrant(new Permission(permissionId), "member", new WarrantSubject(User.OBJECT_TYPE, userId), requestOptions);
    }

    public void removePricingTierFromUser(PricingTier pricingTier, User user) throws WarrantException {
        removePricingTierFromUser(pricingTier.getPricingTierId(), user.getUserId(), new RequestOptions());
    }

    public void removePricingTierFromUser(PricingTier pricingTier, User user, RequestOptions requestOptions) throws WarrantException {
        removePricingTierFromUser(pricingTier.getPricingTierId(), user.getUserId(), requestOptions);
    }

    public void removePricingTierFromUser(String pricingTierId, String userId) throws WarrantException {
        removePricingTierFromUser(pricingTierId, userId, new RequestOptions());
    }

    public void removePricingTierFromUser(String pricingTierId, String userId, RequestOptions requestOptions) throws WarrantException {
        deleteWarrant(new PricingTier(pricingTierId), "member", new WarrantSubject(User.OBJECT_TYPE, userId), requestOptions);
    }

    public void removeFeatureFromUser(Feature feature, User user) throws WarrantException {
        removeFeatureFromUser(feature.getFeatureId(), user.getUserId(), new RequestOptions());
    }

    public void removeFeatureFromUser(Feature feature, User user, RequestOptions requestOptions) throws WarrantException {
        removeFeatureFromUser(feature.getFeatureId(), user.getUserId(), requestOptions);
    }

    public void removeFeatureFromUser(String featureId, String userId) throws WarrantException {
        removeFeatureFromUser(featureId, userId, new RequestOptions());
    }

    public void removeFeatureFromUser(String featureId, String userId, RequestOptions requestOptions) throws WarrantException {
        deleteWarrant(new Feature(featureId), "member", new WarrantSubject(User.OBJECT_TYPE, userId), requestOptions);
    }

    public void removeUserFromTenant(User user, Tenant tenant) throws WarrantException {
        removeUserFromTenant(user.getUserId(), tenant.getTenantId(), new RequestOptions());
    }

    public void removeUserFromTenant(User user, Tenant tenant, RequestOptions requestOptions) throws WarrantException {
        removeUserFromTenant(user.getUserId(), tenant.getTenantId(), requestOptions);
    }

    public void removeUserFromTenant(String userId, String tenantId) throws WarrantException {
        removeUserFromTenant(userId, tenantId, new RequestOptions());
    }

    public void removeUserFromTenant(String userId, String tenantId, RequestOptions requestOptions) throws WarrantException {
        deleteWarrant(new Tenant(tenantId), "member", new WarrantSubject(User.OBJECT_TYPE, userId), requestOptions);
    }

    public void removePricingTierFromTenant(PricingTier pricingTier, Tenant tenant) throws WarrantException {
        removePricingTierFromTenant(pricingTier.getPricingTierId(), tenant.getTenantId(), new RequestOptions());
    }

    public void removePricingTierFromTenant(PricingTier pricingTier, Tenant tenant, RequestOptions requestOptions) throws WarrantException {
        removePricingTierFromTenant(pricingTier.getPricingTierId(), tenant.getTenantId(), requestOptions);
    }

    public void removePricingTierFromTenant(String pricingTierId, String tenantId) throws WarrantException {
        removePricingTierFromTenant(pricingTierId, tenantId, new RequestOptions());
    }

    public void removePricingTierFromTenant(String pricingTierId, String tenantId, RequestOptions requestOptions) throws WarrantException {
        deleteWarrant(new PricingTier(pricingTierId), "member", new WarrantSubject(Tenant.OBJECT_TYPE, tenantId), requestOptions);
    }

    public void removeFeatureFromTenant(Feature feature, Tenant tenant) throws WarrantException {
        removeFeatureFromTenant(feature.getFeatureId(), tenant.getTenantId(), new RequestOptions());
    }

    public void removeFeatureFromTenant(Feature feature, Tenant tenant, RequestOptions requestOptions) throws WarrantException {
        removeFeatureFromTenant(feature.getFeatureId(), tenant.getTenantId(), requestOptions);
    }

    public void removeFeatureFromTenant(String featureId, String tenantId) throws WarrantException {
        removeFeatureFromTenant(featureId, tenantId, new RequestOptions());
    }

    public void removeFeatureFromTenant(String featureId, String tenantId, RequestOptions requestOptions) throws WarrantException {
        deleteWarrant(new Feature(featureId), "member", new WarrantSubject(Tenant.OBJECT_TYPE, tenantId), requestOptions);
    }

    public void removeFeatureFromPricingTier(Feature feature, PricingTier pricingTier) throws WarrantException {
        removeFeatureFromPricingTier(feature.getFeatureId(), pricingTier.getPricingTierId(), new RequestOptions());
    }

    public void removeFeatureFromPricingTier(Feature feature, PricingTier pricingTier, RequestOptions requestOptions) throws WarrantException {
        removeFeatureFromPricingTier(feature.getFeatureId(), pricingTier.getPricingTierId(), requestOptions);
    }

    public void removeFeatureFromPricingTier(String featureId, String pricingTierId) throws WarrantException {
        removeFeatureFromPricingTier(featureId, pricingTierId, new RequestOptions());
    }

    public void removeFeatureFromPricingTier(String featureId, String pricingTierId, RequestOptions requestOptions) throws WarrantException {
        deleteWarrant(new Feature(featureId), "member", new WarrantSubject(PricingTier.OBJECT_TYPE, pricingTierId), requestOptions);
    }

    public void removePermissionFromRole(Permission permission, Role role) throws WarrantException {
        removePermissionFromRole(permission.getPermissionId(), role.getRoleId(), new RequestOptions());
    }

    public void removePermissionFromRole(Permission permission, Role role, RequestOptions requestOptions) throws WarrantException {
        removePermissionFromRole(permission.getPermissionId(), role.getRoleId(), requestOptions);
    }

    public void removePermissionFromRole(String permissionId, String roleId) throws WarrantException {
        removePermissionFromRole(permissionId, roleId, new RequestOptions());
    }

    public void removePermissionFromRole(String permissionId, String roleId, RequestOptions requestOptions) throws WarrantException {
        deleteWarrant(new Permission(permissionId), "member", new WarrantSubject(Role.OBJECT_TYPE, roleId), requestOptions);
    }

    // Checks
    public boolean checkUserHasPermission(User user, String permissionId) throws WarrantException {
        return checkUserHasPermission(user.getUserId(), permissionId, new RequestOptions());
    }

    public boolean checkUserHasPermission(User user, String permissionId, RequestOptions requestOptions) throws WarrantException {
        return checkUserHasPermission(user.getUserId(), permissionId, requestOptions);
    }

    public boolean checkUserHasPermission(String userId, String permissionId) throws WarrantException {
        return checkUserHasPermission(userId, permissionId, new RequestOptions());
    }

    public boolean checkUserHasPermission(String userId, String permissionId, RequestOptions requestOptions) throws WarrantException {
        Permission perm = new Permission();
        perm.setPermissionId(permissionId);
        WarrantSubject subject = new WarrantSubject(User.OBJECT_TYPE, userId);
        return check(perm, "member", subject, requestOptions);
    }

    public boolean checkTenantHasFeature(Tenant tenant, String featureId) throws WarrantException {
        return checkTenantHasFeature(tenant.getTenantId(), featureId, new RequestOptions());
    }

    public boolean checkTenantHasFeature(Tenant tenant, String featureId, RequestOptions requestOptions) throws WarrantException {
        return checkTenantHasFeature(tenant.getTenantId(), featureId, requestOptions);
    }

    public boolean checkTenantHasFeature(String tenantId, String featureId) throws WarrantException {
        return checkTenantHasFeature(tenantId, featureId, new RequestOptions());
    }

    public boolean checkTenantHasFeature(String tenantId, String featureId, RequestOptions requestOptions) throws WarrantException {
        Feature feature = new Feature();
        feature.setFeatureId(featureId);
        WarrantSubject subject = new WarrantSubject(Tenant.OBJECT_TYPE, tenantId);
        return check(feature, "member", subject, requestOptions);
    }

    public boolean checkUserHasFeature(User user, String featureId) throws WarrantException {
        return checkUserHasFeature(user.getUserId(), featureId, new RequestOptions());
    }

    public boolean checkUserHasFeature(User user, String featureId, RequestOptions requestOptions) throws WarrantException {
        return checkUserHasFeature(user.getUserId(), featureId, requestOptions);
    }

    public boolean checkUserHasFeature(String userId, String featureId) throws WarrantException {
        return checkUserHasFeature(userId, featureId, new RequestOptions());
    }

    public boolean checkUserHasFeature(String userId, String featureId, RequestOptions requestOptions) throws WarrantException {
        Feature feature = new Feature();
        feature.setFeatureId(featureId);
        WarrantSubject subject = new WarrantSubject(User.OBJECT_TYPE, userId);
        return check(feature, "member", subject, requestOptions);
    }
}
