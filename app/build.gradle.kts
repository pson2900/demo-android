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
//        freeCompilerArgs += listOf(
//            "-Xcontext-receivers",
//            "-P",
//            "plugin:androidx.compose.compiler.plugins.kotlin:suppressKotlinVersionCompatibilityCheck=true"
//        )
    }

//    composeOptions {
//        kotlinCompilerExtensionVersion = "1.5.3"
//    }

    buildFeatures {
        viewBinding = true
        compose = true
    }
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))
//    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.20")
    implementation(libs.com.google.material)
    implementation(libs.kotlinx.serialization.json)
    // Mark: Koin
    implementation(platform(libs.koin.bom)) // Use BOM for consistent versions
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.compose)
    implementation(libs.koin.androidx.compose)
    // Mark: Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.retrofit.logging.interceptor)
    implementation(libs.retrofit.kotlin.coroutines.adapter)
    testImplementation(libs.retrofit.mockwebserver)
    // Mark: Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.animation)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.constraintlayout.compose)
    debugImplementation(libs.androidx.ui.tooling)

    // Other dependencies
    implementation(libs.androidx.core.splash)
    implementation(libs.androidx.espresso.core)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.adaptive)
    implementation(libs.androidx.adaptive.layout.android)
    implementation(libs.androidx.appcompat.resources)

    // Serialization library
    implementation(libs.kotlinx.serialization.json)
    //// ------


//    implementation(platform(libs.androidx.compose.bom))
//    implementation(libs.androidx.ui)
//    implementation(libs.androidx.ui.graphics)
//    implementation(libs.androidx.ui.tooling.preview)
//    implementation(libs.androidx.material3)
//    implementation(libs.androidx.adaptive)
////    implementation(libs.androidx.adaptive.navigation.android)
//    implementation(libs.androidx.adaptive.layout.android)
//    implementation(libs.androidx.animation)
//    implementation(libs.androidx.core.splash)
//    implementation(libs.coil.compose)
////    implementation(libs.androidx.paging.compose)
////    implementation(libs.androidx.fragment.ktx)
////    implementation(libs.androidx.fragment.compose)
//    implementation(libs.androidx.navigation.compose)
////    implementation(libs.androidx.adaptive.android)
//    implementation(libs.androidx.appcompat.resources)
//    implementation(libs.androidx.core.ktx)
//    implementation(libs.androidx.constraintlayout.compose)
//    implementation(libs.androidx.lifecycle.viewmodel.ktx)
//    testImplementation(libs.junit)
//    androidTestImplementation(libs.androidx.junit)
//    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.accompanist.drawablepainter)
    implementation(libs.accompanist.navigation.animation)
    implementation(libs.androidx.adaptive.navigation.suite)
}