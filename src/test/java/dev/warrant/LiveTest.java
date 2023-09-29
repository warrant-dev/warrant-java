package dev.warrant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import dev.warrant.exception.WarrantException;
import dev.warrant.model.QueryResultSet;
import dev.warrant.model.Warrant;
import dev.warrant.model.WarrantCheck;
import dev.warrant.model.WarrantSpec;
import dev.warrant.model.WarrantSubject;
import dev.warrant.model.object.Feature;
import dev.warrant.model.object.Permission;
import dev.warrant.model.object.PricingTier;
import dev.warrant.model.object.Role;
import dev.warrant.model.object.Tenant;
import dev.warrant.model.object.User;

@Disabled("Remove this annotation and add your API_KEY below to run these against the live server")
public class LiveTest {

    private static final String API_KEY = "";
    private WarrantClient client;

    @BeforeEach
    public void setup() {
        client = new WarrantClient(new WarrantConfig(API_KEY, "https://api.warrant.dev", "https://api.warrant.dev"));
    }

    @Test
    public void crudUsers() throws WarrantException {
        User user1 = client.createUser();
        Assertions.assertNotNull(user1.getUserId());
        Assertions.assertNull(user1.getEmail());

        User user2 = client.createUser(new User("some_id", "test@email.com"));
        RequestOptions requestOptions = new RequestOptions().withWarrantToken("latest");
        User refetchedUser = client.getUser(user2.getUserId(), requestOptions);
        Assertions.assertEquals(user2.getUserId(), refetchedUser.getUserId());
        Assertions.assertEquals(user2.getEmail(), refetchedUser.getEmail());

        user2 = client.updateUser("some_id", new User("some_id", "updated@email.com"));
        refetchedUser = client.getUser("some_id", requestOptions);
        Assertions.assertEquals("some_id", refetchedUser.getUserId());
        Assertions.assertEquals("updated@email.com", refetchedUser.getEmail());

        ListParams listParams = new ListParams().withLimit(10).withPage(1);
        User[] users = client.listUsers(listParams, requestOptions);
        Assertions.assertEquals(2, users.length);

        client.deleteUser(user1);
        client.deleteUser(user2);
        users = client.listUsers(10, 1, requestOptions);
        Assertions.assertEquals(0, users.length);
    }

    @Test
    public void crudTenants() throws WarrantException {
        Tenant tenant1 = client.createTenant();
        Assertions.assertNotNull(tenant1.getTenantId());
        Assertions.assertNull(tenant1.getName());

        Tenant tenant2 = client.createTenant(new Tenant("some_tenant_Id", "new_name"));
        RequestOptions requestOptions = new RequestOptions().withWarrantToken("latest");
        Tenant refetchedTenant = client.getTenant(tenant2.getTenantId(), requestOptions);
        Assertions.assertEquals(tenant2.getTenantId(), refetchedTenant.getTenantId());
        Assertions.assertEquals(tenant2.getName(), refetchedTenant.getName());

        tenant2 = client.updateTenant("some_tenant_Id", new Tenant("some_tenant_Id", "updated_name"));
        refetchedTenant = client.getTenant("some_tenant_Id", requestOptions);
        Assertions.assertEquals("some_tenant_Id", refetchedTenant.getTenantId());
        Assertions.assertEquals("updated_name", refetchedTenant.getName());

        Tenant[] tenants = client.listTenants(10, 1, requestOptions);
        Assertions.assertEquals(2, tenants.length);

        client.deleteTenant(tenant1);
        client.deleteTenant(tenant2);
        tenants = client.listTenants(10, 1, requestOptions);
        Assertions.assertEquals(0, tenants.length);
    }

