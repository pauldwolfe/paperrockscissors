import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "1.9.0"
    application
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "net.gered"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("ch.qos.logback:logback-classic:1.4.7")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

application {
    mainClass.set("net.gered.paperrockscissors.MainKt")
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = application.mainClass.get()
    }
}

tasks.named<ShadowJar>("shadowJar") {
    archiveClassifier.set("uberjar")
}

// prevents an kotlin.io.ReadAfterEOFException from being thrown when readln() is called only when running the
// application via `gradle run` ... i would assume the problem is the overly-fancified console output stuff that
// gradle does during execution interfering with normal stdin functionality? what fun!
// (note that even with this hacky fix, when running via `gradle run`, the output is kinda messy ...)
tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}
