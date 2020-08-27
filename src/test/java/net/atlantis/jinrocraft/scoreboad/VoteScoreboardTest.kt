package net.atlantis.jinrocraft.scoreboad

import io.mockk.every
import io.mockk.mockk
import net.atlantis.jinrocraft.factory.ObjectiveFactory
import net.atlantis.jinrocraft.factory.PlayerFactory
import net.atlantis.jinrocraft.factory.ScoreFactory
import net.atlantis.jinrocraft.factory.WorldFactory
import org.bukkit.Server
import org.bukkit.World
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scoreboard.Scoreboard
import org.bukkit.scoreboard.ScoreboardManager
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.util.*

internal class VoteScoreboardTest {
    companion object {
        private val plugin = mockk<JavaPlugin>()
        private val server = mockk<Server>()
        private val scoreboard = mockk<Scoreboard>()
        private val scoreboardManager = mockk<ScoreboardManager>()
        private val objective = ObjectiveFactory.build()

        @BeforeAll
        @JvmStatic
        internal fun beforeAll() {
            every { server.scoreboardManager } returns scoreboardManager
            every { server.onlinePlayers } returns emptyList()
            every { scoreboardManager.newScoreboard } returns scoreboard
            every { scoreboard.registerNewObjective("vote", "vote", "投票") } returns objective
        }
    }

    private val voteScoreboard = VoteScoreboard(plugin, server)

    @BeforeEach
    fun before() {
        every { server.getWorld("world") } returns getWorld()
        voteScoreboard.init()
    }

    @Test
    fun init() {
        val player = getPlayer()
        val targetPlayer = getPlayer()
        voteScoreboard.set(player, targetPlayer)
        voteScoreboard.init()
        val score = voteScoreboard.getVoteScores()
        Assertions.assertNull(score[player])
    }

    @Test
    fun set() {
        val player = getPlayer()
        val targetPlayer = getPlayer()
        voteScoreboard.set(player, targetPlayer)
        voteScoreboard.set(getPlayer(), player)
        voteScoreboard.set(getPlayer(), player)
        voteScoreboard.set(getPlayer(), player)
        voteScoreboard.set(getPlayer(), player)

        val score = voteScoreboard.getVoteScores()
        Assertions.assertEquals(4, score[player])
        Assertions.assertEquals(1, score[targetPlayer])
    }

    @Test
    fun getExecutePlayer() {
        val player = getPlayer()
        val targetPlayer = getPlayer()
        voteScoreboard.set(player, targetPlayer)
        voteScoreboard.set(getPlayer(), targetPlayer)
        voteScoreboard.set(getPlayer(), targetPlayer)
        voteScoreboard.set(getPlayer(), player)
        voteScoreboard.set(getPlayer(), player)
        Assertions.assertEquals(targetPlayer, voteScoreboard.getExecutePlayer())
    }

    @Nested
    inner class WhenThereAreNoPlayers {
        @BeforeEach
        fun before() {
            every { server.onlinePlayers } returns emptyList()
        }

        @Test
        fun getExecutePlayer() {
            Assertions.assertNull(voteScoreboard.getExecutePlayer())
        }
    }

    @Nested
    inner class WhenTheVotesAreTheSame {
        private val players = listOf(
                getPlayer(),
                getPlayer(),
                getPlayer(),
                getPlayer()
        )

        @BeforeEach
        fun before() {
            every { server.onlinePlayers } returns players
        }

        @Test
        fun getExecutePlayer() {
            val player = getPlayer()
            val targetPlayer = getPlayer()
            voteScoreboard.set(getPlayer(), targetPlayer)
            voteScoreboard.set(getPlayer(), targetPlayer)
            voteScoreboard.set(getPlayer(), player)
            voteScoreboard.set(getPlayer(), player)

            val executePlayer = voteScoreboard.getExecutePlayer()
            Assertions.assertTrue(executePlayer == player || executePlayer == targetPlayer)
        }
    }

    private fun getPlayer(): Player {
        val name = UUID.randomUUID().toString()
        val score = ScoreFactory.build()
        every { objective.getScore(name) } returns score
        return PlayerFactory.build(name)
    }

    private fun getWorld(): World {
        return WorldFactory.build(12000)
    }

    @Suppress("UNCHECKED_CAST")
    private fun VoteScoreboard.getVoteScores(): Map<Player, Int> {
        val method = this::class.java.getDeclaredMethod("getVoteScores")
        method.isAccessible = true
        return method.invoke(this) as Map<Player, Int>
    }

    private fun VoteScoreboard.getExecutePlayer(): Player? {
        val method = this::class.java.getDeclaredMethod("getExecutePlayer")
        method.isAccessible = true
        return method.invoke(this) as Player?
    }
}