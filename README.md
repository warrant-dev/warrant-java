# Warrant Java Library

Use [Warrant](https://warrant.dev/) in server-side Java projects.

[![Maven Central](https://img.shields.io/maven-central/v/dev.warrant/warrant-java)](https://mvnrepository.com/artifact/dev.warrant/warrant-java)
[![Slack](https://img.shields.io/badge/slack-join-brightgreen)](https://join.slack.com/t/warrantcommunity/shared_invite/zt-12g84updv-5l1pktJf2bI5WIKN4_~f4w)

## Installation

### Gradle

```groovy
implementation group: 'dev.warrant', name: 'warrant-java', version: '0.3.0'
```

### Maven

```xml
<dependency>
  <groupId>dev.warrant</groupId>
  <artifactId>warrant-java</artifactId>
  <version>0.3.0</version>
</dependency>
```

## Usage

```java
public static void main(String[] args) throws WarrantException, IOException {
    String apiKey = "api_test_f5dsKVeYnVSLHGje44zAygqgqXiLJBICbFzCiAg1E=";
    WarrantClient client = new WarrantClient(WarrantConfig.withApiKey(apiKey));

    // Create users and sessions
    User user1 = client.createUser();
    System.out.println("Created user with generated id " + user1.getUserId());
    String sessionToken = client.createSession(user1.getUserId());
    System.out.println("Session token for userId " + user1.getUserId() + " : " + sessionToken);

    User user2 = client.createUser("provided_id");
    System.out.println("Created user with provided id " + user2.getUserId());

    // Create and check warrants
    Subject warrantSubject = new Subject("user", user1.getUserId());
    Warrant warrantToCreate = new Warrant("store", "store1", "owner", warrantSubject);
    client.createWarrant(warrantToCreate);
    // Should be "true"
    System.out.println(user1.getUserId() + ": " + client.isAuthorized(Warrant.newUserWarrant("store", "store1", "owner", user1.getUserId())));
    // Should be "false"
    System.out.println(user2.getUserId() + ": " + client.isAuthorized(Warrant.newUserWarrant("store", "store1", "owner", user2.getUserId())));

}
```

We’ve used a random API key in these code examples. Replace it with your
[actual publishable API keys](https://app.warrant.dev) to
test this code through your own Warrant account.

For more information on how to use the Warrant API, please refer to the
[Warrant API reference](https://docs.warrant.dev).

Note that we may release new [minor and patch](https://semver.org/) versions of this library with small but backwards-incompatible fixes to the type declarations. These changes will not affect Warrant itself.

## Warrant Documentation

- [Warrant Docs](https://docs.warrant.dev/)
