import java.net.URI

plugins {
    kotlin("multiplatform") version "1.9.0"
    kotlin("plugin.serialization") version "1.9.0"
    id("maven-publish")
    id("dev.petuska.npm.publish") version "3.3.1"
    id("signing")
}

group = "io.github.mfrancza"
version = "1.2.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
}

kotlin {
    jvmToolchain(11)
    jvm()
    js {
        compilations["main"].packageJson {}
        binaries.library()
        nodejs()
        browser()
        generateTypeScriptDefinitions()
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val jvmMain by getting
        val jvmTest by getting
        val jsMain by getting
        val jsTest by getting
    }
}

val javadocJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = URI("https://maven.pkg.github.com/mfrancza/jwt-revocation-rules")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
        maven {
            name = "OSSRHSnapshot"
            url = URI("https://s01.oss.sonatype.org/content/repositories/snapshots/")
            credentials {
                username = System.getenv("MAVEN_USERNAME")
                password = System.getenv("MAVEN_PASSWORD")
            }
        }
        maven {
            name = "OSSRH"
            url = URI("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = System.getenv("MAVEN_USERNAME")
                password = System.getenv("MAVEN_PASSWORD")
            }
        }
    }

    publications.withType<MavenPublication> {

        // Stub javadoc.jar artifact
        artifact(javadocJar.get())

        // Provide artifacts information requited by Maven Central
        pom {
            name.set("JWT Revocation Rules")
            description.set("Multiplatform library for describing and applying JWT revocation rules managed by the jwt-revocation-manager.")
            url.set("https://github.com/mfrancza/jwt-revocation-rules")

            licenses {
                license {
                    name.set("Apache License 2.0")
                    url.set("http://www.apache.org/licenses/LICENSE-2.0")
                }
            }
            developers {
                developer {
                    id.set("mfrancza")
                    name.set("Matt Franczak")
                    email.set("mfrancza@gmail.com")
                }
            }
            scm {
                url.set("https://github.com/mfrancza/jwt-revocation-rules")
            }

        }
    }
}



signing {
    if (System.getenv("SIGNING_KEY_ID") != null) {
        useInMemoryPgpKeys(
            System.getenv("SIGNING_KEY_ID"),
            System.getenv("SIGNING_KEY"),
            System.getenv("SIGNING_PASSWORD")
        )
        sign(publishing.publications)
    }
}

npmPublish {
    registries {
        register("npmjs") {
            uri.set("https://registry.npmjs.org")
            authToken.set(System.getenv("NPM_AUTH_TOKEN"))
        }
    }
}

rootProject.plugins.withType<org.jetbrains.kotlin.gradle.targets.js.yarn.YarnPlugin> {
    rootProject.the<org.jetbrains.kotlin.gradle.targets.js.yarn.YarnRootExtension>().apply {
        lockFileDirectory = project.rootDir.resolve("kotlin-js-store")
    }
    rootProject.the<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension>().apply {
        versions.webpack.version = "5.76.0"
    }
}