plugins {
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
}

dependencies {
    implementation(project(":shopflow-common"))

    implementation(libs.spring.boot.starter.web)
}
