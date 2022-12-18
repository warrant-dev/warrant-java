package dev.warrant;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import dev.warrant.exception.WarrantException;
import dev.warrant.model.Subject;
import dev.warrant.model.Warrant;
import dev.warrant.model.object.Feature;
import dev.warrant.model.object.Permission;
import dev.warrant.model.object.PricingTier;
import dev.warrant.model.object.Role;
import dev.warrant.model.object.Tenant;
import dev.warrant.model.object.User;

@Disabled("Remove this annotation and add your API_KEY below to run these against the live server")
public class LiveTest {

    private static final String API_KEY = "YOUR_KEY";
    private WarrantClient client;
    private WarrantBaseClient baseClient;

    @BeforeEach
    public void setup() {
        client = new WarrantClient(WarrantConfig.withApiKey(API_KEY));
        baseClient = new WarrantClient(WarrantConfig.withApiKey(API_KEY));
    }

    @Test
    public void crudUsers() throws WarrantException {
        User user1 = client.createUser();
        Assertions.assertNotNull(user1.getUserId());
        Assertions.assertNull(user1.getEmail());

        User user2 = client.createUser(new User("some_id", "test@email.com"));
        User refetchedUser = client.getUser(user2.getUserId());
        Assertions.assertEquals(user2.getUserId(), refetchedUser.getUserId());
        Assertions.assertEquals(user2.getEmail(), refetchedUser.getEmail());

        user2 = client.update("some_id", new User("some_id", "updated@email.com"));
        refetchedUser = client.getUser("some_id");
        Assertions.assertEquals("some_id", refetchedUser.getUserId());
        Assertions.assertEquals("updated@email.com", refetchedUser.getEmail());

        User[] users = client.listUsers(10, 1);
        Assertions.assertEquals(2, users.length);

        client.deleteUser(user1.getUserId());
        client.deleteUser(user2.getUserId());
        users = client.listUsers(10, 1);
        Assertions.assertEquals(0, users.length);
    }

    @Test
    public void crudTenants() throws WarrantException {
        Tenant tenant1 = client.createTenant();
        Assertions.assertNotNull(tenant1.getTenantId());
        Assertions.assertNull(tenant1.getName());

        Tenant tenant2 = client.createTenant(new Tenant("some_tenant_Id", "new_name"));
        Tenant refetchedTenant = client.getTenant(tenant2.getTenantId());
        Assertions.assertEquals(tenant2.getTenantId(), refetchedTenant.getTenantId());
        Assertions.assertEquals(tenant2.getName(), refetchedTenant.getName());

        tenant2 = client.update("some_tenant_Id", new Tenant("some_tenant_Id", "updated_name"));
        refetchedTenant = client.getTenant("some_tenant_Id");
        Assertions.assertEquals("some_tenant_Id", refetchedTenant.getTenantId());
        Assertions.assertEquals("updated_name", refetchedTenant.getName());

        Tenant[] tenants = client.listTenants(10, 1);
        Assertions.assertEquals(2, tenants.length);

        client.deleteTenant(tenant1.getTenantId());
        client.deleteTenant(tenant2.getTenantId());
        tenants = client.listTenants(10, 1);
        Assertions.assertEquals(0, tenants.length);
    }

    @Test
    public void crudRoles() throws WarrantException {
        Role adminRole = client.createRole(new Role("admin", "Admin", "The admin role"));
        Assertions.assertEquals("admin", adminRole.getRoleId());
        Assertions.assertEquals("Admin", adminRole.getName());
        Assertions.assertEquals("The admin role", adminRole.getDescription());

        Role viewerRole = client.createRole(new Role("viewer", "Viewer", "The viewer role"));
        Role refetchedRole = client.getRole(viewerRole.getRoleId());
        Assertions.assertEquals(viewerRole.getRoleId(), refetchedRole.getRoleId());
        Assertions.assertEquals(viewerRole.getName(), refetchedRole.getName());
        Assertions.assertEquals(viewerRole.getDescription(), refetchedRole.getDescription());

        viewerRole = client.update("viewer", new Role("viewer", "Viewer Updated", "Updated desc"));
        refetchedRole = client.getRole("viewer");
        Assertions.assertEquals("viewer", refetchedRole.getRoleId());
        Assertions.assertEquals("Viewer Updated", refetchedRole.getName());
        Assertions.assertEquals("Updated desc", refetchedRole.getDescription());

        Role[] roles = client.listRoles(10, 1);
        Assertions.assertEquals(2, roles.length);

        client.deleteRole(adminRole.getRoleId());
        client.deleteRole(viewerRole.getRoleId());
        roles = client.listRoles(10, 1);
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
        Permission refetchedPermission = client.getPermission(permission2.getPermissionId());
        Assertions.assertEquals(permission2.getPermissionId(), refetchedPermission.getPermissionId());
        Assertions.assertEquals(permission2.getName(), refetchedPermission.getName());
        Assertions.assertEquals(permission2.getDescription(), refetchedPermission.getDescription());

        permission2 = client.update("perm2", new Permission("perm2", "Permission 2 Updated", "Updated desc"));
        refetchedPermission = client.getPermission("perm2");
        Assertions.assertEquals("perm2", refetchedPermission.getPermissionId());
        Assertions.assertEquals("Permission 2 Updated", refetchedPermission.getName());
        Assertions.assertEquals("Updated desc", refetchedPermission.getDescription());

        Permission[] permissions = client.listPermissions(10, 1);
        Assertions.assertEquals(3, permissions.length); // includes default 'view-self-service-dashboard' permission

        client.deletePermission(permission1.getPermissionId());
        client.deletePermission(permission2.getPermissionId());
        permissions = client.listPermissions(10, 1);
        Assertions.assertEquals(1, permissions.length);
    }

