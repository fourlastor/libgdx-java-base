@file:Suppress("UnstableApiUsage")

include(":desktop")
include(":core")
include(":html")

dependencyResolutionManagement {
    versionCatalogs { create("libs") { from(files("libs.versions.toml")) } }
}
