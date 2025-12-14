plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))

    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.spotless.gradlePlugin)
}