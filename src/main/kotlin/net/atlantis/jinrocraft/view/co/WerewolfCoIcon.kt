package net.atlantis.jinrocraft.view.co

import net.atlantis.jinrocraft.view.Icon
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class WerewolfCoIcon : Icon() {
    override val name = "人狼"
    override val description = "クリックでCOします"
    override val itemStack = ItemStack(Material.REDSTONE)
}