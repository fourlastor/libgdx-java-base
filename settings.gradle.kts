@file:Suppress("UnstableApiUsage")

include(":desktop")
include(":core")
include(":html")
include(":gdx-auto-pool")
include(":gdx-json-parser")
include(":gdx-ldtk-loader")
include(":gdx-text-loader")
include(":harlequin")

dependencyResolutionManagement {
    versionCatalogs { create("libs") { from(files("libs.versions.toml")) } }
}
