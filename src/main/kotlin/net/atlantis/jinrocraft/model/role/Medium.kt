package net.atlantis.jinrocraft.model.role

import net.atlantis.jinrocraft.ext.getBooleanMetadata
import net.atlantis.jinrocraft.metadata.MetadataKey
import net.atlantis.jinrocraft.model.RoleService
import net.atlantis.jinrocraft.model.RoleType
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.Entity
import org.bukkit.entity.Player

class Medium : Role() {
    override fun onClickedEntity(player: Player, targetEntity: Entity) {
        if (targetEntity is ArmorStand && targetEntity.getBooleanMetadata(MetadataKey.IS_GRAVE.key) && player.equipment?.itemInMainHand?.type == Material.SHEARS) {
            val targetRoleType = RoleService().getRole(targetEntity)
            when(targetRoleType){
                RoleType.WEREWOLF -> {
                    player.sendMessage("${ChatColor.RED}${targetEntity.name}は ${ChatColor.BOLD}人狼 ${ChatColor.RESET}でした")
                }
                else -> {
                    player.sendMessage("${targetEntity.name}は ${ChatColor.BOLD}村人 ${ChatColor.RESET}でした")
                }
            }
        }
    }

    override fun onAttackedEntity(player: Player, targetEntity: Entity) {
    }
}