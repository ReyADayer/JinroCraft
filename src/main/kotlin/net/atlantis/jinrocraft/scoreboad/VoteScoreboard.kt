package net.atlantis.jinrocraft.scoreboad

import org.bukkit.Server
import org.bukkit.entity.Player
import org.bukkit.scoreboard.DisplaySlot

class VoteScoreboard(private val server: Server) {
    companion object {
        private const val TAG = "vote"
    }

    private val result = mutableMapOf<Player, Player>()
    private val scoreboard = server.scoreboardManager?.newScoreboard!!
    private val objective = scoreboard.registerNewObjective(TAG, TAG, "投票")

    fun init() {
        result.clear()
        showVoteScore()
    }

    fun set(player: Player, targetPlayer: Player) {
        result[player] = targetPlayer
        updateSideBar()
        showVoteScore()
    }

    fun execute() {
        val player = getVoteScores().maxBy { it.value }?.key
        player?.damage(100.0)
    }

    private fun showVoteScore() {
        server.onlinePlayers.forEach {
            it.scoreboard = scoreboard
        }
    }

    private fun updateSideBar() {
        objective.displaySlot = DisplaySlot.SIDEBAR
        val scores = getVoteScores().toList().sortedBy { it.second }.take(5).toMap()
        scores.forEach { player, count ->
            val score = objective.getScore(player.name)
            score.score = count
        }
    }

    private fun getVoteScores(): Map<Player, Int> {
        val scores = mutableMapOf<Player, Int>()
        result.forEach { _, targetPlayer ->
            val value = scores.getOrDefault(targetPlayer, 0)
            scores[targetPlayer] = value + 1
        }
        return scores
    }
}