package net.atlantis.jinrocraft

import net.atlantis.jinrocraft.command.JinroChatCommand
import net.atlantis.jinrocraft.command.RoleCommand
import net.atlantis.jinrocraft.command.StatusCommand
import net.atlantis.jinrocraft.ext.initCommand
import net.atlantis.jinrocraft.ext.registerListener
import net.atlantis.jinrocraft.ext.scheduleAsyncRunnable
import net.atlantis.jinrocraft.listener.PlayerListener
import net.atlantis.jinrocraft.runnable.PassiveRunnable
import org.bukkit.plugin.java.JavaPlugin
import org.koin.core.context.startKoin
import org.koin.dsl.module

class JinroCraft : JavaPlugin() {
    override fun onEnable() {
        saveDefaultConfig()

        setupKoin()

        registerListener(PlayerListener())

        initCommand("status", StatusCommand())
        initCommand("role", RoleCommand())
        initCommand("jc", JinroChatCommand())

        scheduleAsyncRunnable(PassiveRunnable(), 20, 500)
    }

    override fun onDisable() {
    }

    private val myModule = module {
        single<JavaPlugin> { this@JinroCraft }
    }

    private fun setupKoin() {
        startKoin {
            modules(listOf(myModule))
        }
    }
}