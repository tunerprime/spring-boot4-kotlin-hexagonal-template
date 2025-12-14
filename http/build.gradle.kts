plugins {
  application
  id("kotlin-convention")
  alias(libs.plugins.spring.boot)
  alias(libs.plugins.spring.dependency.management)
  alias(libs.plugins.graalvm)
}

group = "sample"
version = "0.0.1-SNAPSHOT"
description = "spring-boot4-kotlin-hexagonal-template"

configurations {
  compileOnly {
    extendsFrom(configurations.annotationProcessor.get())
  }
}

dependencies {
  implementation(libs.spring.boot.starter.actuator)
  implementation(libs.spring.boot.starter.hateoas)
  implementation(libs.spring.boot.starter.webmvc)
  developmentOnly(libs.spring.boot.devtools)
  annotationProcessor(libs.spring.boot.configuration.processor)
  testImplementation(libs.bundles.kotest)
  testImplementation(libs.spring.boot.starter.webmvc.test)
}

tasks.withType<Test> {
  useJUnitPlatform()
}
