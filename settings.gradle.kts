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
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MangaSpot"
include(":app")
include(":feature")
include(":feature:library")
include(":feature:library_api")
include(":core")
include(":feature:bottom_bar")
include(":feature:search_api")
include(":feature:search")
include(":feature:settings_api")
include(":feature:settings")
include(":feature:manga_details_api")
include(":feature:manga_details")
include(":feature:data")
