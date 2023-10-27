package com.github.bomiyr.runtimeconfig

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.file.Directory
import org.gradle.api.provider.Provider

class RuntimeConfigPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val extension = createExtension(target)

        val outputDir = target.layout.buildDirectory.dir("generated/source/runtime-config")

        registerTask(target, outputDir, extension)
        registerGeneratedSourceSet(target, outputDir)
    }

    private fun createExtension(target: Project): RuntimeConfigExtension = target
        .extensions
        .create("runtimeConfig", RuntimeConfigExtension::class.java)
        .apply {
            className.convention("RuntimeConfig")
            classModifier.convention("")
            target.afterEvaluate {
                target.extensions.findByName("android")?.let { ext ->
                    if (ext is CommonExtension<*, *, *, *, *>) {
                        classPackage.convention(ext.namespace)
                    }
                }
            }
        }

    private fun registerTask(
        target: Project,
        outputDir: Provider<Directory>,
        extension: RuntimeConfigExtension,
    ) {
        val taskProvider = target.tasks
            .register("generateRuntimeConfig", RuntimeConfigTask::class.java) {
                it.className.set(extension.className)
                it.classPackage.set(extension.classPackage)
                it.fields.set(extension.fields)
                it.classModifier.set(extension.classModifier)

                it.outputFile.set(
                    outputDir.map { dir ->
                        val path = it.classPackage
                            .get()
                            .replace(".", "/")

                        dir.dir(path)
                            .file(it.className.get() + ".kt")
                    }

                )

            }

        target.tasks.named("preBuild").configure {
            it.dependsOn(taskProvider)
        }
    }

    private fun registerGeneratedSourceSet(
        target: Project,
        outputDir: Provider<Directory>,
    ) {
        target.extensions
            .findByName("android")
            ?.let { ext ->
                if (ext is CommonExtension<*, *, *, *, *>) {
                    ext.sourceSets.named("main") {
                        it.kotlin.srcDir(outputDir)
                    }
                }
            }
    }
}
