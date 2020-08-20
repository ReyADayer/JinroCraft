package net.atlantis.jinrocraft.runnable

import net.atlantis.jinrocraft.model.time.TimeType
import net.atlantis.jinrocraft.view.Title
import org.bukkit.ChatColor
import org.bukkit.Server
import org.bukkit.scheduler.BukkitRunnable
import org.koin.core.KoinComponent
import org.koin.core.inject

class TimeRunnable : BukkitRunnable(), KoinComponent {
    private val server: Server by inject()

    private var currentTimeType = TimeType.MOONING

    override fun run() {
        val world = server.getWorld("world") ?: return
        val timeType = TimeType.findByWorld(world) ?: return
        if (currentTimeType != timeType) {
            currentTimeType = timeType
            val title = Title("${ChatColor.GREEN}${timeType.jpName}", "${ChatColor.WHITE}${timeType.description}")
            server.onlinePlayers.forEach {
                title.send(it)
                it.playSound(it.location, timeType.sound, 1.0f, 0.8f)
            }
        }
    }
}