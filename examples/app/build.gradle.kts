@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("io.github.bomiyr.runtime-config")
}

android {
    namespace = "com.github.bomiyr.runtimeconfig"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.github.bomiyr.runtimeconfig"
        minSdk = 18
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    dynamicFeatures += setOf(":dynamic_feature")
}

dependencies {
    implementation(project(":lib"))
}


runtimeConfig {
    classModifier.set("internal")

    fields.put("appField", "\"myValue\"")   // <- String value
    fields.put("appFieldInt", "100")        // <- Int value
    fields.put("appFieldBool", "true")      // <- Boolean value
    fields.put("appFieldPair", "1 to 2")    // <- Expression value
}