plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "com.example.mangaspot"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.mangaspot"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "2.0.0"
    }

}

dependencies {
    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.androidx.ui.graphics.v1xx)
    implementation(libs.androidx.vectordrawable.animated)

    implementation(libs.androidx.animation)
    implementation(libs.androidx.ui.graphics)

    //hilt
    implementation(libs.hilt.android)
    implementation(project(":core"))
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.material3.android)
    implementation(libs.androidx.hilt.work)
    implementation(project(":feature:library"))
    implementation(libs.androidx.core.splashscreen)
    implementation(project(":feature:bottom_bar"))
    implementation(project(":feature:search"))
    implementation(project(":feature:search_api"))
    implementation(project(":feature:settings"))
    implementation(project(":feature:settings_api"))
    kapt(libs.hilt.android.compiler)


    implementation(libs.androidx.animation.android)
    implementation(libs.androidx.navigation.common.ktx)


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(project(":feature:library_api"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    // Jetpack Compose Core
    implementation(libs.androidx.ui)
    implementation(libs.androidx.material)
    implementation(libs.androidx.ui.tooling.preview)

    // Compose Navigation
    implementation(libs.androidx.navigation.compose)

    // Hilt Navigation for Compose
    implementation(libs.androidx.hilt.navigation.compose)

    // Activity Compose (required for Jetpack Compose)
    implementation(libs.androidx.activity.compose)
}