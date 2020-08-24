package net.atlantis.jinrocraft.model.role

import net.atlantis.jinrocraft.model.GroupType
import net.atlantis.jinrocraft.model.RoleService
import net.atlantis.jinrocraft.model.RoleType
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.koin.core.inject

class Seer : Role() {
    override val name = "占い師"
    override val description = """
        |ハサミを手に持ってプレイヤーを右クリックすると、
        |経験値を10消費して人狼であるかどうかを判定できます
    """.trimMargin()
    override val groupType = GroupType.CITIZENS

    private val roleService: RoleService by inject()

    override fun onPassive(player: Player) {
    }

    override fun onClickedEntity(player: Player, targetEntity: Entity) {
        if (targetEntity is Player && player.level >= 10 && player.equipment?.itemInMainHand?.type == Material.SHEARS) {
            val targetRoleType = roleService.getRole(targetEntity)
            player.level -= 10
            when (targetRoleType) {
                RoleType.WEREWOLF -> {
                    player.sendMessage("${ChatColor.RED}${targetEntity.name}は ${ChatColor.BOLD}人狼 ${ChatColor.RESET}でした")
                }
                RoleType.FOX -> {
                    targetEntity.damage(100.0)
                    player.sendMessage("${targetEntity.name}は ${ChatColor.BOLD}村人 ${ChatColor.RESET}でした")
                }
                else -> {
                    player.sendMessage("${targetEntity.name}は ${ChatColor.BOLD}村人 ${ChatColor.RESET}でした")
                }
            }
        }
    }

    override fun onAttackedEntity(player: Player, targetEntity: Entity, event: EntityDamageByEntityEvent) {
        // 攻撃時の特殊能力は無し
    }
}