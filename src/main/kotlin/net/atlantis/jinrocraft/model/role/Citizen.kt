package net.atlantis.jinrocraft.model.role

import net.atlantis.jinrocraft.model.GroupType
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageByEntityEvent

class Citizen : Role() {
    override val name = "市民"
    override val description = """
        |なんの能力も持たないただの人です。
    """.trimMargin()
    override val groupType = GroupType.CITIZENS

    override fun onPassive(player: Player) {
    }

    override fun onClickedEntity(player: Player, targetEntity: Entity) {
    }

    override fun onAttackedEntity(player: Player, targetEntity: Entity, event: EntityDamageByEntityEvent) {
    }
}