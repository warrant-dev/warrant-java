package dev.warrant;

import java.net.http.HttpClient;
import java.util.Arrays;
import java.util.Collections;

import dev.warrant.exception.WarrantException;
import dev.warrant.model.Feature;
import dev.warrant.model.Permission;
import dev.warrant.model.PermissionCheck;
import dev.warrant.model.PricingTier;
import dev.warrant.model.Role;
import dev.warrant.model.Subject;
import dev.warrant.model.Tenant;
import dev.warrant.model.User;
import dev.warrant.model.Warrant;
import dev.warrant.model.WarrantCheck;
import dev.warrant.model.WarrantCheckResult;
import dev.warrant.model.WarrantObject;

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

    public User update(String userId, User toUpdate) throws WarrantException {
        return makePutRequest("/v1/users/" + userId, toUpdate, User.class);
    }

    public void deleteUser(String userId) throws WarrantException {
        makeDeleteRequest("/v1/users/" + userId);
    }

    public User getUser(String userId) throws WarrantException {
        return makeGetRequest("/v1/users/" + userId, User.class);
    }

    // TODO: implement pagination
    public User[] listUsers(int limit, int page) throws WarrantException {
        return makeGetRequest("/v1/users", User[].class);
    }

    public User[] listUsersForTenant(String tenantId) throws WarrantException {
        return makeGetRequest("/v1/tenants/"+tenantId+"/users", User[].class);
    }

    // Tenants
    public Tenant createTenant() throws WarrantException {
        return makePostRequest("/v1/tenants", Collections.EMPTY_MAP, Tenant.class);
    }

    public Tenant createTenant(Tenant tenant) throws WarrantException {
        return makePostRequest("/v1/tenants", tenant, Tenant.class);
    }

    // TODO: add a test for batch ops
    public Tenant[] createTenants(Tenant[] tenants) throws WarrantException {
        return makePostRequest("/v1/tenants", tenants, Tenant[].class);
    }

    public Tenant update(String tenantId, Tenant toUpdate) throws WarrantException {
        return makePutRequest("/v1/tenants/" + tenantId, toUpdate, Tenant.class);
    }

    public void deleteTenant(String tenantId) throws WarrantException {
        makeDeleteRequest("/v1/tenants/" + tenantId);
    }

    public Tenant getTenant(String tenantId) throws WarrantException {
        return makeGetRequest("/v1/tenants/" + tenantId, Tenant.class);
    }

    public Tenant[] listTenants(int limit, int page) throws WarrantException {
        return makeGetRequest("/v1/tenants", Tenant[].class);
    }

    public Tenant[] listTenantsForUser(String userId) throws WarrantException {
        return makeGetRequest("/v1/users/"+userId+"/tenants", Tenant[].class);
    }

    // Roles
    public Role createRole(Role role) throws WarrantException {
        return makePostRequest("/v1/roles", role, Role.class);
    }

    public Role update(String roleId, Role toUpdate) throws WarrantException {
        return makePutRequest("/v1/roles/" + roleId, toUpdate, Role.class);
    }

    public void deleteRole(String roleId) throws WarrantException {
        makeDeleteRequest("/v1/roles/" + roleId);
    }

    public Role getRole(String roleId) throws WarrantException {
        return makeGetRequest("/v1/roles/" + roleId, Role.class);
    }

    public Role[] listRoles(int limit, int page) throws WarrantException {
        return makeGetRequest("/v1/roles", Role[].class);
    }

    public Role[] listRolesForUser(String userId) throws WarrantException {
        return makeGetRequest("/v1/users/"+userId+"/roles", Role[].class);
    }

    // Permissions
    public Permission createPermission(Permission permission) throws WarrantException {
        return makePostRequest("/v1/permissions", permission, Permission.class);
    }

    public Permission update(String permissionId, Permission toUpdate) throws WarrantException {
        return makePutRequest("/v1/permissions/" + permissionId, toUpdate, Permission.class);
    }

    public void deletePermission(String permissionId) throws WarrantException {
        makeDeleteRequest("/v1/permissions/" + permissionId);
    }

    public Permission getPermission(String permissionId) throws WarrantException {
        return makeGetRequest("/v1/permissions/" + permissionId, Permission.class);
    }

    public Permission[] listPermissions(int limit, int page) throws WarrantException {
        return makeGetRequest("/v1/permissions", Permission[].class);
    }

    public Permission[] listPermissionsForUser(String userId) throws WarrantException {
        return makeGetRequest("/v1/users/"+userId+"/permissions", Permission[].class);
    }

    public Permission[] listPermissionsForRole(String roleId) throws WarrantException {
        return makeGetRequest("/v1/roles/"+roleId+"/permissions", Permission[].class);
    }

    // Features
    public Feature createFeature(Feature feature) throws WarrantException {
        return makePostRequest("/v1/features", feature, Feature.class);
    }

    public void deleteFeature(String featureId) throws WarrantException {
        makeDeleteRequest("/v1/features/" + featureId);
    }

    public Feature getFeature(String featureId) throws WarrantException {
        return makeGetRequest("/v1/features/" + featureId, Feature.class);
    }

    public Feature[] listFeatures(int limit, int page) throws WarrantException {
        return makeGetRequest("/v1/features", Feature[].class);
    }

    public Feature[] listFeaturesForUser(String userId) throws WarrantException {
        return makeGetRequest("/v1/users/"+userId+"/features", Feature[].class);
    }

    public Feature[] listFeaturesForTenant(String tenantId) throws WarrantException {
        return makeGetRequest("/v1/tenants/"+tenantId+"/features", Feature[].class);
    }

    public Feature[] listFeaturesForPricingTier(String pricingTierId) throws WarrantException {
        return makeGetRequest("/v1/pricing-tiers/"+pricingTierId+"/features", Feature[].class);
    }

    // Pricing Tiers
    public PricingTier createPricingTier(PricingTier pricingTier) throws WarrantException {
        return makePostRequest("/v1/pricing-tiers", pricingTier, PricingTier.class);
    }

    public void deletePricingTier(String pricingTierId) throws WarrantException {
        makeDeleteRequest("/v1/pricing-tiers/" + pricingTierId);
    }

    public PricingTier getPricingTier(String pricingTierId) throws WarrantException {
        return makeGetRequest("/v1/pricing-tiers/" + pricingTierId, PricingTier.class);
    }

    public PricingTier[] listPricingTiers(int limit, int page) throws WarrantException {
        return makeGetRequest("/v1/pricing-tiers", PricingTier[].class);
    }

    public PricingTier[] listPricingTiersForTenant(String tenantId) throws WarrantException {
        return makeGetRequest("/v1/tenants/"+tenantId+"/pricing-tiers", PricingTier[].class);
    }

    public PricingTier[] listPricingTiersForUser(String userId) throws WarrantException {
        return makeGetRequest("/v1/users/"+userId+"/pricing-tiers", PricingTier[].class);
    }

    // Assign
    public void assignTo(WarrantObject objToAssign, WarrantObject assignTo) throws WarrantException {
        if (objToAssign == null || assignTo == null) {
            throw new WarrantException("");
        }
        if (assignTo instanceof User) {
            String uri = "/v1/users/" + assignTo.id();
            if (objToAssign instanceof Role) {
                makePostRequest(uri + "/roles/" + objToAssign.id(), Collections.EMPTY_MAP);
            } else if (objToAssign instanceof Permission) {
                makePostRequest(uri + "/permissions/" + objToAssign.id(), Collections.EMPTY_MAP);
            } else if (objToAssign instanceof PricingTier) {
                makePostRequest(uri + "/pricing-tiers/" + objToAssign.id(), Collections.EMPTY_MAP);
            } else if (objToAssign instanceof Feature) {
                makePostRequest(uri + "/features/" + objToAssign.id(), Collections.EMPTY_MAP);
            } else {
                throw new WarrantException("");
            }
        } else if (assignTo instanceof Tenant) {
            String uri = "/v1/tenants/" + assignTo.id();
            if (objToAssign instanceof User) {
                makePostRequest(uri + "/users/" + objToAssign.id(), Collections.EMPTY_MAP);
            } else if (objToAssign instanceof PricingTier) {
                makePostRequest(uri + "/pricing-tiers/" + objToAssign.id(), Collections.EMPTY_MAP);
            } else if (objToAssign instanceof Feature) {
                makePostRequest(uri + "/features/" + objToAssign.id(), Collections.EMPTY_MAP);
            } else {
                throw new WarrantException("");
            }
        } else if (objToAssign instanceof Feature && assignTo instanceof PricingTier) {
            makePostRequest("/v1/pricing-tiers/" + assignTo.id() + "/features/" + objToAssign.id(), Collections.EMPTY_MAP);
        } else if (objToAssign instanceof Permission && assignTo instanceof Role) {
            makePostRequest("/v1/roles/" + assignTo.id() + "/permissions/" + objToAssign.id(), Collections.EMPTY_MAP);
        } else {
            throw new WarrantException("");
        }
    }

    // Remove associations
    public void removeFrom(WarrantObject objToRemove, WarrantObject removeFrom) throws WarrantException {
        if (objToRemove == null || removeFrom == null) {
            throw new WarrantException("");
        }
        if (removeFrom instanceof User) {
            String uri = "/v1/users/" + removeFrom.id();
            if (objToRemove instanceof Role) {
                makeDeleteRequest(uri + "/roles/" + objToRemove.id());
            } else if (objToRemove instanceof Permission) {
                makeDeleteRequest(uri + "/permissions/" + objToRemove.id());
            } else if (objToRemove instanceof PricingTier) {
                makeDeleteRequest(uri + "/pricing-tiers/" + objToRemove.id());
            } else if (objToRemove instanceof Feature) {
                makeDeleteRequest(uri + "/features/" + objToRemove.id());
            } else {
                throw new WarrantException("");
            }
        } else if (removeFrom instanceof Tenant) {
            String uri = "/v1/tenants/" + removeFrom.id();
            if (objToRemove instanceof User) {
                makeDeleteRequest(uri + "/users/" + objToRemove.id());
            } else if (objToRemove instanceof PricingTier) {
                makeDeleteRequest(uri + "/pricing-tiers/" + objToRemove.id());
            } else if (objToRemove instanceof Feature) {
                makeDeleteRequest(uri + "/features/" + objToRemove.id());
            } else {
                throw new WarrantException("");
            }
        } else if (objToRemove instanceof Feature && removeFrom instanceof PricingTier) {
            makeDeleteRequest("/v1/pricing-tiers/" + removeFrom.id() + "/features/" + objToRemove.id());
        } else if (objToRemove instanceof Permission && removeFrom instanceof Role) {
            makeDeleteRequest("/v1/roles/" + removeFrom.id() + "/permissions/" + objToRemove.id());
        } else {
            throw new WarrantException("");
        }
    }

    // Checks
    public boolean hasPermission(String userId, String permissionId) throws WarrantException {
        Subject userSubject = new Subject("user", userId);
        Warrant permissionWarrant = new Warrant("permission", permissionId, "member", userSubject);
        WarrantCheck warrantCheck = new WarrantCheck(Arrays.asList(permissionWarrant));
        WarrantCheckResult result = check(warrantCheck);
        return result.getCode() == 200;
    }

    public boolean hasPermission(PermissionCheck permissionCheck) throws WarrantException {
        Subject userSubject = new Subject("user", permissionCheck.getUserId());
        Warrant permissionWarrant = new Warrant("permission", permissionCheck.getPermissionId(), "member", userSubject);
        WarrantCheck warrantCheck = new WarrantCheck(Arrays.asList(permissionWarrant));
        WarrantCheckResult result = check(warrantCheck);
        return result.getCode() == 200;
    }

    public boolean hasFeature(Tenant tenant, String featureId) throws WarrantException {
        Subject tenantSubject = new Subject("tenant", tenant.getTenantId());
        Warrant featureWarrant = new Warrant("feature", featureId, "member", tenantSubject);
        WarrantCheck warrantCheck = new WarrantCheck(Arrays.asList(featureWarrant));
        WarrantCheckResult result = check(warrantCheck);
        return result.getCode() == 200;        
    }

    public boolean hasFeature(String userId, String featureId) throws WarrantException {
        Subject userSubject = new Subject("user", userId);
        Warrant featureWarrant = new Warrant("feature", featureId, "member", userSubject);
        WarrantCheck warrantCheck = new WarrantCheck(Arrays.asList(featureWarrant));
        WarrantCheckResult result = check(warrantCheck);
        return result.getCode() == 200;
    }
}
