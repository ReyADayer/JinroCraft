package net.atlantis.jinrocraft.command

import net.atlantis.jinrocraft.config.PluginPreference
import net.atlantis.jinrocraft.model.RoleService
import org.bukkit.Server
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.koin.core.inject

class GameCommand : BaseCommand() {
    private val server: Server by inject()
    private val pluginPreference: PluginPreference by inject()
    private val roleService: RoleService by inject()

    override fun onCommandByPlayer(player: Player, command: Command, label: String, args: CommandArgs): Boolean {
        return when (args[0]) {
            "start" -> {
                pluginPreference.gameStart = true
                roleService.reset()
                roleService.initRoles()
                pluginPreference.syncRoles()
                val world = server.getWorld("world") ?: return false
                world.time = 0
                true
            }
            "stop" -> {
                pluginPreference.gameStart = false
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