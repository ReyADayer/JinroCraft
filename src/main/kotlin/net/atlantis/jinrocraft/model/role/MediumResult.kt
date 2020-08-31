package net.atlantis.jinrocraft.model.role

import org.bukkit.ChatColor
import org.bukkit.entity.ArmorStand

enum class MediumResult {
    WEREWOLF,
    GREAT_WOLF,
    NOT_WEREWOLF;

    fun message(targetEntity: ArmorStand): String {
        return when (this) {
            WEREWOLF -> "${ChatColor.RED}${targetEntity.name}は ${ChatColor.BOLD}人狼 ${ChatColor.RESET}でした"
            GREAT_WOLF -> "${ChatColor.RED}${targetEntity.name}は ${ChatColor.BOLD}大狼 ${ChatColor.RESET}でした"
            NOT_WEREWOLF -> "${targetEntity.name}は ${ChatColor.BOLD}村人 ${ChatColor.RESET}でした"
        }
    }
}