plugins {
    `java-library`
    `maven-publish`
}

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/public/")
    maven("https://repo.essentialsx.net/releases/")
    maven("https://repo.sayandev.org/snapshots")
    maven("https://repo.repsy.io/mvn/quantiom/minecraft")
}

dependencies {
    compileOnly(libs.spigot) {
        exclude("*", "*")
    }
    // CMI-API
    compileOnly(files("$projectDir/libs/CMIAPI.jar"))
    compileOnly(libs.essentialsx) {
        exclude("*", "*")
    }
    compileOnly(libs.sayanvanish) {
        exclude("*", "*")
    }
    compileOnly(libs.advancedvanish) {
        exclude("*", "*")
    }
}

group = "uk.firedev"
version = properties["project-version"] as String
java.sourceCompatibility = JavaVersion.VERSION_17

publishing {
    repositories {
        maven {
            url = uri("https://repo.codemc.io/repository/FireML/")

            val mavenUsername = System.getenv("JENKINS_USERNAME")
            val mavenPassword = System.getenv("JENKINS_PASSWORD")

            if (mavenUsername != null && mavenPassword != null) {
                credentials {
                    username = mavenUsername
                    password = mavenPassword
                }
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
