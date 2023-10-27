@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.kotlin.jvm)
    `java-gradle-plugin`
    `maven-publish`
}

group = "com.github.bomiyr.runtimeconfig"
version = "0.1"


dependencies {
    implementation(libs.android.gradle.plugin)
}

gradlePlugin {
    plugins {
        create("RuntimeConfigPlugin") {
            id = "com.github.bomiyr.runtime-config"
            implementationClass = "com.github.bomiyr.runtimeconfig.RuntimeConfigPlugin"
        }
    }
}
