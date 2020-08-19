package net.atlantis.jinrocraft.view.co

import net.atlantis.jinrocraft.view.Icon
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class SeerCoIcon : Icon() {
    override val name = "占い師"
    override val description = "クリックでCOします"
    override val itemStack = ItemStack(Material.HEART_OF_THE_SEA)
}