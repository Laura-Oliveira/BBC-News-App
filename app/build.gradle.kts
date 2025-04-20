plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-parcelize")
    kotlin("kapt")
}

android {
    namespace = "com.news"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.news"
        minSdk = 24
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    configurations.all {
        resolutionStrategy {
            force("androidx.test:runner:1.6.1")
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.espresso.core)
    implementation(libs.androidx.junit.ktx)
    implementation(libs.filament.android)
    implementation(libs.androidx.swiperefreshlayout)
    testImplementation(libs.junit)
    testImplementation(libs.androidx.rules)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //View Model & Data Binding
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)

    //Retrofit
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation(libs.converter.scalars)
    implementation (libs.logging.interceptor)

    //RecylerView & Picasso (Images)
    implementation (libs.androidx.recyclerview)
    implementation (libs.picasso)

    //Espresso
    androidTestImplementation (libs.androidx.espresso.contrib)
    androidTestImplementation (libs.androidx.espresso.intents)

    //JUnit
    testImplementation (libs.junit)

    //Coroutines Test (optional)
    androidTestImplementation (libs.kotlinx.coroutines.test)

    //General Utilities to Android Tests
    androidTestImplementation (libs.androidx.runner)
    testImplementation (libs.androidx.core.testing)

    //Mockito | MockK
    testImplementation (libs.mockito.core)
    androidTestImplementation (libs.mockito.android)
    testImplementation (libs.mockk)
}