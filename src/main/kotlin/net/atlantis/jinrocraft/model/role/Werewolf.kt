package net.atlantis.jinrocraft.model.role

import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageByEntityEvent

class Werewolf : Role() {
    override fun onClickedEntity(player: Player, targetEntity: Entity) {
    }

    override fun onAttackedEntity(player: Player, targetEntity: Entity, event: EntityDamageByEntityEvent) {
        if (player.world.time > 18000L) {
            event.damage += 2.0
        }
    }
}