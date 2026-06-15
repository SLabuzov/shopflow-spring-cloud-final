plugins {
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
}

dependencyManagement {
    imports {
        mavenBom(libs.spring.cloud.dependencies.get().toString())
    }
}

dependencies {
    implementation(project(":shopflow-common"))

    implementation(libs.spring.boot.starter.data.jpa)
    implementation(libs.spring.boot.starter.kafka)
    implementation(libs.spring.boot.starter.liquibase)
    implementation(libs.spring.boot.starter.web)

    implementation(libs.spring.cloud.starter.config)
    implementation(libs.spring.cloud.starter.netflix.eureka.client)

    implementation(libs.lombok)

    annotationProcessor(libs.lombok)

    runtimeOnly(libs.postgresql)
}
