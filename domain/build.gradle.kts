plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
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
    implementation(libs.androidx.core.ktx)
    implementation(libs.com.google.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}