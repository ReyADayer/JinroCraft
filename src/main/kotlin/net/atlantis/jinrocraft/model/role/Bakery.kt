package net.atlantis.jinrocraft.model.role

import net.atlantis.jinrocraft.model.GroupType
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageByEntityEvent

class Bakery : Role() {
    override val name = "パン屋"
    override val description = """
        |毎朝パンを焼いて、すべてのプレイヤーに配布します。
    """.trimMargin()
    override val groupType = GroupType.CITIZENS

    override fun onPassive(player: Player) {
    }

    override fun onClickedEntity(player: Player, targetEntity: Entity) {
    }

    override fun onAttackedEntity(player: Player, targetEntity: Entity, event: EntityDamageByEntityEvent) {
    }
}