// Top-level build file where you can add configuration options common to all sub-projects/modules.


plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.kotlin.parcelize) apply false
    id("com.google.devtools.ksp") version "2.1.0-1.0.29" apply false
    kotlin("plugin.serialization") version "2.1.10" apply false
}
buildscript {

    dependencies {
        classpath(libs.kotlin.gradle.plugin)
        classpath(libs.gradle)
    }
}

allprojects {
    extra["defaultTargetSdkVersion"] = 35
    extra["defaultMinSdkVersion"] = 26
    extra["defaultCompileSdkVersion"] = "android-35"
    extra["defaultCompileSdk"] = 35
    extra["defaultBuildToolsVersion"] = "35.0.0"
}