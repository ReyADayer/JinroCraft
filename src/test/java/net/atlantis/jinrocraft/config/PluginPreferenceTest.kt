package net.atlantis.jinrocraft.config

import io.mockk.every
import io.mockk.mockk
import net.atlantis.jinrocraft.factory.PlayerFactory
import net.atlantis.jinrocraft.model.RoleService
import net.atlantis.jinrocraft.model.RoleType
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import java.util.*

internal class PluginPreferenceTest {
    companion object {
        private val plugin = mockk<JavaPlugin>()
        private val config = mockk<FileConfiguration>()
        private val pluginPreference = PluginPreference(plugin, config)
        private val roleService = mockk<RoleService>()

        @BeforeAll
        @JvmStatic
        internal fun beforeAll() {
        }
    }

    @Test
    fun getPlayerRole() {
        val madmanPlayer = getPlayer(RoleType.MADMAN)
        Assertions.assertEquals(RoleType.MADMAN.key, pluginPreference.getPlayerRole(madmanPlayer))
        val foxPlayer = getPlayer(RoleType.FOX)
        Assertions.assertEquals(RoleType.FOX.key, pluginPreference.getPlayerRole(foxPlayer))
    }

    @Test
    fun getRoleSettings() {
        RoleType.values().forEach {
            initRoleSetting(it, 0)
        }
        initRoleSetting(RoleType.WEREWOLF, 5)
        initRoleSetting(RoleType.MADMAN, 3)
        initRoleSetting(RoleType.HUNTER, 1)
        val roleSettings = pluginPreference.getRoleSettings()
        Assertions.assertEquals(5, roleSettings[RoleType.WEREWOLF])
        Assertions.assertEquals(3, roleSettings[RoleType.MADMAN])
        Assertions.assertEquals(1, roleSettings[RoleType.HUNTER])
        Assertions.assertEquals(0, roleSettings[RoleType.FOX])
    }

    @Test
    fun getRoleSetting() {
        initRoleSetting(RoleType.WEREWOLF, 5)
        Assertions.assertEquals(5, pluginPreference.getRoleSetting(RoleType.WEREWOLF))
    }

    @Test
    fun getPlayerRoleKey() {
        val player = getPlayer(RoleType.CITIZEN)
        Assertions.assertEquals("role.${player.uniqueId}", pluginPreference.getPlayerRoleKey(player))
    }

    private fun getPlayer(roleType: RoleType): Player {
        val uuid = UUID.randomUUID()
        val player = PlayerFactory.build(roleType, roleService, uuid = uuid)
        every { config.getString("role.$uuid") } returns roleType.key
        return player
    }

    private fun initRoleSetting(roleType: RoleType, count: Int) {
        every { config.getInt("role_setting.${roleType.key}", 0) } returns count
    }

    private fun PluginPreference.getPlayerRoleKey(player: Entity): String {
        val method = this::class.java.getDeclaredMethod("getPlayerRoleKey", Entity::class.java)
        method.isAccessible = true
        return method.invoke(this, player) as String
    }
}