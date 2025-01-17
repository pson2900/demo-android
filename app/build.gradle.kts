plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.compose.compiler)
    id("kotlinx-serialization")
}

android {
    namespace = "com.example.demo_structure"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.demo_structure"
        minSdk = 26
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    // Enable ViewBinding and Jetpack Compose
    buildFeatures {
        viewBinding = true
        compose = true
    }
}

dependencies {
    // Project dependencies
    implementation(project(":data"))
    implementation(project(":domain"))

    // Mark: Google
    implementation(libs.com.google.material) // Material Components for UI elements

    // Mark: Dependency Injection - Koin
    implementation(platform(libs.koin.bom)) // Use Koin BOM for consistent versions
    implementation(libs.koin.core) // Koin Core for DI
    implementation(libs.koin.android) // Koin for Android DI
    implementation(libs.koin.compose) // Koin integration with Compose
    implementation(libs.koin.androidx.compose) // Koin for AndroidX Compose

    // Mark: Retrofit
    implementation(libs.retrofit) // Retrofit for network requests
    implementation(libs.retrofit.converter.gson) // Retrofit Gson converter
    implementation(libs.retrofit.logging.interceptor) // Retrofit logging interceptor
    implementation(libs.retrofit.kotlin.coroutines.adapter) // Retrofit Kotlin coroutines adapter
    testImplementation(libs.retrofit.mockwebserver) // MockWebServer for testing Retrofit

    // Mark: Compose
    implementation(platform(libs.androidx.compose.bom)) // Use Compose BOM for consistent versions
    implementation(libs.androidx.ui) // Compose UI library
    implementation(libs.androidx.material3) // Material3 for Compose
    implementation(libs.androidx.ui.graphics) // Compose UI graphics
    implementation(libs.androidx.ui.tooling) // Tooling support for Compose
    implementation(libs.androidx.ui.tooling.preview) // Preview support for Compose
    implementation(libs.androidx.animation) // Compose animation library
    implementation(libs.androidx.navigation.compose) // Compose Navigation for screen transitions
    implementation(libs.androidx.constraintlayout.compose) // Compose ConstraintLayout

    // Debug dependencies
    debugImplementation(libs.androidx.ui.tooling) // Compose tooling for debugging

    // Mark: Other dependencies
    implementation(libs.androidx.core.splash) // SplashScreen API support
    implementation(libs.androidx.espresso.core) // Espresso for UI testing
    implementation(libs.androidx.lifecycle.runtime.ktx) // Lifecycle runtime for ViewModel and LiveData
    implementation(libs.androidx.lifecycle.viewmodel.ktx) // ViewModel KTX extensions
    implementation(libs.androidx.lifecycle.viewmodel.compose.ktx) // ViewModel Compose KTX extensions
    implementation(libs.androidx.activity.compose) // Compose support for activities
    implementation(libs.androidx.adaptive) // Adaptive UI for Compose
    implementation(libs.androidx.adaptive.layout.android) // Adaptive layout for Android
    implementation(libs.androidx.appcompat.resources) // AppCompat resources

    // Mark: Serialization library
    implementation(libs.kotlinx.serialization.json) // Kotlin Serialization for JSON parsing

    // Mark: Accompanist for additional Compose functionality
    implementation(libs.accompanist.drawablepainter) // Drawable painter for Compose
    implementation(libs.accompanist.navigation.animation) // Navigation with animations for Compose

    // Mark: AndroidX Material3 Adaptive Navigation Suite for Compose
    implementation(libs.androidx.adaptive.navigation.suite) // Adaptive Navigation Suite for Compose
    implementation(libs.compose)
    implementation ("androidx.compose.material:material:1.4.2")
}
