package net.atlantis.jinrocraft.model.role

import net.atlantis.jinrocraft.model.GroupType
import net.atlantis.jinrocraft.model.RoleService
import net.atlantis.jinrocraft.model.RoleType
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.koin.core.inject

class Zealot : Role() {
    override val name = "狂信者"
    override val description = """
        |人狼に味方をする市民です。
        |誰が人狼であるかが分かります。
    """.trimMargin()
    override val groupType = GroupType.WEREWOLVES

    private val roleService: RoleService by inject()

    override fun onPassive(player: Player) {
    }

    override fun onClickedEntity(player: Player, targetEntity: Entity) {
    }

    override fun onAttackedEntity(player: Player, targetEntity: Entity, event: EntityDamageByEntityEvent) {
    }

    override fun onShownStatus(player: Player) {
        super.onShownStatus(player)
        val players = roleService.getRolePlayers(RoleType.WEREWOLF)
        var text = "人狼のプレイヤー "
        players.forEach {
            text += "${it.name} "
        }
        player.sendMessage(text)
    }
}