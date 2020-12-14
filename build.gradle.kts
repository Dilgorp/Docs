import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.4.0"
	id("io.spring.dependency-management") version "1.0.10.RELEASE"
	kotlin("jvm") version "1.4.10"
	kotlin("plugin.spring") version "1.4.10"
}

group = "ru.dilgorp"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.jetbrains.kotlin:kotlin-reflect:1.4.10")
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation ("org.springframework.boot:spring-boot-starter-security")
	implementation ("org.springframework.boot:spring-boot-starter-web")
	implementation("org.postgresql:postgresql:42.2.9")
	implementation(kotlin("stdlib"))

	implementation ("com.auth0:java-jwt:3.10.3")

	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
	testImplementation("com.h2database:h2")
	testImplementation("org.mockito:mockito-core:3.6.28")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
