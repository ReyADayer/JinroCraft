package net.atlantis.jinrocraft.model

import io.mockk.every
import io.mockk.mockk
import net.atlantis.jinrocraft.config.PluginPreference
import net.atlantis.jinrocraft.ext.getStringMetadata
import net.atlantis.jinrocraft.metadata.MetadataKeys
import net.atlantis.jinrocraft.model.role.Citizen
import org.bukkit.GameMode
import org.bukkit.Server
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

internal class RoleServiceTest {
    companion object {
        private val plugin = mockk<JavaPlugin>()
        private val server = mockk<Server>()
        private val pluginPreference = mockk<PluginPreference>()
        private val roleService = RoleService(plugin, server, pluginPreference)

        @BeforeAll
        @JvmStatic
        internal fun beforeAll() {
        }
    }

    @Test
    fun getRoles() {
        val roleSettings = mapOf(
                RoleType.WEREWOLF to 3,
                RoleType.MADMAN to 1,
                RoleType.MEDIUM to 1,
                RoleType.SEER to 1,
                RoleType.HUNTER to 1
        )
        every { pluginPreference.getRoleSettings() } returns roleSettings
        Assertions.assertEquals("役職内訳 人狼:3 狂人:1 霊能者:1 占い師:1 狩人:1", roleService.getRoles())
    }

    @Test
    fun getRole() {
        Assertions.assertEquals(RoleType.CITIZEN, roleService.getRole(getPlayer(RoleType.CITIZEN)))
        Assertions.assertEquals(RoleType.WEREWOLF, roleService.getRole(getPlayer(RoleType.WEREWOLF)))
    }

    @Test
    fun getRolePlayers() {
        val citizenPlayer = getPlayer(RoleType.CITIZEN)
        val players = listOf(
                citizenPlayer,
                getPlayer(RoleType.HUNTER)
        )
        every { server.onlinePlayers } returns players
        val rolePlayers = roleService.getRolePlayers(RoleType.CITIZEN)
        Assertions.assertTrue(rolePlayers.contains(citizenPlayer))
        Assertions.assertEquals(1, rolePlayers.size)
    }

    @Test
    fun getRoleClass() {
        val role = roleService.getRoleClass(getPlayer(RoleType.CITIZEN))!!
        Assertions.assertEquals(Citizen::class, role::class)
    }

    private fun getPlayer(roleType: RoleType): Player {
        val player = mockk<Player>()
        every { player.gameMode } returns GameMode.SURVIVAL
        every { player.getStringMetadata(MetadataKeys.ROLE) } returns roleType.key
        return player
    }
}