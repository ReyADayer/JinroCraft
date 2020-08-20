package net.atlantis.jinrocraft

import net.atlantis.jinrocraft.command.ComingOutCommand
import net.atlantis.jinrocraft.command.JinroChatCommand
import net.atlantis.jinrocraft.command.RoleCommand
import net.atlantis.jinrocraft.command.StatusCommand
import net.atlantis.jinrocraft.command.VoteCommand
import net.atlantis.jinrocraft.config.PluginPreference
import net.atlantis.jinrocraft.ext.initCommand
import net.atlantis.jinrocraft.ext.registerListener
import net.atlantis.jinrocraft.ext.scheduleAsyncRunnable
import net.atlantis.jinrocraft.listener.PlayerListener
import net.atlantis.jinrocraft.listener.ViewListener
import net.atlantis.jinrocraft.runnable.PassiveRunnable
import net.atlantis.jinrocraft.runnable.TimeRunnable
import net.atlantis.jinrocraft.scoreboad.CoScoreboard
import net.atlantis.jinrocraft.scoreboad.VoteScoreboard
import org.bukkit.plugin.java.JavaPlugin
import org.koin.core.context.startKoin
import org.koin.dsl.module

class JinroCraft : JavaPlugin() {
    override fun onEnable() {
        saveDefaultConfig()

        setupKoin()

        registerListener(PlayerListener())
        registerListener(ViewListener())

        initCommand("status", StatusCommand())
        initCommand("role", RoleCommand())
        initCommand("jc", JinroChatCommand())
        initCommand("co", ComingOutCommand())
        initCommand("vote", VoteCommand())

        scheduleAsyncRunnable(PassiveRunnable(), 20, 500)
        scheduleAsyncRunnable(TimeRunnable(), 20, 20)
    }

    override fun onDisable() {
    }

    private val myModule = module {
        single<JavaPlugin> { this@JinroCraft }
        single { config }
        single { server }
        single { PluginPreference(get(), get()) }
        single { CoScoreboard(get()) }
        single { VoteScoreboard(get()) }
    }

    private fun setupKoin() {
        startKoin {
            modules(listOf(myModule))
        }
    }
}