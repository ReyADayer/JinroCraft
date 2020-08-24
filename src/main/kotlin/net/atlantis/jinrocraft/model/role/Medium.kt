package net.atlantis.jinrocraft.model.role

import net.atlantis.jinrocraft.ext.getBooleanMetadata
import net.atlantis.jinrocraft.metadata.MetadataKeys
import net.atlantis.jinrocraft.model.GroupType
import net.atlantis.jinrocraft.model.RoleService
import net.atlantis.jinrocraft.model.RoleType
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.koin.core.inject

class Medium : Role() {
    override val name = "霊能者"
    override val description = """
        |ハサミを手に持った状態で墓を右クリックすると、
        |死亡した人が人狼であるかどうかを分かります。
    """.trimMargin()
    override val groupType = GroupType.CITIZENS

    private val roleService: RoleService by inject()

    override fun onPassive(player: Player) {
    }

    override fun onClickedEntity(player: Player, targetEntity: Entity) {
        if (targetEntity is ArmorStand && targetEntity.getBooleanMetadata(MetadataKeys.IS_GRAVE) && player.equipment?.itemInMainHand?.type == Material.SHEARS) {
            val targetRoleType = roleService.getRole(targetEntity)
            when (targetRoleType) {
                RoleType.WEREWOLF -> {
                    player.sendMessage("${ChatColor.RED}${targetEntity.name}は ${ChatColor.BOLD}人狼 ${ChatColor.RESET}でした")
                }
                else -> {
                    player.sendMessage("${targetEntity.name}は ${ChatColor.BOLD}村人 ${ChatColor.RESET}でした")
                }
            }
        }
    }

    override fun onAttackedEntity(player: Player, targetEntity: Entity, event: EntityDamageByEntityEvent) {
    }
}