    @Test
    public void crudRoles() throws WarrantException {
        Role adminRole = client.createRole(new Role("admin", "Admin", "The admin role"));
        Assertions.assertEquals("admin", adminRole.getRoleId());
        Assertions.assertEquals("Admin", adminRole.getName());
        Assertions.assertEquals("The admin role", adminRole.getDescription());

        Role viewerRole = client.createRole(new Role("viewer", "Viewer", "The viewer role"));
        RequestOptions requestOptions = new RequestOptions().withWarrantToken("latest");
        Role refetchedRole = client.getRole(viewerRole.getRoleId(), requestOptions);
        Assertions.assertEquals(viewerRole.getRoleId(), refetchedRole.getRoleId());
        Assertions.assertEquals(viewerRole.getName(), refetchedRole.getName());
        Assertions.assertEquals(viewerRole.getDescription(), refetchedRole.getDescription());

        viewerRole = client.updateRole("viewer", new Role("viewer", "Viewer Updated", "Updated desc"));
        refetchedRole = client.getRole("viewer", requestOptions);
        Assertions.assertEquals("viewer", refetchedRole.getRoleId());
        Assertions.assertEquals("Viewer Updated", refetchedRole.getName());
        Assertions.assertEquals("Updated desc", refetchedRole.getDescription());

        Role[] roles = client.listRoles(10, 1, requestOptions);
        Assertions.assertEquals(2, roles.length);

        client.deleteRole(adminRole);
        client.deleteRole(viewerRole);
        roles = client.listRoles(10, 1, requestOptions);
        Assertions.assertEquals(0, roles.length);
    }

    @Test
    public void crudPermissions() throws WarrantException {
        Permission permission1 = client
                .createPermission(new Permission("perm1", "Permission 1", "Permission with id 1"));
        Assertions.assertEquals("perm1", permission1.getPermissionId());
        Assertions.assertEquals("Permission 1", permission1.getName());
        Assertions.assertEquals("Permission with id 1", permission1.getDescription());

        Permission permission2 = client
                .createPermission(new Permission("perm2", "Permission 2", "Permission with id 2"));
        RequestOptions requestOptions = new RequestOptions().withWarrantToken("latest");
        Permission refetchedPermission = client.getPermission(permission2.getPermissionId(), requestOptions);
        Assertions.assertEquals(permission2.getPermissionId(), refetchedPermission.getPermissionId());
        Assertions.assertEquals(permission2.getName(), refetchedPermission.getName());
        Assertions.assertEquals(permission2.getDescription(), refetchedPermission.getDescription());

        permission2 = client.updatePermission("perm2", new Permission("perm2", "Permission 2 Updated", "Updated desc"));
        refetchedPermission = client.getPermission("perm2", requestOptions);
        Assertions.assertEquals("perm2", refetchedPermission.getPermissionId());
        Assertions.assertEquals("Permission 2 Updated", refetchedPermission.getName());
        Assertions.assertEquals("Updated desc", refetchedPermission.getDescription());

        Permission[] permissions = client.listPermissions(10, 1, requestOptions);
        Assertions.assertEquals(2, permissions.length);

        client.deletePermission(permission1);
        client.deletePermission(permission2);
        permissions = client.listPermissions(10, 1, requestOptions);
        Assertions.assertEquals(0, permissions.length);
    }

    @Test
    public void crudFeatures() throws WarrantException {
        Feature feature1 = client.createFeature(new Feature("new-feature"));
        Assertions.assertEquals("new-feature", feature1.getFeatureId());

        Feature feature2 = client.createFeature(new Feature("feature-2"));
        RequestOptions requestOptions = new RequestOptions().withWarrantToken("latest");
        Feature refetchedFeature = client.getFeature(feature2.getFeatureId(), requestOptions);
        Assertions.assertEquals(feature2.getFeatureId(), refetchedFeature.getFeatureId());

        Feature[] features = client.listFeatures(10, 1, requestOptions);
        Assertions.assertEquals(2, features.length);

        client.deleteFeature(feature1);
        client.deleteFeature(feature2);
        features = client.listFeatures(10, 1, requestOptions);
        Assertions.assertEquals(0, features.length);
    }

