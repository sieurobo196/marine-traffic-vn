import org.springframework.boot.gradle.tasks.bundling.BootBuildImage

plugins {
    java
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.springframework.boot") version "2.5.10"
    id("org.siouan.frontend-jdk11") version "5.2.0"
    id("com.palantir.docker") version "0.26.0"
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

allprojects {
    group = "com.marine.traffic"
    version = "1.0-SNAPSHOT"

    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}


subprojects {
    when (project.name) {
        "marine-traffic-vn" -> {
            apply {
            }
        }
        else -> {

            apply {
                plugin("java")
                plugin("java-library")
                plugin("io.spring.dependency-management")
                plugin("org.springframework.boot")
            }

            java {
                toolchain {
                    languageVersion.set(JavaLanguageVersion.of(11))
                }
            }

            repositories {
                mavenCentral()
                {
                    url = uri("https://jitpack.io")
                }
            }

            java {
//                withJavadocJar()
            }

            tasks.getByName<Test>("test") {
                useJUnitPlatform()
            }

            tasks.getByName<BootBuildImage>("bootBuildImage") {

            }

            tasks.register("buildImage") {
                description = "Build docker image via :bootBuildImage"
                dependsOn(tasks.getByName<BootBuildImage>("bootBuildImage"))

            }

            tasks.register<Copy>("copyForDocker") {
                from(layout.buildDirectory.dir("libs"))
                exclude("*-plain.jar")
                exclude("*-javadoc.jar")
                into(layout.buildDirectory.dir("docker"))
                rename(".*.jar", "app.jar")
            }

            tasks.named("build") { finalizedBy("copyForDocker") }
        }
    }
}
