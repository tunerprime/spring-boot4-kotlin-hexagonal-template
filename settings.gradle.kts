rootProject.name = "spring-boot4-kotlin-hexagonal-template"
pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

include("http")

includeBuild("build-logic")