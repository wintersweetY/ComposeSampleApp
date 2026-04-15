pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "ComposeSampleApp"
include(":app")
include(":core:common")
include(":core:ui")
include(":core:designsystem")
include(":core:network")
include(":core:database")
include(":domain")
include(":data")
include(":feature:home:api")
include(":feature:home:impl")
include(":feature:square:api")
include(":feature:square:impl")
include(":feature:mine:api")
include(":feature:mine:impl")
