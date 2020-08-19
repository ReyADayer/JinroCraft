package net.atlantis.jinrocraft.scoreboad

import net.atlantis.jinrocraft.model.RoleType
import org.bukkit.Server
import org.bukkit.entity.Player
import org.bukkit.scoreboard.Scoreboard
import org.bukkit.scoreboard.Team

class CoScoreboard(private val server: Server) {
    fun set(player: Player, roleType: RoleType) {
        val scoreBoard = server.scoreboardManager?.mainScoreboard ?: return
        val team = getTeam(scoreBoard, roleType)
        team.addEntry(player.name)
    }

    private fun getTeam(scoreboard: Scoreboard, roleType: RoleType): Team {
        scoreboard.getTeam(roleType.key)?.let {
            return it
        }
        return scoreboard.registerNewTeam(roleType.key).apply {
            displayName = roleType.jpName
            prefix = "[${roleType.jpName}] "
        }
    }
}