    @Test
    public void crudFeatures() throws WarrantException {
        Feature feature1 = client.createFeature(new Feature("new-feature"));
        Assertions.assertEquals("new-feature", feature1.getFeatureId());

        Feature feature2 = client.createFeature(new Feature("feature-2"));
        Feature refetchedFeature = client.getFeature(feature2.getFeatureId());
        Assertions.assertEquals(feature2.getFeatureId(), refetchedFeature.getFeatureId());

        Feature[] features = client.listFeatures(10, 1);
        Assertions.assertEquals(2, features.length);

        client.deleteFeature(feature1.getFeatureId());
        client.deleteFeature(feature2.getFeatureId());
        features = client.listFeatures(10, 1);
        Assertions.assertEquals(0, features.length);
    }

    @Test
    public void crudPricingTiers() throws WarrantException {
        PricingTier tier1 = client.createPricingTier(new PricingTier("new-tier1"));
        Assertions.assertEquals("new-tier1", tier1.getPricingTierId());

        PricingTier tier2 = client.createPricingTier(new PricingTier("tier-2"));
        PricingTier refetchedTier = client.getPricingTier(tier2.getPricingTierId());
        Assertions.assertEquals(tier2.getPricingTierId(), refetchedTier.getPricingTierId());

        PricingTier[] tiers = client.listPricingTiers(10, 1);
        Assertions.assertEquals(2, tiers.length);

        client.deletePricingTier(tier1.getPricingTierId());
        client.deletePricingTier(tier2.getPricingTierId());
        tiers = client.listPricingTiers(10, 1);
        Assertions.assertEquals(0, tiers.length);
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
        Assertions.assertEquals(0, client.listTenantsForUser(user1.getUserId(), 100, 1).length);
        Assertions.assertEquals(0, client.listUsersForTenant("tenant-1", 100, 1).length);
        client.assignTo(user1, tenant1);
        Tenant[] tenants = client.listTenantsForUser(user1.getUserId(), 100, 1);
        Assertions.assertEquals(1, tenants.length);
        Assertions.assertEquals("tenant-1", tenants[0].getTenantId());
        User[] users = client.listUsersForTenant("tenant-1", 100, 1);
        Assertions.assertEquals(1, users.length);
        Assertions.assertEquals(user1.getUserId(), users[0].getUserId());
        Assertions.assertEquals(1, client.listTenantsForUser(user1.getUserId(), 100, 1).length);
        client.removeFrom(user1, tenant1);
        Assertions.assertEquals(0, client.listTenantsForUser(user1.getUserId(), 100, 1).length);

        // Clean up
        client.deleteUser(user1.getUserId());
        client.deleteUser(user2.getUserId());
        client.deleteTenant(tenant1.getTenantId());
        client.deleteTenant(tenant2.getTenantId());
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
        Assertions.assertEquals(0, client.listRolesForUser(adminUser.getUserId(), 100, 1).length);
        Assertions.assertEquals(0, client.listPermissionsForRole(adminRole.getRoleId(), 100, 1).length);
        Assertions.assertFalse(client.hasPermission(adminUser.getUserId(), "create-report"));
        client.assignTo(createPermission, adminRole);
        client.assignTo(adminRole, adminUser);
        Permission[] adminPermissions = client.listPermissionsForRole(adminRole.getRoleId(), 100, 1);
        Assertions.assertEquals(1, adminPermissions.length);
        Assertions.assertEquals("create-report", adminPermissions[0].getPermissionId());
        Assertions.assertTrue(client.hasPermission(adminUser.getUserId(), "create-report"));
        Role[] userRoles = client.listRolesForUser(adminUser.getUserId(), 100, 1);
        Assertions.assertEquals(1, userRoles.length);
        Assertions.assertEquals("admin", userRoles[0].getRoleId());
        client.removeFrom(createPermission, adminRole);
        Assertions.assertFalse(client.hasPermission(adminUser.getUserId(), "create-report"));
        Assertions.assertEquals(1, client.listRolesForUser(adminUser.getUserId(), 100, 1).length);
        client.removeFrom(adminRole, adminUser);
        Assertions.assertEquals(0, client.listRolesForUser(adminUser.getUserId(), 100, 1).length);

        // Assign 'view-report' -> viewer user
        Assertions.assertFalse(client.hasPermission(viewer.getUserId(), "view-report"));
        Assertions.assertEquals(0, client.listPermissionsForUser(viewer.getUserId(), 100, 1).length);
        client.assignTo(viewPermission, viewer);
        Assertions.assertTrue(client.hasPermission(viewer.getUserId(), "view-report"));
        Permission[] userPermissions = client.listPermissionsForUser(viewer.getUserId(), 100, 1);
        Assertions.assertEquals(1, userPermissions.length);
        Assertions.assertEquals("view-report", userPermissions[0].getPermissionId());
        client.removeFrom(viewPermission, viewer);
        Assertions.assertFalse(client.hasPermission(viewer.getUserId(), "view-report"));
        Assertions.assertEquals(0, client.listPermissionsForUser(viewer.getUserId(), 100, 1).length);

        // Clean up
        client.deleteUser(adminUser.getUserId());
        client.deleteUser(viewer.getUserId());
        client.deleteRole(adminRole.getRoleId());
        client.deleteRole(viewerRole.getRoleId());
        client.deletePermission(createPermission.getPermissionId());
        client.deletePermission(viewPermission.getPermissionId());
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
        Assertions.assertFalse(client.hasFeature(paidUser.getUserId(), "custom-feature"));
        Assertions.assertEquals(0, client.listFeaturesForUser(paidUser.getUserId(), 100, 1).length);
        client.assignTo(customFeature, paidUser);
        Assertions.assertTrue(client.hasFeature(paidUser.getUserId(), "custom-feature"));
        Feature[] paidUserFeatures = client.listFeaturesForUser(paidUser.getUserId(), 100, 1);
        Assertions.assertEquals(1, paidUserFeatures.length);
        Assertions.assertEquals("custom-feature", paidUserFeatures[0].getFeatureId());
        client.removeFrom(customFeature, paidUser);
        Assertions.assertFalse(client.hasFeature(paidUser.getUserId(), "custom-feature"));
        Assertions.assertEquals(0, client.listFeaturesForUser(paidUser.getUserId(), 100, 1).length);

        // Assign 'feature-1' -> 'free' tier -> free user
        Assertions.assertFalse(client.hasFeature(freeUser.getUserId(), "feature-1"));
        Assertions.assertEquals(0, client.listFeaturesForPricingTier("free", 100, 1).length);
        Assertions.assertEquals(0, client.listPricingTiersForUser(freeUser.getUserId(), 100, 1).length);
        client.assignTo(feature1, freeTier);
        client.assignTo(freeTier, freeUser);
        Assertions.assertTrue(client.hasFeature(freeUser.getUserId(), "feature-1"));
        Feature[] freeTierFeatures = client.listFeaturesForPricingTier("free", 100, 1);
        Assertions.assertEquals(1, freeTierFeatures.length);
        Assertions.assertEquals("feature-1", freeTierFeatures[0].getFeatureId());
        PricingTier[] freeUserTiers = client.listPricingTiersForUser(freeUser.getUserId(), 100, 1);
        Assertions.assertEquals(1, freeUserTiers.length);
        Assertions.assertEquals("free", freeUserTiers[0].getPricingTierId());
        client.removeFrom(feature1, freeTier);
        Assertions.assertFalse(client.hasFeature(freeUser.getUserId(), "feature-1"));
        Assertions.assertEquals(0, client.listFeaturesForPricingTier("free", 100, 1).length);
        Assertions.assertEquals(1, client.listPricingTiersForUser(freeUser.getUserId(), 100, 1).length);
        client.removeFrom(freeTier, freeUser);
        Assertions.assertEquals(0, client.listPricingTiersForUser(freeUser.getUserId(), 100, 1).length);

        // Clean up
        client.deleteUser(freeUser.getUserId());
        client.deleteUser(paidUser.getUserId());
        client.deletePricingTier(freeTier.getPricingTierId());
        client.deletePricingTier(paidTier.getPricingTierId());
        client.deleteFeature(customFeature.getFeatureId());
        client.deleteFeature(feature1.getFeatureId());
        client.deleteFeature(feature2.getFeatureId());
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
        Assertions.assertFalse(client.hasFeature(paidTenant.getTenantId(), "custom-feature"));
        Assertions.assertEquals(0, client.listFeaturesForTenant(paidTenant.getTenantId(), 100, 1).length);
        client.assignTo(customFeature, paidTenant);
        Assertions.assertTrue(client.hasFeature(paidTenant, "custom-feature"));
        Feature[] paidTenantFeatures = client.listFeaturesForTenant(paidTenant.getTenantId(), 100, 1);
        Assertions.assertEquals(1, paidTenantFeatures.length);
        Assertions.assertEquals("custom-feature", paidTenantFeatures[0].getFeatureId());
        client.removeFrom(customFeature, paidTenant);
        Assertions.assertFalse(client.hasFeature(paidTenant.getTenantId(), "custom-feature"));
        Assertions.assertEquals(0, client.listFeaturesForTenant(paidTenant.getTenantId(), 100, 1).length);

        // Assign 'feature-1' -> 'free' tier -> free tenant
        Assertions.assertFalse(client.hasFeature(freeTenant.getTenantId(), "feature-1"));
        Assertions.assertEquals(0, client.listFeaturesForPricingTier("free", 100, 1).length);
        Assertions.assertEquals(0, client.listPricingTiersForTenant(freeTenant.getTenantId(), 100, 1).length);
        client.assignTo(feature1, freeTier);
        client.assignTo(freeTier, freeTenant);
        Assertions.assertTrue(client.hasFeature(freeTenant, "feature-1"));
        Feature[] freeTierFeatures = client.listFeaturesForPricingTier("free", 100, 1);
        Assertions.assertEquals(1, freeTierFeatures.length);
        Assertions.assertEquals("feature-1", freeTierFeatures[0].getFeatureId());
        PricingTier[] freeTenantTiers = client.listPricingTiersForTenant(freeTenant.getTenantId(), 100, 1);
        Assertions.assertEquals(1, freeTenantTiers.length);
        Assertions.assertEquals("free", freeTenantTiers[0].getPricingTierId());
        client.removeFrom(feature1, freeTier);
        Assertions.assertFalse(client.hasFeature(freeTenant.getTenantId(), "feature-1"));
        Assertions.assertEquals(0, client.listFeaturesForPricingTier("free", 100, 1).length);
        Assertions.assertEquals(1, client.listPricingTiersForTenant(freeTenant.getTenantId(), 100, 1).length);
        client.removeFrom(freeTier, freeTenant);
        Assertions.assertEquals(0, client.listPricingTiersForTenant(freeTenant.getTenantId(), 100, 1).length);

        // Clean up
        client.deleteTenant(freeTenant.getTenantId());
        client.deleteTenant(paidTenant.getTenantId());
        client.deletePricingTier(freeTier.getPricingTierId());
        client.deletePricingTier(paidTier.getPricingTierId());
        client.deleteFeature(customFeature.getFeatureId());
        client.deleteFeature(feature1.getFeatureId());
        client.deleteFeature(feature2.getFeatureId());
    }

