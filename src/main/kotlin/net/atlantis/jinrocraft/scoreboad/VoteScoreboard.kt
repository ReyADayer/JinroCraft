package net.atlantis.jinrocraft.scoreboad

import net.atlantis.jinrocraft.ext.getOnlineAlivePlayers
import net.atlantis.jinrocraft.ext.playSound
import net.atlantis.jinrocraft.model.time.TimeType
import org.bukkit.Server
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.scoreboard.DisplaySlot
import java.util.*

class VoteScoreboard(private val plugin: JavaPlugin, private val server: Server) {
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
        val world = server.getWorld("world") ?: return
        val timeType = TimeType.findByWorld(world) ?: return
        if (timeType == TimeType.EVENING) {
            result[player] = targetPlayer
            updateSideBar()
            showVoteScore()
            player.sendMessage("${targetPlayer.name}に投票しました")
        } else {
            player.sendMessage("投票時間ではありません")
        }
    }

    fun execute() {
        val player = getExecutePlayer() ?: return
        server.broadcastMessage("${player.name}が処刑されます")
        object : BukkitRunnable() {
            override fun run() {
                player.damage(100.0)
                player.location.playSound(Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR, 1.0f, 1.0f)
            }
        }.runTaskLater(plugin, 50)
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

    private fun getExecutePlayer(): Player? {
        getVoteScores().maxBy { it.value }?.key?.let {
            return it
        }
        val players = server.getOnlineAlivePlayers()
        if (players.isEmpty()) {
            return null
        }
        return players[Random().nextInt(players.size)]
    }
}