package net.atlantis.jinrocraft.model.role

import net.atlantis.jinrocraft.model.GroupType
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageByEntityEvent

class Fox : Role() {
    override val name = "妖狐"
    override val description = """
        |人狼からダメージを受けません。
        |占い師に占われると死亡します。
    """.trimMargin()
    override val groupType = GroupType.FOXES

    override fun onPassive(player: Player) {
    }

    override fun onClickedEntity(player: Player, targetEntity: Entity) {
    }

    override fun onAttackedEntity(player: Player, targetEntity: Entity, event: EntityDamageByEntityEvent) {
    }
}