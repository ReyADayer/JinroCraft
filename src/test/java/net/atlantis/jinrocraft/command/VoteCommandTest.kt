package net.atlantis.jinrocraft.command

import io.mockk.every
import io.mockk.mockk
import net.atlantis.jinrocraft.factory.PlayerFactory
import net.atlantis.jinrocraft.scoreboad.VoteScoreboard
import org.bukkit.GameMode
import org.bukkit.Server
import org.bukkit.command.Command
import org.bukkit.entity.Player
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.Module
import org.koin.dsl.module

internal class VoteCommandTest {
    companion object {
        private val server = mockk<Server>()
        private val voteScoreboard = mockk<VoteScoreboard>()

        private val mockModule: Module = module {
            single { server }
            single { voteScoreboard }
        }

        @BeforeAll
        @JvmStatic
        internal fun beforeAll() {
            every { voteScoreboard.set(any(), any()) } returns Unit
            startKoin {
                modules(listOf(mockModule))
            }
        }

        @AfterAll
        @JvmStatic
        internal fun afterAll() {
            stopKoin()
        }
    }

    private val voteCommand = VoteCommand()
    private val command = mockk<Command>()
    private val player = getPlayer("Player")
    private val targetPlayer = getPlayer("Target")

    private val players = listOf(
            getPlayer("Anna"),
            getPlayer("Mary"),
            getPlayer("Gail"),
            getPlayer("Thomas")
    )

    @BeforeEach
    fun before() {
        every { server.onlinePlayers } returns players
    }

    @Nested
    inner class WhenDeadPlayer {
        @Test
        fun onCommandByPlayer() {
            every { player.gameMode } returns GameMode.SPECTATOR
            val args = getArgs("Example")
            val result = voteCommand.onCommandByPlayer(player, command, "", args)
            Assertions.assertFalse(result)
        }
    }

    @Nested
    inner class WhenTargetIsAlive {
        @Test
        fun onCommandByPlayer() {
            val args = getArgs("Anna")
            val result = voteCommand.onCommandByPlayer(player, command, "", args)
            Assertions.assertTrue(result)
        }
    }

    @Nested
    inner class WhenTargetIsDead {
        @Test
        fun onCommandByPlayer() {
            every { targetPlayer.gameMode } returns GameMode.SPECTATOR
            val args = getArgs(targetPlayer.name)
            val result = voteCommand.onCommandByPlayer(player, command, "", args)
            Assertions.assertFalse(result)
        }
    }

    private fun getPlayer(name: String): Player {
        val player = PlayerFactory.build(name = name)
        every { server.getPlayer(name) } returns player
        return player
    }

    private fun getArgs(targetPlayerName: String): CommandArgs {
        return CommandArgs(arrayOf(targetPlayerName))
    }
}