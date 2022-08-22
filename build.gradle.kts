// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        maven {
            setUrl("https://jitpack.io")
        }

    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.2.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.41")
        classpath("com.github.takahirom:decomposer:main-SNAPSHOT")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}

plugins.apply("com.github.takahirom.decomposer")

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}