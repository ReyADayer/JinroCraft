package net.atlantis.jinrocraft.command

import net.atlantis.jinrocraft.ext.getOnlineAlivePlayers
import net.atlantis.jinrocraft.message.JinroMessage
import net.atlantis.jinrocraft.model.RoleService
import net.atlantis.jinrocraft.model.RoleType
import org.bukkit.GameMode
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.koin.core.inject

class JinroChatCommand : BaseCommand() {
    private val plugin: JavaPlugin by inject()
    private val roleService: RoleService by inject()

    override fun onCommandByPlayer(player: Player, command: Command, label: String, args: CommandArgs): Boolean {
        val roleType = roleService.getRole(player)
        if (player.gameMode != GameMode.SURVIVAL) {
            return false
        }

        if (roleType == RoleType.WEREWOLF) {
            val text = args[0] ?: return true
            plugin.server.getOnlineAlivePlayers()
                    .filter { roleService.getRole(player) == RoleType.WEREWOLF }
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