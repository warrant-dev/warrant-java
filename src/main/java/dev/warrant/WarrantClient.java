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

    public User createUser(User user) throws WarrantException {
        return makePostRequest("/v1/users", user, User.class);
    }

    public User[] createUsers(User[] users) throws WarrantException {
        return makePostRequest("/v1/users", users, User[].class);
    }

    public User updateUser(String userId, User toUpdate) throws WarrantException {
        return makePutRequest("/v1/users/" + userId, toUpdate, User.class);
    }

    public void deleteUser(User user) throws WarrantException {
        deleteUser(user.getUserId());
    }

    public void deleteUser(String userId) throws WarrantException {
        makeDeleteRequest("/v1/users/" + userId);
    }

    public User getUser(String userId) throws WarrantException {
        return makeGetRequest("/v1/users/" + userId, User.class);
    }

    public User[] listUsers(int limit, int page) throws WarrantException {
        return makeGetRequest("/v1/users", getPaginationParams(limit, page), User[].class);
    }

    public User[] listUsersForTenant(Tenant tenant, int limit, int page) throws WarrantException {
        return listUsersForTenant(tenant.getTenantId(), limit, page);
    }

    public User[] listUsersForTenant(String tenantId, int limit, int page) throws WarrantException {
        return makeGetRequest("/v1/tenants/" + tenantId + "/users", getPaginationParams(limit, page), User[].class);
    }

    // Tenants
    public Tenant createTenant() throws WarrantException {
        return makePostRequest("/v1/tenants", Collections.EMPTY_MAP, Tenant.class);
    }

    public Tenant createTenant(Tenant tenant) throws WarrantException {
        return makePostRequest("/v1/tenants", tenant, Tenant.class);
    }

    public Tenant[] createTenants(Tenant[] tenants) throws WarrantException {
        return makePostRequest("/v1/tenants", tenants, Tenant[].class);
    }

    public Tenant updateTenant(String tenantId, Tenant toUpdate) throws WarrantException {
        return makePutRequest("/v1/tenants/" + tenantId, toUpdate, Tenant.class);
    }

    public void deleteTenant(Tenant tenant) throws WarrantException {
        deleteTenant(tenant.getTenantId());
    }

    public void deleteTenant(String tenantId) throws WarrantException {
        makeDeleteRequest("/v1/tenants/" + tenantId);
    }

    public Tenant getTenant(String tenantId) throws WarrantException {
        return makeGetRequest("/v1/tenants/" + tenantId, Tenant.class);
    }

    public Tenant[] listTenants(int limit, int page) throws WarrantException {
        return makeGetRequest("/v1/tenants", getPaginationParams(limit, page), Tenant[].class);
    }

    public Tenant[] listTenantsForUser(User user, int limit, int page) throws WarrantException {
        return listTenantsForUser(user.getUserId(), limit, page);
    }

    public Tenant[] listTenantsForUser(String userId, int limit, int page) throws WarrantException {
        return makeGetRequest("/v1/users/" + userId + "/tenants", getPaginationParams(limit, page), Tenant[].class);
    }

    // Roles
    public Role createRole(Role role) throws WarrantException {
        return makePostRequest("/v1/roles", role, Role.class);
    }

    public Role updateRole(String roleId, Role toUpdate) throws WarrantException {
        return makePutRequest("/v1/roles/" + roleId, toUpdate, Role.class);
    }

    public void deleteRole(Role role) throws WarrantException {
        deleteRole(role.getRoleId());
    }

    public void deleteRole(String roleId) throws WarrantException {
        makeDeleteRequest("/v1/roles/" + roleId);
    }

    public Role getRole(String roleId) throws WarrantException {
        return makeGetRequest("/v1/roles/" + roleId, Role.class);
    }

    public Role[] listRoles(int limit, int page) throws WarrantException {
        return makeGetRequest("/v1/roles", getPaginationParams(limit, page), Role[].class);
    }

    public Role[] listRolesForUser(User user, int limit, int page) throws WarrantException {
        return listRolesForUser(user.getUserId(), limit, page);
    }

    public Role[] listRolesForUser(String userId, int limit, int page) throws WarrantException {
        return makeGetRequest("/v1/users/" + userId + "/roles", getPaginationParams(limit, page), Role[].class);
    }

    // Permissions
    public Permission createPermission(Permission permission) throws WarrantException {
        return makePostRequest("/v1/permissions", permission, Permission.class);
    }

    public Permission updatePermission(String permissionId, Permission toUpdate) throws WarrantException {
        return makePutRequest("/v1/permissions/" + permissionId, toUpdate, Permission.class);
    }

    public void deletePermission(Permission permission) throws WarrantException {
        deletePermission(permission.getPermissionId());
    }

    public void deletePermission(String permissionId) throws WarrantException {
        makeDeleteRequest("/v1/permissions/" + permissionId);
    }

    public Permission getPermission(String permissionId) throws WarrantException {
        return makeGetRequest("/v1/permissions/" + permissionId, Permission.class);
    }

    public Permission[] listPermissions(int limit, int page) throws WarrantException {
        return makeGetRequest("/v1/permissions", getPaginationParams(limit, page), Permission[].class);
    }

    public Permission[] listPermissionsForUser(User user, int limit, int page) throws WarrantException {
        return listPermissionsForUser(user.getUserId(), limit, page);
    }

    public Permission[] listPermissionsForUser(String userId, int limit, int page) throws WarrantException {
        return makeGetRequest("/v1/users/" + userId + "/permissions", getPaginationParams(limit, page),
                Permission[].class);
    }

    public Permission[] listPermissionsForRole(Role role, int limit, int page) throws WarrantException {
        return listPermissionsForRole(role.getRoleId(), limit, page);
    }

    public Permission[] listPermissionsForRole(String roleId, int limit, int page) throws WarrantException {
        return makeGetRequest("/v1/roles/" + roleId + "/permissions", getPaginationParams(limit, page),
                Permission[].class);
    }

    // Features
    public Feature createFeature(Feature feature) throws WarrantException {
        return makePostRequest("/v1/features", feature, Feature.class);
    }

    public void deleteFeature(Feature feature) throws WarrantException {
        deleteFeature(feature.getFeatureId());
    }

    public void deleteFeature(String featureId) throws WarrantException {
        makeDeleteRequest("/v1/features/" + featureId);
    }

    public Feature getFeature(String featureId) throws WarrantException {
        return makeGetRequest("/v1/features/" + featureId, Feature.class);
    }

    public Feature[] listFeatures(int limit, int page) throws WarrantException {
        return makeGetRequest("/v1/features", getPaginationParams(limit, page), Feature[].class);
    }

    public Feature[] listFeaturesForUser(User user, int limit, int page) throws WarrantException {
        return listFeaturesForUser(user.getUserId(), limit, page);
    }

    public Feature[] listFeaturesForUser(String userId, int limit, int page) throws WarrantException {
        return makeGetRequest("/v1/users/" + userId + "/features", getPaginationParams(limit, page), Feature[].class);
    }

    public Feature[] listFeaturesForTenant(Tenant tenant, int limit, int page) throws WarrantException {
        return listFeaturesForTenant(tenant.getTenantId(), limit, page);
    }

    public Feature[] listFeaturesForTenant(String tenantId, int limit, int page) throws WarrantException {
        return makeGetRequest("/v1/tenants/" + tenantId + "/features", getPaginationParams(limit, page),
                Feature[].class);
    }

    public Feature[] listFeaturesForPricingTier(PricingTier pricingTier, int limit, int page) throws WarrantException {
        return listFeaturesForPricingTier(pricingTier.getPricingTierId(), limit, page);
    }

    public Feature[] listFeaturesForPricingTier(String pricingTierId, int limit, int page) throws WarrantException {
        return makeGetRequest("/v1/pricing-tiers/" + pricingTierId + "/features", getPaginationParams(limit, page),
                Feature[].class);
    }

    // Pricing Tiers
    public PricingTier createPricingTier(PricingTier pricingTier) throws WarrantException {
        return makePostRequest("/v1/pricing-tiers", pricingTier, PricingTier.class);
    }

    public void deletePricingTier(PricingTier pricingTier) throws WarrantException {
        deletePricingTier(pricingTier.getPricingTierId());
    }

    public void deletePricingTier(String pricingTierId) throws WarrantException {
        makeDeleteRequest("/v1/pricing-tiers/" + pricingTierId);
    }

    public PricingTier getPricingTier(String pricingTierId) throws WarrantException {
        return makeGetRequest("/v1/pricing-tiers/" + pricingTierId, PricingTier.class);
    }

    public PricingTier[] listPricingTiers(int limit, int page) throws WarrantException {
        return makeGetRequest("/v1/pricing-tiers", getPaginationParams(limit, page), PricingTier[].class);
    }

    public PricingTier[] listPricingTiersForTenant(Tenant tenant, int limit, int page) throws WarrantException {
        return listPricingTiersForTenant(tenant.getTenantId(), limit, page);
    }

    public PricingTier[] listPricingTiersForTenant(String tenantId, int limit, int page) throws WarrantException {
        return makeGetRequest("/v1/tenants/" + tenantId + "/pricing-tiers", getPaginationParams(limit, page),
                PricingTier[].class);
    }

    public PricingTier[] listPricingTiersForUser(User user, int limit, int page) throws WarrantException {
        return listPricingTiersForUser(user.getUserId(), limit, page);
    }

    public PricingTier[] listPricingTiersForUser(String userId, int limit, int page) throws WarrantException {
        return makeGetRequest("/v1/users/" + userId + "/pricing-tiers", getPaginationParams(limit, page),
                PricingTier[].class);
    }

    // Assign
    public Warrant assignRoleToUser(Role role, User user) throws WarrantException {
        return assignRoleToUser(role.getRoleId(), user.getUserId());
    }

    public Warrant assignRoleToUser(String roleId, String userId) throws WarrantException {
        return createWarrant(new Role(roleId), "member", new WarrantSubject("user", userId));
    }

    public Warrant assignPermissionToUser(Permission permission, User user) throws WarrantException {
        return assignPermissionToUser(permission.getPermissionId(), user.getUserId());
    }

    public Warrant assignPermissionToUser(String permissionId, String userId) throws WarrantException {
        return createWarrant(new Permission(permissionId), "member", new WarrantSubject("user", userId));
    }

    public Warrant assignPricingTierToUser(PricingTier pricingTier, User user) throws WarrantException {
        return assignPricingTierToUser(pricingTier.getPricingTierId(), user.getUserId());
    }

    public Warrant assignPricingTierToUser(String pricingTierId, String userId) throws WarrantException {
        return createWarrant(new PricingTier(pricingTierId), "member", new WarrantSubject("user", userId));
    }

    public Warrant assignFeatureToUser(Feature feature, User user) throws WarrantException {
        return assignFeatureToUser(feature.getFeatureId(), user.getUserId());
    }

    public Warrant assignFeatureToUser(String featureId, String userId) throws WarrantException {
        return createWarrant(new Feature(featureId), "member", new WarrantSubject("user", userId));
    }

    public Warrant assignUserToTenant(User user, Tenant tenant) throws WarrantException {
        return assignUserToTenant(user.getUserId(), tenant.getTenantId());
    }

    public Warrant assignUserToTenant(String userId, String tenantId) throws WarrantException {
        return createWarrant(new Tenant(tenantId), "member", new WarrantSubject("user", userId));
    }

    public Warrant assignPricingTierToTenant(PricingTier pricingTier, Tenant tenant) throws WarrantException {
        return assignPricingTierToTenant(pricingTier.getPricingTierId(), tenant.getTenantId());
    }

    public Warrant assignPricingTierToTenant(String pricingTierId, String tenantId) throws WarrantException {
        return createWarrant(new PricingTier(pricingTierId), "member", new WarrantSubject("tenant", tenantId));
    }

    public Warrant assignFeatureToTenant(Feature feature, Tenant tenant) throws WarrantException {
        return assignFeatureToTenant(feature.getFeatureId(), tenant.getTenantId());
    }

    public Warrant assignFeatureToTenant(String featureId, String tenantId) throws WarrantException {
        return createWarrant(new Feature(featureId), "member", new WarrantSubject("tenant", tenantId));
    }

    public Warrant assignFeatureToPricingTier(Feature feature, PricingTier pricingTier) throws WarrantException {
        return assignFeatureToPricingTier(feature.getFeatureId(), pricingTier.getPricingTierId());
    }

    public Warrant assignFeatureToPricingTier(String featureId, String pricingTierId) throws WarrantException {
        return createWarrant(new Feature(featureId), "member", new WarrantSubject("pricing-tier", pricingTierId));
    }

    public Warrant assignPermissionToRole(Permission permission, Role role) throws WarrantException {
        return assignPermissionToRole(permission.getPermissionId(), role.getRoleId());
    }

    public Warrant assignPermissionToRole(String permissionId, String roleId) throws WarrantException {
        return createWarrant(new Permission(permissionId), "member", new WarrantSubject("role", roleId));

    }

    // Remove associations
    public void removeRoleFromUser(Role role, User user) throws WarrantException {
        removeRoleFromUser(role.getRoleId(), user.getUserId());
    }

    public void removeRoleFromUser(String roleId, String userId) throws WarrantException {
        deleteWarrant(new Role(roleId), "member", new WarrantSubject("user", userId));
    }

    public void removePermissionFromUser(Permission permission, User user) throws WarrantException {
        removePermissionFromUser(permission.getPermissionId(), user.getUserId());
    }

    public void removePermissionFromUser(String permissionId, String userId) throws WarrantException {
        deleteWarrant(new Permission(permissionId), "member", new WarrantSubject("user", userId));
    }

    public void removePricingTierFromUser(PricingTier pricingTier, User user) throws WarrantException {
        removePricingTierFromUser(pricingTier.getPricingTierId(), user.getUserId());
    }

    public void removePricingTierFromUser(String pricingTierId, String userId) throws WarrantException {
        deleteWarrant(new PricingTier(pricingTierId), "member", new WarrantSubject("user", userId));
    }

    public void removeFeatureFromUser(Feature feature, User user) throws WarrantException {
        removeFeatureFromUser(feature.getFeatureId(), user.getUserId());
    }

    public void removeFeatureFromUser(String featureId, String userId) throws WarrantException {
        deleteWarrant(new Feature(featureId), "member", new WarrantSubject("user", userId));
    }

    public void removeUserFromTenant(User user, Tenant tenant) throws WarrantException {
        removeUserFromTenant(user.getUserId(), tenant.getTenantId());
    }

    public void removeUserFromTenant(String userId, String tenantId) throws WarrantException {
        deleteWarrant(new Tenant(tenantId), "member", new WarrantSubject("user", userId));
    }

    public void removePricingTierFromTenant(PricingTier pricingTier, Tenant tenant) throws WarrantException {
        removePricingTierFromTenant(pricingTier.getPricingTierId(), tenant.getTenantId());
    }

    public void removePricingTierFromTenant(String pricingTierId, String tenantId) throws WarrantException {
        deleteWarrant(new PricingTier(pricingTierId), "member", new WarrantSubject("tenant", tenantId));
    }

    public void removeFeatureFromTenant(Feature feature, Tenant tenant) throws WarrantException {
        removeFeatureFromTenant(feature.getFeatureId(), tenant.getTenantId());
    }

    public void removeFeatureFromTenant(String featureId, String tenantId) throws WarrantException {
        deleteWarrant(new Feature(featureId), "member", new WarrantSubject("tenant", tenantId));
    }

    public void removeFeatureFromPricingTier(Feature feature, PricingTier pricingTier) throws WarrantException {
        removeFeatureFromPricingTier(feature.getFeatureId(), pricingTier.getPricingTierId());
    }

    public void removeFeatureFromPricingTier(String featureId, String pricingTierId) throws WarrantException {
        deleteWarrant(new Feature(featureId), "member", new WarrantSubject("pricing-tier", pricingTierId));
    }

    public void removePermissionFromRole(Permission permission, Role role) throws WarrantException {
        removePermissionFromRole(permission.getPermissionId(), role.getRoleId());
    }

    public void removePermissionFromRole(String permissionId, String roleId) throws WarrantException {
        deleteWarrant(new Permission(permissionId), "member", new WarrantSubject("role", roleId));
    }

    // Checks
    public boolean checkUserHasPermission(User user, String permissionId) throws WarrantException {
        return checkUserHasPermission(user.getUserId(), permissionId);
    }

    public boolean checkUserHasPermission(String userId, String permissionId) throws WarrantException {
        Permission perm = new Permission();
        perm.setPermissionId(permissionId);
        WarrantSubject subject = new WarrantSubject("user", userId);
        return check(perm, "member", subject);
    }

    public boolean checkTenantHasFeature(Tenant tenant, String featureId) throws WarrantException {
        return checkTenantHasFeature(tenant.getTenantId(), featureId);
    }

    public boolean checkTenantHasFeature(String tenantId, String featureId) throws WarrantException {
        Feature feature = new Feature();
        feature.setFeatureId(featureId);
        WarrantSubject subject = new WarrantSubject("tenant", tenantId);
        return check(feature, "member", subject);
    }

    public boolean checkUserHasFeature(User user, String featureId) throws WarrantException {
        return checkUserHasFeature(user.getUserId(), featureId);
    }

    public boolean checkUserHasFeature(String userId, String featureId) throws WarrantException {
        Feature feature = new Feature();
        feature.setFeatureId(featureId);
        WarrantSubject subject = new WarrantSubject("user", userId);
        return check(feature, "member", subject);
    }
}
