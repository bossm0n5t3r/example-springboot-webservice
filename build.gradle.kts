val kotlinVersion: String by project
val springBootVersion: String by project
val mockitoVersion: String by project
val junitJupiterVersion: String by project

buildscript {
    val kotlinVersion: String by project
    val springBootVersion: String by project

    repositories {
        mavenCentral()
        jcenter()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("org.jetbrains.kotlin:kotlin-allopen:$kotlinVersion")
        classpath("org.jetbrains.kotlin:kotlin-noarg:$kotlinVersion")
        classpath("org.springframework.boot:spring-boot-gradle-plugin:$springBootVersion")
    }
}

plugins {
    val kotlinVersion = "1.3.71"

    kotlin("jvm") version kotlinVersion
    id("io.spring.dependency-management") version "1.0.7.RELEASE"
    id("idea")
}

repositories {
    mavenCentral()
    jcenter()
}

group = "org.example"
version = "1.0-SNAPSHOT"

apply(plugin = "org.jetbrains.kotlin.jvm")
apply(plugin = "org.jetbrains.kotlin.plugin.spring")
apply(plugin = "java")
apply(plugin = "idea")
apply(plugin = "eclipse")
apply(plugin = "org.springframework.boot")
apply(plugin = "io.spring.dependency-management")

java {
    sourceCompatibility = JavaVersion.VERSION_14
    targetCompatibility = JavaVersion.VERSION_14
}

dependencies {
    api("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
    api("org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion")
    api("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
    api("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
    api("org.springframework.boot:spring-boot-starter-web")
    api("org.projectlombok:lombok")

    testApi("org.springframework.boot:spring-boot-starter-test")
    testApi("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
    testApi("org.junit.jupiter:junit-jupiter-params:$junitJupiterVersion")
    testApi("org.mockito:mockito-core:$mockitoVersion")
    testApi("org.mockito:mockito-junit-jupiter:$mockitoVersion")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
}

tasks.compileKotlin {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict", "-Xuse-experimental=kotlin.Experimental")
        jvmTarget = "13"
    }
}

tasks.compileTestKotlin {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict", "-Xuse-experimental=kotlin.Experimental")
        jvmTarget = "13"
    }
}

tasks.test {
    maxHeapSize = "1536m"
    useJUnitPlatform()
}
