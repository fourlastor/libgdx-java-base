@file:Suppress("UnstableApiUsage")

pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

include(":desktop")
include(":core")
//include(":html")
include(":gdx-json-parser")
include(":gdx-ldtk-loader")

dependencyResolutionManagement {
    versionCatalogs { create("libs") }
}

includeBuild("../libgdx") {
    dependencySubstitution {
        substitute(module("com.badlogicgames.gdx:gdx")).using( project(":gdx"))
    }
}
