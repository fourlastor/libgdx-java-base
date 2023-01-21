@file:Suppress("UnstableApiUsage")

include(":desktop")
include(":core")
include(":html")
include(":gdx-ldtk-loader")
include(":harlequin")
include(":gdx-text-loader")

dependencyResolutionManagement {
    versionCatalogs { create("libs") { from(files("libs.versions.toml")) } }
}
