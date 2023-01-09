import com.badlogic.gdx.tools.texturepacker.TexturePacker

@Suppress(
    // known false positive: https://youtrack.jetbrains.com/issue/KTIJ-19369
    "DSL_SCOPE_VIOLATION"
)
plugins {
    id("java-library")
    alias(libs.plugins.spotless)
}

java.sourceCompatibility = JavaVersion.VERSION_1_8
java.targetCompatibility = JavaVersion.VERSION_1_8

val assetsDir = rootProject.files("assets")

sourceSets.main.configure {
    resources.srcDir(assetsDir)
}

tasks.compileJava.configure {
    dependsOn(tasks.named("packTextures"))
    options.encoding = "UTF-8"
}

spotless {
    isEnforceCheck = false
    java {
        palantirJavaFormat()
    }
}

tasks.create("packTextures") {
    val inputDir = "$rootDir/assets/images/included"
    val outputDir = "$rootDir/assets/images/included/packed"
    inputs.dir(inputDir)
    outputs.dir(outputDir)
    doLast {
        delete(outputDir)
        TexturePacker.process(inputDir, outputDir, "images.pack")
    }
}

dependencies {
    implementation(libs.gdx.core)
    implementation(libs.gdx.ai)
    implementation(libs.gdx.box2d)
    implementation(libs.gdx.controllers.core)
    implementation(libs.ashley)
    implementation(libs.textratypist)
    implementation(libs.dagger.core)
    api(libs.dagger.gwt)
    annotationProcessor(libs.dagger.compiler)
//    implementation "com.github.tommyettinger:textratypist:0.7.2"
}
