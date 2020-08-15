package net.atlantis.jinrocraft.ext

import org.bukkit.Location
import org.bukkit.boss.BossBar
import org.bukkit.entity.Entity
import org.bukkit.inventory.ItemStack
import org.bukkit.metadata.FixedMetadataValue
import org.bukkit.metadata.Metadatable
import org.bukkit.plugin.java.JavaPlugin
import java.sql.Timestamp

fun Metadatable.getBooleanMetadata(key: String): Boolean {
    return try {
        getMetadata(key)[0].value() as Boolean
    } catch (e: IndexOutOfBoundsException) {
        false
    }
}

fun Metadatable.setBooleanMetadata(plugin: JavaPlugin, key: String, value: Boolean) {
    setMetadata(key, FixedMetadataValue(plugin, value))
}

fun Metadatable.getIntMetadata(key: String): Int? {
    return try {
        getMetadata(key)[0].value() as Int
    } catch (e: IndexOutOfBoundsException) {
        null
    }
}

fun Metadatable.setIntMetadata(plugin: JavaPlugin, key: String, value: Int) {
    setMetadata(key, FixedMetadataValue(plugin, value))
}

fun Metadatable.getStringMetadata(key: String): String? {
    return try {
        getMetadata(key)[0].value() as String
    } catch (e: IndexOutOfBoundsException) {
        null
    }
}

fun Metadatable.setStringMetadata(plugin: JavaPlugin, key: String, value: String) {
    setMetadata(key, FixedMetadataValue(plugin, value))
}

@Suppress("UNCHECKED_CAST")
fun Metadatable.getIntListMetadata(key: String): List<Int>? {
    return try {
        getMetadata(key)[0].value() as List<Int>
    } catch (e: IndexOutOfBoundsException) {
        null
    }
}

fun Metadatable.setIntListMetadata(plugin: JavaPlugin, key: String, value: List<Int>) {
    setMetadata(key, FixedMetadataValue(plugin, value))
}

@Suppress("UNCHECKED_CAST")
fun Metadatable.getStringListMetadata(key: String): List<String>? {
    return try {
        getMetadata(key)[0].value() as List<String>
    } catch (e: IndexOutOfBoundsException) {
        null
    }
}

fun Metadatable.setStringListMetadata(plugin: JavaPlugin, key: String, value: List<String>) {
    setMetadata(key, FixedMetadataValue(plugin, value))
}

@Suppress("UNCHECKED_CAST")
fun Metadatable.getMapMetadata(key: String): Map<String, Int>? {
    return try {
        getMetadata(key)[0].value() as Map<String, Int>
    } catch (e: IndexOutOfBoundsException) {
        null
    }
}

fun Metadatable.setMapMetadata(plugin: JavaPlugin, key: String, value: Map<String, Int>) {
    setMetadata(key, FixedMetadataValue(plugin, value))
}

@Suppress("UNCHECKED_CAST")
fun Metadatable.getEntityMetadata(key: String): Entity? {
    return try {
        getMetadata(key)[0].value() as Entity
    } catch (e: IndexOutOfBoundsException) {
        null
    }
}

fun Metadatable.setEntityMetadata(plugin: JavaPlugin, key: String, value: Entity) {
    setMetadata(key, FixedMetadataValue(plugin, value))
}

@Suppress("UNCHECKED_CAST")
fun Metadatable.getItemStackMetadata(key: String): ItemStack? {
    return try {
        getMetadata(key)[0].value() as ItemStack
    } catch (e: IndexOutOfBoundsException) {
        null
    }
}

fun Metadatable.setItemStackMetadata(plugin: JavaPlugin, key: String, value: ItemStack) {
    setMetadata(key, FixedMetadataValue(plugin, value))
}

@Suppress("UNCHECKED_CAST")
fun Metadatable.getLocationMetadata(key: String): Location? {
    return try {
        getMetadata(key)[0].value() as Location
    } catch (e: IndexOutOfBoundsException) {
        null
    }
}

fun Metadatable.setLocationMetadata(plugin: JavaPlugin, key: String, value: Location) {
    setMetadata(key, FixedMetadataValue(plugin, value))
}

@Suppress("UNCHECKED_CAST")
fun Metadatable.getTimestampMetadata(key: String): Timestamp? {
    return try {
        getMetadata(key)[0].value() as Timestamp
    } catch (e: IndexOutOfBoundsException) {
        null
    }
}

fun Metadatable.setTimestampMetadata(plugin: JavaPlugin, key: String, value: Timestamp) {
    setMetadata(key, FixedMetadataValue(plugin, value))
}

@Suppress("UNCHECKED_CAST")
fun Metadatable.getBossBarMetadata(key: String): BossBar? {
    return try {
        getMetadata(key)[0].value() as BossBar
    } catch (e: IndexOutOfBoundsException) {
        null
    }
}

fun Metadatable.setBossBarMetadata(plugin: JavaPlugin, key: String, value: BossBar) {
    setMetadata(key, FixedMetadataValue(plugin, value))
}