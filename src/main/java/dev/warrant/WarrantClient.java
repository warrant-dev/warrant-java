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
        return makePostRequest("/v1/users", Collections.EMPTY_MAP, User.class);
    }

    public User createUser(RequestOptions requestOptions) throws WarrantException {
        return makePostRequest("/v1/users", Collections.EMPTY_MAP, User.class, requestOptions.asMap());
    }

    public User createUser(User user) throws WarrantException {
        return makePostRequest("/v1/users", user, User.class);
    }

    public User createUser(User user, RequestOptions requestOptions) throws WarrantException {
        return makePostRequest("/v1/users", user, User.class, requestOptions.asMap());
    }

    public User[] createUsers(User[] users) throws WarrantException {
        return makePostRequest("/v1/users", users, User[].class);
    }

    public User[] createUsers(User[] users, RequestOptions requestOptions) throws WarrantException {
        return makePostRequest("/v1/users", users, User[].class, requestOptions.asMap());
    }

    public User updateUser(String userId, User toUpdate) throws WarrantException {
        return makePutRequest("/v1/users/" + userId, toUpdate, User.class);
    }

    public User updateUser(String userId, User toUpdate, RequestOptions requestOptions) throws WarrantException {
        return makePutRequest("/v1/users/" + userId, toUpdate, User.class, requestOptions.asMap());
    }

    public void deleteUser(User user) throws WarrantException {
        deleteUser(user.getUserId());
    }

    public void deleteUser(String userId) throws WarrantException {
        makeDeleteRequest("/v1/users/" + userId);
    }

    public void deleteUser(String userId, RequestOptions requestOptions) throws WarrantException {
        makeDeleteRequest("/v1/users/" + userId, requestOptions.asMap());
    }

    public User getUser(String userId) throws WarrantException {
        return makeGetRequest("/v1/users/" + userId, User.class);
    }

    public User getUser(String userId, RequestOptions requestOptions) throws WarrantException {
        return makeGetRequest("/v1/users/" + userId, User.class, requestOptions.asMap());
    }

    public User[] listUsers(int limit, int page) throws WarrantException {
        return makeGetRequest("/v1/users", getPaginationParams(limit, page), User[].class);
    }

    public User[] listUsers(int limit, int page, RequestOptions requestOptions) throws WarrantException {
        return makeGetRequest("/v1/users", getPaginationParams(limit, page), User[].class, requestOptions.asMap());
    }

    public User[] listUsers(ListParams listParams) throws WarrantException {
        return makeGetRequest("/v1/users", listParams.asMap(), User[].class);
    }

    public User[] listUsers(ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        return makeGetRequest("/v1/users", listParams.asMap(), User[].class, requestOptions.asMap());
    }

    public User[] listUsersForTenant(Tenant tenant, int limit, int page) throws WarrantException {
        return listUsersForTenant(tenant.getTenantId(), limit, page);
    }

    public User[] listUsersForTenant(Tenant tenant, int limit, int page, RequestOptions requestOptions) throws WarrantException {
        return listUsersForTenant(tenant.getTenantId(), limit, page, requestOptions);
    }

    public User[] listUsersForTenant(Tenant tenant, ListParams listParams) throws WarrantException {
        return listUsersForTenant(tenant.getTenantId(), listParams);
    }

    public User[] listUsersForTenant(Tenant tenant, ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        return listUsersForTenant(tenant.getTenantId(), listParams, requestOptions);
    }

    public User[] listUsersForTenant(String tenantId, int limit, int page) throws WarrantException {
        return makeGetRequest("/v1/tenants/" + tenantId + "/users", getPaginationParams(limit, page), User[].class);
    }

    public User[] listUsersForTenant(String tenantId, int limit, int page, RequestOptions requestOptions) throws WarrantException {
        return makeGetRequest("/v1/tenants/" + tenantId + "/users", getPaginationParams(limit, page), User[].class, requestOptions.asMap());
    }

    public User[] listUsersForTenant(String tenantId, ListParams listParams) throws WarrantException {
        return makeGetRequest("/v1/tenants/" + tenantId + "/users", listParams.asMap(), User[].class);
    }

    public User[] listUsersForTenant(String tenantId, ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        return makeGetRequest("/v1/tenants/" + tenantId + "/users", listParams.asMap(), User[].class, requestOptions.asMap());
    }

    // Tenants
    public Tenant createTenant() throws WarrantException {
        return makePostRequest("/v1/tenants", Collections.EMPTY_MAP, Tenant.class);
    }

    public Tenant createTenant(RequestOptions requestOptions) throws WarrantException {
        return makePostRequest("/v1/tenants", Collections.EMPTY_MAP, Tenant.class, requestOptions.asMap());
    }

    public Tenant createTenant(Tenant tenant) throws WarrantException {
        return makePostRequest("/v1/tenants", tenant, Tenant.class);
    }

    public Tenant createTenant(Tenant tenant, RequestOptions requestOptions) throws WarrantException {
        return makePostRequest("/v1/tenants", tenant, Tenant.class, requestOptions.asMap());
    }

    public Tenant[] createTenants(Tenant[] tenants) throws WarrantException {
        return makePostRequest("/v1/tenants", tenants, Tenant[].class);
    }

    public Tenant[] createTenants(Tenant[] tenants, RequestOptions requestOptions) throws WarrantException {
        return makePostRequest("/v1/tenants", tenants, Tenant[].class, requestOptions.asMap());
    }

    public Tenant updateTenant(String tenantId, Tenant toUpdate) throws WarrantException {
        return makePutRequest("/v1/tenants/" + tenantId, toUpdate, Tenant.class);
    }

    public Tenant updateTenant(String tenantId, Tenant toUpdate, RequestOptions requestOptions) throws WarrantException {
        return makePutRequest("/v1/tenants/" + tenantId, toUpdate, Tenant.class, requestOptions.asMap());
    }

    public void deleteTenant(Tenant tenant) throws WarrantException {
        deleteTenant(tenant.getTenantId());
    }

    public void deleteTenant(Tenant tenant, RequestOptions requestOptions) throws WarrantException {
        deleteTenant(tenant.getTenantId(), requestOptions);
    }

    public void deleteTenant(String tenantId) throws WarrantException {
        makeDeleteRequest("/v1/tenants/" + tenantId);
    }

    public void deleteTenant(String tenantId, RequestOptions requestOptions) throws WarrantException {
        makeDeleteRequest("/v1/tenants/" + tenantId, requestOptions.asMap());
    }

    public Tenant getTenant(String tenantId) throws WarrantException {
        return makeGetRequest("/v1/tenants/" + tenantId, Tenant.class);
    }

    public Tenant getTenant(String tenantId, RequestOptions requestOptions) throws WarrantException {
        return makeGetRequest("/v1/tenants/" + tenantId, Tenant.class, requestOptions.asMap());
    }

    public Tenant[] listTenants(int limit, int page) throws WarrantException {
        return makeGetRequest("/v1/tenants", getPaginationParams(limit, page), Tenant[].class);
    }

    public Tenant[] listTenants(int limit, int page, RequestOptions requestOptions) throws WarrantException {
        return makeGetRequest("/v1/tenants", getPaginationParams(limit, page), Tenant[].class, requestOptions.asMap());
    }

    public Tenant[] listTenants(ListParams listParams) throws WarrantException {
        return makeGetRequest("/v1/tenants", listParams.asMap(), Tenant[].class);
    }

    public Tenant[] listTenants(ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        return makeGetRequest("/v1/tenants", listParams.asMap(), Tenant[].class, requestOptions.asMap());
    }

    public Tenant[] listTenantsForUser(User user, int limit, int page) throws WarrantException {
        return listTenantsForUser(user.getUserId(), limit, page);
    }

    public Tenant[] listTenantsForUser(User user, int limit, int page, RequestOptions requestOptions) throws WarrantException {
        return listTenantsForUser(user.getUserId(), limit, page, requestOptions);
    }

    public Tenant[] listTenantsForUser(User user, ListParams listParams) throws WarrantException {
        return listTenantsForUser(user.getUserId(), listParams);
    }

    public Tenant[] listTenantsForUser(User user, ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        return listTenantsForUser(user.getUserId(), listParams, requestOptions);
    }

    public Tenant[] listTenantsForUser(String userId, int limit, int page) throws WarrantException {
        return makeGetRequest("/v1/users/" + userId + "/tenants", getPaginationParams(limit, page), Tenant[].class);
    }

    public Tenant[] listTenantsForUser(String userId, int limit, int page, RequestOptions requestOptions) throws WarrantException {
        return makeGetRequest("/v1/users/" + userId + "/tenants", getPaginationParams(limit, page), Tenant[].class, requestOptions.asMap());
    }

    public Tenant[] listTenantsForUser(String userId, ListParams listParams) throws WarrantException {
        return makeGetRequest("/v1/users/" + userId + "/tenants", listParams.asMap(), Tenant[].class);
    }

    public Tenant[] listTenantsForUser(String userId, ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        return makeGetRequest("/v1/users/" + userId + "/tenants", listParams.asMap(), Tenant[].class, requestOptions.asMap());
    }

    // Roles
    public Role createRole(Role role) throws WarrantException {
        return makePostRequest("/v1/roles", role, Role.class);
    }

    public Role createRole(Role role, RequestOptions requestOptions) throws WarrantException {
        return makePostRequest("/v1/roles", role, Role.class, requestOptions.asMap());
    }

    public Role updateRole(String roleId, Role toUpdate) throws WarrantException {
        return makePutRequest("/v1/roles/" + roleId, toUpdate, Role.class);
    }

    public Role updateRole(String roleId, Role toUpdate, RequestOptions requestOptions) throws WarrantException {
        return makePutRequest("/v1/roles/" + roleId, toUpdate, Role.class, requestOptions.asMap());
    }

    public void deleteRole(Role role) throws WarrantException {
        deleteRole(role.getRoleId());
    }

    public void deleteRole(Role role, RequestOptions requestOptions) throws WarrantException {
        deleteRole(role.getRoleId(), requestOptions);
    }

    public void deleteRole(String roleId) throws WarrantException {
        makeDeleteRequest("/v1/roles/" + roleId);
    }

    public void deleteRole(String roleId, RequestOptions requestOptions) throws WarrantException {
        makeDeleteRequest("/v1/roles/" + roleId, requestOptions.asMap());
    }

    public Role getRole(String roleId) throws WarrantException {
        return makeGetRequest("/v1/roles/" + roleId, Role.class);
    }

    public Role getRole(String roleId, RequestOptions requestOptions) throws WarrantException {
        return makeGetRequest("/v1/roles/" + roleId, Role.class, requestOptions.asMap());
    }

    public Role[] listRoles(int limit, int page) throws WarrantException {
        return makeGetRequest("/v1/roles", getPaginationParams(limit, page), Role[].class);
    }

    public Role[] listRoles(int limit, int page, RequestOptions requestOptions) throws WarrantException {
        return makeGetRequest("/v1/roles", getPaginationParams(limit, page), Role[].class, requestOptions.asMap());
    }

    public Role[] listRoles(ListParams listParams) throws WarrantException {
        return makeGetRequest("/v1/roles", listParams.asMap(), Role[].class);
    }

    public Role[] listRoles(ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        return makeGetRequest("/v1/roles", listParams.asMap(), Role[].class, requestOptions.asMap());
    }

    public Role[] listRolesForUser(User user, int limit, int page) throws WarrantException {
        return listRolesForUser(user.getUserId(), limit, page);
    }

    public Role[] listRolesForUser(User user, int limit, int page, RequestOptions requestOptions) throws WarrantException {
        return listRolesForUser(user.getUserId(), limit, page, requestOptions);
    }

    public Role[] listRolesForUser(String userId, int limit, int page) throws WarrantException {
        return makeGetRequest("/v1/users/" + userId + "/roles", getPaginationParams(limit, page), Role[].class);
    }

    public Role[] listRolesForUser(String userId, int limit, int page, RequestOptions requestOptions) throws WarrantException {
        return makeGetRequest("/v1/users/" + userId + "/roles", getPaginationParams(limit, page), Role[].class, requestOptions.asMap());
    }

    // Permissions
    public Permission createPermission(Permission permission) throws WarrantException {
        return makePostRequest("/v1/permissions", permission, Permission.class);
    }

    public Permission createPermission(Permission permission, RequestOptions requestOptions) throws WarrantException {
        return makePostRequest("/v1/permissions", permission, Permission.class, requestOptions.asMap());
    }

    public Permission updatePermission(String permissionId, Permission toUpdate) throws WarrantException {
        return makePutRequest("/v1/permissions/" + permissionId, toUpdate, Permission.class);
    }

    public Permission updatePermission(String permissionId, Permission toUpdate, RequestOptions requestOptions) throws WarrantException {
        return makePutRequest("/v1/permissions/" + permissionId, toUpdate, Permission.class, requestOptions.asMap());
    }

    public void deletePermission(Permission permission) throws WarrantException {
        deletePermission(permission.getPermissionId());
    }

    public void deletePermission(Permission permission, RequestOptions requestOptions) throws WarrantException {
        deletePermission(permission.getPermissionId(), requestOptions);
    }

    public void deletePermission(String permissionId) throws WarrantException {
        makeDeleteRequest("/v1/permissions/" + permissionId);
    }

    public void deletePermission(String permissionId, RequestOptions requestOptions) throws WarrantException {
        makeDeleteRequest("/v1/permissions/" + permissionId, requestOptions.asMap());
    }

    public Permission getPermission(String permissionId) throws WarrantException {
        return makeGetRequest("/v1/permissions/" + permissionId, Permission.class);
    }

    public Permission getPermission(String permissionId, RequestOptions requestOptions) throws WarrantException {
        return makeGetRequest("/v1/permissions/" + permissionId, Permission.class, requestOptions.asMap());
    }

    public Permission[] listPermissions(int limit, int page) throws WarrantException {
        return makeGetRequest("/v1/permissions", getPaginationParams(limit, page), Permission[].class);
    }

    public Permission[] listPermissions(int limit, int page, RequestOptions requestOptions) throws WarrantException {
        return makeGetRequest("/v1/permissions", getPaginationParams(limit, page), Permission[].class, requestOptions.asMap());
    }

    public Permission[] listPermissions(ListParams listParams) throws WarrantException {
        return makeGetRequest("/v1/permissions", listParams.asMap(), Permission[].class);
    }

    public Permission[] listPermissions(ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        return makeGetRequest("/v1/permissions", listParams.asMap(), Permission[].class, requestOptions.asMap());
    }

    public Permission[] listPermissionsForUser(User user, int limit, int page) throws WarrantException {
        return listPermissionsForUser(user.getUserId(), limit, page);
    }

    public Permission[] listPermissionsForUser(User user, int limit, int page, RequestOptions requestOptions) throws WarrantException {
        return listPermissionsForUser(user.getUserId(), limit, page, requestOptions);
    }

    public Permission[] listPermissionsForUser(String userId, int limit, int page) throws WarrantException {
        return makeGetRequest("/v1/users/" + userId + "/permissions", getPaginationParams(limit, page),
                Permission[].class);
    }

    public Permission[] listPermissionsForUser(String userId, int limit, int page, RequestOptions requestOptions) throws WarrantException {
        return makeGetRequest("/v1/users/" + userId + "/permissions", getPaginationParams(limit, page),
                Permission[].class, requestOptions.asMap());
    }

    public Permission[] listPermissionsForRole(Role role, int limit, int page) throws WarrantException {
        return listPermissionsForRole(role.getRoleId(), limit, page);
    }

    public Permission[] listPermissionsForRole(Role role, int limit, int page, RequestOptions requestOptions) throws WarrantException {
        return listPermissionsForRole(role.getRoleId(), limit, page, requestOptions);
    }

    public Permission[] listPermissionsForRole(String roleId, int limit, int page) throws WarrantException {
        return makeGetRequest("/v1/roles/" + roleId + "/permissions", getPaginationParams(limit, page),
                Permission[].class);
    }

    public Permission[] listPermissionsForRole(String roleId, int limit, int page, RequestOptions requestOptions) throws WarrantException {
        return makeGetRequest("/v1/roles/" + roleId + "/permissions", getPaginationParams(limit, page),
                Permission[].class, requestOptions.asMap());
    }

    // Features
    public Feature createFeature(Feature feature) throws WarrantException {
        return makePostRequest("/v1/features", feature, Feature.class);
    }

    public Feature createFeature(Feature feature, RequestOptions requestOptions) throws WarrantException {
        return makePostRequest("/v1/features", feature, Feature.class, requestOptions.asMap());
    }

    public void deleteFeature(Feature feature) throws WarrantException {
        deleteFeature(feature.getFeatureId());
    }

    public void deleteFeature(Feature feature, RequestOptions requestOptions) throws WarrantException {
        deleteFeature(feature.getFeatureId(), requestOptions);
    }

    public void deleteFeature(String featureId) throws WarrantException {
        makeDeleteRequest("/v1/features/" + featureId);
    }

    public void deleteFeature(String featureId, RequestOptions requestOptions) throws WarrantException {
        makeDeleteRequest("/v1/features/" + featureId, requestOptions.asMap());
    }

    public Feature getFeature(String featureId) throws WarrantException {
        return makeGetRequest("/v1/features/" + featureId, Feature.class);
    }

    public Feature getFeature(String featureId, RequestOptions requestOptions) throws WarrantException {
        return makeGetRequest("/v1/features/" + featureId, Feature.class, requestOptions.asMap());
    }

    public Feature[] listFeatures(int limit, int page) throws WarrantException {
        return makeGetRequest("/v1/features", getPaginationParams(limit, page), Feature[].class);
    }

    public Feature[] listFeatures(int limit, int page, RequestOptions requestOptions) throws WarrantException {
        return makeGetRequest("/v1/features", getPaginationParams(limit, page), Feature[].class, requestOptions.asMap());
    }

    public Feature[] listFeatures(ListParams listParams) throws WarrantException {
        return makeGetRequest("/v1/features", listParams.asMap(), Feature[].class);
    }

    public Feature[] listFeatures(ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        return makeGetRequest("/v1/features", listParams.asMap(), Feature[].class, requestOptions.asMap());
    }

    public Feature[] listFeaturesForUser(User user, int limit, int page) throws WarrantException {
        return listFeaturesForUser(user.getUserId(), limit, page);
    }

    public Feature[] listFeaturesForUser(User user, int limit, int page, RequestOptions requestOptions) throws WarrantException {
        return listFeaturesForUser(user.getUserId(), limit, page, requestOptions);
    }

    public Feature[] listFeaturesForUser(String userId, int limit, int page) throws WarrantException {
        return makeGetRequest("/v1/users/" + userId + "/features", getPaginationParams(limit, page), Feature[].class);
    }

    public Feature[] listFeaturesForUser(String userId, int limit, int page, RequestOptions requestOptions) throws WarrantException {
        return makeGetRequest("/v1/users/" + userId + "/features", getPaginationParams(limit, page), Feature[].class, requestOptions.asMap());
    }

    public Feature[] listFeaturesForTenant(Tenant tenant, int limit, int page) throws WarrantException {
        return listFeaturesForTenant(tenant.getTenantId(), limit, page);
    }

    public Feature[] listFeaturesForTenant(Tenant tenant, int limit, int page, RequestOptions requestOptions) throws WarrantException {
        return listFeaturesForTenant(tenant.getTenantId(), limit, page, requestOptions);
    }

    public Feature[] listFeaturesForTenant(String tenantId, int limit, int page) throws WarrantException {
        return makeGetRequest("/v1/tenants/" + tenantId + "/features", getPaginationParams(limit, page),
                Feature[].class);
    }

    public Feature[] listFeaturesForTenant(String tenantId, int limit, int page, RequestOptions requestOptions) throws WarrantException {
        return makeGetRequest("/v1/tenants/" + tenantId + "/features", getPaginationParams(limit, page),
                Feature[].class, requestOptions.asMap());
    }

    public Feature[] listFeaturesForPricingTier(PricingTier pricingTier, int limit, int page) throws WarrantException {
        return listFeaturesForPricingTier(pricingTier.getPricingTierId(), limit, page);
    }

    public Feature[] listFeaturesForPricingTier(PricingTier pricingTier, int limit, int page, RequestOptions requestOptions) throws WarrantException {
        return listFeaturesForPricingTier(pricingTier.getPricingTierId(), limit, page, requestOptions);
    }

    public Feature[] listFeaturesForPricingTier(String pricingTierId, int limit, int page) throws WarrantException {
        return makeGetRequest("/v1/pricing-tiers/" + pricingTierId + "/features", getPaginationParams(limit, page),
                Feature[].class);
    }

    public Feature[] listFeaturesForPricingTier(String pricingTierId, int limit, int page, RequestOptions requestOptions) throws WarrantException {
        return makeGetRequest("/v1/pricing-tiers/" + pricingTierId + "/features", getPaginationParams(limit, page),
                Feature[].class, requestOptions.asMap());
    }

    // Pricing Tiers
    public PricingTier createPricingTier(PricingTier pricingTier) throws WarrantException {
        return makePostRequest("/v1/pricing-tiers", pricingTier, PricingTier.class);
    }

    public PricingTier createPricingTier(PricingTier pricingTier, RequestOptions requestOptions) throws WarrantException {
        return makePostRequest("/v1/pricing-tiers", pricingTier, PricingTier.class, requestOptions.asMap());
    }

    public void deletePricingTier(PricingTier pricingTier) throws WarrantException {
        deletePricingTier(pricingTier.getPricingTierId());
    }

    public void deletePricingTier(PricingTier pricingTier, RequestOptions requestOptions) throws WarrantException {
        deletePricingTier(pricingTier.getPricingTierId(), requestOptions);
    }

    public void deletePricingTier(String pricingTierId) throws WarrantException {
        makeDeleteRequest("/v1/pricing-tiers/" + pricingTierId);
    }

    public void deletePricingTier(String pricingTierId, RequestOptions requestOptions) throws WarrantException {
        makeDeleteRequest("/v1/pricing-tiers/" + pricingTierId, requestOptions.asMap());
    }

    public PricingTier getPricingTier(String pricingTierId) throws WarrantException {
        return makeGetRequest("/v1/pricing-tiers/" + pricingTierId, PricingTier.class);
    }

    public PricingTier getPricingTier(String pricingTierId, RequestOptions requestOptions) throws WarrantException {
        return makeGetRequest("/v1/pricing-tiers/" + pricingTierId, PricingTier.class, requestOptions.asMap());
    }

    public PricingTier[] listPricingTiers(int limit, int page) throws WarrantException {
        return makeGetRequest("/v1/pricing-tiers", getPaginationParams(limit, page), PricingTier[].class);
    }

    public PricingTier[] listPricingTiers(int limit, int page, RequestOptions requestOptions) throws WarrantException {
        return makeGetRequest("/v1/pricing-tiers", getPaginationParams(limit, page), PricingTier[].class, requestOptions.asMap());
    }

    public PricingTier[] listPricingTiers(ListParams listParams) throws WarrantException {
        return makeGetRequest("/v1/pricing-tiers", listParams.asMap(), PricingTier[].class);
    }

    public PricingTier[] listPricingTiers(ListParams listParams, RequestOptions requestOptions) throws WarrantException {
        return makeGetRequest("/v1/pricing-tiers", listParams.asMap(), PricingTier[].class, requestOptions.asMap());
    }

    public PricingTier[] listPricingTiersForTenant(Tenant tenant, int limit, int page) throws WarrantException {
        return listPricingTiersForTenant(tenant.getTenantId(), limit, page);
    }

    public PricingTier[] listPricingTiersForTenant(Tenant tenant, int limit, int page, RequestOptions requestOptions) throws WarrantException {
        return listPricingTiersForTenant(tenant.getTenantId(), limit, page, requestOptions);
    }

    public PricingTier[] listPricingTiersForTenant(String tenantId, int limit, int page) throws WarrantException {
        return makeGetRequest("/v1/tenants/" + tenantId + "/pricing-tiers", getPaginationParams(limit, page),
                PricingTier[].class);
    }

    public PricingTier[] listPricingTiersForTenant(String tenantId, int limit, int page, RequestOptions requestOptions) throws WarrantException {
        return makeGetRequest("/v1/tenants/" + tenantId + "/pricing-tiers", getPaginationParams(limit, page),
                PricingTier[].class, requestOptions.asMap());
    }

    public PricingTier[] listPricingTiersForUser(User user, int limit, int page) throws WarrantException {
        return listPricingTiersForUser(user.getUserId(), limit, page);
    }

    public PricingTier[] listPricingTiersForUser(User user, int limit, int page, RequestOptions requestOptions) throws WarrantException {
        return listPricingTiersForUser(user.getUserId(), limit, page, requestOptions);
    }

    public PricingTier[] listPricingTiersForUser(String userId, int limit, int page) throws WarrantException {
        return makeGetRequest("/v1/users/" + userId + "/pricing-tiers", getPaginationParams(limit, page),
                PricingTier[].class);
    }

    public PricingTier[] listPricingTiersForUser(String userId, int limit, int page, RequestOptions requestOptions) throws WarrantException {
        return makeGetRequest("/v1/users/" + userId + "/pricing-tiers", getPaginationParams(limit, page),
                PricingTier[].class, requestOptions.asMap());
    }

    // Assign
    public Warrant assignRoleToUser(Role role, User user) throws WarrantException {
        return assignRoleToUser(role.getRoleId(), user.getUserId());
    }

    public Warrant assignRoleToUser(Role role, User user, RequestOptions requestOptions) throws WarrantException {
        return assignRoleToUser(role.getRoleId(), user.getUserId(), requestOptions);
    }

    public Warrant assignRoleToUser(String roleId, String userId) throws WarrantException {
        return createWarrant(new Role(roleId), "member", new WarrantSubject(User.OBJECT_TYPE, userId));
    }

    public Warrant assignRoleToUser(String roleId, String userId, RequestOptions requestOptions) throws WarrantException {
        return createWarrant(new Role(roleId), "member", new WarrantSubject(User.OBJECT_TYPE, userId), requestOptions);
    }

    public Warrant assignPermissionToUser(Permission permission, User user) throws WarrantException {
        return assignPermissionToUser(permission.getPermissionId(), user.getUserId());
    }

    public Warrant assignPermissionToUser(Permission permission, User user, RequestOptions requestOptions) throws WarrantException {
        return assignPermissionToUser(permission.getPermissionId(), user.getUserId(), requestOptions);
    }

    public Warrant assignPermissionToUser(String permissionId, String userId) throws WarrantException {
        return createWarrant(new Permission(permissionId), "member", new WarrantSubject(User.OBJECT_TYPE, userId));
    }

    public Warrant assignPermissionToUser(String permissionId, String userId, RequestOptions requestOptions) throws WarrantException {
        return createWarrant(new Permission(permissionId), "member", new WarrantSubject(User.OBJECT_TYPE, userId), requestOptions);
    }

    public Warrant assignPricingTierToUser(PricingTier pricingTier, User user) throws WarrantException {
        return assignPricingTierToUser(pricingTier.getPricingTierId(), user.getUserId());
    }

    public Warrant assignPricingTierToUser(PricingTier pricingTier, User user, RequestOptions requestOptions) throws WarrantException {
        return assignPricingTierToUser(pricingTier.getPricingTierId(), user.getUserId(), requestOptions);
    }

    public Warrant assignPricingTierToUser(String pricingTierId, String userId) throws WarrantException {
        return createWarrant(new PricingTier(pricingTierId), "member", new WarrantSubject(User.OBJECT_TYPE, userId));
    }

    public Warrant assignPricingTierToUser(String pricingTierId, String userId, RequestOptions requestOptions) throws WarrantException {
        return createWarrant(new PricingTier(pricingTierId), "member", new WarrantSubject(User.OBJECT_TYPE, userId), requestOptions);
    }

    public Warrant assignFeatureToUser(Feature feature, User user) throws WarrantException {
        return assignFeatureToUser(feature.getFeatureId(), user.getUserId());
    }

    public Warrant assignFeatureToUser(Feature feature, User user, RequestOptions requestOptions) throws WarrantException {
        return assignFeatureToUser(feature.getFeatureId(), user.getUserId(), requestOptions);
    }

    public Warrant assignFeatureToUser(String featureId, String userId) throws WarrantException {
        return createWarrant(new Feature(featureId), "member", new WarrantSubject(User.OBJECT_TYPE, userId));
    }

    public Warrant assignFeatureToUser(String featureId, String userId, RequestOptions requestOptions) throws WarrantException {
        return createWarrant(new Feature(featureId), "member", new WarrantSubject(User.OBJECT_TYPE, userId), requestOptions);
    }

    public Warrant assignUserToTenant(User user, Tenant tenant) throws WarrantException {
        return assignUserToTenant(user.getUserId(), tenant.getTenantId());
    }

    public Warrant assignUserToTenant(User user, Tenant tenant, RequestOptions requestOptions) throws WarrantException {
        return assignUserToTenant(user.getUserId(), tenant.getTenantId(), requestOptions);
    }

    public Warrant assignUserToTenant(String userId, String tenantId) throws WarrantException {
        return createWarrant(new Tenant(tenantId), "member", new WarrantSubject(User.OBJECT_TYPE, userId));
    }

    public Warrant assignUserToTenant(String userId, String tenantId, RequestOptions requestOptions) throws WarrantException {
        return createWarrant(new Tenant(tenantId), "member", new WarrantSubject(User.OBJECT_TYPE, userId), requestOptions);
    }

    public Warrant assignPricingTierToTenant(PricingTier pricingTier, Tenant tenant) throws WarrantException {
        return assignPricingTierToTenant(pricingTier.getPricingTierId(), tenant.getTenantId());
    }

    public Warrant assignPricingTierToTenant(PricingTier pricingTier, Tenant tenant, RequestOptions requestOptions) throws WarrantException {
        return assignPricingTierToTenant(pricingTier.getPricingTierId(), tenant.getTenantId(), requestOptions);
    }

    public Warrant assignPricingTierToTenant(String pricingTierId, String tenantId) throws WarrantException {
        return createWarrant(new PricingTier(pricingTierId), "member", new WarrantSubject(Tenant.OBJECT_TYPE, tenantId));
    }

    public Warrant assignPricingTierToTenant(String pricingTierId, String tenantId, RequestOptions requestOptions) throws WarrantException {
        return createWarrant(new PricingTier(pricingTierId), "member", new WarrantSubject(Tenant.OBJECT_TYPE, tenantId), requestOptions);
    }

    public Warrant assignFeatureToTenant(Feature feature, Tenant tenant) throws WarrantException {
        return assignFeatureToTenant(feature.getFeatureId(), tenant.getTenantId());
    }

    public Warrant assignFeatureToTenant(Feature feature, Tenant tenant, RequestOptions requestOptions) throws WarrantException {
        return assignFeatureToTenant(feature.getFeatureId(), tenant.getTenantId(), requestOptions);
    }

    public Warrant assignFeatureToTenant(String featureId, String tenantId) throws WarrantException {
        return createWarrant(new Feature(featureId), "member", new WarrantSubject(Tenant.OBJECT_TYPE, tenantId));
    }

    public Warrant assignFeatureToTenant(String featureId, String tenantId, RequestOptions requestOptions) throws WarrantException {
        return createWarrant(new Feature(featureId), "member", new WarrantSubject(Tenant.OBJECT_TYPE, tenantId), requestOptions);
    }

    public Warrant assignFeatureToPricingTier(Feature feature, PricingTier pricingTier) throws WarrantException {
        return assignFeatureToPricingTier(feature.getFeatureId(), pricingTier.getPricingTierId());
    }

    public Warrant assignFeatureToPricingTier(Feature feature, PricingTier pricingTier, RequestOptions requestOptions) throws WarrantException {
        return assignFeatureToPricingTier(feature.getFeatureId(), pricingTier.getPricingTierId(), requestOptions);
    }

    public Warrant assignFeatureToPricingTier(String featureId, String pricingTierId) throws WarrantException {
        return createWarrant(new Feature(featureId), "member", new WarrantSubject(PricingTier.OBJECT_TYPE, pricingTierId));
    }

    public Warrant assignFeatureToPricingTier(String featureId, String pricingTierId, RequestOptions requestOptions) throws WarrantException {
        return createWarrant(new Feature(featureId), "member", new WarrantSubject(PricingTier.OBJECT_TYPE, pricingTierId), requestOptions);
    }

    public Warrant assignPermissionToRole(Permission permission, Role role) throws WarrantException {
        return assignPermissionToRole(permission.getPermissionId(), role.getRoleId());
    }

    public Warrant assignPermissionToRole(Permission permission, Role role, RequestOptions requestOptions) throws WarrantException {
        return assignPermissionToRole(permission.getPermissionId(), role.getRoleId(), requestOptions);
    }

    public Warrant assignPermissionToRole(String permissionId, String roleId) throws WarrantException {
        return createWarrant(new Permission(permissionId), "member", new WarrantSubject(Role.OBJECT_TYPE, roleId));
    }

    public Warrant assignPermissionToRole(String permissionId, String roleId, RequestOptions requestOptions) throws WarrantException {
        return createWarrant(new Permission(permissionId), "member", new WarrantSubject(Role.OBJECT_TYPE, roleId), requestOptions);
    }

    // Remove associations
    public void removeRoleFromUser(Role role, User user) throws WarrantException {
        removeRoleFromUser(role.getRoleId(), user.getUserId());
    }

    public void removeRoleFromUser(Role role, User user, RequestOptions requestOptions) throws WarrantException {
        removeRoleFromUser(role.getRoleId(), user.getUserId(), requestOptions);
    }

    public void removeRoleFromUser(String roleId, String userId) throws WarrantException {
        deleteWarrant(new Role(roleId), "member", new WarrantSubject(User.OBJECT_TYPE, userId));
    }

    public void removeRoleFromUser(String roleId, String userId, RequestOptions requestOptions) throws WarrantException {
        deleteWarrant(new Role(roleId), "member", new WarrantSubject(User.OBJECT_TYPE, userId), requestOptions.asMap());
    }

    public void removePermissionFromUser(Permission permission, User user) throws WarrantException {
        removePermissionFromUser(permission.getPermissionId(), user.getUserId());
    }

    public void removePermissionFromUser(Permission permission, User user, RequestOptions requestOptions) throws WarrantException {
        removePermissionFromUser(permission.getPermissionId(), user.getUserId(), requestOptions);
    }

    public void removePermissionFromUser(String permissionId, String userId) throws WarrantException {
        deleteWarrant(new Permission(permissionId), "member", new WarrantSubject(User.OBJECT_TYPE, userId));
    }

    public void removePermissionFromUser(String permissionId, String userId, RequestOptions requestOptions) throws WarrantException {
        deleteWarrant(new Permission(permissionId), "member", new WarrantSubject(User.OBJECT_TYPE, userId), requestOptions.asMap());
    }

    public void removePricingTierFromUser(PricingTier pricingTier, User user) throws WarrantException {
        removePricingTierFromUser(pricingTier.getPricingTierId(), user.getUserId());
    }

    public void removePricingTierFromUser(PricingTier pricingTier, User user, RequestOptions requestOptions) throws WarrantException {
        removePricingTierFromUser(pricingTier.getPricingTierId(), user.getUserId(), requestOptions);
    }

    public void removePricingTierFromUser(String pricingTierId, String userId) throws WarrantException {
        deleteWarrant(new PricingTier(pricingTierId), "member", new WarrantSubject(User.OBJECT_TYPE, userId));
    }

    public void removePricingTierFromUser(String pricingTierId, String userId, RequestOptions requestOptions) throws WarrantException {
        deleteWarrant(new PricingTier(pricingTierId), "member", new WarrantSubject(User.OBJECT_TYPE, userId), requestOptions.asMap());
    }

    public void removeFeatureFromUser(Feature feature, User user) throws WarrantException {
        removeFeatureFromUser(feature.getFeatureId(), user.getUserId());
    }

    public void removeFeatureFromUser(Feature feature, User user, RequestOptions requestOptions) throws WarrantException {
        removeFeatureFromUser(feature.getFeatureId(), user.getUserId(), requestOptions);
    }

    public void removeFeatureFromUser(String featureId, String userId) throws WarrantException {
        deleteWarrant(new Feature(featureId), "member", new WarrantSubject(User.OBJECT_TYPE, userId));
    }

    public void removeFeatureFromUser(String featureId, String userId, RequestOptions requestOptions) throws WarrantException {
        deleteWarrant(new Feature(featureId), "member", new WarrantSubject(User.OBJECT_TYPE, userId), requestOptions.asMap());
    }

    public void removeUserFromTenant(User user, Tenant tenant) throws WarrantException {
        removeUserFromTenant(user.getUserId(), tenant.getTenantId());
    }

    public void removeUserFromTenant(User user, Tenant tenant, RequestOptions requestOptions) throws WarrantException {
        removeUserFromTenant(user.getUserId(), tenant.getTenantId(), requestOptions);
    }

    public void removeUserFromTenant(String userId, String tenantId) throws WarrantException {
        deleteWarrant(new Tenant(tenantId), "member", new WarrantSubject(User.OBJECT_TYPE, userId));
    }

    public void removeUserFromTenant(String userId, String tenantId, RequestOptions requestOptions) throws WarrantException {
        deleteWarrant(new Tenant(tenantId), "member", new WarrantSubject(User.OBJECT_TYPE, userId), requestOptions.asMap());
    }

    public void removePricingTierFromTenant(PricingTier pricingTier, Tenant tenant) throws WarrantException {
        removePricingTierFromTenant(pricingTier.getPricingTierId(), tenant.getTenantId());
    }

    public void removePricingTierFromTenant(PricingTier pricingTier, Tenant tenant, RequestOptions requestOptions) throws WarrantException {
        removePricingTierFromTenant(pricingTier.getPricingTierId(), tenant.getTenantId(), requestOptions);
    }

    public void removePricingTierFromTenant(String pricingTierId, String tenantId) throws WarrantException {
        deleteWarrant(new PricingTier(pricingTierId), "member", new WarrantSubject(Tenant.OBJECT_TYPE, tenantId));
    }

    public void removePricingTierFromTenant(String pricingTierId, String tenantId, RequestOptions requestOptions) throws WarrantException {
        deleteWarrant(new PricingTier(pricingTierId), "member", new WarrantSubject(Tenant.OBJECT_TYPE, tenantId), requestOptions.asMap());
    }

    public void removeFeatureFromTenant(Feature feature, Tenant tenant) throws WarrantException {
        removeFeatureFromTenant(feature.getFeatureId(), tenant.getTenantId());
    }

    public void removeFeatureFromTenant(Feature feature, Tenant tenant, RequestOptions requestOptions) throws WarrantException {
        removeFeatureFromTenant(feature.getFeatureId(), tenant.getTenantId(), requestOptions);
    }

    public void removeFeatureFromTenant(String featureId, String tenantId) throws WarrantException {
        deleteWarrant(new Feature(featureId), "member", new WarrantSubject(Tenant.OBJECT_TYPE, tenantId));
    }

    public void removeFeatureFromTenant(String featureId, String tenantId, RequestOptions requestOptions) throws WarrantException {
        deleteWarrant(new Feature(featureId), "member", new WarrantSubject(Tenant.OBJECT_TYPE, tenantId), requestOptions.asMap());
    }

    public void removeFeatureFromPricingTier(Feature feature, PricingTier pricingTier) throws WarrantException {
        removeFeatureFromPricingTier(feature.getFeatureId(), pricingTier.getPricingTierId());
    }

    public void removeFeatureFromPricingTier(Feature feature, PricingTier pricingTier, RequestOptions requestOptions) throws WarrantException {
        removeFeatureFromPricingTier(feature.getFeatureId(), pricingTier.getPricingTierId(), requestOptions);
    }

    public void removeFeatureFromPricingTier(String featureId, String pricingTierId) throws WarrantException {
        deleteWarrant(new Feature(featureId), "member", new WarrantSubject(PricingTier.OBJECT_TYPE, pricingTierId));
    }

    public void removeFeatureFromPricingTier(String featureId, String pricingTierId, RequestOptions requestOptions) throws WarrantException {
        deleteWarrant(new Feature(featureId), "member", new WarrantSubject(PricingTier.OBJECT_TYPE, pricingTierId), requestOptions.asMap());
    }

    public void removePermissionFromRole(Permission permission, Role role) throws WarrantException {
        removePermissionFromRole(permission.getPermissionId(), role.getRoleId());
    }

    public void removePermissionFromRole(Permission permission, Role role, RequestOptions requestOptions) throws WarrantException {
        removePermissionFromRole(permission.getPermissionId(), role.getRoleId(), requestOptions);
    }

    public void removePermissionFromRole(String permissionId, String roleId) throws WarrantException {
        deleteWarrant(new Permission(permissionId), "member", new WarrantSubject(Role.OBJECT_TYPE, roleId));
    }

    public void removePermissionFromRole(String permissionId, String roleId, RequestOptions requestOptions) throws WarrantException {
        deleteWarrant(new Permission(permissionId), "member", new WarrantSubject(Role.OBJECT_TYPE, roleId), requestOptions.asMap());
    }

    // Checks
    public boolean checkUserHasPermission(User user, String permissionId) throws WarrantException {
        return checkUserHasPermission(user.getUserId(), permissionId);
    }

    public boolean checkUserHasPermission(User user, String permissionId, RequestOptions requestOptions) throws WarrantException {
        return checkUserHasPermission(user.getUserId(), permissionId, requestOptions);
    }

    public boolean checkUserHasPermission(String userId, String permissionId) throws WarrantException {
        Permission perm = new Permission();
        perm.setPermissionId(permissionId);
        WarrantSubject subject = new WarrantSubject(User.OBJECT_TYPE, userId);
        return check(perm, "member", subject);
    }

    public boolean checkUserHasPermission(String userId, String permissionId, RequestOptions requestOptions) throws WarrantException {
        Permission perm = new Permission();
        perm.setPermissionId(permissionId);
        WarrantSubject subject = new WarrantSubject(User.OBJECT_TYPE, userId);
        return check(perm, "member", subject, requestOptions);
    }

    public boolean checkTenantHasFeature(Tenant tenant, String featureId) throws WarrantException {
        return checkTenantHasFeature(tenant.getTenantId(), featureId);
    }

    public boolean checkTenantHasFeature(Tenant tenant, String featureId, RequestOptions requestOptions) throws WarrantException {
        return checkTenantHasFeature(tenant.getTenantId(), featureId, requestOptions);
    }

    public boolean checkTenantHasFeature(String tenantId, String featureId) throws WarrantException {
        Feature feature = new Feature();
        feature.setFeatureId(featureId);
        WarrantSubject subject = new WarrantSubject(Tenant.OBJECT_TYPE, tenantId);
        return check(feature, "member", subject);
    }

    public boolean checkTenantHasFeature(String tenantId, String featureId, RequestOptions requestOptions) throws WarrantException {
        Feature feature = new Feature();
        feature.setFeatureId(featureId);
        WarrantSubject subject = new WarrantSubject(Tenant.OBJECT_TYPE, tenantId);
        return check(feature, "member", subject, requestOptions);
    }

    public boolean checkUserHasFeature(User user, String featureId) throws WarrantException {
        return checkUserHasFeature(user.getUserId(), featureId);
    }

    public boolean checkUserHasFeature(User user, String featureId, RequestOptions requestOptions) throws WarrantException {
        return checkUserHasFeature(user.getUserId(), featureId, requestOptions);
    }

    public boolean checkUserHasFeature(String userId, String featureId) throws WarrantException {
        Feature feature = new Feature();
        feature.setFeatureId(featureId);
        WarrantSubject subject = new WarrantSubject(User.OBJECT_TYPE, userId);
        return check(feature, "member", subject);
    }

    public boolean checkUserHasFeature(String userId, String featureId, RequestOptions requestOptions) throws WarrantException {
        Feature feature = new Feature();
        feature.setFeatureId(featureId);
        WarrantSubject subject = new WarrantSubject(User.OBJECT_TYPE, userId);
        return check(feature, "member", subject, requestOptions);
    }
}
