package net.atlantis.jinrocraft.listener

import io.mockk.every
import io.mockk.mockk
import net.atlantis.jinrocraft.config.PluginPreference
import net.atlantis.jinrocraft.factory.LocationFactory
import net.atlantis.jinrocraft.factory.PlayerFactory
import net.atlantis.jinrocraft.factory.WorldFactory
import net.atlantis.jinrocraft.model.RoleService
import org.bukkit.ChatColor
import org.bukkit.GameMode
import org.bukkit.Server
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.Player
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.event.player.PlayerRespawnEvent
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

internal class PlayerListenerTest {
    companion object {
        private val server = mockk<Server>()
        private val playerListener = PlayerListener()
        private val roleService = mockk<RoleService>()
        private val pluginPreference = mockk<PluginPreference>()

        private val mockModule: Module = module {
            single { server }
            single { roleService }
            single { pluginPreference }
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

    val player = PlayerFactory.build(name = "メアリー")

    @Test
    fun onDeath() {
        val world = WorldFactory.build(0)
        val location = LocationFactory.build(world)
        every { player.location } returns location
        every { world.spawn<ArmorStand>(any(), any(), any()) } returns mockk()
        every { server.onlinePlayers } returns emptyList()
        every { pluginPreference.gameStart = false } returns Unit

        val event = PlayerDeathEvent(player, emptyList(), 0, "Death Message")
        playerListener.onDeath(event)
        Assertions.assertEquals("${ChatColor.RED}メアリーが襲撃されました", event.deathMessage)
    }

    @Nested
    inner class WhenAlive {
        @BeforeEach
        fun setUp() {
            every { player.gameMode } returns  GameMode.SURVIVAL
        }

        @Test
        fun onChat() {
            val event = AsyncPlayerChatEvent(true, player, "Message", emptySet())
            playerListener.onChat(event)
            Assertions.assertFalse(event.isCancelled)
        }
    }

    @Nested
    inner class WhenDead {
        @BeforeEach
        fun setUp() {
            every { player.gameMode } returns  GameMode.SPECTATOR
        }

        @Test
        fun onChat() {
            val event = AsyncPlayerChatEvent(true, player, "Message", emptySet())
            playerListener.onChat(event)
            Assertions.assertTrue(event.isCancelled)
        }
    }
}