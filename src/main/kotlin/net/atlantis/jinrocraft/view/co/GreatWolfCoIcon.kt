package net.atlantis.jinrocraft.view.co

import net.atlantis.jinrocraft.view.Icon
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class GreatWolfCoIcon : Icon() {
    override val name = "大狼"
    override val description = "クリックでCOします"
    override val itemStack = ItemStack(Material.REDSTONE_BLOCK)
}