    @Test
    public void crudPricingTiers() throws WarrantException {
        PricingTier tier1 = client.createPricingTier(new PricingTier("new-tier1"));
        Assertions.assertEquals("new-tier1", tier1.getPricingTierId());

        PricingTier tier2 = client.createPricingTier(new PricingTier("tier-2"));
        RequestOptions requestOptions = new RequestOptions().withWarrantToken("latest");
        PricingTier refetchedTier = client.getPricingTier(tier2.getPricingTierId(), requestOptions);
        Assertions.assertEquals(tier2.getPricingTierId(), refetchedTier.getPricingTierId());

        PricingTier[] tiers = client.listPricingTiers(10, 1, requestOptions);
        Assertions.assertEquals(2, tiers.length);

        client.deletePricingTier(tier1);
        client.deletePricingTier(tier2);
        tiers = client.listPricingTiers(10, 1, requestOptions);
        Assertions.assertEquals(0, tiers.length);
    }

    @Test
    public void crudObjects() throws WarrantException {
        // BaseWarrantObject roleGeneratedId = client.createObject("role");
        // Assertions.assertEquals("role", roleGeneratedId.getObjectType());
        // Assertions.assertNotNull(roleGeneratedId.getObjectId());
        // Assertions.assertNull(roleGeneratedId.getMeta());

        // Map<String, Object> meta = new HashMap<>();
        // meta.put("name", "admin role");
        // BaseWarrantObject roleWithMeta = client.createObject("role", "admin", meta);
        // Assertions.assertEquals("role", roleWithMeta.getObjectType());
        // Assertions.assertEquals("admin", roleWithMeta.getObjectId());
        // Assertions.assertNotNull(roleWithMeta.getMeta());
        // Assertions.assertEquals("admin role", roleWithMeta.getMeta().get("name"));

        // Map<String, Object> updatedMeta = new HashMap<>();
        // updatedMeta.put("name", "admin role");
        // updatedMeta.put("description", "role description");
        // BaseWarrantObject updatedRole = client.updateObject(roleWithMeta.getObjectType(), roleWithMeta.getObjectId(), updatedMeta);
        // Assertions.assertEquals(roleWithMeta.getObjectType(), updatedRole.getObjectType());
        // Assertions.assertEquals(roleWithMeta.getObjectId(), updatedRole.getObjectId());
        // Assertions.assertEquals(2, updatedRole.getMeta().size());
        // Assertions.assertEquals("admin role", updatedRole.getMeta().get("name"));
        // Assertions.assertEquals("role description", updatedRole.getMeta().get("description"));

        // BaseWarrantObject fetchedObj = client.getObject(roleGeneratedId.getObjectType(), roleGeneratedId.getObjectId());
        // Assertions.assertEquals(roleGeneratedId.getObjectType(), fetchedObj.getObjectType());
        // Assertions.assertEquals(roleGeneratedId.getObjectId(), fetchedObj.getObjectId());
        // Assertions.assertEquals(roleGeneratedId.getMeta(), fetchedObj.getMeta());

        User user = client.createUser(new User("object_test_1", "email@email.com"));
        Assertions.assertEquals("object_test_1", user.getUserId());
        Assertions.assertEquals("email@email.com", user.getEmail());
        client.deleteObject(user);
    }

    @Test
    public void batchCreateUsersAndTenants() throws WarrantException {
        User[] newUsers = { new User("user-1"), new User("user-2") };
        User[] createdUsers = client.createUsers(newUsers);
        Assertions.assertEquals(2, createdUsers.length);
        Assertions.assertEquals("user-1", createdUsers[0].getUserId());
        Assertions.assertEquals("user-2", createdUsers[1].getUserId());

        Tenant[] newTenants = { new Tenant("tenant-1"), new Tenant("tenant-2") };
        Tenant[] createdTenants = client.createTenants(newTenants);
        Assertions.assertEquals(2, createdTenants.length);
        Assertions.assertEquals("tenant-1", createdTenants[0].getTenantId());
        Assertions.assertEquals("tenant-2", createdTenants[1].getTenantId());

        client.deleteUser("user-1");
        client.deleteUser("user-2");
        client.deleteTenant("tenant-1");
        client.deleteTenant("tenant-2");
    }

