package net.atlantis.jinrocraft.model.npc

import net.atlantis.jinrocraft.ext.setBooleanMetadata
import net.atlantis.jinrocraft.ext.setStringMetadata
import net.atlantis.jinrocraft.ext.spawn
import net.atlantis.jinrocraft.metadata.MetadataKey
import net.atlantis.jinrocraft.model.RoleService
import net.atlantis.jinrocraft.util.ItemStackUtil
import org.bukkit.Location
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.koin.core.KoinComponent
import org.koin.core.inject

class Grave : KoinComponent {
    private val plugin: JavaPlugin by inject()
    private val roleService: RoleService by inject()

    fun create(location: Location, player: Player) {
        location.spawn<ArmorStand> { armorStand ->
            armorStand.customName = "${player.name}の墓"
            armorStand.isCustomNameVisible = true
            armorStand.setAI(false)
            armorStand.isVisible = false
            armorStand.isInvulnerable = true
            armorStand.isSmall = false
            armorStand.setBooleanMetadata(plugin, MetadataKey.IS_GRAVE.key, true)
            roleService.getRole(player)?.let { roleType ->
                armorStand.setStringMetadata(plugin, MetadataKey.ROLE.key, roleType.key)
            }
            armorStand.equipment?.helmet = ItemStackUtil.head(player)
        }
    }
}