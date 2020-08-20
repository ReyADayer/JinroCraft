package net.atlantis.jinrocraft.command

import net.atlantis.jinrocraft.view.co.CoMenu
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.koin.core.inject

class ComingOutCommand : BaseCommand() {
    override fun onCommandByPlayer(player: Player, command: Command, label: String, args: CommandArgs): Boolean {
        CoMenu(player).show()
        return true
    }

    override fun onCommandByOther(sender: CommandSender, command: Command, label: String, args: CommandArgs): Boolean {
        sender.sendMessage("You must be a player!")
        return false
    }
}