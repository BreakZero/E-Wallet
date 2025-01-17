dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("easy") {
            from(files("./building.versions.toml"))
        }
    }
}

rootProject.name = "buildLogic"
include(":convention")