plugins {
    java
    kotlin("jvm").version(Dependencies.Kotlin.version)
    kotlin("kapt").version(Dependencies.Kotlin.version)
}

group = "net.atlantis"
version = "1.1.2"

repositories {
    jcenter()
    mavenCentral()
    maven(Dependencies.Spigot.repository)
    maven(Dependencies.SonaType.repository)
    maven(Dependencies.ProtocolLib.repository)
}

dependencies {
    compile(Dependencies.Spigot.api)
    compileOnly(Dependencies.Spigot.annotations)
    kapt(Dependencies.Spigot.annotations)
    compileOnly(Dependencies.ProtocolLib.core) {
        exclude(Dependencies.BukkitExecutors.group, Dependencies.BukkitExecutors.module)
    }
    compile(Dependencies.Kotlin.stdlib)
    compile(Dependencies.Kotlin.reflect)
    compile(Dependencies.Rx.java)
    compile(Dependencies.Koin.core)
    compile(Dependencies.Koin.coreExt)
    compile(Dependencies.Json.core)
    testCompile(Dependencies.JUnit.core)
    testCompile(Dependencies.Koin.test)
    testCompile(Dependencies.MockK.core)
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