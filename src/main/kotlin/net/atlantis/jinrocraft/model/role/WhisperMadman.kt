package net.atlantis.jinrocraft.model.role

import net.atlantis.jinrocraft.model.GroupType
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageByEntityEvent

class WhisperMadman : Role() {
    override val name = "ささやく狂人"
    override val description = """
        |人狼に味方をする市民です。
        |人狼と会話することができます。
    """.trimMargin()
    override val groupType = GroupType.WEREWOLVES

    override val canJinroChat = true

    override fun onPassive(player: Player) {
    }

    override fun onClickedEntity(player: Player, targetEntity: Entity) {
    }

    override fun onAttackedEntity(player: Player, targetEntity: Entity, event: EntityDamageByEntityEvent) {
    }
}