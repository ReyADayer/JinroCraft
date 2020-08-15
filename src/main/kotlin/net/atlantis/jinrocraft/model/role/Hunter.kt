package net.atlantis.jinrocraft.model.role

import org.bukkit.Material
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.inventory.ItemStack

class Hunter : Role() {
    override fun onPassive(player: Player) {
        if (player.inventory.firstEmpty() != -1) {
            player.inventory.addItem(ItemStack(Material.ARROW, 3))
        }
    }

    override fun onClickedEntity(player: Player, targetEntity: Entity) {
    }

    override fun onAttackedEntity(player: Player, targetEntity: Entity, event: EntityDamageByEntityEvent) {

    }
}