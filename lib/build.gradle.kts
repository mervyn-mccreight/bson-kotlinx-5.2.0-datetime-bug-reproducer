plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.mongodb.driver)
    implementation(libs.mongodb.bson.kotlinx)
    implementation(libs.kotlinx.serialization.core)
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation(libs.junit.jupiter.engine)
}

kotlin {
    jvmToolchain(21)
}

tasks
    .withType<Test>()
    .configureEach { useJUnitPlatform() }
