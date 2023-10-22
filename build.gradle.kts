plugins {
    kotlin("jvm") version "1.9.0"

    kotlin("plugin.serialization") version "1.9.10"

    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("io.papermc.paperweight.userdev") version "1.5.5"
    id("xyz.jpenilla.run-paper") version "2.2.0"
}

group = "me.qualterz.minecraft"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")

    paperweight.paperDevBundle("1.20.2-R0.1-SNAPSHOT")
}

tasks.runServer {
    minecraftVersion("1.20.1")
}

tasks.assemble {
    dependsOn(tasks.reobfJar)
}

tasks.shadowJar {
    mergeServiceFiles()
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}