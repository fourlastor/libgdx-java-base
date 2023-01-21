@file:Suppress("UnstableApiUsage")

include(":desktop")
include(":core")
include(":html")
include(":gdx-ldtk")
include(":text-loader")

dependencyResolutionManagement {
    versionCatalogs { create("libs") { from(files("libs.versions.toml")) } }
}
