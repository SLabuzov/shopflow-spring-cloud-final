plugins {
	java
	alias(libs.plugins.spring.boot) apply false
	alias(libs.plugins.spring.dependency.management) apply false
}

group = "by.sample"
version = "0.0.1-SNAPSHOT"

subprojects {
	apply(plugin = "java")

	group = rootProject.group
	version = rootProject.version

	java {
		toolchain {
			languageVersion = JavaLanguageVersion.of(21)
		}
	}

	repositories {
		mavenCentral()
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}
}
