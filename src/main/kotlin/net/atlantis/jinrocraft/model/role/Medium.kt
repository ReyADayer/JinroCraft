package net.atlantis.jinrocraft.model.role

import net.atlantis.jinrocraft.ext.getBooleanMetadata
import net.atlantis.jinrocraft.metadata.MetadataKeys
import net.atlantis.jinrocraft.model.GroupType
import net.atlantis.jinrocraft.model.RoleService
import net.atlantis.jinrocraft.model.RoleType
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
        if (targetEntity is ArmorStand && targetEntity.getBooleanMetadata(MetadataKeys.IS_GRAVE) && canUse(player)) {
            val targetRoleType = roleService.getRole(targetEntity)
            val result = result(targetRoleType)
            player.sendMessage(result.message(targetEntity))
        }
    }

    override fun onAttackedEntity(player: Player, targetEntity: Entity, event: EntityDamageByEntityEvent) {
    }

    private fun canUse(player: Player): Boolean {
        return player.equipment?.itemInMainHand?.type == Material.SHEARS
    }

    private fun result(roleType: RoleType?): MediumResult {
        return when (roleType) {
            RoleType.WEREWOLF -> {
                MediumResult.WEREWOLF
            }
            RoleType.GREAT_WOLF -> {
                MediumResult.GREAT_WOLF
            }
            else -> {
                MediumResult.NOT_WEREWOLF
            }
        }
    }
}