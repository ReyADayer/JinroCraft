package net.atlantis.jinrocraft.util

import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta

object ItemStackUtil {
    fun head(player: Player): ItemStack {
        val skull = ItemStack(Material.PLAYER_HEAD, 1)
        val itemMeta = skull.itemMeta as SkullMeta
        itemMeta.owningPlayer = player
        skull.itemMeta = itemMeta
        return skull
    }
}