    @Test
    public void sessions() throws WarrantException {
        User user = client.createUser();
        Tenant tenant = client.createTenant();
        client.assignTo(user, tenant);
        client.assignTo(new Permission("view-self-service-dashboard", null, null), user);

        Assertions.assertNotNull(client.createUserAuthzSession(user.getUserId()));
        Assertions.assertNotNull(client.createUserSelfServiceDashboardUrl(user.getUserId(), tenant.getTenantId(),
                "http://localhost:8080"));

        client.deleteUser(user.getUserId());
        client.deleteTenant(tenant.getTenantId());
    }

    @Test
    public void warrants() throws WarrantException {
        User newUser = client.createUser();
        Permission newPermission = client.createPermission(new Permission("perm1", "Permission 1", "Permission with id 1"));

        Assertions.assertFalse(baseClient.check(newPermission, "member", new Subject(newUser.type(), newUser.id())));
        baseClient.createWarrant(newPermission, "member", new Subject(newUser.type(), newUser.id()));
        Assertions.assertTrue(baseClient.check(newPermission, "member", new Subject(newUser.type(), newUser.id())));
        QueryWarrantsFilters filters = new QueryWarrantsFilters();
        filters.setSubject(new Subject(newUser.type(), newUser.id()));
        Warrant[] warrants = baseClient.queryWarrants(filters, 100, 1);
        Assertions.assertEquals(1, warrants.length);
        Assertions.assertEquals("permission", warrants[0].getObjectType());
        Assertions.assertEquals("perm1", warrants[0].getObjectId());
        Assertions.assertEquals("member", warrants[0].getRelation());
        baseClient.deleteWarrant(newPermission, "member", new Subject(newUser.type(), newUser.id()));
        Assertions.assertFalse(baseClient.check(newPermission, "member", new Subject(newUser.type(), newUser.id())));

        client.deleteUser(newUser.getUserId());
        client.deletePermission(newPermission.getPermissionId());
    }
}
