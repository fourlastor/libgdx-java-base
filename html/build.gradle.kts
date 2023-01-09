plugins {
    java
    id( "org.wisepersist.gwt") version "1.1.16"
    id ("war")
    id ("org.gretty") version "4.0.3"
}

java.sourceCompatibility = JavaVersion.VERSION_1_8
java.targetCompatibility = JavaVersion.VERSION_1_8

gwt {
    gwtVersion="2.8.2" // Should match the gwt version used for building the gwt backend
    maxHeapSize="1G" // Default 256m is not enough for gwt compiler. GWT is HUNGRY
    minHeapSize="1G"

    src = files(file("src/main/java")) // Needs to be in front of "modules" below.
    src += files(File(project(":core").projectDir, "build/generated/sources/annotationProcessor/java/main"))
    modules("io.github.fourlastor.game.client.GdxDefinition")
    devModules("io.github.fourlastor.game.client.GdxDefinitionSuperdev")
//    project.webAppDirName = 'webapp'
//
    compiler.strict = true
    compiler.disableCastChecking = true

}

//import org.wisepersist.gradle.plugins.gwt.GwtSuperDev
//import org.akhikhl.gretty.AppBeforeIntegrationTestTask

//gretty.httpPort = 8080
//gretty.resourceBase = project.buildDir.path + "/gwt/draftOut"
//gretty.contextPath = "/"
//gretty.portPropertiesFileName = "TEMP_PORTS.properties"
//
//task startHttpServer () {
//    dependsOn draftCompileGwt
//
//    doFirst {
//        copy {
//            from "webapp"
//            into gretty.resourceBase
//        }
//
//        copy {
//            from "war"
//            into gretty.resourceBase
//        }
//    }
//}
//
//task beforeRun(type: AppBeforeIntegrationTestTask, dependsOn: startHttpServer) {
//    // The next line allows ports to be reused instead of
//    // needing a process to be manually terminated.
//    file("build/TEMP_PORTS.properties").delete()
//    // Somewhat of a hack; uses Gretty's support for wrapping a task in
//    // a start and then stop of a Jetty server that serves files while
//    // also running the SuperDev code server.
//    integrationTestTask 'superDev'
//
//    interactive false
//}
//
//task superDev (type: GwtSuperDev) {
//    dependsOn startHttpServer
//    doFirst {
//        gwt.modules = gwt.devModules
//    }
//}
//
//task dist(dependsOn: [clean, compileGwt]) {
//    doLast {
//        file("build/dist").mkdirs()
//        copy {
//            from "build/gwt/out"
//            into "build/dist"
//        }
//        copy {
//            from "webapp"
//            into "build/dist"
//            }
//        copy {
//            from "war"
//            into "build/dist"
//        }
//    }
//}
//
//task addSource {
//    doLast {
//        sourceSets.main.compileClasspath += files(project(':core').sourceSets.main.allJava.srcDirs)
//    }
//}
//
//tasks.compileGwt.dependsOn(addSource)
//tasks.draftCompileGwt.dependsOn(addSource)
//tasks.checkGwt.dependsOn(addSource)
//checkGwt.war = file("war")
//
//sourceCompatibility = 1.8
////sourceSets.srcDirs = [ "src/" ]

dependencies {
    implementation (project(":core"))
    implementation (libs.java.inject)
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
}

fun DependencyHandlerScope.sources(
    provider: Provider<MinimalExternalModuleDependency>,
) = implementation(variantOf(provider) { classifier("sources") })
