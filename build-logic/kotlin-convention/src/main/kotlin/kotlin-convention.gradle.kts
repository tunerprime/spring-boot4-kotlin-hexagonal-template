plugins {
    kotlin("jvm")
    id("com.diffplug.spotless")
}

spotless {
    val excludeFiles = arrayOf(".idea/**/*.*", ".vscode/**/*.*")
    kotlin {
        target("**/*.kt")
        targetExclude(*excludeFiles, "build/generated/**")
        ktlint("1.8.0")
            .editorConfigOverride(
            mapOf(
                "indent_size" to "2",
                "continuation_indent_size" to "2",
                "max_line_length" to "160",
                "insert_final_newline" to "true"
            )
        )
        trimTrailingWhitespace()
        endWithNewline()
    }

    kotlinGradle {
        target("**/*.gradle.kts")
        targetExclude(*excludeFiles)
        ktlint("1.8.0")
            .editorConfigOverride(
            mapOf(
                "indent_size" to "2",
                "continuation_indent_size" to "2",
                "max_line_length" to "160",
                "insert_final_newline" to "true"
            )
        )
        trimTrailingWhitespace()
        endWithNewline()
    }
}

tasks.withType<Test> {
  dependsOn("spotlessCheck")
}
