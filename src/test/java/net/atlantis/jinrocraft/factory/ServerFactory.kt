package net.atlantis.jinrocraft.factory

import io.mockk.every
import io.mockk.mockk
import org.bukkit.Server
import org.bukkit.entity.Player

object ServerFactory {
    fun build(players: List<Player> = emptyList()): Server {
        val server = mockk<Server>()
        every { server.onlinePlayers } returns players
        return server
    }
}