package net.atlantis.jinrocraft.model

import net.atlantis.jinrocraft.config.PluginPreference
import net.atlantis.jinrocraft.ext.getOnlineAlivePlayers
import net.atlantis.jinrocraft.view.Title
import org.bukkit.ChatColor
import org.bukkit.Server
import org.bukkit.entity.Player
import org.koin.core.KoinComponent
import org.koin.core.inject

class GameEnd : KoinComponent {
    private val server: Server by inject()
    private val roleService: RoleService by inject()
    private val pluginPreference: PluginPreference by inject()

    fun execute() {
        val result = result() ?: return
        val title = Title("${ChatColor.AQUA}${result.jpName}の勝利", "")
        server.onlinePlayers.forEach {
            title.send(it)
        }
        pluginPreference.gameStart = false
    }

    private fun result(): GroupType? {
        val players = server.getOnlineAlivePlayers()
        val wereWolfs = players.filter { roleService.getRole(it)?.countType == CountType.WEREWOLF }
        if (wereWolfs.isEmpty()) {
            return if (isFoxesAlive(players)) {
                GroupType.FOXES
            } else {
                GroupType.CITIZENS
            }
        }
        val citizens = players.filter { roleService.getRole(it)?.countType == CountType.CITIZEN }
        if (citizens.size <= wereWolfs.size) {
            return if (isFoxesAlive(players)) {
                GroupType.FOXES
            } else {
                GroupType.WEREWOLVES
            }
        }
        return null
    }

    private fun isFoxesAlive(players: List<Player>): Boolean {
        val foxes = players.filter { roleService.getRole(it)?.countType == CountType.FOXES }
        return foxes.isNotEmpty()
    }
}