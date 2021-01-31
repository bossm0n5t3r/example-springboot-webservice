val kotlinVersion: String by project
val springBootVersion: String by project
val mockitoVersion: String by project
val junitJupiterVersion: String by project

plugins {
    val springBootVersion = "2.2.6.RELEASE"
    val kotlinVersion = "1.3.71"
    val springDependencyManagementVersion = "1.0.7.RELEASE"

    id("org.springframework.boot") version springBootVersion
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
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
    useJUnitPlatform()
}

val ktlint: Configuration by configurations.creating

dependencies {
    ktlint("com.pinterest:ktlint:0.37.2")
}

val verifyKtlint = task("ktlint", JavaExec::class) {
    description = "Check *.gradle.kts code style."
    classpath = ktlint
    main = "com.pinterest.ktlint.Main"
    args("**/*.gradle.kts", "src/**/*.kt")
}

tasks.check.get().dependsOn(verifyKtlint)

task("ktlintFormat", JavaExec::class) {
    description = "Fix *.gradle.kts code style violations."
    classpath = verifyKtlint.classpath
    main = verifyKtlint.main
    args("-F")
    args(verifyKtlint.args)
}
