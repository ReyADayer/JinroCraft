package net.atlantis.jinrocraft

import net.atlantis.jinrocraft.command.ComingOutCommand
import net.atlantis.jinrocraft.command.GameCommand
import net.atlantis.jinrocraft.command.JinroChatCommand
import net.atlantis.jinrocraft.command.RoleCommand
import net.atlantis.jinrocraft.command.StatusCommand
import net.atlantis.jinrocraft.command.VoteCommand
import net.atlantis.jinrocraft.config.PluginPreference
import net.atlantis.jinrocraft.ext.initCommand
import net.atlantis.jinrocraft.ext.registerListener
import net.atlantis.jinrocraft.ext.scheduleAsyncRunnable
import net.atlantis.jinrocraft.ext.scheduleRunnable
import net.atlantis.jinrocraft.listener.PlayerListener
import net.atlantis.jinrocraft.listener.ViewListener
import net.atlantis.jinrocraft.model.RoleService
import net.atlantis.jinrocraft.runnable.PassiveRunnable
import net.atlantis.jinrocraft.runnable.TimeRunnable
import net.atlantis.jinrocraft.scoreboad.CoScoreboard
import net.atlantis.jinrocraft.scoreboad.VoteScoreboard
import org.bukkit.permissions.PermissionDefault
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.plugin.java.annotation.command.Command
import org.bukkit.plugin.java.annotation.command.Commands
import org.bukkit.plugin.java.annotation.permission.Permission
import org.bukkit.plugin.java.annotation.permission.Permissions
import org.bukkit.plugin.java.annotation.plugin.ApiVersion
import org.bukkit.plugin.java.annotation.plugin.Description
import org.bukkit.plugin.java.annotation.plugin.Plugin
import org.bukkit.plugin.java.annotation.plugin.author.Author
import org.koin.core.context.startKoin
import org.koin.dsl.module

@Plugin(name = "JinroCraft", version = "1.1.2")
@Description("Jinro game in minecraft.")
@Author("ReyADayer")
@ApiVersion(ApiVersion.Target.v1_15)
@Permissions(
        Permission(
                name = PluginPermissions.ADMIN,
                desc = "Gives access to JinroCraft admin commands",
                defaultValue = PermissionDefault.OP
        )
)
@Commands(
        Command(
                name = PluginCommands.ROLE,
                desc = "role command",
                permission = PluginPermissions.ADMIN,
                usage = "/<command>",
                permissionMessage = "You don't have <permission>"
        ),
        Command(
                name = PluginCommands.GAME,
                desc = "game command",
                permission = PluginPermissions.ADMIN,
                usage = "/<command>",
                permissionMessage = "You don't have <permission>"
        ),
        Command(
                name = PluginCommands.STATUS,
                desc = "status command",
                usage = "/<command>"
        ),
        Command(
                name = PluginCommands.JC,
                desc = "jinro chat command",
                usage = "/<command>"
        ),
        Command(
                name = PluginCommands.CO,
                desc = "co command",
                usage = "/<command>"
        ),
        Command(
                name = PluginCommands.VOTE,
                desc = "vote command",
                usage = "/<command> PlayerName"
        )
)
class JinroCraft : JavaPlugin() {
    override fun onEnable() {
        saveDefaultConfig()

        setupKoin()

        registerListener(PlayerListener())
        registerListener(ViewListener())

        initCommand(PluginCommands.GAME, GameCommand())
        initCommand(PluginCommands.STATUS, StatusCommand())
        initCommand(PluginCommands.ROLE, RoleCommand())
        initCommand(PluginCommands.JC, JinroChatCommand())
        initCommand(PluginCommands.CO, ComingOutCommand())
        initCommand(PluginCommands.VOTE, VoteCommand())

        scheduleAsyncRunnable(PassiveRunnable(), 20, 500)
        scheduleRunnable(TimeRunnable(), 20, 20)
    }

    override fun onDisable() {
    }

    private val myModule = module {
        single<JavaPlugin> { this@JinroCraft }
        single { config }
        single { server }
        single { PluginPreference(get(), get()) }
        single { CoScoreboard(get()) }
        single { VoteScoreboard(get(), get()) }
        single { RoleService(get(), get()) }
    }

    private fun setupKoin() {
        startKoin {
            modules(listOf(myModule))
        }
    }
}