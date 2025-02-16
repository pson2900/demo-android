plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.domain"

    defaultConfig {
        minSdk = rootProject.extra["defaultMinSdkVersion"] as Int
        buildToolsVersion = rootProject.extra["defaultBuildToolsVersion"] as String
        compileSdkVersion = rootProject.extra["defaultCompileSdkVersion"] as String
        compileSdk = rootProject.extra["defaultCompileSdk"] as Int
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    kotlinOptions {
        jvmTarget = "21"
    }
}

dependencies {
    implementation(kotlin("stdlib"))
    // Mark: AndroidX
    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.androidx.core.ktx)

    // Mark: KotlinX
    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.datetime)

    // Mark: Testing
    androidTestImplementation(libs.androidx.junit)
//    androidTestImplementation(libs.androidx.runner)
//    androidTestImplementation(libs.androidx.rules)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.espresso.contrib)
}