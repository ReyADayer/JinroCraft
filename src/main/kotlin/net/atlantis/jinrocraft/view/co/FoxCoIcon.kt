package net.atlantis.jinrocraft.view.co

import net.atlantis.jinrocraft.view.Icon
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class FoxCoIcon : Icon() {
    override val name = "妖狐"
    override val description = "クリックでCOします"
    override val itemStack = ItemStack(Material.NETHER_STAR)
}