package net.atlantis.jinrocraft.model

import net.atlantis.jinrocraft.ext.getStringMetadata
import net.atlantis.jinrocraft.ext.setStringMetadata
import net.atlantis.jinrocraft.metadata.MetadataKey
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.entity.Entity
import org.bukkit.plugin.java.JavaPlugin
import org.koin.core.KoinComponent
import org.koin.core.inject

class RoleService : KoinComponent {
    companion object {
        private const val CONFIG_ROLE_KEY = "role"
    }

    private val plugin: JavaPlugin by inject()

    fun reset() {
        val config: FileConfiguration = plugin.config
        config.set(CONFIG_ROLE_KEY, null)
    }

    fun initRole(entity: Entity) {
        val config: FileConfiguration = plugin.config
        val roleTypeKey = config.getString(getConfigRoleKey(entity))
        val roleType = RoleType.findByKey(roleTypeKey)
        if (roleType == null) {
            // 存在しないときは市民に設定する
            setRole(entity, RoleType.CITIZEN)
        } else {
            entity.setStringMetadata(plugin, MetadataKey.ROLE.key, roleType.key)
        }
    }

    fun getRole(entity: Entity): RoleType? {
        val roleTypeKey = entity.getStringMetadata(MetadataKey.ROLE.key) ?: return null
        return RoleType.findByKey(roleTypeKey)
    }

    fun setRole(entity: Entity, roleTypeKey: String) {
        val roleType = RoleType.findByKey(roleTypeKey) ?: return
        setRole(entity, roleType)
    }

    fun setRole(entity: Entity, roleType: RoleType) {
        val config: FileConfiguration = plugin.config
        config.set(getConfigRoleKey(entity), roleType.key)
        entity.setStringMetadata(plugin, MetadataKey.ROLE.key, roleType.key)
        plugin.saveConfig()
    }

    private fun getConfigRoleKey(player: Entity): String = "$CONFIG_ROLE_KEY.${player.uniqueId}"
}