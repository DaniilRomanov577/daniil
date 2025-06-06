plugins {
    alias(libs.plugins.android.application)
    //id("com.android.application")
    id("com.google.gms.google-services")

}

android {
    namespace = "com.example.chelyabinsk__2025"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.chelyabinsk__2025"
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
}

dependencies {

    implementation(platform("com.google.firebase:firebase-bom:33.13.0"))

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation("androidx.recyclerview:recyclerview:1.2.1")
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.android.material:material:1.6.0")
    implementation("com.google.android.material:material:1.9.0")


}