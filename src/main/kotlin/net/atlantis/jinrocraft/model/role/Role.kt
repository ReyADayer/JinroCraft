package net.atlantis.jinrocraft.model.role

import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageByEntityEvent

abstract class Role {
    abstract fun onPassive(player: Player)

    abstract fun onClickedEntity(player: Player, targetEntity: Entity)

    abstract fun onAttackedEntity(player: Player, targetEntity: Entity, event: EntityDamageByEntityEvent)
}