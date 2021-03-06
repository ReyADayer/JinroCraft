package net.atlantis.jinrocraft.model

import io.mockk.every
import io.mockk.mockk
import net.atlantis.jinrocraft.config.PluginPreference
import net.atlantis.jinrocraft.factory.PlayerFactory
import org.bukkit.Server
import org.bukkit.configuration.file.FileConfiguration
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

internal class GameEndTest {
    companion object {
        private val server = mockk<Server>()
        private val roleService = mockk<RoleService>()

        private val mockModule: Module = module {
            single { server }
            single { mockk<FileConfiguration>() }
            single { PluginPreference(get(), get()) }
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
        internal fun afterAll(){
            stopKoin()
        }
    }

    private val gameEnd = GameEnd()

    @Nested
    inner class WhenNoPlayers {
        private val players = listOf<Player>()

        @BeforeEach
        fun setUp() {
            every { server.onlinePlayers } returns players
        }

        @Test
        fun result() {
            Assertions.assertEquals(GroupType.CITIZENS, gameEnd.result())
        }

        @Test
        fun isFoxesAlive() {
            Assertions.assertFalse(gameEnd.isFoxesAlive(players))
        }
    }

    @Nested
    inner class WhenCitizenPlayers {
        private val players = listOf(
                getPlayer(RoleType.CITIZEN),
                getPlayer(RoleType.HUNTER)
        )

        @BeforeEach
        fun setUp() {
            every { server.onlinePlayers } returns players
        }

        @Test
        fun result() {
            Assertions.assertEquals(GroupType.CITIZENS, gameEnd.result())
        }

        @Test
        fun isFoxesAlive() {
            Assertions.assertFalse(gameEnd.isFoxesAlive(players))
        }
    }

    @Nested
    inner class WhenIncludeWerewolfPlayers {
        private val players = listOf(
                getPlayer(RoleType.CITIZEN),
                getPlayer(RoleType.HUNTER),
                getPlayer(RoleType.WEREWOLF)
        )

        @BeforeEach
        fun setUp() {
            every { server.onlinePlayers } returns players
        }

        @Test
        fun result() {
            Assertions.assertNull(gameEnd.result())
        }

        @Test
        fun isFoxesAlive() {
            Assertions.assertFalse(gameEnd.isFoxesAlive(players))
        }
    }

    @Nested
    inner class WhenWinWerewolfPlayers {
        private val players = listOf(
                getPlayer(RoleType.CITIZEN),
                getPlayer(RoleType.HUNTER),
                getPlayer(RoleType.WEREWOLF),
                getPlayer(RoleType.WEREWOLF)
        )

        @BeforeEach
        fun setUp() {
            every { server.onlinePlayers } returns players
        }

        @Test
        fun result() {
            Assertions.assertEquals(GroupType.WEREWOLVES, gameEnd.result())
        }

        @Test
        fun isFoxesAlive() {
            Assertions.assertFalse(gameEnd.isFoxesAlive(players))
        }
    }

    @Nested
    inner class WhenWinWerewolfPlayersIncludesFoxes {
        private val players = listOf(
                getPlayer(RoleType.CITIZEN),
                getPlayer(RoleType.HUNTER),
                getPlayer(RoleType.WEREWOLF),
                getPlayer(RoleType.WEREWOLF),
                getPlayer(RoleType.FOX)
        )

        @BeforeEach
        fun setUp() {
            every { server.onlinePlayers } returns players
        }

        @Test
        fun result() {
            Assertions.assertEquals(GroupType.FOXES, gameEnd.result())
        }

        @Test
        fun isFoxesAlive() {
            Assertions.assertTrue(gameEnd.isFoxesAlive(players))
        }
    }

    private fun getPlayer(roleType: RoleType): Player {
        return PlayerFactory.build(roleType, roleService)
    }

    private fun GameEnd.result(): GroupType? {
        val method = this::class.java.getDeclaredMethod("result")
        method.isAccessible = true
        return method.invoke(this) as GroupType?
    }

    private fun GameEnd.isFoxesAlive(players: List<Player>): Boolean {
        val method = this::class.java.getDeclaredMethod("isFoxesAlive", List::class.java)
        method.isAccessible = true
        return method.invoke(this, players) as Boolean
    }
}