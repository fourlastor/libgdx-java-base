import org.akhikhl.gretty.AppBeforeIntegrationTestTask
import org.wisepersist.gradle.plugins.gwt.GwtSuperDev

@Suppress(
    // known false positive: https://youtrack.jetbrains.com/issue/KTIJ-19369
    "DSL_SCOPE_VIOLATION"
)
plugins {
    java
    war
    alias(libs.plugins.gretty)
    alias(libs.plugins.gwt)
}

java.sourceCompatibility = JavaVersion.VERSION_1_8
java.targetCompatibility = JavaVersion.VERSION_1_8

war.webAppDirName = "webapp"

tasks.named("clean", Delete::class.java) {
    delete(project.file("war"))
}

gwt {
    gwtVersion="2.8.2" // Should match the gwt version used for building the gwt backend
    maxHeapSize="1G" // Default 256m is not enough for gwt compiler. GWT is HUNGRY
    minHeapSize="1G"

    src = files(file("src/main/java")) // Needs to be in front of "modules" below.
    src += files(File(project(":core").projectDir, "build/generated/sources/annotationProcessor/java/main"))
    modules("io.github.fourlastor.game.client.GdxDefinition")
    devModules("io.github.fourlastor.game.client.GdxDefinitionSuperdev")
    compiler.strict = true
    compiler.disableCastChecking = true
}

gretty {
    httpPort = 8080
    resourceBase = "$buildDir/gwt/draftOut"
    contextPath = "/"
    portPropertiesFileName = "TEMP_PORTS.properties"
}

val startHttpServer = tasks.create("startHttpServer", Copy::class.java) {
    dependsOn(tasks.draftCompileGwt)
    from("webapp", "war")
    into(gretty.resourceBase)
}

tasks.create("beforeRun", AppBeforeIntegrationTestTask::class.java) {
    // The next line allows ports to be reused instead of
    // needing a process to be manually terminated.
    file("build/TEMP_PORTS.properties").delete()
    dependsOn(tasks.named("startHttpServer"))
    interactive = false
    integrationTestTask("superDev")

}

tasks.create("superDev", GwtSuperDev::class.java) {
    dependsOn(startHttpServer)
    doFirst {
        gwt.modules = gwt.devModules
    }
}

val distDir = "$buildDir/dist"

tasks.create("dist") {
    dependsOn(tasks.clean, tasks.compileGwt)
    doLast {
        copy {
            from("$buildDir/gwt/out") {
                exclude( "**/*.symbolMap") // Not used by a dist, and these can be large.
            }
            into(distDir)
        }
        copy {
            from(project.file("webapp")) {
                exclude("index.html", "refresh.png")
            }
            into(distDir)
        }
        copy {
            from(project.file("webapp")) {
                include("index.html")
                filter { it.replace(Regex("<a class=\"superdev\" .+"), "") }
                // This does not modify the original index.html, only the copy in the dist.
                // If you decide to manually remove or comment out the superdev button from index.html, you should also
                // either remove or comment out only the "filter" line above this.
            }
            into(distDir)
        }
        copy {
            from(project.file("war"))
            into(distDir)
        }
    }
}

java.sourceSets.main.configure {
    val externalSrc = listOf(
        ":core",
        ":harlequin",
        ":gdx-auto-pool",
        ":gdx-json-parser",
        ":gdx-ldtk-loader",
        ":gdx-text-loader",
    ).map { project(it).sourceSets.main.get().allJava.srcDirs }
    compileClasspath += files(externalSrc)
}

dependencies {
    implementation(project(":core"))
    implementation(project(":harlequin"))
    implementation(project(":gdx-auto-pool"))
    implementation(project(":gdx-json-parser"))
    implementation(project(":gdx-ldtk-loader"))
    implementation(project(":gdx-text-loader"))
    implementation(libs.java.inject)
    implementation(libs.gdx.backend.gwt)
    sources(libs.gdx.backend.gwt)
    sources(libs.gdx.core)
    sources(libs.gdx.ai)
    sources(libs.gdx.box2d.core)
    implementation(libs.gdx.box2d.gwt)
    sources(libs.gdx.box2d.gwt)
    sources(libs.gdx.controllers.core)
    implementation(libs.gdx.controllers.gwt)
    sources(libs.gdx.controllers.gwt)
    sources(libs.ashley)
    sources(libs.textratypist)
    sources(libs.regexodus)
}

fun DependencyHandlerScope.sources(
    provider: Provider<MinimalExternalModuleDependency>,
) = implementation(variantOf(provider) { classifier("sources") })
