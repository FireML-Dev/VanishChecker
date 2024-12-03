rootProject.name = "VanishChecker"

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            library("spigot", "org.spigotmc:spigot-api:1.16.5-R0.1-SNAPSHOT")
            library("essentialsx", "net.essentialsx:EssentialsX:2.20.1")
            library("sayanvanish", "org.sayandev:sayanvanish-api:1.6.0")
            library("advancedvanish", "me.quantiom:advancedvanish:1.2.6")
        }
    }
}