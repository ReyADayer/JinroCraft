package net.atlantis.jinrocraft.runnable

import net.atlantis.jinrocraft.config.PluginPreference
import net.atlantis.jinrocraft.ext.getOnlineAlivePlayers
import net.atlantis.jinrocraft.model.RoleService
import net.atlantis.jinrocraft.model.RoleType
import net.atlantis.jinrocraft.model.time.TimeType
import net.atlantis.jinrocraft.scoreboad.VoteScoreboard
import net.atlantis.jinrocraft.view.Title
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.Server
import org.bukkit.inventory.ItemStack
import org.bukkit.scheduler.BukkitRunnable
import org.koin.core.KoinComponent
import org.koin.core.inject

class TimeRunnable : BukkitRunnable(), KoinComponent {
    private val server: Server by inject()
    private val voteScoreboard: VoteScoreboard by inject()
    private val pluginPreference: PluginPreference by inject()
    private val roleService: RoleService by inject()

    private var currentTimeType = TimeType.MOONING

    override fun run() {
        if (!pluginPreference.gameStart) {
            return
        }
        val world = server.getWorld("world") ?: return
        val timeType = TimeType.findByWorld(world) ?: return
        if (currentTimeType != timeType) {
            currentTimeType = timeType
            val title = Title("${ChatColor.GREEN}${timeType.jpName}", "${ChatColor.WHITE}${timeType.description}")
            server.onlinePlayers.forEach {
                title.send(it)
                it.playSound(it.location, timeType.sound, 1.0f, 0.8f)
            }
            when (currentTimeType) {
                TimeType.MOONING -> {
                    giveBreads()
                }
                TimeType.EVENING -> {
                    voteScoreboard.init()
                }
                TimeType.NIGHT -> {
                    voteScoreboard.execute()
                }
            }
        }
    }

    private fun giveBreads() {
        if (!pluginPreference.roles.contains(RoleType.BAKERY.key)) {
            return
        }

        val isBakeryAlive = server.getOnlineAlivePlayers().firstOrNull { roleService.getRole(it) == RoleType.BAKERY } != null
        if (isBakeryAlive) {
            server.broadcastMessage("おいしいパンが届けられました")
            server.getOnlineAlivePlayers().forEach {
                if (it.inventory.firstEmpty() != -1) {
                    it.inventory.addItem(ItemStack(Material.BREAD, 1))
                }
            }
        } else {
            server.broadcastMessage("パンが届けられなくなったようです")
        }
    }
}