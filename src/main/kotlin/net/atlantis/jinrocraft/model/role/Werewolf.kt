package net.atlantis.jinrocraft.model.role

import net.atlantis.jinrocraft.model.RoleService
import net.atlantis.jinrocraft.model.RoleType
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.koin.core.inject

class Werewolf : Role() {
    private val roleService: RoleService by inject()

    override fun onPassive(player: Player) {
    }

    override fun onClickedEntity(player: Player, targetEntity: Entity) {
    }

    override fun onAttackedEntity(player: Player, targetEntity: Entity, event: EntityDamageByEntityEvent) {
        val targetRoleType = roleService.getRole(targetEntity)
        if (targetRoleType == RoleType.FOX) {
            event.damage = 0.0
        } else if (player.world.time > 18000L) {
            event.damage += 2.0
        }
    }
}