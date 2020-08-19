package net.atlantis.jinrocraft.model

import org.bukkit.ChatColor
import org.bukkit.Server
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.koin.core.KoinComponent
import org.koin.core.inject

class ComingOut(private val player: Player) : KoinComponent {
    private val server: Server by inject()

    fun send(roleType: RoleType) {
        val message = "${ChatColor.YELLOW}${player.name}が ${roleType.jpName} CO"
        server.onlinePlayers.forEach {
            it.sendMessage(message)
            it.playSound(it.location, Sound.BLOCK_NOTE_BLOCK_GUITAR, 1.0f, 1.3f)
        }
    }
}