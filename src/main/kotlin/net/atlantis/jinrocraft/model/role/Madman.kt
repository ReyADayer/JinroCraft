package net.atlantis.jinrocraft.model.role

import net.atlantis.jinrocraft.model.GroupType
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageByEntityEvent

class Madman : Role() {
    override val name = "狂人"
    override val description = """
        |人狼に味方をする市民です。
        |能力はありません。
    """.trimMargin()
    override val groupType = GroupType.WEREWOLVES

    override fun onPassive(player: Player) {
    }

    override fun onClickedEntity(player: Player, targetEntity: Entity) {
    }

    override fun onAttackedEntity(player: Player, targetEntity: Entity, event: EntityDamageByEntityEvent) {
    }
}