    @Test
    public void exampleMultiTenancy() throws WarrantException {
        // Create users
        User user1 = client.createUser();
        User user2 = client.createUser();

        // Create tenants
        Tenant tenant1 = client.createTenant(new Tenant("tenant-1", "Tenant 1"));
        Tenant tenant2 = client.createTenant(new Tenant("tenant-2", "Tenant 2"));

        // Assign user1 -> tenant1
        RequestOptions requestOptions = new RequestOptions().withWarrantToken("latest");
        Assertions.assertEquals(0, client.listTenantsForUser(user1, 100, 1, requestOptions).length);
        Assertions.assertEquals(0, client.listUsersForTenant("tenant-1", 100, 1, requestOptions).length);
        client.assignUserToTenant(user1, tenant1);
        Tenant[] tenants = client.listTenantsForUser(user1, 100, 1, requestOptions);
        Assertions.assertEquals(1, tenants.length);
        Assertions.assertEquals("tenant-1", tenants[0].getTenantId());
        User[] users = client.listUsersForTenant("tenant-1", 100, 1, requestOptions);
        Assertions.assertEquals(1, users.length);
        Assertions.assertEquals(user1.getUserId(), users[0].getUserId());
        Assertions.assertEquals(1, client.listTenantsForUser(user1, 100, 1, requestOptions).length);
        client.removeUserFromTenant(user1, tenant1);
        Assertions.assertEquals(0, client.listTenantsForUser(user1, 100, 1, requestOptions).length);

        // Clean up
        client.deleteUser(user1);
        client.deleteUser(user2);
        client.deleteTenant(tenant1);
        client.deleteTenant(tenant2);
    }

    @Test
    public void exampleRbac() throws WarrantException {
        // Create users
        User adminUser = client.createUser();
        User viewer = client.createUser();

        // Create roles
        Role adminRole = client.createRole(new Role("admin", "Admin", "The admin role"));
        Role viewerRole = client.createRole(new Role("viewer", "Viewer", "The viewer role"));

        // Create permissions
        Permission createPermission = client
                .createPermission(new Permission("create-report", "Create Report", "Permission to create reports"));
        Permission viewPermission = client
                .createPermission(new Permission("view-report", "View Report", "Permission to view reports"));

        // Assign 'create-report' -> admin role -> admin user
        RequestOptions requestOptions = new RequestOptions().withWarrantToken("latest");
        Assertions.assertEquals(0, client.listRolesForUser(adminUser, 100, 1, requestOptions).length);
        Assertions.assertEquals(0, client.listPermissionsForRole(adminRole, 100, 1, requestOptions).length);
        Assertions.assertFalse(client.checkUserHasPermission(adminUser, "create-report", requestOptions));
        client.assignPermissionToRole(createPermission, adminRole);
        client.assignRoleToUser(adminRole, adminUser);
        Permission[] adminPermissions = client.listPermissionsForRole(adminRole, 100, 1, requestOptions);
        Assertions.assertEquals(1, adminPermissions.length);
        Assertions.assertEquals("create-report", adminPermissions[0].getPermissionId());
        Assertions.assertTrue(client.checkUserHasPermission(adminUser, "create-report", requestOptions));
        Role[] userRoles = client.listRolesForUser(adminUser, 100, 1, requestOptions);
        Assertions.assertEquals(1, userRoles.length);
        Assertions.assertEquals("admin", userRoles[0].getRoleId());
        client.removePermissionFromRole(createPermission, adminRole);
        Assertions.assertFalse(client.checkUserHasPermission(adminUser, "create-report", requestOptions));
        Assertions.assertEquals(1, client.listRolesForUser(adminUser.getUserId(), 100, 1, requestOptions).length);
        client.removeRoleFromUser(adminRole, adminUser);
        Assertions.assertEquals(0, client.listRolesForUser(adminUser.getUserId(), 100, 1, requestOptions).length);

        // Assign 'view-report' -> viewer user
        Assertions.assertFalse(client.checkUserHasPermission(viewer, "view-report", requestOptions));
        Assertions.assertEquals(0, client.listPermissionsForUser(viewer, 100, 1, requestOptions).length);
        client.assignPermissionToUser(viewPermission, viewer);
        Assertions.assertTrue(client.checkUserHasPermission(viewer, "view-report", requestOptions));
        Permission[] userPermissions = client.listPermissionsForUser(viewer, 100, 1, requestOptions);
        Assertions.assertEquals(1, userPermissions.length);
        Assertions.assertEquals("view-report", userPermissions[0].getPermissionId());
        client.removePermissionFromUser(viewPermission, viewer);
        Assertions.assertFalse(client.checkUserHasPermission(viewer, "view-report", requestOptions));
        Assertions.assertEquals(0, client.listPermissionsForUser(viewer, 100, 1, requestOptions).length);

        // Clean up
        client.deleteUser(adminUser);
        client.deleteUser(viewer);
        client.deleteRole(adminRole);
        client.deleteRole(viewerRole);
        client.deletePermission(createPermission);
        client.deletePermission(viewPermission);
    }

