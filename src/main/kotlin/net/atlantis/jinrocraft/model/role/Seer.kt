package net.atlantis.jinrocraft.model.role

import net.atlantis.jinrocraft.model.RoleService
import net.atlantis.jinrocraft.model.RoleType
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageByEntityEvent

class Seer : Role() {
    override fun onClickedEntity(player: Player, targetEntity: Entity) {
        if (targetEntity is Player && player.level >= 10 && player.equipment?.itemInMainHand?.type == Material.SHEARS) {
            val targetRoleType = RoleService().getRole(targetEntity)
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