package net.atlantis.jinrocraft.config

import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class PluginPreference(private val plugin: JavaPlugin, private val config: FileConfiguration) {
    private object Keys {
        const val ROLE = "role"
        const val INGAME_ROLES = "ingame_roles"
    }

    fun resetPlayerRole() {
        set(Keys.ROLE, null)
    }

    fun getPlayerRole(player: Player): String? {
        return config.getString(getPlayerRoleKey(player))
    }

    fun setPlayerRole(player: Player, text: String) {
        set(getPlayerRoleKey(player), text)
    }

    var roles: List<String>
        get() = config.getStringList(Keys.INGAME_ROLES)
        set(value) = set(Keys.INGAME_ROLES, value)

    private fun getPlayerRoleKey(player: Entity): String = "${Keys.ROLE}.${player.uniqueId}"

    private fun set(key: String, value: Any?) {
        config.set(key, value)
        plugin.saveConfig()
    }
}