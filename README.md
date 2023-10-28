# RuntimeConfig

Android SDK has pretty useful tool to define build-time constants in code. You can add custom
constants in gradle configuration and they will be added into generated `BuildConfig.java` class. 
See more info here https://developer.android.com/build/gradle-tips#share-custom-fields-and-resource-values-with-your-app-code

But this tool has one downside on multi-module project: every BuildConfig parameter change require 
recompilation for all dependent modules.

BuildConfig is still good for constants that hardly ever change and can bring compile-time 
optimisations. But it is **bad** for frequently changed data, like timestamp, git commit hash, 
build revision, application version etc.

Here RuntimeConfig plugin can shine. It will generate you runtime constants which does not generate 
ABI-change every time after fields change.

## Usage

Plugin generates Kotlin file, so it is required to have Kotlin configured in your module.

Enable plugin:

```kotlin
plugins {
    id("io.github.bomiyr.runtime-config") version "1.0"
}
```

Then use extension to setup your config:

```kotlin
runtimeConfig {
    field("timestamp", System.currentTimeMillis())
    
    // or with using property:
    fields.put("appField", "\"myValue\"")   // <- String value
    fields.put("appFieldInt", "100")        // <- Int value
    fields.put("appFieldBool", "true")      // <- Boolean value
    fields.put("appFieldPair", "1 to 2")    // <- Expression value
}
```

All available properties:
```kotlin
runtimeConfig {
    field("timestamp", System.currentTimeMillis())
    fields.put("appField", "\"myValue\"")

    className.set("CustomClassName") // `RuntimeConfig` by default
    classPackage.set("your.custom.package")
    classModifier.set("internal") // Empty (public) by default
}
```

## What is going on

Plugin generates kotlin object inside your `build/generated/source/runtime-config` directory and adds it
as source dir to `main` sourcet for your android module. 
