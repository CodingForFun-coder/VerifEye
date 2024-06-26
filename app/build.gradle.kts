plugins {
    id("com.android.application") version "8.4.0"
    id("org.jetbrains.kotlin.android") version "1.9.22"
}

android {
    namespace = "com.example.verifeye"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.verifeye"
        minSdk = 21
        targetSdk = 34
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

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.vectordrawable)
    implementation (libs.asynchttpclient)
    implementation (libs.glide)
    annotationProcessor (libs.compiler)
    implementation (libs.androidx.recyclerview)
    implementation (libs.androidx.cardview)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation (libs.appcompat.v160)
    implementation (libs.material.v180)
    implementation(libs.okhttp)
    implementation (libs.androidx.lifecycle.viewmodel.ktx)

}