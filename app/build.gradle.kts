plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.webpa.mobile"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.webpa.mobile"
        minSdk = 26
        targetSdk = 36
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.12"
    }
}

dependencies {

    implementation("androidx.room:room-runtime:2.5.0") // Библиотека "Room"
    ksp("androidx.room:room-compiler:2.5.0") // Кодогенератор
    implementation("androidx.room:room-ktx:2.5.0") // Дополнительно для Kotlin Coroutines, Kotlin Flows

    implementation("androidx.datastore:datastore-preferences:1.1.1")


    implementation("io.coil-kt:coil-compose:2.6.0")


    //retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.5.0")
    implementation("com.squareup.picasso:picasso:2.71828")



    implementation(libs.compose.material3)
    //nabigation

    implementation("androidx.hilt:hilt-navigation-compose:1.3.0")

    val nav_version = "2.9.6"

    implementation("androidx.navigation:navigation-compose:$nav_version")

    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.5")
    // Views/Fragments integration
    implementation("androidx.navigation:navigation-fragment:${nav_version}")
    implementation("androidx.navigation:navigation-ui:${nav_version}")
    // Feature module support for Fragments
    implementation("androidx.navigation:navigation-dynamic-features-fragment:$nav_version")

    // Testing Navigation
    androidTestImplementation("androidx.navigation:navigation-testing:$nav_version")

    // JSON serialization library, works with the Kotlin serialization plugin
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")


    implementation("com.google.dagger:hilt-android:2.57.1")
    ksp("com.google.dagger:hilt-android-compiler:2.57.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}