    @Test
    public void examplePricingTiersAndFeaturesUsers() throws WarrantException {
        // Create users
        User freeUser = client.createUser();
        User paidUser = client.createUser();

        // Create pricing tiers
        PricingTier freeTier = client.createPricingTier(new PricingTier("free"));
        PricingTier paidTier = client.createPricingTier(new PricingTier("paid"));

        // Create features
        Feature customFeature = client.createFeature(new Feature("custom-feature"));
        Feature feature1 = client.createFeature(new Feature("feature-1"));
        Feature feature2 = client.createFeature(new Feature("feature-2"));

        // Assign 'custom-feature' -> paid user
        RequestOptions requestOptions = new RequestOptions().withWarrantToken("latest");
        Assertions.assertFalse(client.checkUserHasFeature(paidUser, "custom-feature", requestOptions));
        Assertions.assertEquals(0, client.listFeaturesForUser(paidUser, 100, 1, requestOptions).length);
        client.assignFeatureToUser(customFeature, paidUser);
        Assertions.assertTrue(client.checkUserHasFeature(paidUser, "custom-feature", requestOptions));
        Feature[] paidUserFeatures = client.listFeaturesForUser(paidUser, 100, 1, requestOptions);
        Assertions.assertEquals(1, paidUserFeatures.length);
        Assertions.assertEquals("custom-feature", paidUserFeatures[0].getFeatureId());
        client.removeFeatureFromUser(customFeature, paidUser);
        Assertions.assertFalse(client.checkUserHasFeature(paidUser, "custom-feature", requestOptions));
        Assertions.assertEquals(0, client.listFeaturesForUser(paidUser, 100, 1, requestOptions).length);

        // Assign 'feature-1' -> 'free' tier -> free user
        Assertions.assertFalse(client.checkUserHasFeature(freeUser, "feature-1", requestOptions));
        Assertions.assertEquals(0, client.listFeaturesForPricingTier("free", 100, 1, requestOptions).length);
        Assertions.assertEquals(0, client.listPricingTiersForUser(freeUser, 100, 1, requestOptions).length);
        client.assignFeatureToPricingTier(feature1, freeTier);
        client.assignPricingTierToUser(freeTier, freeUser);
        Assertions.assertTrue(client.checkUserHasFeature(freeUser, "feature-1", requestOptions));
        Feature[] freeTierFeatures = client.listFeaturesForPricingTier("free", 100, 1, requestOptions);
        Assertions.assertEquals(1, freeTierFeatures.length);
        Assertions.assertEquals("feature-1", freeTierFeatures[0].getFeatureId());
        PricingTier[] freeUserTiers = client.listPricingTiersForUser(freeUser, 100, 1, requestOptions);
        Assertions.assertEquals(1, freeUserTiers.length);
        Assertions.assertEquals("free", freeUserTiers[0].getPricingTierId());
        client.removeFeatureFromPricingTier(feature1, freeTier);
        Assertions.assertFalse(client.checkUserHasFeature(freeUser, "feature-1", requestOptions));
        Assertions.assertEquals(0, client.listFeaturesForPricingTier("free", 100, 1, requestOptions).length);
        Assertions.assertEquals(1, client.listPricingTiersForUser(freeUser, 100, 1, requestOptions).length);
        client.removePricingTierFromUser(freeTier, freeUser);
        Assertions.assertEquals(0, client.listPricingTiersForUser(freeUser, 100, 1, requestOptions).length);

        // Clean up
        client.deleteUser(freeUser);
        client.deleteUser(paidUser);
        client.deletePricingTier(freeTier);
        client.deletePricingTier(paidTier);
        client.deleteFeature(customFeature);
        client.deleteFeature(feature1);
        client.deleteFeature(feature2);
    }

