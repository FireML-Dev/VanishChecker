# VanishChecker
Checks if a player or list of players are vanished via multiple plugins.

It is required to shade this into your plugin for it to work.

### Adding VanishChecker as a dependency and shading:

##### Gradle (Kotlin)
```kotlin
repositories {
    maven("https://repo.firedev.uk/repository/maven-public/")
}

dependencies {
    // The following version may not be the latest. Please check it before using.
    implementation("uk.firedev:VanishChecker:1.0.3")
}

tasks.shadowJar {
    relocate("uk.firedev.vanishchecker", "[package name here].vanishchecker")
}
```

##### Maven
```xml
    <repository>
        <id>firedev-repo</id>
        <url>https://repo.firedev.uk/repository/maven-public/</url>
    </repository>

    <dependency>
        <groupId>uk.firedev</groupId>
        <artifactId>VanishChecker</artifactId>
        <!--The following version may not be the latest. Please check it before using.-->
        <version>1.0.3</version>
        <scope>compile</scope>
    </dependency>

    <relocations>
        <relocation>
            <pattern>uk.firedev.vanishchecker</pattern>
            <shadedPattern>[package name here].vanishchecker</shadedPattern>
        </relocation>
    </relocations>
```
