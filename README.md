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