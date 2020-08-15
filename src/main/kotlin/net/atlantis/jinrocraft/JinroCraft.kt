package net.atlantis.jinrocraft

import net.atlantis.jinrocraft.command.RoleCommand
import net.atlantis.jinrocraft.ext.initCommand
import net.atlantis.jinrocraft.ext.registerListener
import net.atlantis.jinrocraft.listener.PlayerListener
import org.bukkit.plugin.java.JavaPlugin
import org.koin.core.context.startKoin
import org.koin.dsl.module

class JinroCraft : JavaPlugin() {
    override fun onEnable() {
        saveDefaultConfig()

        setupKoin()

        registerListener(PlayerListener())

        initCommand("role", RoleCommand())
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