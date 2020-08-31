object Dependencies {
    object Kotlin {
        val version = "1.4.0"
        val classpath = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:$version"
        val reflect = "org.jetbrains.kotlin:kotlin-reflect:$version"

        object Coroutines {
            val version = "1.2.2"
            val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        }
    }

    object Spigot {
        val version = "1.15.2-R0.1-SNAPSHOT"
        val api = "org.spigotmc:spigot-api:$version"
        val annotations = "org.spigotmc:plugin-annotations:1.2.3-SNAPSHOT"
        val repository = "https://hub.spigotmc.org/nexus/content/repositories/snapshots/"
    }

    object SonaType {
        val repository = "https://oss.sonatype.org/content/groups/public/"
    }

    object Rx {
        val java = "io.reactivex.rxjava2:rxjava:2.2.17"
    }

    object Koin {
        val version = "2.1.6"
        val classpath = "org.koin:koin-gradle-plugin:$version"
        val core = "org.koin:koin-core:$version"
        val coreExt = "org.koin:koin-core-ext:$version"
        val test = "org.koin:koin-test:$version"
    }

    object Json {
        val core = "org.json:json:20190722"
    }

    object JUnit {
        val core = "org.junit.jupiter:junit-jupiter:5.5.2"
    }

    object MockK {
        val version = "1.10.0"
        val core = "io.mockk:mockk:$version"
    }
}