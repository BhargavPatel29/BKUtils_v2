plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "in.qeasy.bkutils"
    compileSdk = 34

    defaultConfig {
        applicationId = "in.qeasy.bkutils"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "2.2408.15"

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
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(project(":bktoast"))
    implementation(project(":bkpref"))
    implementation(project(":bkcalender"))
    implementation(project(":bkanim"))
    implementation(project(":bkdialog"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    //Terminal Command to export .aar (release)
    //gradlew assemble
}