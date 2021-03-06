package net.atlantis.jinrocraft.model

import net.atlantis.jinrocraft.config.PluginPreference
import net.atlantis.jinrocraft.ext.getOnlineAlivePlayers
import net.atlantis.jinrocraft.ext.getStringMetadata
import net.atlantis.jinrocraft.ext.setStringMetadata
import net.atlantis.jinrocraft.metadata.MetadataKeys
import net.atlantis.jinrocraft.model.role.Role
import org.bukkit.Server
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.util.*
import kotlin.reflect.full.primaryConstructor

class RoleService(
        private val plugin: JavaPlugin,
        private val server: Server,
        private val pluginPreference: PluginPreference
) {

    fun reset() {
        pluginPreference.resetPlayerRole()
        pluginPreference.roles = listOf()
    }

    fun initRoles() {
        plugin.server.onlinePlayers.forEach {
            setRole(it, RoleType.CITIZEN)
        }
        val roleSettings = pluginPreference.getRoleSettings()
        roleSettings.forEach { roleType, count ->
            if (count > 0) {
                repeat(count) {
                    val players = plugin.server.getOnlineAlivePlayers().filter { getRole(it) == RoleType.CITIZEN }
                    val player = players[Random().nextInt(players.size)]
                    setRole(player, roleType)
                }
            }
        }
    }

    fun initRole(player: Player) {
        val roleTypeKey = pluginPreference.getPlayerRole(player)
        val roleType = RoleType.findByKey(roleTypeKey)
        if (roleType == null) {
            // 存在しないときは市民に設定する
            pluginPreference.setPlayerRole(player, RoleType.CITIZEN.key)
        } else {
            player.setStringMetadata(plugin, MetadataKeys.ROLE, roleType.key)
        }
    }

    fun getRoles(): String {
        var result = "役職内訳"
        val roleSettings = pluginPreference.getRoleSettings()
        roleSettings.forEach { roleType, count ->
            if (count > 0) {
                result += " ${roleType.jpName}:$count"
            }
        }
        return result
    }

    fun getRole(entity: Entity): RoleType? {
        val roleTypeKey = entity.getStringMetadata(MetadataKeys.ROLE) ?: return null
        return RoleType.findByKey(roleTypeKey)
    }

    fun setRole(player: Player, roleTypeKey: String) {
        val roleType = RoleType.findByKey(roleTypeKey) ?: return
        setRole(player, roleType)
    }

    fun setRole(player: Player, roleType: RoleType) {
        pluginPreference.setPlayerRole(player, roleType.key)
        player.setStringMetadata(plugin, MetadataKeys.ROLE, roleType.key)
        player.sendMessage("あなたは${roleType.jpName}です")
    }

    fun setting(roleTypeKey: String, count: Int) {
        val roleType = RoleType.findByKey(roleTypeKey) ?: return
        pluginPreference.setRoleSetting(roleType, count)
        pluginPreference.syncRoles()
    }

    fun clearSetting() {
        pluginPreference.resetRoleSetting()
        pluginPreference.roles = listOf()
    }

    fun getRolePlayers(roleType: RoleType): List<Player> {
        return server.onlinePlayers.filter { getRole(it) == roleType }
    }

    fun getRoleClass(entity: Entity): Role? {
        return getRoleClass(getRole(entity))
    }

    fun getRoleClass(roleType: RoleType?): Role? {
        return roleType?.roleClass?.primaryConstructor?.call()
    }
}