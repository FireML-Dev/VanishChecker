rootProject.name = "VanishChecker"

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            library("spigot", "org.spigotmc:spigot-api:1.16.5-R0.1-SNAPSHOT")
            library("cmi", "CMI-API:CMI-API:9.7.0.1")
            library("essentialsx", "net.essentialsx:EssentialsX:2.20.1")
            library("annotations", "org.jetbrains:annotations:24.1.0")
        }
    }
}