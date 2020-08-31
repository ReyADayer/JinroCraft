package net.atlantis.jinrocraft.view.co

import net.atlantis.jinrocraft.view.Icon
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class BakeryCoIcon : Icon() {
    override val name = "パン屋"
    override val description = "クリックでCOします"
    override val itemStack = ItemStack(Material.BREAD)
}