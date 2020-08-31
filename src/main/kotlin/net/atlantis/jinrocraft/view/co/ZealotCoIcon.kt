package net.atlantis.jinrocraft.view.co

import net.atlantis.jinrocraft.view.Icon
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class ZealotCoIcon : Icon() {
    override val name = "狂信者"
    override val description = "クリックでCOします"
    override val itemStack = ItemStack(Material.ENDER_PEARL)
}