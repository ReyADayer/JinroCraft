package net.atlantis.jinrocraft.listener

import net.atlantis.jinrocraft.model.Role
import net.atlantis.jinrocraft.model.npc.Grave
import org.bukkit.GameMode
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerRespawnEvent
import org.bukkit.plugin.java.JavaPlugin
import org.koin.core.KoinComponent
import org.koin.core.inject

class PlayerListener : Listener, KoinComponent {
    private val plugin: JavaPlugin by inject()

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val player = event.player
        Role().initRole(player)
    }

    @EventHandler
    fun onDeath(event: PlayerDeathEvent) {
        val player = event.entity
        event.deathMessage = "${player}が襲撃されました"
        Grave().create(player.location, player)
    }

    @EventHandler
    fun onRespawn(event: PlayerRespawnEvent) {
        val player = event.player
        player.gameMode = GameMode.SPECTATOR
    }

    @EventHandler
    fun onChat(event: AsyncPlayerChatEvent) {
        val player = event.player
        if (player.gameMode == GameMode.SPECTATOR) {
            event.isCancelled = true
        }
    }
}