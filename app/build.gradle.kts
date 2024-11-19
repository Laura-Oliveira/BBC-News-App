plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-parcelize")
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
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.espresso.core)
    implementation(libs.androidx.junit.ktx)
    implementation(libs.filament.android)
    testImplementation(libs.junit)
    testImplementation(libs.androidx.rules)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //Retrofit
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation(libs.converter.scalars)
    implementation (libs.logging.interceptor)

    //RecylerView & Picasso (Images)
    implementation (libs.androidx.recyclerview)
    implementation (libs.picasso)

    //Espresso
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1") // Versão mais recente
    androidTestImplementation ("androidx.test.espresso:espresso-contrib:3.5.1") // Para RecyclerView e outros componentes adicionais
    androidTestImplementation ("androidx.test.espresso:espresso-intents:3.5.1") // Para verificar Intents

    //JUnit
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")

    //Coroutines Test (optional)
    androidTestImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")

    //General Utilities to Android Tests
    androidTestImplementation ("androidx.test:rules:1.5.0")
    androidTestImplementation ("androidx.test:runner:1.5.2")
    testImplementation ("androidx.arch.core:core-testing:2.1.0")

    //Mockito | MockK
    testImplementation ("org.mockito:mockito-core:4.11.0")
    androidTestImplementation ("org.mockito:mockito-android:4.11.0")
    testImplementation ("io.mockk:mockk:1.13.5")
}