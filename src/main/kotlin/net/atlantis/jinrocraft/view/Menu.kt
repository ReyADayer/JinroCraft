package net.atlantis.jinrocraft.view

import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.koin.core.KoinComponent

abstract class Menu(protected val player: Player) : KoinComponent {
    abstract fun show()
    abstract fun clickIcon(itemStack: ItemStack)
}