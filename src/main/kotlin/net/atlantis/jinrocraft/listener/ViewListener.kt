package net.atlantis.jinrocraft.listener

import net.atlantis.jinrocraft.view.co.CoMenu
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.koin.core.KoinComponent

class ViewListener : Listener, KoinComponent {
    @EventHandler
    fun onClickSummonMenu(event: InventoryClickEvent) {
        if (CoMenu.isMenu(event.view.title)) {
            event.currentItem?.let {
                CoMenu(event.whoClicked as Player).clickIcon(it)
            }
            event.isCancelled = true
        }
    }
}