import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

@Suppress(
    // known false positive: https://youtrack.jetbrains.com/issue/KTIJ-19369
    "DSL_SCOPE_VIOLATION"
)
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

val jarName = property("io.github.fourlastor.game.jar_name") as String

application {
    val className = "io.github.fourlastor.game.DesktopLauncher"
    mainClass.set(className)
}

tasks.withType(ShadowJar::class.java) {
    archiveFileName.set("$jarName.jar")
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
) = implementation(variantOf(provider) { classifier("natives-desktop") })
