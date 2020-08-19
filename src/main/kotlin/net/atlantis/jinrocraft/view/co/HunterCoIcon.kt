package net.atlantis.jinrocraft.view.co

import net.atlantis.jinrocraft.view.Icon
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class HunterCoIcon : Icon() {
    override val name = "狩人"
    override val description = "クリックでCOします"
    override val itemStack = ItemStack(Material.BOW)
}