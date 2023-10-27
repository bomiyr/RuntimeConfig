pluginManagement {
    includeBuild("./plugin")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "RuntimeConfig"
include(":app")
project(":app").projectDir = File(rootDir, "examples/app")

include(":lib")
project(":lib").projectDir = File(rootDir, "examples/lib")

include(":dynamic_feature")
project(":dynamic_feature").projectDir = File(rootDir, "examples/dynamic_feature")
