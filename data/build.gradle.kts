plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.data"
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
    implementation(project(":domain"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.com.google.material)
    // Mark: Testing
//    testImplementation(libs.junit)
//    androidTestImplementation(libs.androidx.junit)
//    androidTestImplementation(libs.android.test.core.ktx)
//    androidTestImplementation(libs.android.test.junit.ktx)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.espresso.contrib)
    // Mark: Retrofit
    implementation(libs.retrofit) // Retrofit for network requests
    implementation(libs.retrofit.converter.moshi)
    implementation(libs.retrofit.moshi.kotlin)
    implementation(libs.retrofit.converter.gson) // Retrofit Gson converter
    implementation(libs.retrofit.logging.interceptor) // Retrofit logging interceptor
    implementation(libs.retrofit.kotlin.coroutines.adapter) // Retrofit Kotlin coroutines adapter
    implementation(libs.retrofit.kotlin.serialization) // Retrofit Kotlin coroutines adapter
    testImplementation(libs.retrofit.mockwebserver) // MockWebServer for testing Retrofit

    // Mark: Room
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    // Preferences DataStore
    implementation(libs.androidx.datastore.preferences)
}