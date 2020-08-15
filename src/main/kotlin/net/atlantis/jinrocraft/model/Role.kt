package net.atlantis.jinrocraft.model

import net.atlantis.jinrocraft.ext.getStringMetadata
import net.atlantis.jinrocraft.ext.setStringMetadata
import net.atlantis.jinrocraft.metadata.MetadataKey
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.koin.core.KoinComponent
import org.koin.core.inject

class Role : KoinComponent {
    companion object {
        private const val CONFIG_ROLE_KEY = "role"
    }

    private val plugin: JavaPlugin by inject()

    fun reset() {
        val config: FileConfiguration = plugin.config
        config.set(CONFIG_ROLE_KEY, null)
    }

    fun initRole(player: Player) {
        val config: FileConfiguration = plugin.config
        val roleTypeKey = config.getString(getConfigRoleKey(player))
        val roleType = RoleType.findByKey(roleTypeKey)
        if (roleType == null) {
            // 存在しないときは市民に設定する
            setRole(player, RoleType.CITIZEN)
        } else {
            player.setStringMetadata(plugin, MetadataKey.ROLE.key, roleType.key)
        }
    }

    fun getRole(player: Player): RoleType? {
        val roleTypeKey = player.getStringMetadata(MetadataKey.ROLE.key) ?: return null
        return RoleType.findByKey(roleTypeKey)
    }

    fun setRole(player: Player, roleTypeKey: String) {
        val roleType = RoleType.findByKey(roleTypeKey) ?: return
        setRole(player, roleType)
    }

    fun setRole(player: Player, roleType: RoleType) {
        val config: FileConfiguration = plugin.config
        config.set(getConfigRoleKey(player), roleType.key)
        player.setStringMetadata(plugin, MetadataKey.ROLE.key, roleType.key)
        plugin.saveConfig()
    }

    private fun getConfigRoleKey(player: Player): String = "$CONFIG_ROLE_KEY.${player.uniqueId}"
}