package net.atlantis.jinrocraft.ext

import org.bukkit.command.CommandExecutor
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable

fun JavaPlugin.initCommand(command: String, executor: CommandExecutor) {
    getCommand(command)?.setExecutor(executor)
}

fun JavaPlugin.registerListener(listener: Listener) {
    server.pluginManager.registerEvents(listener, this)
}

fun JavaPlugin.scheduleRunnable(runnable: BukkitRunnable, delay: Long, period: Long) {
    runnable.runTaskTimer(this, delay, period)
}

fun JavaPlugin.scheduleAsyncRunnable(runnable: BukkitRunnable, delay: Long, period: Long) {
    runnable.runTaskTimerAsynchronously(this, delay, period)
}