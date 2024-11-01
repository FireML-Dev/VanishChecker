rootProject.name = "VanishChecker"

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            library("spigot", "org.spigotmc:spigot-api:1.16.5-R0.1-SNAPSHOT")
            library("essentialsx", "net.essentialsx:EssentialsX:2.20.1")
        }
    }
}