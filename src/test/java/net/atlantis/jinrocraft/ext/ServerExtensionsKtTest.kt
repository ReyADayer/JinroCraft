package net.atlantis.jinrocraft.ext

import io.mockk.every
import io.mockk.mockk
import net.atlantis.jinrocraft.factory.PlayerFactory
import org.bukkit.GameMode
import org.bukkit.Server
import org.bukkit.entity.Player
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class ServerExtensionsKtTest {
    companion object {
        private val server = mockk<Server>()
    }

    @Test
    fun getOnlineAlivePlayers() {
        val player = getPlayer(GameMode.SURVIVAL)
        val deadPlayer = getPlayer(GameMode.SPECTATOR)
        every { server.onlinePlayers } returns listOf(player, deadPlayer)
        val alivePlayers = server.getOnlineAlivePlayers()
        Assertions.assertTrue(alivePlayers.contains(player))
        Assertions.assertFalse(alivePlayers.contains(deadPlayer))
    }

    private fun getPlayer(gameMode: GameMode): Player {
        val player = PlayerFactory.build()
        every { player.gameMode } returns gameMode
        return player
    }
}