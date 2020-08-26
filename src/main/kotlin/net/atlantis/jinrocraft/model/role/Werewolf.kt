package net.atlantis.jinrocraft.model.role

import net.atlantis.jinrocraft.model.GroupType
import net.atlantis.jinrocraft.model.RoleService
import net.atlantis.jinrocraft.model.RoleType
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.koin.core.inject

class Werewolf : Role() {
    override val name = "人狼"
    override val description = """
        |夜の間、攻撃によるダメージが2増加します。
    """.trimMargin()
    override val groupType = GroupType.WEREWOLVES

    override val canJinroChat = true

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