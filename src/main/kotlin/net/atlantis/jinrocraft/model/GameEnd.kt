package net.atlantis.jinrocraft.model

import net.atlantis.jinrocraft.ext.getOnlineAlivePlayers
import net.atlantis.jinrocraft.view.Title
import org.bukkit.ChatColor
import org.bukkit.Server
import org.koin.core.KoinComponent
import org.koin.core.inject

class GameEnd : KoinComponent {
    private val server: Server by inject()
    private val roleService: RoleService by inject()

    fun execute() {
        val result = result() ?: return
        val title = Title("${ChatColor.AQUA}${result.jpName}の勝利", "")
        server.onlinePlayers.forEach {
            title.send(it)
        }
    }

    private fun result(): GroupType? {
        val players = server.getOnlineAlivePlayers()
        val wereWolfs = players.filter { roleService.getRole(it) == RoleType.WEREWOLF }
        if (wereWolfs.isEmpty()) {
            val foxes = players.filter { roleService.getRole(it) == RoleType.FOX }
            return if (foxes.isEmpty()) {
                GroupType.CITIZENS
            } else {
                GroupType.FOXES
            }
        }
        val citizens = players.filterNot { listOf(RoleType.WEREWOLF, RoleType.FOX).contains(roleService.getRole(it)) }
        if (citizens.size <= wereWolfs.size) {
            val foxes = players.filter { roleService.getRole(it) == RoleType.FOX }
            return if (foxes.isEmpty()) {
                GroupType.WEREWOLVES
            } else {
                GroupType.FOXES
            }
        }
        return null
    }
}