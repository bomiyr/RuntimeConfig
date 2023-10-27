package com.github.bomiyr.runtimeconfig

class TestClass {
    init {
        // reading values:
        println(RuntimeConfig.appField)
        println(RuntimeConfig.appFieldInt)
        println(RuntimeConfig.appFieldPair)
        println(RuntimeConfig.appFieldBool)

        // RuntimeConfig from library is accessible, because it is public by default
        println(com.github.bomiyr.runtimeconfig.testlib.RuntimeConfig.timeOfClassLoad)
    }
}
