@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.kotlin.jvm)
    `java-gradle-plugin`
    alias(libs.plugins.gradle.plugin.publish)
    alias(libs.plugins.kotlin.dokka)
}

group = "io.github.bomiyr"
version = "1.0"

dependencies {
    implementation(libs.android.gradle.plugin)
}

gradlePlugin {
    website.set("https://github.com/bomiyr/RuntimeConfig")
    vcsUrl.set("https://github.com/bomiyr/RuntimeConfig.git")

    plugins {
        create("RuntimeConfigPlugin") {
            id = "io.github.bomiyr.runtime-config"
            implementationClass = "com.github.bomiyr.runtimeconfig.RuntimeConfigPlugin"
            displayName = "RuntimeConfig"
            description =
                "RuntimeConfig is a replacement for Android BuildConfig, but without abi-change after changing field values"
            tags.set(listOf("android", "RuntimeConfig", "BuildConfig", "kotlin"))
        }
    }
}

tasks.named("javadoc").configure {
    val dokkaTask = tasks.named("dokkaJavadoc")
    this.dependsOn(dokkaTask)
    this.outputs.dir(dokkaTask)
}

