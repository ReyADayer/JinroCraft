package net.atlantis.jinrocraft.command

import net.atlantis.jinrocraft.ext.getOnlineAlivePlayers
import net.atlantis.jinrocraft.message.JinroMessage
import net.atlantis.jinrocraft.model.RoleService
import org.bukkit.GameMode
import org.bukkit.Server
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.koin.core.inject

class JinroChatCommand : BaseCommand() {
    private val server: Server by inject()
    private val roleService: RoleService by inject()

    override fun onCommandByPlayer(player: Player, command: Command, label: String, args: CommandArgs): Boolean {
        if (player.gameMode != GameMode.SURVIVAL) {
            return false
        }
        val role = roleService.getRoleClass(player) ?: return false
        if (role.canJinroChat) {
            val text = args[0] ?: return true
            server.getOnlineAlivePlayers()
                    .filter { roleService.getRoleClass(player)?.canJinroChat == true }
                    .forEach {
                        JinroMessage().text(player, it, text)
                    }
            return true
        } else {
            player.sendMessage("人狼のみ使用できます")
            return true
        }
    }

    override fun onCommandByOther(sender: CommandSender, command: Command, label: String, args: CommandArgs): Boolean {
        sender.sendMessage("You must be a player!")
        return false
    }
}