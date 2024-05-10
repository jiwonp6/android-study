// 모듈 수준의 'build.gradle' 파일
// 각 모듈의 디렉토리에 위치
// 각 모듈의 빌드 구성요소, 의존성 관리

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.busanIt.android_study"     // 패키지 이름
    compileSdk = 34                             // SDK 버전

    defaultConfig {
        applicationId = "com.busanIt.android_study"
        minSdk = 24                             // 최소 지원 SDK 버전
        targetSdk = 34                          // 타겟 SDK 버전
        versionCode = 1                         // 앱의 버전 (코드)
        versionName = "1.0"                     // 버전의 이름

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
    kotlinOptions {
        jvmTarget = "1.8"
    }
}
// 라이브러리 의존성 관리
dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}