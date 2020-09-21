package net.atlantis.jinrocraft.config

import net.atlantis.jinrocraft.model.RoleType
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class PluginPreference(private val plugin: JavaPlugin, private val config: FileConfiguration) {
    private object Keys {
        const val ROLE = "role"
        const val INGAME_ROLES = "ingame_roles"
        const val GAME_START = "game_start"
        const val ROLE_SETTING = "role_setting"
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

    var gameStart: Boolean
        get() = config.getBoolean(Keys.GAME_START)
        set(value) = set(Keys.GAME_START, value)

    var roles: List<String>
        get() = config.getStringList(Keys.INGAME_ROLES)
        set(value) = set(Keys.INGAME_ROLES, value)

    fun resetRoleSetting() {
        set(Keys.ROLE_SETTING, null)
    }

    fun syncRoles() {
        val roleSettings = getRoleSettings()
        val roleTypeKeys: List<String> = roleSettings.filter { it.value != 0 }.map { it.key.key }
        roles = roleTypeKeys
    }

    fun getRoleSettings(): Map<RoleType, Int> {
        val result = mutableMapOf<RoleType, Int>()
        RoleType.values().forEach {
            if (it != RoleType.CITIZEN) {
                val count = getRoleSetting(it)
                result[it] = count
            }
        }
        return result
    }

    fun getRoleSetting(roleType: RoleType): Int {
        return config.getInt(getRoleSettingKey(roleType), 0)
    }

    fun setRoleSetting(roleType: RoleType, count: Int) {
        set(getRoleSettingKey(roleType), count)
    }

    private fun getPlayerRoleKey(player: Entity): String = "${Keys.ROLE}.${player.uniqueId}"

    private fun getRoleSettingKey(roleType: RoleType): String = "${Keys.ROLE_SETTING}.${roleType.key}"

    private fun set(key: String, value: Any?) {
        config.set(key, value)
        plugin.saveConfig()
    }
}