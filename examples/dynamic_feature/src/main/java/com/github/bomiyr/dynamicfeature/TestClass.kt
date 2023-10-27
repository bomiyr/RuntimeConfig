package com.github.bomiyr.dynamicfeature

class TestClass {
    init {
        println(RuntimeConfig.buildTimestamp)

        // RuntimeConfig from app is not accessible, because it defines internal modifier:
        // println(com.github.bomiyr.runtimeconfig.RuntimeConfig.appField) - unreachable
    }
}