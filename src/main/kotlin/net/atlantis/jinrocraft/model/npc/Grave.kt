package net.atlantis.jinrocraft.model.npc

import net.atlantis.jinrocraft.ext.setBooleanMetadata
import net.atlantis.jinrocraft.ext.spawn
import net.atlantis.jinrocraft.metadata.MetadataKey
import net.atlantis.jinrocraft.util.ItemStackUtil
import org.bukkit.Location
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.koin.core.KoinComponent
import org.koin.core.inject

class Grave : KoinComponent {
    private val plugin: JavaPlugin by inject()

    fun create(location: Location, player: Player) {
        location.spawn<ArmorStand> {
            it.customName = "${player.name}の墓"
            it.isCustomNameVisible = true
            it.setAI(false)
            it.isVisible = false
            it.isInvulnerable = true
            it.isSmall = false
            it.setBooleanMetadata(plugin, MetadataKey.IS_GRAVE.key, true)
            it.equipment?.helmet = ItemStackUtil.head(player)
        }
    }
}