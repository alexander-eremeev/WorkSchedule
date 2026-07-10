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

rootProject.name = "workschedule"
// ----------------------------------------------------------------------------------------------------
//  When enabled, tasks using a shared build service without declaring the requirement via the Task.usesService method
//   will emit a deprecation warning.
//   Если этот параметр включен, задачи, использующие общую службу сборки без объявления требования
//   с помощью метода Task.usesService, будут выдавать предупреждение об устаревании.
// ?   enableFeaturePreview("STABLE_CONFIGURATION_CACHE")
// ----------------------------------------------------------------------------------------------------
// https://docs.gradle.org/7.0/release-notes.html
// Type-safe project accessors  Типобезопасные методы доступа к проектам
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")


include(":app")
