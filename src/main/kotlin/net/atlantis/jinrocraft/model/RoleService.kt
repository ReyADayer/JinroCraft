package net.atlantis.jinrocraft.model

import net.atlantis.jinrocraft.config.PluginPreference
import net.atlantis.jinrocraft.ext.getStringMetadata
import net.atlantis.jinrocraft.ext.setStringMetadata
import net.atlantis.jinrocraft.metadata.MetadataKey
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.koin.core.KoinComponent
import org.koin.core.inject

class RoleService : KoinComponent {
    private val plugin: JavaPlugin by inject()
    private val pluginPreference: PluginPreference by inject()

    fun reset() {
        pluginPreference.resetPlayerRole()
        pluginPreference.roles = listOf()
    }

    fun initRole(player: Player) {
        val roleTypeKey = pluginPreference.getPlayerRole(player)
        val roleType = RoleType.findByKey(roleTypeKey)
        if (roleType == null) {
            // 存在しないときは市民に設定する
            pluginPreference.setPlayerRole(player, RoleType.CITIZEN.key)
        } else {
            player.setStringMetadata(plugin, MetadataKey.ROLE.key, roleType.key)
        }
    }

    fun getRole(entity: Entity): RoleType? {
        val roleTypeKey = entity.getStringMetadata(MetadataKey.ROLE.key) ?: return null
        return RoleType.findByKey(roleTypeKey)
    }

    fun setRole(player: Player, roleTypeKey: String) {
        val roleType = RoleType.findByKey(roleTypeKey) ?: return
        setRole(player, roleType)
    }

    fun setRole(player: Player, roleType: RoleType) {
        pluginPreference.setPlayerRole(player, roleType.key)
        player.setStringMetadata(plugin, MetadataKey.ROLE.key, roleType.key)
        val roles = pluginPreference.roles.toMutableList()
        roles.add(roleType.key)
        roles.distinct()
        pluginPreference.roles = roles
    }
}