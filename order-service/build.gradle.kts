plugins {
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
}

dependencies {
    implementation(libs.spring.boot.starter.data.jpa)
    implementation(libs.spring.boot.starter.liquibase)
    implementation(libs.spring.boot.starter.validation)
    implementation(libs.spring.boot.starter.web)

    implementation(libs.lombok)
    implementation(libs.mapstruct)

    annotationProcessor(libs.lombok)
    annotationProcessor(libs.mapstruct.processor)

    runtimeOnly(libs.postgresql)
}
