package net.atlantis.jinrocraft.command

import io.mockk.every
import io.mockk.mockk
import net.atlantis.jinrocraft.factory.PlayerFactory
import net.atlantis.jinrocraft.model.RoleService
import net.atlantis.jinrocraft.model.RoleType
import org.bukkit.GameMode
import org.bukkit.Server
import org.bukkit.command.Command
import org.bukkit.entity.Player
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import kotlin.reflect.full.primaryConstructor

internal class JinroChatCommandTest {
    companion object {
        private val server = mockk<Server>()
        private val roleService = mockk<RoleService>()

        private val mockModule: Module = module {
            single { server }
            single { roleService }
        }

        @BeforeAll
        @JvmStatic
        internal fun beforeAll() {
            startKoin {
                modules(listOf(mockModule))
            }
        }

        @AfterAll
        @JvmStatic
        internal fun afterAll() {
            stopKoin()
        }
    }

    private val jinroChatCommand = JinroChatCommand()
    private val command = mockk<Command>()

    private val players = listOf(
            getPlayer(RoleType.WEREWOLF),
            getPlayer(RoleType.WEREWOLF),
            getPlayer(RoleType.CITIZEN),
            getPlayer(RoleType.CITIZEN),
            getPlayer(RoleType.HUNTER)
    )

    @BeforeEach
    fun before() {
        every { server.onlinePlayers } returns players
    }

    @Nested
    inner class WhenDeadPlayer {
        @Test
        fun onCommandByPlayer() {
            val player = getPlayer(RoleType.WEREWOLF)
            every { player.gameMode } returns GameMode.SPECTATOR
            val args = CommandArgs(arrayOf("example"))
            val result = jinroChatCommand.onCommandByPlayer(player, command, "", args)
            Assertions.assertFalse(result)
        }
    }

    @Nested
    inner class WhenWerewolfPlayer {
        @Test
        fun onCommandByPlayer() {
            val player = getPlayer(RoleType.WEREWOLF)
            val args = CommandArgs(arrayOf("example"))
            val result = jinroChatCommand.onCommandByPlayer(player, command, "", args)
            Assertions.assertTrue(result)
        }
    }

    @Nested
    inner class WhenNotWerewolfPlayer {
        @Test
        fun onCommandByPlayer() {
            val player = getPlayer(RoleType.CITIZEN)
            val args = CommandArgs(arrayOf("example"))
            val result = jinroChatCommand.onCommandByPlayer(player, command, "", args)
            Assertions.assertTrue(result)
        }
    }

    private fun getPlayer(roleType: RoleType): Player {
        val player = PlayerFactory.build(roleType, roleService)
        every { roleService.getRoleClass(player) } returns roleType.roleClass.primaryConstructor?.call()
        return player
    }
}