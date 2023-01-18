import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import de.undercouch.gradle.tasks.download.Download
import org.gradle.internal.os.OperatingSystem

@Suppress(
    // known false positive: https://youtrack.jetbrains.com/issue/KTIJ-19369
    "DSL_SCOPE_VIOLATION"
)
plugins {
    java
    application
    alias(libs.plugins.beryxRuntime)
    alias(libs.plugins.download)
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

val safeName = stringProperty("io.github.fourlastor.game.safe_name")
val gameName = stringProperty("io.github.fourlastor.game.game_name")
val gameVersion = stringProperty("io.github.fourlastor.game.version")
val packageInputDir = "jpackage/in"
val packageOutputDir = "jpackage/out"
val gameBuildDir = "$buildDir/game"
val appDirName = "Game.AppDir"
val appImageName = "Game"
val linuxAppDir = "$gameBuildDir/appimage"
val macAppDir = "$gameBuildDir/mac"
val jpackageBuildDir = "$gameBuildDir/jpackage"
val currentOs: OperatingSystem = OperatingSystem.current()

application {
    val className = "io.github.fourlastor.game.DesktopLauncher"
    mainClass.set(className)
}

tasks.withType(ShadowJar::class.java) {
    archiveFileName.set("$safeName.jar")
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

runtime {
    options.value(
        listOf(
            "--strip-debug",
            "--compress", "2",
            "--no-header-files",
            "--no-man-pages"
        )
    )
    if (currentOs.isWindows) {
        options.add("--strip-native-commands")
    }
    modules.value(
        listOf(
            "java.base",
            "java.desktop",
            "java.logging",
            "jdk.incubator.foreign",
            "jdk.unsupported",
        )
    )

    launcher {
        unixScriptTemplate = rootProject.file("$packageInputDir/scripts/unixrun.mustache")
    }

    imageDir.file(jpackageBuildDir)

    @Suppress("INACCESSIBLE_TYPE")
    if (currentOs.isLinux) {
        targetPlatform("linux-x64") {
            setJdkHome(
                jdkDownload(
                    "https://github.com/adoptium/temurin17-binaries/releases/download/jdk-17.0.4.1%2B1/OpenJDK17U-jdk_x64_linux_hotspot_17.0.4.1_1.tar.gz"
                )
            )
        }
        targetPlatform("linux-aarch64") {
            setJdkHome(
                jdkDownload(
                    "https://github.com/adoptium/temurin17-binaries/releases/download/jdk-17.0.4.1%2B1/OpenJDK17U-jdk_aarch64_linux_hotspot_17.0.4.1_1.tar.gz"
                )
            )
        }
        targetPlatform("mac-x64") {
            setJdkHome(
                jdkDownload(
                    "https://github.com/adoptium/temurin17-binaries/releases/download/jdk-17.0.4.1%2B1/OpenJDK17U-jdk_x64_mac_hotspot_17.0.4.1_1.tar.gz"
                )
            )
        }
        targetPlatform("mac-aarch64") {
            setJdkHome(
                jdkDownload(
                    "https://github.com/adoptium/temurin17-binaries/releases/download/jdk-17.0.4.1%2B1/OpenJDK17U-jdk_aarch64_mac_hotspot_17.0.4.1_1.tar.gz"
                )
            )
        }
    }

    jpackage {
        jpackageHome = stringProperty("io.github.fourlastor.game.jpackage_home")
        skipInstaller = true
        mainJar = "${safeName}.jar"
        imageName = safeName
        imageOutputDir = file(jpackageBuildDir)
        if (currentOs.isWindows) {
            imageOptions.addAll(listOf("--icon", rootProject.file("$packageInputDir/icons/icon.ico").absolutePath))
        }
    }
}


val architectures = listOf("x64", "aarch64")

val prepareAppImageFiles = tasks.register("prepareAppImageFiles") {
    group = "package"

    dependsOn(tasks.runtime)
    doLast {
        architectures.forEach { arch ->
            copy {
                from(file("$jpackageBuildDir/desktop-linux-$arch")) {
                    into(appDirName)
                }
                from(rootProject.file("Game.AppDir.Template")) {
                    into(appDirName)
                }
                from(rootProject.file("$packageInputDir/icons/icon.png")) {
                    into(appDirName)
                }
                into(rootProject.file("$linuxAppDir/$arch"))
            }
        }
    }
}

val downloadAppImageTools = tasks.register("downloadAppImageTools", Download::class.java) {
    src(
        listOf(
            "https://github.com/AppImage/AppImageKit/releases/download/continuous/appimagetool-x86_64.AppImage",
            "https://github.com/AppImage/AppImageKit/releases/download/continuous/runtime-x86_64",
            "https://github.com/AppImage/AppImageKit/releases/download/continuous/runtime-aarch64",
        )
    )
    dest(rootProject.file("appimagetools"))
    overwrite(false)
}

val prepareAppImageTools = tasks.register("prepareAppImageTools", Exec::class.java) {
    dependsOn(downloadAppImageTools)
    workingDir("appimagetools")
    commandLine("chmod", "+x", "appimagetool-x86_64.AppImage")
}

val buildAppImages = tasks.register("buildAppImages") {
    group = "package"
    dependsOn(prepareAppImageFiles, prepareAppImageTools)
    doLast {
        architectures.forEach { arch ->
            exec {
                workingDir("appimagetools")
                environment("ARCH" to appImageArch(arch))
                commandLine(
                    "./appimagetool-x86_64.AppImage", "-n", "$linuxAppDir/$arch/$appDirName",
                    "$linuxAppDir/$appImageName-$arch",
                    "--runtime-file", "./${runtimeName(arch)}"
                )
            }
        }
    }
}

val packageLinuxX64 = tasks.register<Zip>("packageLinux-x64") {
    group = "package"
    archiveFileName.set("$safeName-linux-x64.zip")
    destinationDirectory.set(rootProject.file(packageOutputDir))
    dependsOn(buildAppImages)
    from(rootProject.file("$packageInputDir/dist-extras"))
    from(rootProject.file("$packageInputDir/linux"))
    from(file("$linuxAppDir/$appImageName-x64"))
    into("$safeName-v$gameVersion-linux-64")
}

val packageLinuxAarch64 = tasks.register<Zip>("packageLinux-aarch64") {
    group = "package"
    archiveFileName.set("$safeName-linux-aarch64.zip")
    destinationDirectory.set(rootProject.file(packageOutputDir))
    dependsOn(buildAppImages)
    from(rootProject.file("$packageInputDir/dist-extras"))
    from(file("$linuxAppDir/$appImageName-aarch64"))
    into("$safeName-v$gameVersion-linux-aarch64")
}

tasks.register("packageLinux") {
    group = "package"
    dependsOn(packageLinuxX64, packageLinuxAarch64)
}

val buildMacAppBundle = tasks.register("buildMacAppBundle") {
    group = "package"
    dependsOn(tasks.runtime)
    doLast {
        architectures.forEach { arch ->
            copy {
                from(file("$jpackageBuildDir/desktop-mac-$arch")) {
                    into("MacOS")
                }
                from(rootProject.file("$packageInputDir/icons/icon.icns")) {
                    into("Resources")
                }
                from(rootProject.file("$packageInputDir/mac-app-folder/Info.plist"))
                into(rootProject.file("$macAppDir/$arch/$gameName.app/Contents"))
            }
        }
    }
}


val packageMacX64 = tasks.register<Zip>("packageMac-x64") {
    group = "package"
    archiveFileName.set("$safeName-mac-x64.zip")
    destinationDirectory.set(rootProject.file(packageOutputDir))
    dependsOn(buildMacAppBundle)
    from(file("$macAppDir/x64"))
    into("$safeName-v$gameVersion-mac-x64")
}

val packageMacAarch64 = tasks.register<Zip>("packageMac-aarch64") {
    group = "package"
    archiveFileName.set("$safeName-mac-aarch64.zip")
    destinationDirectory.set(rootProject.file(packageOutputDir))
    dependsOn(buildMacAppBundle)
    from(file("$macAppDir/aarch64"))
    into("$safeName-v$gameVersion-mac-aarch64")
}

tasks.register("packageMac") {
    group = "package"
    dependsOn(packageMacX64, packageMacAarch64)
}

tasks.register<Zip>("packageWindows") {
    group = "package"
    archiveFileName.set("$safeName-windows64.zip")
    destinationDirectory.set(rootProject.file(packageOutputDir))
    dependsOn(tasks.jpackageImage)
    from(rootProject.file("$packageInputDir/dist-extras"))
    from(rootProject.file("$packageInputDir/windows"))
    from(file(jpackageBuildDir))
    into("$safeName-v$gameVersion-windows-64")
}

tasks.register<Zip>("packageOtherPlatforms") {
    group = "package"
    archiveFileName.set("$safeName-otherplatforms.zip")
    destinationDirectory.set(rootProject.file(packageOutputDir))
    dependsOn(tasks.shadowJar)
    from(rootProject.file("$packageInputDir/dist-extras"))
    from(rootProject.file("$packageInputDir/otherplatforms"))
    from(file("$buildDir/libs/$safeName.jar"))
    into("$safeName-v$gameVersion-otherplatforms")
}

fun runtimeName(arch: String): String = when (arch) {
    "aarch64" -> "runtime-aarch64"
    "x64" -> "runtime-x86_64"
    else -> error("Runtime for architecture $arch unknown")
}

fun appImageArch(arch: String) = when (arch) {
    "aarch64" -> "arm_aarch64"
    "x64" -> "x86_64"
    else -> error("AppImage arch for architecture $arch unknown")
}

fun Project.stringProperty(name: String) =
    requireNotNull(property(name) as? String) { "Property $name is not a string" }
