plugins {
    java
    application
    alias(libs.plugins.spotless)
    alias(libs.plugins.shadow)
}

java.sourceCompatibility = JavaVersion.VERSION_1_8
java.targetCompatibility = JavaVersion.VERSION_1_8

spotless {
    isEnforceCheck = false
    java {
        palantirJavaFormat()
    }
}


application {
    val className = "io.github.fourlastor.game.DesktopLauncher"
    mainClass.set(className)
}

dependencies {
    implementation(project(":core"))
    nativesDesktop(libs.gdx.platform)
    nativesDesktop(libs.gdx.box2d.platform)
    implementation(libs.gdx.backend.lwjgl3)
    implementation(libs.gdx.controllers.desktop)
}

fun DependencyHandlerScope.nativesDesktop(
    provider: Provider<MinimalExternalModuleDependency>,
) = runtimeOnly(variantOf(provider) { classifier("natives-desktop") })
