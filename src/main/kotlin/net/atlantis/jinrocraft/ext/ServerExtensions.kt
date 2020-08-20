package net.atlantis.jinrocraft.ext

import org.bukkit.GameMode
import org.bukkit.Server
import org.bukkit.entity.Player

fun Server.getOnlineAlivePlayers(): List<Player> = onlinePlayers.filter { it.gameMode == GameMode.SURVIVAL }