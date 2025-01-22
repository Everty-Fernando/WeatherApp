pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "App Weather"
include(":app")
include(":shared:presentation")
include(":shared:network")
include(":shared:utils")
include(":features:home:home_data")
include(":features:home:home_domain")
