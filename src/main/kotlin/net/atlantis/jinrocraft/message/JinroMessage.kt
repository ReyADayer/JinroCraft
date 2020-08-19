package net.atlantis.jinrocraft.message

import org.bukkit.ChatColor
import org.bukkit.entity.Player

class JinroMessage : Message() {
    override val header: String = "人狼チャット"

    fun text(sender: Player, player: Player, text: String) {
        send(player, "${ChatColor.RED} ${sender.name} > $text")
    }
}