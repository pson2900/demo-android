plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.compose.compiler)
    id("kotlinx-serialization")

}


android {

    namespace = "com.example.demo_structure"

    defaultConfig {
        applicationId = "com.example.demo_structure"
        minSdk = rootProject.extra["defaultMinSdkVersion"] as Int
        buildToolsVersion = rootProject.extra["defaultBuildToolsVersion"] as String
        compileSdkVersion = rootProject.extra["defaultCompileSdkVersion"] as String
        compileSdk = rootProject.extra["defaultCompileSdk"] as Int
        targetSdk = rootProject.extra["defaultTargetSdkVersion"] as Int
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {

        debug {
            isMinifyEnabled = false
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    flavorDimensions += "version"
    productFlavors {
        create("develop") {
            applicationIdSuffix = ".develop"
            dimension = "version"
        }
        create("production") {
            applicationIdSuffix = ".production"
            dimension = "version"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    kotlinOptions {
        jvmTarget = "21"
    }

    // Enable ViewBinding and Jetpack Compose
    buildFeatures {
        viewBinding = true
        compose = true
        buildConfig = true
    }
    packaging {
        resources {
            excludes += "META-INF/LICENSE*"
            excludes += "META-INF/LICENSE.md"
            excludes += "META-INF/LICENSE-notice.md"
            excludes += "META-INF/ASL2.0"
        }
    }
    dependenciesInfo {
        includeInApk = true
        includeInBundle = true
    }
}

dependencies {
    // Project dependencies
    implementation(project(":data"))
    implementation(project(":domain"))

    // Mark: AndroidX Core
    implementation(libs.androidx.core.ktx)

    // Mark: AndroidX Workers
    implementation(libs.androidx.work.runtime.ktx)

    // Mark: AndroidX Lifecycle
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose.ktx)

    // Mark: AndroidX Room
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.runtime)

    // Mark: AndroidX Navigation
    implementation(libs.androidx.navigation.ktx)

    // Mark: AndroidX Activity Compose
    implementation(libs.androidx.activity.compose)

    // Mark: AndroidX Core Splashscreen
    implementation(libs.androidx.core.splash)

    // Mark: AndroidX Appcompat
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.appcompat.resources)

    // Mark: AndroidX Constraint Layout
    implementation(libs.androidx.constraintlayout.compose)
    implementation(libs.androidx.monitor)

    // Mark: Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
//    androidTestImplementation(libs.androidx.runner)
//    androidTestImplementation(libs.androidx.rules)
    androidTestImplementation("androidx.test.ext:junit:1.1.5") // Thay thế androidx.test:runner
    androidTestImplementation("androidx.test:core-ktx:1.5.0") // Thay thế một phần của androidx.test:rules
    androidTestImplementation("androidx.test.ext:junit-ktx:1.1.5") // Thay thế một phần của androidx.test:rules
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.espresso.contrib)
    testImplementation(libs.mockk)
    androidTestImplementation(libs.mockk.android)

    // Mark: Google Material Components
    implementation(libs.com.google.material) // Material Components for UI elements

    // Mark: Dependency Injection - Koin
    implementation(platform(libs.koin.bom)) // Use Koin BOM for consistent versions
    implementation(libs.koin.core) // Koin Core for DI
    implementation(libs.koin.android) // Koin for Android DI
    implementation(libs.koin.compose) // Koin integration with Compose
    implementation(libs.koin.androidx.compose) // Koin for AndroidX Compose
    testImplementation(libs.koin.test) // Koin test

    // Mark: Retrofit
    implementation(libs.retrofit) // Retrofit for network requests
    implementation(libs.retrofit.moshi.kotlin)
    implementation(libs.retrofit.converter.moshi)
    implementation(libs.retrofit.converter.gson) // Retrofit Gson converter
    implementation(libs.retrofit.logging.interceptor) // Retrofit logging interceptor
    implementation(libs.retrofit.kotlin.coroutines.adapter) // Retrofit Kotlin coroutines adapter
    implementation(libs.retrofit.kotlin.serialization) // Retrofit Kotlin coroutines adapter
    testImplementation(libs.retrofit.mockwebserver) // MockWebServer for testing Retrofit

    // Mark: Compose
    implementation(platform(libs.androidx.compose.bom)) // Use Compose BOM for consistent versions
    implementation(libs.androidx.ui.compose) // Compose UI library
    implementation(libs.androidx.ui.graphics.compose) // Compose UI graphics
    implementation(libs.androidx.ui.runtime.compose) // Compose Runtime
    implementation(libs.androidx.ui.test.compose) //Compose UI testing
    implementation(libs.androidx.ui.util.compose) // Compose UI Util
    implementation(libs.androidx.ui.foundation.compose) //Compose UI Foundation
    implementation(libs.androidx.ui.tooling.compose) // Tooling support for Compose
    implementation(libs.androidx.ui.tooling.preview.compose) // Preview support for Compose
    implementation(libs.androidx.animation.compose) // Compose animation library
    implementation(libs.androidx.navigation.compose) // Compose Navigation for screen transitions
    implementation(libs.androidx.material3.compose) // Material3 for Compose

    implementation(libs.androidx.material3.window.size) // Material3 Window Size Class
    implementation(libs.androidx.lifecycle.viewmodel.compose) // ViewModel for Compose
    implementation(libs.androidx.material)
    // Mark: Other dependencies
    implementation(libs.androidx.adaptive) // Adaptive UI for Compose
    implementation(libs.androidx.adaptive.layout)
    implementation(libs.androidx.adaptive.navigation)
    implementation(libs.androidx.adaptive.layout.android) // Adaptive layout for Android

    // Mark: Kotlinx library
    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.serialization.json) // Kotlin Serialization for JSON parsing
    implementation(libs.kotlinx.datetime)

    // Mark: Accompanist for additional Compose functionality
    implementation(libs.accompanist.drawablepainter) // Drawable painter for Compose
    implementation(libs.accompanist.navigation.animation) // Navigation with animations for Compose
    implementation(libs.accompanist.permissions)
    implementation(libs.accompanist.systemuicontroller)
    // Mark: Coil Compose
    implementation(libs.coil.compose)


    // Mark: AndroidX Material3 Adaptive Navigation Suite for Compose
    implementation(libs.androidx.adaptive.navigation.suite) // Adaptive Navigation Suite for Compose
    //GlideImage jetpack
    implementation("com.github.bumptech.glide:compose:1.0.0-beta01")
    //Gson
    implementation("com.google.code.gson:gson:2.12.1")
    //
    implementation("com.posthog:posthog-android:3.11.2")

}
