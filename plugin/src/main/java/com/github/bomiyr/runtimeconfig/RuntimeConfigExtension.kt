package com.github.bomiyr.runtimeconfig

import org.gradle.api.provider.MapProperty
import org.gradle.api.provider.Property

/**
 * RuntimeConfig plugin extension
 */
abstract class RuntimeConfigExtension {
    /**
     * RuntimeConfig fields. Map holds key:value pairs (name:value)
     */
    abstract val fields: MapProperty<String, String>

    /**
     * Name of the generated class. Default value: `RuntimeConfig`
     */
    abstract val className: Property<String>

    /**
     * Package for the generated class. By default it is namespace for your android module
     * (application/library/dynamic-feature)
     */
    abstract val classPackage: Property<String>

    /**
     * Modifier for generated object. Default value: empty (public)
     */
    abstract val classModifier: Property<String>

    /**
     * Add field to runtime config
     */
    fun field(name: String, value: String) {
        fields.put(name, value)
    }
}