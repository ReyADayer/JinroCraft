object Dependencies {
    object Kotlin {
        val version = "1.3.70"
        val classpath = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:$version"
        val reflect = "org.jetbrains.kotlin:kotlin-reflect:$version"

        object Coroutines {
            val version = "1.2.2"
            val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        }
    }

    object Spigot {
        val version = "1.16.1-R0.1-SNAPSHOT"
        val api = "org.spigotmc:spigot-api:$version"
        val repository = "https://hub.spigotmc.org/nexus/content/repositories/snapshots/"
    }

    object SonaType {
        val repository = "https://oss.sonatype.org/content/groups/public/"
    }

    object Rx {
        val java = "io.reactivex.rxjava2:rxjava:2.2.17"
    }
}