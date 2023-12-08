import com.badlogic.gdx.tools.texturepacker.TexturePacker

plugins {
    `java-library`
    idea
    alias(libs.plugins.spotless)
}

java.sourceCompatibility = JavaVersion.VERSION_1_8
java.targetCompatibility = JavaVersion.VERSION_1_8

val assetsDir = rootProject.files("assets")

sourceSets.main.configure {
    resources.srcDir(assetsDir)
}


val gwtCompatPath = "io/github/fourlastor/gwtCompat"
idea.module { excludeDirs.add(file("src/main/java/$gwtCompatPath")) }
listOf(tasks.compileJava, tasks.compileTestJava, tasks.javadoc).forEach {
    it.configure { exclude(gwtCompatPath) }
}

spotless {
    isEnforceCheck = false
    java {
        palantirJavaFormat()
    }
}

val packTextures = tasks.register("packTextures") {
    val inputDir = "$rootDir/assets/images/included"
    val outputDir = "$rootDir/assets/images/packed"
    inputs.dir(inputDir)
    outputs.dir(outputDir)
    doLast {
        delete(outputDir)
        TexturePacker.process(TexturePacker.Settings().apply {
            combineSubdirectories = true
        }, inputDir, outputDir, "images.pack")
    }
}

tasks {
    processResources {
        dependsOn(packTextures)
    }
    compileJava {
        options.encoding = "UTF-8"
    }
}

dependencies {
    implementation(project(":gdx-ldtk-loader"))
    implementation(libs.harlequin.core)
    implementation(libs.harlequin.ashley)
    implementation(libs.perceptual)
    implementation(libs.gdx.core)
    implementation(libs.gdx.ai)
    implementation(libs.gdx.box2d.core)
    implementation(libs.gdx.controllers.core)
    implementation(libs.ashley)
    implementation(libs.tommyettinger.textratypist)
    implementation(libs.tommyettinger.jdkgdxds)
    implementation(libs.dagger.core)
    api(libs.dagger.gwt)
    annotationProcessor(libs.dagger.compiler)
}
