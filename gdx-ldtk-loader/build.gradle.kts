plugins {
    `java-library`
    alias(libs.plugins.spotless)
}

group = "io.github.fourlastor"
version = "1.0"

java.sourceCompatibility = JavaVersion.VERSION_1_8
java.targetCompatibility = JavaVersion.VERSION_1_8

spotless {
    isEnforceCheck = false
    java {
        palantirJavaFormat()
    }
}

dependencies {
    api(project(":gdx-json-parser"))
    api(libs.dagger.core)
    api(libs.gdx.core)
}
