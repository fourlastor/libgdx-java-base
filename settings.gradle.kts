@file:Suppress("UnstableApiUsage")

include(":desktop")
include(":core")
include(":html")
include("gdx-ldtk")

dependencyResolutionManagement {
    versionCatalogs { create("libs") { from(files("libs.versions.toml")) } }
}
