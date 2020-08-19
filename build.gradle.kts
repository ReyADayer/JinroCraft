plugins {
    java
    kotlin("jvm").version(Dependencies.Kotlin.version)
}

group = "net.atlantis"
version = "1.0.0"

repositories {
    jcenter()
    mavenCentral()
    maven(Dependencies.Spigot.repository)
    maven(Dependencies.SonaType.repository)
}

dependencies {
    compile(Dependencies.Spigot.api)
    compile(Dependencies.Kotlin.stdlib)
    compile(Dependencies.Kotlin.reflect)
    compile(Dependencies.Rx.java)
    compile(Dependencies.Koin.core)
    compile(Dependencies.Koin.coreExt)
    testCompile(Dependencies.JUnit.core)
}

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath(Dependencies.Kotlin.classpath)
        classpath(Dependencies.Koin.classpath)
    }
}

tasks {
    withType<Jar> {
        from(configurations.getByName("compile").map { if (it.isDirectory) it else zipTree(it) })
    }

    withType<Test>().configureEach {
        useJUnitPlatform()
    }
}