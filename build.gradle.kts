// 프로젝트 수준의 'build.gradle' 파일
// 최상위 프로젝트 레벨에 위치
// 프로젝트 전체 설정, 빌드 구성요소를 관리

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
}