    @Test
    public void examplePricingTiersAndFeaturesTenants() throws WarrantException {
        // Create tenants
        Tenant freeTenant = client.createTenant();
        Tenant paidTenant = client.createTenant();

        // Create pricing tiers
        PricingTier freeTier = client.createPricingTier(new PricingTier("free"));
        PricingTier paidTier = client.createPricingTier(new PricingTier("paid"));

        // Create features
        Feature customFeature = client.createFeature(new Feature("custom-feature"));
        Feature feature1 = client.createFeature(new Feature("feature-1"));
        Feature feature2 = client.createFeature(new Feature("feature-2"));

        // Assign 'custom-feature' -> paid tenant
        RequestOptions requestOptions = new RequestOptions().withWarrantToken("latest");
        Assertions.assertFalse(client.checkTenantHasFeature(paidTenant, "custom-feature", requestOptions));
        Assertions.assertEquals(0, client.listFeaturesForTenant(paidTenant, 100, 1, requestOptions).length);
        client.assignFeatureToTenant(customFeature, paidTenant);
        Assertions.assertTrue(client.checkTenantHasFeature(paidTenant, "custom-feature", requestOptions));
        Feature[] paidTenantFeatures = client.listFeaturesForTenant(paidTenant, 100, 1, requestOptions);
        Assertions.assertEquals(1, paidTenantFeatures.length);
        Assertions.assertEquals("custom-feature", paidTenantFeatures[0].getFeatureId());
        client.removeFeatureFromTenant(customFeature, paidTenant);
        Assertions.assertFalse(client.checkTenantHasFeature(paidTenant, "custom-feature", requestOptions));
        Assertions.assertEquals(0, client.listFeaturesForTenant(paidTenant, 100, 1, requestOptions).length);

        // Assign 'feature-1' -> 'free' tier -> free tenant
        Assertions.assertFalse(client.checkTenantHasFeature(freeTenant, "feature-1", requestOptions));
        Assertions.assertEquals(0, client.listFeaturesForPricingTier("free", 100, 1, requestOptions).length);
        Assertions.assertEquals(0, client.listPricingTiersForTenant(freeTenant, 100, 1, requestOptions).length);
        client.assignFeatureToPricingTier(feature1, freeTier);
        client.assignPricingTierToTenant(freeTier, freeTenant);
        Assertions.assertTrue(client.checkTenantHasFeature(freeTenant, "feature-1", requestOptions));
        Feature[] freeTierFeatures = client.listFeaturesForPricingTier("free", 100, 1, requestOptions);
        Assertions.assertEquals(1, freeTierFeatures.length);
        Assertions.assertEquals("feature-1", freeTierFeatures[0].getFeatureId());
        PricingTier[] freeTenantTiers = client.listPricingTiersForTenant(freeTenant, 100, 1, requestOptions);
        Assertions.assertEquals(1, freeTenantTiers.length);
        Assertions.assertEquals("free", freeTenantTiers[0].getPricingTierId());
        client.removeFeatureFromPricingTier(feature1, freeTier);
        Assertions.assertFalse(client.checkTenantHasFeature(freeTenant, "feature-1", requestOptions));
        Assertions.assertEquals(0, client.listFeaturesForPricingTier("free", 100, 1, requestOptions).length);
        Assertions.assertEquals(1, client.listPricingTiersForTenant(freeTenant, 100, 1, requestOptions).length);
        client.removePricingTierFromTenant(freeTier, freeTenant);
        Assertions.assertEquals(0, client.listPricingTiersForTenant(freeTenant, 100, 1, requestOptions).length);

        // Clean up
        client.deleteTenant(freeTenant);
        client.deleteTenant(paidTenant);
        client.deletePricingTier(freeTier);
        client.deletePricingTier(paidTier);
        client.deleteFeature(customFeature);
        client.deleteFeature(feature1);
        client.deleteFeature(feature2);
    }

