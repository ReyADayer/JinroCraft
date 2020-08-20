package net.atlantis.jinrocraft.command

import net.atlantis.jinrocraft.model.RoleService
import org.bukkit.Server
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.koin.core.inject

class RoleCommand : BaseCommand() {
    private val server: Server by inject()
    private val roleService: RoleService by inject()

    override fun onCommandByPlayer(player: Player, command: Command, label: String, args: CommandArgs): Boolean {
        return when (args[0]) {
            "reset" -> {
                roleService.reset()
                true
            }
            "setting" -> {
                val roleTypeKey = args[1] ?: return false
                val count = args[2]?.toIntOrNull() ?: return false
                roleService.setting(roleTypeKey, count)
                server.broadcastMessage(roleService.getRoles())
                true
            }
            "list" -> {
                server.broadcastMessage(roleService.getRoles())
                true
            }
            "clear_setting" -> {
                roleService.clearSetting()
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