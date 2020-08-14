import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm").version(Dependencies.Kotlin.version)
}

group = "net.atlantis"
version = "1.0.0"

repositories {
    mavenCentral()
    maven(Dependencies.Spigot.repository)
    maven(Dependencies.SonaType.repository)
}

dependencies {
    compile(Dependencies.Spigot.api)
    compile(Dependencies.Kotlin.stdlib)
    compile(Dependencies.Kotlin.reflect)
    compile(Dependencies.Rx.java)
}

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath(Dependencies.Kotlin.classpath)
    }
}

tasks {
    withType<Jar> {
        from(configurations.getByName("compile").map { if (it.isDirectory) it else zipTree(it) })
    }
}