    @Test
    public void sessions() throws WarrantException {
        User user = client.createUser();
        Tenant tenant = client.createTenant();
        client.createWarrant(tenant, "admin", new WarrantSubject(user.type(), user.id()));

        Assertions.assertNotNull(client.createUserAuthzSession(user.getUserId()));
        Assertions
                .assertNotNull(client.createUserSelfServiceDashboardUrl(user.getUserId(), tenant.getTenantId(), "rbac",
                        "http://localhost:8080"));

        client.deleteUser(user);
        client.deleteTenant(tenant);
    }

    @Test
    public void warrants() throws WarrantException {
        User user = client.createUser();
        Permission permission1 = client.createPermission(new Permission("perm1"));
        Permission permission2 = client.createPermission(new Permission("perm2"));
        Role role1 = client.createRole(new Role("role1"));

        WarrantSubject subject = new WarrantSubject(user.type(), user.id());
        client.createWarrant(permission1, "member", subject);
        client.createWarrant(permission2, "member", subject);
        client.createWarrant(role1, "member", subject);

        RequestOptions requestOptions = new RequestOptions().withWarrantToken("latest");
        WarrantFilters filters = new WarrantFilters().withObjectType("permission");
        ListParams listParams = new ListParams();
        Warrant[] warrants = client.listWarrants(filters, listParams, requestOptions);
        Assertions.assertEquals(2, warrants.length);

        filters = filters.withObjectType("role");
        warrants = client.listWarrants(filters, listParams,
                requestOptions);
        Assertions.assertEquals(1, warrants.length);

        client.deleteWarrant(permission1, "member", subject);
        client.deleteWarrant(permission2, "member", subject);
        client.deleteWarrant(role1, "member", subject);
        client.deletePermission(permission1);
        client.deletePermission(permission2);
        client.deleteRole(role1);
        client.deleteUser(user);
    }

    @Test
    public void warrantsWithPolicy() throws WarrantException {
        User testUser = client.createUser(new User("test-user"));
        Permission testPermission = client
                .createPermission(new Permission("test-permission"));

        client.createWarrant(testPermission, "member", new WarrantSubject(testUser.type(), testUser.id()),
                "geo == 'us' && isActivated == true");
        Map<String, Object> warrantContext = new HashMap<>();
        warrantContext.put("geo", "us");
        warrantContext.put("isActivated", true);
        RequestOptions requestOptions = new RequestOptions().withWarrantToken("latest");
        Assertions.assertTrue(client.check(testPermission, "member", new WarrantSubject(testUser.type(), testUser.id()),
                warrantContext, requestOptions));

        warrantContext.clear();
        warrantContext.put("geo", "eu");
        warrantContext.put("isActivated", false);
        Assertions.assertFalse(client.check(testPermission, "member",
                new WarrantSubject(testUser.type(), testUser.id()), warrantContext, requestOptions));

        client.deleteWarrant(testPermission, "member", new WarrantSubject(testUser.type(), testUser.id()),
                "geo == 'us' && isActivated == true");
        client.deleteUser(testUser);
        client.deletePermission(testPermission);
    }

