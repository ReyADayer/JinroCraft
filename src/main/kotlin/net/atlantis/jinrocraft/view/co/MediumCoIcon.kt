package net.atlantis.jinrocraft.view.co

import net.atlantis.jinrocraft.view.Icon
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class MediumCoIcon : Icon() {
    override val name = "霊能者"
    override val description = "クリックでCOします"
    override val itemStack = ItemStack(Material.SHULKER_SHELL)
}