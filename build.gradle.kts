buildscript {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
    dependencies {
        classpath (libs.gdx.tools)
    }
}

allprojects {
    repositories {
        mavenCentral()
    }
}
