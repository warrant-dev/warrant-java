package dev.warrant;

import java.net.http.HttpClient;
import java.util.Collections;

import dev.warrant.exception.WarrantException;
import dev.warrant.model.WarrantSubject;
import dev.warrant.model.object.Feature;
import dev.warrant.model.object.Permission;
import dev.warrant.model.object.PricingTier;
import dev.warrant.model.object.Role;
import dev.warrant.model.object.Tenant;
import dev.warrant.model.object.User;
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
        return makePostRequest("/v1/users", Collections.EMPTY_MAP, User.class, requestOptions.asMap());
    }

    public User createUser(User user) throws WarrantException {
        return createUser(user, new RequestOptions());
    }

    public User createUser(User user, RequestOptions requestOptions) throws WarrantException {
        return makePostRequest("/v1/users", user, User.class, requestOptions.asMap());
    }

    public User[] createUsers(User[] users) throws WarrantException {
        return createUsers(users, new RequestOptions());
    }

    public User[] createUsers(User[] users, RequestOptions requestOptions) throws WarrantException {
        return makePostRequest("/v1/users", users, User[].class, requestOptions.asMap());
    }

    public User updateUser(String userId, User toUpdate) throws WarrantException {
        return updateUser(userId, toUpdate, new RequestOptions());
    }

    public User updateUser(String userId, User toUpdate, RequestOptions requestOptions) throws WarrantException {
        return makePutRequest("/v1/users/" + userId, toUpdate, User.class, requestOptions.asMap());
    }

    public void deleteUser(User user) throws WarrantException {
        deleteUser(user.getUserId(), new RequestOptions());
    }

    public void deleteUser(String userId) throws WarrantException {
        deleteUser(userId, new RequestOptions());
    }

    public void deleteUser(String userId, RequestOptions requestOptions) throws WarrantException {
        makeDeleteRequest("/v1/users/" + userId, requestOptions.asMap());
    }

    public User getUser(String userId) throws WarrantException {
        return getUser(userId, new RequestOptions());
    }

    public User getUser(String userId, RequestOptions requestOptions) throws WarrantException {
        return makeGetRequest("/v1/users/" + userId, User.class, requestOptions.asMap());
    }

    public User[] listUsers(int limit, int page) throws WarrantException {
        return listUsers(new ListParams().withLimit(limit).withPage(page), new RequestOptions());
    }

    public User[] listUsers(int limit, int page, RequestOptions requestOptions) throws WarrantException {
        return listUsers(new ListParams().withLimit(limit).withPage(page), requestOptions);
    }

    public User[] listUsers(ListParams listParams) throws WarrantException {
        return listUsers(listParams, new RequestOptions());
    }

    public User[] listUsers(ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        return makeGetRequest("/v1/users", listParams.asMap(), User[].class, requestOptions.asMap());
    }

    public User[] listUsersForTenant(Tenant tenant, int limit, int page) throws WarrantException {
        return listUsersForTenant(tenant.getTenantId(), new ListParams().withLimit(limit).withPage(page), new RequestOptions());
    }

    public User[] listUsersForTenant(Tenant tenant, int limit, int page, RequestOptions requestOptions) throws WarrantException {
        return listUsersForTenant(tenant.getTenantId(), new ListParams().withLimit(limit).withPage(page), requestOptions);
    }

    public User[] listUsersForTenant(Tenant tenant, ListParams listParams) throws WarrantException {
        return listUsersForTenant(tenant.getTenantId(), listParams, new RequestOptions());
    }

    public User[] listUsersForTenant(Tenant tenant, ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        return listUsersForTenant(tenant.getTenantId(), listParams, requestOptions);
    }

    public User[] listUsersForTenant(String tenantId, int limit, int page) throws WarrantException {
        return listUsersForTenant(tenantId, new ListParams().withLimit(limit).withPage(page), new RequestOptions());
    }

    public User[] listUsersForTenant(String tenantId, int limit, int page, RequestOptions requestOptions) throws WarrantException {
        return listUsersForTenant(tenantId, new ListParams().withLimit(limit).withPage(page), requestOptions);
    }

    public User[] listUsersForTenant(String tenantId, ListParams listParams) throws WarrantException {
        return listUsersForTenant(tenantId, listParams, new RequestOptions());
    }

    public User[] listUsersForTenant(String tenantId, ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        return makeGetRequest("/v1/tenants/" + tenantId + "/users", listParams.asMap(), User[].class, requestOptions.asMap());
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
        return makePostRequest("/v1/tenants", tenant, Tenant.class, requestOptions.asMap());
    }

    public Tenant[] createTenants(Tenant[] tenants) throws WarrantException {
        return createTenants(tenants, new RequestOptions());
    }

    public Tenant[] createTenants(Tenant[] tenants, RequestOptions requestOptions) throws WarrantException {
        return makePostRequest("/v1/tenants", tenants, Tenant[].class, requestOptions.asMap());
    }

    public Tenant updateTenant(String tenantId, Tenant toUpdate) throws WarrantException {
        return updateTenant(tenantId, toUpdate, new RequestOptions());
    }

    public Tenant updateTenant(String tenantId, Tenant toUpdate, RequestOptions requestOptions) throws WarrantException {
        return makePutRequest("/v1/tenants/" + tenantId, toUpdate, Tenant.class, requestOptions.asMap());
    }

    public void deleteTenant(Tenant tenant) throws WarrantException {
        deleteTenant(tenant.getTenantId(), new RequestOptions());
    }

    public void deleteTenant(Tenant tenant, RequestOptions requestOptions) throws WarrantException {
        deleteTenant(tenant.getTenantId(), requestOptions);
    }

    public void deleteTenant(String tenantId) throws WarrantException {
        deleteTenant(tenantId, new RequestOptions());
    }

    public void deleteTenant(String tenantId, RequestOptions requestOptions) throws WarrantException {
        makeDeleteRequest("/v1/tenants/" + tenantId, requestOptions.asMap());
    }

    public Tenant getTenant(String tenantId) throws WarrantException {
        return getTenant(tenantId, new RequestOptions());
    }

    public Tenant getTenant(String tenantId, RequestOptions requestOptions) throws WarrantException {
        return makeGetRequest("/v1/tenants/" + tenantId, Tenant.class, requestOptions.asMap());
    }

    public Tenant[] listTenants(int limit, int page) throws WarrantException {
        return listTenants(new ListParams().withLimit(limit).withPage(page), new RequestOptions());
    }

    public Tenant[] listTenants(int limit, int page, RequestOptions requestOptions) throws WarrantException {
        return listTenants(new ListParams().withLimit(limit).withPage(page), requestOptions);
    }

    public Tenant[] listTenants(ListParams listParams) throws WarrantException {
        return listTenants(listParams, new RequestOptions());
    }

    public Tenant[] listTenants(ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        return makeGetRequest("/v1/tenants", listParams.asMap(), Tenant[].class, requestOptions.asMap());
    }

    public Tenant[] listTenantsForUser(User user, int limit, int page) throws WarrantException {
        return listTenantsForUser(user.getUserId(), new ListParams().withLimit(limit).withPage(page), new RequestOptions());
    }

    public Tenant[] listTenantsForUser(User user, int limit, int page, RequestOptions requestOptions) throws WarrantException {
        return listTenantsForUser(user.getUserId(), new ListParams().withLimit(limit).withPage(page), requestOptions);
    }

    public Tenant[] listTenantsForUser(User user, ListParams listParams) throws WarrantException {
        return listTenantsForUser(user.getUserId(), listParams, new RequestOptions());
    }

    public Tenant[] listTenantsForUser(User user, ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        return listTenantsForUser(user.getUserId(), listParams, requestOptions);
    }

    public Tenant[] listTenantsForUser(String userId, int limit, int page) throws WarrantException {
        return listTenantsForUser(userId, new ListParams().withLimit(limit).withPage(page), new RequestOptions());
    }

    public Tenant[] listTenantsForUser(String userId, int limit, int page, RequestOptions requestOptions) throws WarrantException {
        return listTenantsForUser(userId, new ListParams().withLimit(limit).withPage(page), requestOptions);
    }

    public Tenant[] listTenantsForUser(String userId, ListParams listParams) throws WarrantException {
        return listTenantsForUser(userId, listParams, new RequestOptions());
    }

    public Tenant[] listTenantsForUser(String userId, ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        return makeGetRequest("/v1/users/" + userId + "/tenants", listParams.asMap(), Tenant[].class, requestOptions.asMap());
    }

    // Roles
    public Role createRole(Role role) throws WarrantException {
        return createRole(role, new RequestOptions());
    }

    public Role createRole(Role role, RequestOptions requestOptions) throws WarrantException {
        return makePostRequest("/v1/roles", role, Role.class, requestOptions.asMap());
    }

    public Role updateRole(String roleId, Role toUpdate) throws WarrantException {
        return updateRole(roleId, toUpdate, new RequestOptions());
    }

    public Role updateRole(String roleId, Role toUpdate, RequestOptions requestOptions) throws WarrantException {
        return makePutRequest("/v1/roles/" + roleId, toUpdate, Role.class, requestOptions.asMap());
    }

    public void deleteRole(Role role) throws WarrantException {
        deleteRole(role.getRoleId(), new RequestOptions());
    }

    public void deleteRole(Role role, RequestOptions requestOptions) throws WarrantException {
        deleteRole(role.getRoleId(), requestOptions);
    }

    public void deleteRole(String roleId) throws WarrantException {
        deleteRole(roleId, new RequestOptions());
    }

    public void deleteRole(String roleId, RequestOptions requestOptions) throws WarrantException {
        makeDeleteRequest("/v1/roles/" + roleId, requestOptions.asMap());
    }

    public Role getRole(String roleId) throws WarrantException {
        return getRole(roleId, new RequestOptions());
    }

    public Role getRole(String roleId, RequestOptions requestOptions) throws WarrantException {
        return makeGetRequest("/v1/roles/" + roleId, Role.class, requestOptions.asMap());
    }

    public Role[] listRoles(int limit, int page) throws WarrantException {
        return listRoles(new ListParams().withLimit(limit).withPage(page), new RequestOptions());
    }

    public Role[] listRoles(int limit, int page, RequestOptions requestOptions) throws WarrantException {
        return listRoles(new ListParams().withLimit(limit).withPage(page), requestOptions);
    }

    public Role[] listRoles(ListParams listParams) throws WarrantException {
        return listRoles(listParams, new RequestOptions());
    }

    public Role[] listRoles(ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        return makeGetRequest("/v1/roles", listParams.asMap(), Role[].class, requestOptions.asMap());
    }

    public Role[] listRolesForUser(User user, int limit, int page) throws WarrantException {
        return listRolesForUser(user.getUserId(), new ListParams().withLimit(limit).withPage(page), new RequestOptions());
    }

    public Role[] listRolesForUser(User user, int limit, int page, RequestOptions requestOptions) throws WarrantException {
        return listRolesForUser(user.getUserId(), new ListParams().withLimit(limit).withPage(page), requestOptions);
    }

    public Role[] listRolesForUser(User user, ListParams listParams) throws WarrantException {
        return listRolesForUser(user.getUserId(), listParams, new RequestOptions());
    }

    public Role[] listRolesForUser(User user, ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        return listRolesForUser(user.getUserId(), listParams, requestOptions);
    }

    public Role[] listRolesForUser(String userId, int limit, int page) throws WarrantException {
        return listRolesForUser(userId, new ListParams().withLimit(limit).withPage(page), new RequestOptions());
    }

    public Role[] listRolesForUser(String userId, int limit, int page, RequestOptions requestOptions) throws WarrantException {
        return listRolesForUser(userId, new ListParams().withLimit(limit).withPage(page), requestOptions);
    }

    public Role[] listRolesForUser(String userId, ListParams listParams) throws WarrantException {
        return listRolesForUser(userId, listParams, new RequestOptions());
    }

    public Role[] listRolesForUser(String userId, ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        return makeGetRequest("/v1/users/" + userId + "/roles", listParams.asMap(), Role[].class, requestOptions.asMap());
    }

    // Permissions
    public Permission createPermission(Permission permission) throws WarrantException {
        return createPermission(permission, new RequestOptions());
    }

    public Permission createPermission(Permission permission, RequestOptions requestOptions) throws WarrantException {
        return makePostRequest("/v1/permissions", permission, Permission.class, requestOptions.asMap());
    }

    public Permission updatePermission(String permissionId, Permission toUpdate) throws WarrantException {
        return updatePermission(permissionId, toUpdate, new RequestOptions());
    }

    public Permission updatePermission(String permissionId, Permission toUpdate, RequestOptions requestOptions) throws WarrantException {
        return makePutRequest("/v1/permissions/" + permissionId, toUpdate, Permission.class, requestOptions.asMap());
    }

    public void deletePermission(Permission permission) throws WarrantException {
        deletePermission(permission.getPermissionId(), new RequestOptions());
    }

    public void deletePermission(Permission permission, RequestOptions requestOptions) throws WarrantException {
        deletePermission(permission.getPermissionId(), requestOptions);
    }

    public void deletePermission(String permissionId) throws WarrantException {
        deletePermission(permissionId, new RequestOptions());
    }

    public void deletePermission(String permissionId, RequestOptions requestOptions) throws WarrantException {
        makeDeleteRequest("/v1/permissions/" + permissionId, requestOptions.asMap());
    }

    public Permission getPermission(String permissionId) throws WarrantException {
        return getPermission(permissionId, new RequestOptions());
    }

    public Permission getPermission(String permissionId, RequestOptions requestOptions) throws WarrantException {
        return makeGetRequest("/v1/permissions/" + permissionId, Permission.class, requestOptions.asMap());
    }

    public Permission[] listPermissions(int limit, int page) throws WarrantException {
        return listPermissions(new ListParams().withLimit(limit).withPage(page), new RequestOptions());
    }

    public Permission[] listPermissions(int limit, int page, RequestOptions requestOptions) throws WarrantException {
        return listPermissions(new ListParams().withLimit(limit).withPage(page), requestOptions);
    }

    public Permission[] listPermissions(ListParams listParams) throws WarrantException {
        return listPermissions(listParams, new RequestOptions());
    }

    public Permission[] listPermissions(ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        return makeGetRequest("/v1/permissions", listParams.asMap(), Permission[].class, requestOptions.asMap());
    }

    public Permission[] listPermissionsForUser(User user, int limit, int page) throws WarrantException {
        return listPermissionsForUser(user.getUserId(), new ListParams().withLimit(limit).withPage(page), new RequestOptions());
    }

    public Permission[] listPermissionsForUser(User user, int limit, int page, RequestOptions requestOptions) throws WarrantException {
        return listPermissionsForUser(user.getUserId(), new ListParams().withLimit(limit).withPage(page), requestOptions);
    }

    public Permission[] listPermissionsForUser(User user, ListParams listParams) throws WarrantException {
        return listPermissionsForUser(user.getUserId(), listParams, new RequestOptions());
    }

    public Permission[] listPermissionsForUser(User user, ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        return listPermissionsForUser(user.getUserId(), listParams, requestOptions);
    }

    public Permission[] listPermissionsForUser(String userId, int limit, int page) throws WarrantException {
        return listPermissionsForUser(userId, new ListParams().withLimit(limit).withPage(page), new RequestOptions());
    }

    public Permission[] listPermissionsForUser(String userId, int limit, int page, RequestOptions requestOptions) throws WarrantException {
        return listPermissionsForUser(userId, new ListParams().withLimit(limit).withPage(page), requestOptions);
    }

    public Permission[] listPermissionsForUser(String userId, ListParams listParams) throws WarrantException {
        return listPermissionsForUser(userId, listParams, new RequestOptions());
    }

    public Permission[] listPermissionsForUser(String userId, ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        return makeGetRequest("/v1/users/" + userId + "/permissions", listParams.asMap(), Permission[].class, requestOptions.asMap());
    }

    public Permission[] listPermissionsForRole(Role role, int limit, int page) throws WarrantException {
        return listPermissionsForRole(role.getRoleId(), new ListParams().withLimit(limit).withPage(page), new RequestOptions());
    }

    public Permission[] listPermissionsForRole(Role role, int limit, int page, RequestOptions requestOptions) throws WarrantException {
        return listPermissionsForRole(role.getRoleId(), new ListParams().withLimit(limit).withPage(page), requestOptions);
    }

    public Permission[] listPermissionsForRole(Role role, ListParams listParams) throws WarrantException {
        return listPermissionsForRole(role.getRoleId(), listParams, new RequestOptions());
    }

    public Permission[] listPermissionsForRole(Role role, ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        return listPermissionsForRole(role.getRoleId(), listParams, requestOptions);
    }

    public Permission[] listPermissionsForRole(String roleId, int limit, int page) throws WarrantException {
        return listPermissionsForRole(roleId, new ListParams().withLimit(limit).withPage(page), new RequestOptions());
    }

    public Permission[] listPermissionsForRole(String roleId, int limit, int page, RequestOptions requestOptions) throws WarrantException {
        return listPermissionsForRole(roleId, new ListParams().withLimit(limit).withPage(page), requestOptions);
    }

    public Permission[] listPermissionsForRole(String roleId, ListParams listParams) throws WarrantException {
        return listPermissionsForRole(roleId, listParams, new RequestOptions());
    }

    public Permission[] listPermissionsForRole(String roleId, ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        return makeGetRequest("/v1/roles/" + roleId + "/permissions", listParams.asMap(), Permission[].class, requestOptions.asMap());
    }

    // Features
    public Feature createFeature(Feature feature) throws WarrantException {
        return createFeature(feature, new RequestOptions());
    }

    public Feature createFeature(Feature feature, RequestOptions requestOptions) throws WarrantException {
        return makePostRequest("/v1/features", feature, Feature.class, requestOptions.asMap());
    }

    public void deleteFeature(Feature feature) throws WarrantException {
        deleteFeature(feature.getFeatureId(), new RequestOptions());
    }

    public void deleteFeature(Feature feature, RequestOptions requestOptions) throws WarrantException {
        deleteFeature(feature.getFeatureId(), requestOptions);
    }

    public void deleteFeature(String featureId) throws WarrantException {
        deleteFeature(featureId, new RequestOptions());
    }

    public void deleteFeature(String featureId, RequestOptions requestOptions) throws WarrantException {
        makeDeleteRequest("/v1/features/" + featureId, requestOptions.asMap());
    }

    public Feature getFeature(String featureId) throws WarrantException {
        return getFeature(featureId, new RequestOptions());
    }

    public Feature getFeature(String featureId, RequestOptions requestOptions) throws WarrantException {
        return makeGetRequest("/v1/features/" + featureId, Feature.class, requestOptions.asMap());
    }

    public Feature[] listFeatures(int limit, int page) throws WarrantException {
        return listFeatures(new ListParams().withLimit(limit).withPage(page), new RequestOptions());
    }

    public Feature[] listFeatures(int limit, int page, RequestOptions requestOptions) throws WarrantException {
        return listFeatures(new ListParams().withLimit(limit).withPage(page), requestOptions);
    }

    public Feature[] listFeatures(ListParams listParams) throws WarrantException {
        return listFeatures(listParams, new RequestOptions());
    }

    public Feature[] listFeatures(ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        return makeGetRequest("/v1/features", listParams.asMap(), Feature[].class, requestOptions.asMap());
    }

    public Feature[] listFeaturesForUser(User user, int limit, int page) throws WarrantException {
        return listFeaturesForUser(user.getUserId(), new ListParams().withLimit(limit).withPage(page), new RequestOptions());
    }

    public Feature[] listFeaturesForUser(User user, int limit, int page, RequestOptions requestOptions) throws WarrantException {
        return listFeaturesForUser(user.getUserId(), new ListParams().withLimit(limit).withPage(page), requestOptions);
    }

    public Feature[] listFeaturesForUser(User user, ListParams listParams) throws WarrantException {
        return listFeaturesForUser(user.getUserId(), listParams, new RequestOptions());
    }

    public Feature[] listFeaturesForUser(User user, ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        return listFeaturesForUser(user.getUserId(), listParams, requestOptions);
    }

    public Feature[] listFeaturesForUser(String userId, int limit, int page) throws WarrantException {
        return listFeaturesForUser(userId, new ListParams().withLimit(limit).withPage(page), new RequestOptions());
    }

    public Feature[] listFeaturesForUser(String userId, int limit, int page, RequestOptions requestOptions) throws WarrantException {
        return listFeaturesForUser(userId, new ListParams().withLimit(limit).withPage(page), requestOptions);
    }

    public Feature[] listFeaturesForUser(String userId, ListParams listParams) throws WarrantException {
        return listFeaturesForUser(userId, listParams, new RequestOptions());
    }

    public Feature[] listFeaturesForUser(String userId, ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        return makeGetRequest("/v1/users/" + userId + "/features", listParams.asMap(), Feature[].class, requestOptions.asMap());
    }

    public Feature[] listFeaturesForTenant(Tenant tenant, int limit, int page) throws WarrantException {
        return listFeaturesForTenant(tenant.getTenantId(), new ListParams().withLimit(limit).withPage(page), new RequestOptions());
    }

    public Feature[] listFeaturesForTenant(Tenant tenant, int limit, int page, RequestOptions requestOptions) throws WarrantException {
        return listFeaturesForTenant(tenant.getTenantId(), new ListParams().withLimit(limit).withPage(page), requestOptions);
    }

    public Feature[] listFeaturesForTenant(Tenant tenant, ListParams listParams) throws WarrantException {
        return listFeaturesForTenant(tenant.getTenantId(), listParams, new RequestOptions());
    }

    public Feature[] listFeaturesForTenant(Tenant tenant, ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        return listFeaturesForTenant(tenant.getTenantId(), listParams, requestOptions);
    }

    public Feature[] listFeaturesForTenant(String tenantId, int limit, int page) throws WarrantException {
        return listFeaturesForTenant(tenantId, new ListParams().withLimit(limit).withPage(page), new RequestOptions());
    }

    public Feature[] listFeaturesForTenant(String tenantId, int limit, int page, RequestOptions requestOptions) throws WarrantException {
        return listFeaturesForTenant(tenantId, new ListParams().withLimit(limit).withPage(page), requestOptions);
    }

    public Feature[] listFeaturesForTenant(String tenantId, ListParams listParams) throws WarrantException {
        return listFeaturesForTenant(tenantId, listParams, new RequestOptions());
    }

    public Feature[] listFeaturesForTenant(String tenantId, ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        return makeGetRequest("/v1/tenants/" + tenantId + "/features", listParams.asMap(), Feature[].class, requestOptions.asMap());
    }

    public Feature[] listFeaturesForPricingTier(PricingTier pricingTier, int limit, int page) throws WarrantException {
        return listFeaturesForPricingTier(pricingTier.getPricingTierId(), new ListParams().withLimit(limit).withPage(page), new RequestOptions());
    }

    public Feature[] listFeaturesForPricingTier(PricingTier pricingTier, int limit, int page, RequestOptions requestOptions) throws WarrantException {
        return listFeaturesForPricingTier(pricingTier.getPricingTierId(), new ListParams().withLimit(limit).withPage(page), requestOptions);
    }

    public Feature[] listFeaturesForPricingTier(PricingTier pricingTier, ListParams listParams) throws WarrantException {
        return listFeaturesForPricingTier(pricingTier.getPricingTierId(), listParams, new RequestOptions());
    }

    public Feature[] listFeaturesForPricingTier(PricingTier pricingTier, ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        return listFeaturesForPricingTier(pricingTier.getPricingTierId(), listParams, requestOptions);
    }

    public Feature[] listFeaturesForPricingTier(String pricingTierId, int limit, int page) throws WarrantException {
        return listFeaturesForPricingTier(pricingTierId, new ListParams().withLimit(limit).withPage(page), new RequestOptions());
    }

    public Feature[] listFeaturesForPricingTier(String pricingTierId, int limit, int page, RequestOptions requestOptions) throws WarrantException {
        return listFeaturesForPricingTier(pricingTierId, new ListParams().withLimit(limit).withPage(page), requestOptions);
    }

    public Feature[] listFeaturesForPricingTier(String pricingTierId, ListParams listParams) throws WarrantException {
        return listFeaturesForPricingTier(pricingTierId, listParams, new RequestOptions());
    }

    public Feature[] listFeaturesForPricingTier(String pricingTierId, ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        return makeGetRequest("/v1/pricing-tiers/" + pricingTierId + "/features", listParams.asMap(), Feature[].class, requestOptions.asMap());
    }

    // Pricing Tiers
    public PricingTier createPricingTier(PricingTier pricingTier) throws WarrantException {
        return createPricingTier(pricingTier, new RequestOptions());
    }

    public PricingTier createPricingTier(PricingTier pricingTier, RequestOptions requestOptions) throws WarrantException {
        return makePostRequest("/v1/pricing-tiers", pricingTier, PricingTier.class, requestOptions.asMap());
    }

    public void deletePricingTier(PricingTier pricingTier) throws WarrantException {
        deletePricingTier(pricingTier.getPricingTierId(), new RequestOptions());
    }

    public void deletePricingTier(PricingTier pricingTier, RequestOptions requestOptions) throws WarrantException {
        deletePricingTier(pricingTier.getPricingTierId(), requestOptions);
    }

    public void deletePricingTier(String pricingTierId) throws WarrantException {
        deletePricingTier(pricingTierId, new RequestOptions());
    }

    public void deletePricingTier(String pricingTierId, RequestOptions requestOptions) throws WarrantException {
        makeDeleteRequest("/v1/pricing-tiers/" + pricingTierId, requestOptions.asMap());
    }

    public PricingTier getPricingTier(String pricingTierId) throws WarrantException {
        return getPricingTier(pricingTierId, new RequestOptions());
    }

    public PricingTier getPricingTier(String pricingTierId, RequestOptions requestOptions) throws WarrantException {
        return makeGetRequest("/v1/pricing-tiers/" + pricingTierId, PricingTier.class, requestOptions.asMap());
    }

    public PricingTier[] listPricingTiers(int limit, int page) throws WarrantException {
        return listPricingTiers(new ListParams().withLimit(limit).withPage(page), new RequestOptions());
    }

    public PricingTier[] listPricingTiers(int limit, int page, RequestOptions requestOptions) throws WarrantException {
        return listPricingTiers(new ListParams().withLimit(limit).withPage(page), requestOptions);
    }

    public PricingTier[] listPricingTiers(ListParams listParams) throws WarrantException {
        return listPricingTiers(listParams, new RequestOptions());
    }

    public PricingTier[] listPricingTiers(ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        return makeGetRequest("/v1/pricing-tiers", listParams.asMap(), PricingTier[].class, requestOptions.asMap());
    }

    public PricingTier[] listPricingTiersForTenant(Tenant tenant, int limit, int page) throws WarrantException {
        return listPricingTiersForTenant(tenant.getTenantId(), new ListParams().withLimit(limit).withPage(page), new RequestOptions());
    }

    public PricingTier[] listPricingTiersForTenant(Tenant tenant, int limit, int page, RequestOptions requestOptions) throws WarrantException {
        return listPricingTiersForTenant(tenant.getTenantId(), new ListParams().withLimit(limit).withPage(page), requestOptions);
    }

    public PricingTier[] listPricingTiersForTenant(Tenant tenant, ListParams listParams) throws WarrantException {
        return listPricingTiersForTenant(tenant.getTenantId(), listParams, new RequestOptions());
    }

    public PricingTier[] listPricingTiersForTenant(Tenant tenant, ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        return listPricingTiersForTenant(tenant.getTenantId(), listParams, requestOptions);
    }

    public PricingTier[] listPricingTiersForTenant(String tenantId, int limit, int page) throws WarrantException {
        return listPricingTiersForTenant(tenantId, new ListParams().withLimit(limit).withPage(page), new RequestOptions());
    }

    public PricingTier[] listPricingTiersForTenant(String tenantId, int limit, int page, RequestOptions requestOptions) throws WarrantException {
        return listPricingTiersForTenant(tenantId, new ListParams().withLimit(limit).withPage(page), requestOptions);
    }

    public PricingTier[] listPricingTiersForTenant(String tenantId, ListParams listParams) throws WarrantException {
        return listPricingTiersForTenant(tenantId, listParams, new RequestOptions());
    }

    public PricingTier[] listPricingTiersForTenant(String tenantId, ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        return makeGetRequest("/v1/tenants/" + tenantId + "/pricing-tiers", listParams.asMap(), PricingTier[].class, requestOptions.asMap());
    }

    public PricingTier[] listPricingTiersForUser(User user, int limit, int page) throws WarrantException {
        return listPricingTiersForUser(user.getUserId(), new ListParams().withLimit(limit).withPage(page), new RequestOptions());
    }

    public PricingTier[] listPricingTiersForUser(User user, int limit, int page, RequestOptions requestOptions) throws WarrantException {
        return listPricingTiersForUser(user.getUserId(), new ListParams().withLimit(limit).withPage(page), requestOptions);
    }

    public PricingTier[] listPricingTiersForUser(User user, ListParams listParams) throws WarrantException {
        return listPricingTiersForUser(user.getUserId(), listParams, new RequestOptions());
    }

    public PricingTier[] listPricingTiersForUser(User user, ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        return listPricingTiersForUser(user.getUserId(), listParams, requestOptions);
    }

    public PricingTier[] listPricingTiersForUser(String userId, int limit, int page) throws WarrantException {
        return listPricingTiersForUser(userId, new ListParams().withLimit(limit).withPage(page), new RequestOptions());
    }

    public PricingTier[] listPricingTiersForUser(String userId, int limit, int page, RequestOptions requestOptions) throws WarrantException {
        return listPricingTiersForUser(userId, new ListParams().withLimit(limit).withPage(page), requestOptions);
    }

    public PricingTier[] listPricingTiersForUser(String userId, ListParams listParams) throws WarrantException {
        return listPricingTiersForUser(userId, listParams, new RequestOptions());
    }

    public PricingTier[] listPricingTiersForUser(String userId, ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        return makeGetRequest("/v1/users/" + userId + "/pricing-tiers", listParams.asMap(), PricingTier[].class, requestOptions.asMap());
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
