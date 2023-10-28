@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.dynamic.feature)
    alias(libs.plugins.kotlin.android)
    id("io.github.bomiyr.runtime-config")
}
android {
    namespace = "com.github.bomiyr.dynamicfeature"
    compileSdk = 34

    defaultConfig {
        minSdk = 16
    }

    kotlinOptions {
        jvmTarget = "17"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(project(":app"))
}

runtimeConfig {
    fields.put("buildTimestamp", System.currentTimeMillis().toString())
}
