rootProject.name = "spring-boot4-kotlin-hexagonal-template"

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

include("http")

includeBuild("build-logic")