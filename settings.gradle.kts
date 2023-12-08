@file:Suppress("UnstableApiUsage")

pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

include(":desktop")
include(":core")
include(":html")
include(":gdx-json-parser")
include(":gdx-ldtk-loader")

dependencyResolutionManagement {
    versionCatalogs { create("libs") }
}
