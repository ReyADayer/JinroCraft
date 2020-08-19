package net.atlantis.jinrocraft.view.co

import net.atlantis.jinrocraft.view.Icon
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class MadmanCoIcon : Icon() {
    override val name = "狂人"
    override val description = "クリックでCOします"
    override val itemStack = ItemStack(Material.FERMENTED_SPIDER_EYE)
}