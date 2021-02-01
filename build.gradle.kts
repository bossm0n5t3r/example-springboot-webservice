val kotlinVersion: String by project
val springBootVersion: String by project
val mockitoVersion: String by project
val junitJupiterVersion: String by project

plugins {
    val springBootVersion = "2.2.6.RELEASE"
    val kotlinVersion = "1.4.10"
    val springDependencyManagementVersion = "1.0.7.RELEASE"

    id("org.springframework.boot") version springBootVersion
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.jpa") version kotlinVersion
    id("io.spring.dependency-management") version springDependencyManagementVersion
}

repositories {
    mavenCentral()
    jcenter()
}

group = "org.example"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_14
    targetCompatibility = JavaVersion.VERSION_14
}

dependencies {
    api("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
    api("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
    api("org.springframework.boot:spring-boot-starter-web")
    // lombok
    api("org.projectlombok:lombok")
    // JPA
    api("org.springframework.boot:spring-boot-starter-data-jpa")
    api("com.h2database:h2")
    // mustache
    api("org.springframework.boot:spring-boot-starter-mustache")
    // security
    api("org.springframework.boot:spring-boot-starter-oauth2-client")
    // jdbc
    api("org.springframework.session:spring-session-jdbc")

    testApi("org.springframework.boot:spring-boot-starter-test")
    testApi("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
    testApi("org.junit.jupiter:junit-jupiter-params:$junitJupiterVersion")
    testApi("org.mockito:mockito-core:$mockitoVersion")
    testApi("org.mockito:mockito-junit-jupiter:$mockitoVersion")
    testApi("org.springframework.security:spring-security-test")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
}

tasks.test {
    useJUnitPlatform()
}

val ktlint by configurations.creating

dependencies {
    ktlint("com.pinterest:ktlint:0.40.0")
}

val outputDir = "${project.buildDir}/reports/ktlint/"
val inputFiles = project.fileTree(mapOf("dir" to "src", "include" to "**/*.kt"))

val ktlintCheck by tasks.creating(JavaExec::class) {
    inputs.files(inputFiles)
    outputs.dir(outputDir)

    description = "Check Kotlin code style."
    classpath = ktlint
    main = "com.pinterest.ktlint.Main"
    args = listOf("src/**/*.kt")
}

val ktlintFormat by tasks.creating(JavaExec::class) {
    inputs.files(inputFiles)
    outputs.dir(outputDir)

    description = "Fix Kotlin code style deviations."
    classpath = ktlint
    main = "com.pinterest.ktlint.Main"
    args = listOf("-F", "src/**/*.kt")
}