    @Test
    public void testAllOfAnyOfBatchCheck() throws WarrantException {
        User user = client.createUser();
        Permission permission1 = client.createPermission(new Permission("perm1b"));
        Permission permission2 = client.createPermission(new Permission("perm2b"));
        Permission permission3 = client.createPermission(new Permission("perm3b"));
        WarrantSubject userSubject = new WarrantSubject(user.type(), user.id());
        client.createWarrant(permission1, "member", userSubject);
        client.createWarrant(permission2, "member", userSubject);

        List<WarrantSpec> checks = new ArrayList<>();
        checks.add(new WarrantSpec("permission", permission1.id(), "member", userSubject));
        checks.add(new WarrantSpec("permission", permission2.id(), "member", userSubject));
        List<WarrantCheck> results = client.checkBatch(checks);
        Assertions.assertEquals(2, results.size());
        Assertions.assertTrue(results.get(0).isAuthorized());
        Assertions.assertTrue(results.get(1).isAuthorized());

        checks = new ArrayList<>();
        checks.add(new WarrantSpec("permission", permission1.id(), "member", userSubject));
        checks.add(new WarrantSpec("permission", permission3.id(), "member", userSubject));
        results = client.checkBatch(checks);
        Assertions.assertEquals(2, results.size());
        Assertions.assertTrue(results.get(0).isAuthorized());
        Assertions.assertFalse(results.get(1).isAuthorized());

        WarrantCheck result = client.checkAnyOf(checks);
        Assertions.assertTrue(result.isAuthorized());

        result = client.checkAllOf(checks);
        Assertions.assertFalse(result.isAuthorized());

        client.deleteWarrant(permission1, "member", userSubject);
        client.deleteWarrant(permission2, "member", userSubject);
        client.deleteUser(user);
        client.deletePermission(permission1);
        client.deletePermission(permission2);
        client.deletePermission(permission3);
    }

    @Test
    public void testQuery() throws WarrantException {
        User userA = client.createUser(new User("userA"));
        User userB = client.createUser(new User("userB"));
        Permission permission1 = client.createPermission(
                new Permission("perm1", "Permission 1", "This is permission 1."));
        Permission permission2 = client.createPermission(new Permission("perm2"));
        Permission permission3 = client.createPermission(
                new Permission("perm3", "Permission 3", "This is permission 3."));
        Role role1 = client.createRole(new Role("role1", "Role 1", "This is role 1."));
        Role role2 = client.createRole(new Role("role2", "Role 2"));

        client.assignPermissionToRole("perm1", "role1");
        client.assignPermissionToRole("perm2", "role2");
        client.assignPermissionToRole("perm3", "role2");
        client.createWarrant(role2, "member", new WarrantSubject("role", "role1"));
        client.assignRoleToUser("role1", "userA");
        client.assignRoleToUser("role2", "userB");

        QueryResultSet resultSet = client.query("select role where user:userA is member",
                new ListParams().withLimit(1));
        Assertions.assertEquals(1, resultSet.getResults().length);
        Assertions.assertEquals("role", resultSet.getResults()[0].getObjectType());
        Assertions.assertEquals("role1", resultSet.getResults()[0].getObjectId());
        Assertions.assertFalse(resultSet.getResults()[0].isImplicit());
        Assertions.assertEquals("role", resultSet.getResults()[0].getWarrant().getObjectType());
        Assertions.assertEquals("role1", resultSet.getResults()[0].getWarrant().getObjectId());
        Assertions.assertEquals("member", resultSet.getResults()[0].getWarrant().getRelation());
        Assertions.assertEquals("user", resultSet.getResults()[0].getWarrant().getSubject().getObjectType());
        Assertions.assertEquals("userA", resultSet.getResults()[0].getWarrant().getSubject().getObjectId());

        resultSet = client.query("select role where user:userA is member",
                new ListParams().withLimit(1).withAfterId(resultSet.getLastId()));
        Assertions.assertEquals(1, resultSet.getResults().length);
        Assertions.assertEquals("role", resultSet.getResults()[0].getObjectType());
        Assertions.assertEquals("role2", resultSet.getResults()[0].getObjectId());
        Assertions.assertTrue(resultSet.getResults()[0].isImplicit());
        Assertions.assertEquals("role", resultSet.getResults()[0].getWarrant().getObjectType());
        Assertions.assertEquals("role2", resultSet.getResults()[0].getWarrant().getObjectId());
        Assertions.assertEquals("member", resultSet.getResults()[0].getWarrant().getRelation());
        Assertions.assertEquals("role", resultSet.getResults()[0].getWarrant().getSubject().getObjectType());
        Assertions.assertEquals("role1", resultSet.getResults()[0].getWarrant().getSubject().getObjectId());

        client.deleteRole(role1);
        client.deleteRole(role2);
        client.deletePermission(permission3);
        client.deletePermission(permission2);
        client.deletePermission(permission1);
        client.deleteUser(userB);
        client.deleteUser(userA);
    }
}
