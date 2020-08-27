package net.atlantis.jinrocraft.command

import io.mockk.every
import io.mockk.mockk
import net.atlantis.jinrocraft.factory.PlayerFactory
import net.atlantis.jinrocraft.model.RoleService
import net.atlantis.jinrocraft.model.RoleType
import org.bukkit.command.Command
import org.bukkit.entity.Player
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import kotlin.reflect.full.primaryConstructor

internal class StatusCommandTest {
    companion object {
        private val roleService = mockk<RoleService>()

        private val mockModule: Module = module {
            single { roleService }
        }

        @BeforeAll
        @JvmStatic
        internal fun beforeAll() {
            startKoin {
                modules(listOf(mockModule))
            }
        }
    }

    private val statusCommand = StatusCommand()
    private val command = mockk<Command>()
    private val player = getPlayer(RoleType.CITIZEN)

    @Test
    fun onCommandByPlayer() {
        val args = CommandArgs(arrayOf())
        val result = statusCommand.onCommandByPlayer(player, command, "", args)
        Assertions.assertTrue(result)
    }

    private fun getPlayer(roleType: RoleType): Player {
        val player = PlayerFactory.build()
        every { roleService.getRoleClass(player) } returns roleType.roleClass.primaryConstructor?.call()
        return player
    }
}