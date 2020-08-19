package net.atlantis.jinrocraft.message

import org.bukkit.ChatColor
import org.bukkit.entity.Player

abstract class Message {
    abstract val header: String

    fun send(player: Player, text: String) {
        val message = message(text)
        player.sendMessage(message)
    }

    private fun message(text: String): String {
        return ChatColor.WHITE.toString() + "[" +
                ChatColor.RED.toString() + header +
                ChatColor.WHITE.toString() + "] : " +
                ChatColor.RESET.toString() + text
    }
}