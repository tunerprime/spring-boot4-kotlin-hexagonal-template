plugins {
  java
  application
  id("kotlin-convention")
  alias(libs.plugins.spring.boot)
  alias(libs.plugins.spring.dependency.management)
  alias(libs.plugins.graalvm)
}

group = "sample"
version = "0.0.1-SNAPSHOT"
description = "spring-boot4-kotlin-hexagonal-template"

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(21)
  }
}

configurations {
  compileOnly {
    extendsFrom(configurations.annotationProcessor.get())
  }
}

repositories {
  mavenCentral()
}

dependencies {
  implementation(libs.spring.boot.starter.actuator)
  implementation(libs.spring.boot.starter.hateoas)
  implementation(libs.spring.boot.starter.webmvc)
  developmentOnly(libs.spring.boot.devtools)
  compileOnly(libs.lombok)
  annotationProcessor(libs.spring.boot.configuration.processor)
  annotationProcessor(libs.lombok)
  testImplementation(libs.spring.boot.starter.test)
}

tasks.withType<Test> {
  useJUnitPlatform()
}
