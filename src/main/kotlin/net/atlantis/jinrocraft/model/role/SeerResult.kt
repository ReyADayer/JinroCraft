package net.atlantis.jinrocraft.model.role

import org.bukkit.ChatColor
import org.bukkit.entity.Player

enum class SeerResult {
    WEREWOLF,
    NOT_WEREWOLF,
    FOX;

    fun message(targetPlayer: Player): String {
        return when (this) {
            WEREWOLF -> "${ChatColor.RED}${targetPlayer.name}は ${ChatColor.BOLD}人狼 ${ChatColor.RESET}でした"
            NOT_WEREWOLF, FOX -> "${targetPlayer.name}は ${ChatColor.BOLD}村人 ${ChatColor.RESET}でした"
        }
    }
}