package net.atlantis.jinrocraft.command

import org.bukkit.Server
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.koin.core.inject

class VoteCommand : BaseCommand() {
    private val server: Server by inject()

    override fun onCommandByPlayer(player: Player, command: Command, label: String, args: CommandArgs): Boolean {
        val selectedPlayerName = args[0] ?: return false
        val selectedPlayer = server.getPlayer(selectedPlayerName) ?: return false
        // TODO : 投票処理を追加
        return true
    }

    override fun onCommandByOther(sender: CommandSender, command: Command, label: String, args: CommandArgs): Boolean {
        sender.sendMessage("You must be a player!")
        return false
    }
}