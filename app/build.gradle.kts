plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.feasthub"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.feasthub"
        minSdk = 29
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.8.0"))
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation ("pl.droidsonroids.gif:android-gif-drawable:1.2.22")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-auth-ktx:22.1.1")
    implementation("com.google.firebase:firebase-database-ktx:20.2.2")
    implementation("com.squareup.picasso:picasso:2.8")
    implementation("androidx.annotation:annotation:1.6.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation("com.google.firebase:firebase-messaging-ktx:23.2.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation(platform("com.google.firebase:firebase-bom:32.2.2"))
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.android.gms:play-services-auth:20.6.0")
    implementation ("com.google.firebase:firebase-messaging:22.0.0")
    implementation ("com.google.android.gms:play-services-auth:20.7.0")
    implementation ("com.etebarian:meow-bottom-navigation:1.2.0")
    implementation ("com.shreyaspatil:EasyUpiPayment:2.0")
    implementation ("de.hdodenhof:circleimageview:3.1.0")
    implementation ("com.google.code.gson:gson:2.8.8")
    implementation ("com.razorpay:checkout:1.6.33")

}