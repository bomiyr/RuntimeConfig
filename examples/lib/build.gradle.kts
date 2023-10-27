@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("com.github.bomiyr.runtime-config")
}

android {
    namespace = "com.github.bomiyr.runtimeconfig.testlib"
    compileSdk = 34

    defaultConfig {
        minSdk = 16
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
}

runtimeConfig {
    field("timeOfClassLoad", "System.currentTimeMillis()")
}