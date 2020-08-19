package net.atlantis.jinrocraft.command

import net.atlantis.jinrocraft.model.RoleService
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.koin.core.inject

class RoleCommand : BaseCommand() {
    private val plugin: JavaPlugin by inject()

    override fun onCommandByPlayer(player: Player, command: Command, label: String, args: CommandArgs): Boolean {
        return when (args[0]) {
            "set" -> {
                val selectedPlayerName = args[1] ?: return false
                val selectedPlayer = plugin.server.getPlayer(selectedPlayerName) ?: return false
                val roleTypeKey = args[2] ?: return false
                RoleService().setRole(selectedPlayer, roleTypeKey)
                true
            }
            "reset" -> {
                RoleService().reset()
                true
            }
            else -> false
        }
    }

    override fun onCommandByOther(sender: CommandSender, command: Command, label: String, args: CommandArgs): Boolean {
        sender.sendMessage("You must be a player!")
        return false
    }
}