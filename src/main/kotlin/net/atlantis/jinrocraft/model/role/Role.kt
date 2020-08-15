package net.atlantis.jinrocraft.model.role

import org.bukkit.entity.Entity
import org.bukkit.entity.Player

abstract class Role {
    abstract fun onClickedEntity(player: Player, targetEntity: Entity)

    abstract fun onAttackedEntity(player: Player, targetEntity: Entity)
}