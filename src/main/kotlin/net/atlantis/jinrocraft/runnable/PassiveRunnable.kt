package net.atlantis.jinrocraft.runnable

import net.atlantis.jinrocraft.ext.getOnlineAlivePlayers
import net.atlantis.jinrocraft.model.RoleService
import net.atlantis.jinrocraft.model.RoleType
import net.atlantis.jinrocraft.model.role.Hunter
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable
import org.koin.core.KoinComponent
import org.koin.core.inject

class PassiveRunnable : BukkitRunnable(), KoinComponent {
    private val plugin: JavaPlugin by inject()

    override fun run() {
        plugin.server.getOnlineAlivePlayers()
                .forEach {
                    val roleType = RoleService().getRole(it)
                    object : BukkitRunnable() {
                        override fun run() {
                            when (roleType) {
                                RoleType.HUNTER -> {
                                    Hunter().onPassive(it)
                                }
                                else -> {

                                }
                            }
                        }
                    }.runTaskLater(plugin, 1)
                }

    }
}