plugins {
    kotlin("jvm") version "1.9.0"

    kotlin("plugin.serialization") version "1.9.10"

    id("maven-publish")

    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("io.papermc.paperweight.userdev") version "1.5.5"
    id("xyz.jpenilla.run-paper") version "2.2.0"
}

group = "io.github.qualterz"
version = "0.1.2"

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/qualterz/cmarkup")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = rootProject.name
            version = project.version.toString()

            from(components["java"])

            versionMapping {
                usage("java-api") {
                    fromResolutionOf("runtimeClasspath")
                }
                usage("java-runtime") {
                    fromResolutionResult()
                }
            }
        }
    }
}

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