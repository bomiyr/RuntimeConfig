package com.github.bomiyr.runtimeconfig

import org.gradle.api.provider.MapProperty
import org.gradle.api.provider.Property

abstract class RuntimeConfigExtension {
    abstract val fields: MapProperty<String, String>
    abstract val className: Property<String>
    abstract val classPackage: Property<String>
    abstract val classModifier: Property<String>

    fun field(name: String, value: String) {
        fields.put(name, value)
    }
}