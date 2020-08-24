package net.atlantis.jinrocraft.model.role

import net.atlantis.jinrocraft.model.GroupType
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.koin.core.KoinComponent

abstract class Role : KoinComponent {
    abstract val name: String
    abstract val description: String
    abstract val groupType: GroupType

    abstract fun onPassive(player: Player)

    abstract fun onClickedEntity(player: Player, targetEntity: Entity)

    abstract fun onAttackedEntity(player: Player, targetEntity: Entity, event: EntityDamageByEntityEvent)

    open fun onShownStatus(player: Player) {
        player.sendMessage("あなたは $name（${groupType.jpName}） です")
        player.sendMessage(description)
        player.sendMessage("勝利条件 : ${groupType.victoryCondition}")
    }
}