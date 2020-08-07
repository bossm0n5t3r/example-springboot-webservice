val kotlinVersion: String by project
val springBootVersion: String by project

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
}

repositories {
    mavenCentral()
    jcenter()
}

group = "org.example"
version = "1.0-SNAPSHOT"


dependencies {
    api("org.springframework.boot:spring-boot-starter-web")
    api("org.springframework.boot:spring-boot-starter-test")
}
