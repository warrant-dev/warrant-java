# Warrant Java Library

Use [Warrant](https://warrant.dev/) in server-side Java projects.

[![Maven Central](https://img.shields.io/maven-central/v/dev.warrant/warrant-java)](https://mvnrepository.com/artifact/dev.warrant/warrant-java)
[![Slack](https://img.shields.io/badge/slack-join-brightgreen)](https://join.slack.com/t/warrantcommunity/shared_invite/zt-12g84updv-5l1pktJf2bI5WIKN4_~f4w)

## Installation

### Gradle

```groovy
implementation group: 'dev.warrant', name: 'warrant-java', version: '4.0.0'
```

### Maven

```xml
<dependency>
  <groupId>dev.warrant</groupId>
  <artifactId>warrant-java</artifactId>
  <version>4.0.0</version>
</dependency>
```

## Usage

```java
public static void main(String[] args) throws WarrantException, IOException {
    String apiKey = "YOUR_KEY";
    WarrantClient client = new WarrantClient(WarrantConfig.withApiKey(apiKey));

    // Create users, roles, permissions
    User user = client.createUser();
    Role adminRole = client.createRole(new Role("admin", "Admin", "Admin role"));
    Permission createPermission = client.createPermission(new Permission("create-report", "Create Report" "Permission to create reports"));

    // RBAC example
    client.assignPermissionToRole(createPermission, adminRole);
    client.assignRoleToUser(adminRole, user);
    client.checkUserHasPermission(user, "create-report"); // returns true
}
```

## Configuring API Endpoints

By default, the SDK makes requests to `api.warrant.dev`. You can override this endpoint, as well as a `check` endpoint (if using Warrant Edge) via a `WarrantConfig` object passed to the client:

```java
public static void main(String[] args) throws WarrantException, IOException {
    String apiKey = "YOUR_KEY";
    // Initialize api endpoint to https://api.warrant.dev and check endpoint to "http://localhost:3000" (local Edge instance)
    WarrantClient client = new WarrantClient(new WarrantConfig(apiKey, "https://api.warrant.dev", "http://localhost:3000"));
}
```

We’ve used a random API key in these code examples. Replace it with your [actual API keys](https://app.warrant.dev) to test this code through your own Warrant account.

For more information on how to use the Warrant API, please refer to the [Warrant API reference](https://docs.warrant.dev).

Note that we may release new [minor and patch](https://semver.org/) versions of this library with small but backwards-incompatible fixes to the type declarations. These changes will not affect Warrant itself.

## Warrant Documentation

- [Warrant Docs](https://docs.warrant.dev/)

## Development

Build and run all checks & tests:

```shell
./gradlew build
```
