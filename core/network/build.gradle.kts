plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.gson)
    implementation(libs.okhttp.core)
    implementation(libs.okhttp.logging)
}
