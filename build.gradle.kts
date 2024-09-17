plugins {
    `java-library`
    `maven-publish`
}

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/public/")
    maven("https://repo.essentialsx.net/releases/")
    maven("https://repo.firedev.uk/repository/maven-public/")
}

dependencies {
    compileOnly(libs.spigot) {
        exclude("*", "*")
    }
    compileOnly(libs.cmi)
    compileOnly(libs.essentialsx) {
        exclude("*", "*")
    }

    implementation(libs.annotations)
}

group = "uk.firedev"
version = "1.0.2"
java.sourceCompatibility = JavaVersion.VERSION_17

publishing {
    repositories {
        maven {
            name = "firedevRepo"

            // Repository settings
            var repoUrlString = "https://repo.firedev.uk/repository/maven-"
            repoUrlString += if (project.version.toString().endsWith("-SNAPSHOT")) {
                "snapshots/"
            } else {
                "releases/"
            }
            url = uri(repoUrlString)

            credentials(PasswordCredentials::class)
            authentication {
                create<BasicAuthentication>("basic")
            }
        }
    }
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()

            from(components["java"])
        }
    }
}

tasks {
    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }
}
