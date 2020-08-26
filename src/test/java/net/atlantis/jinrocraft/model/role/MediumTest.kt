package net.atlantis.jinrocraft.model.role

import io.mockk.every
import io.mockk.mockk
import net.atlantis.jinrocraft.model.RoleType
import org.bukkit.Material
import org.bukkit.entity.Player
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class MediumTest {
    private val medium = Medium()

    @Test
    fun canUse() {
        Assertions.assertTrue(medium.canUse(getPlayer(Material.SHEARS)))
        Assertions.assertFalse(medium.canUse(getPlayer(Material.DIAMOND_AXE)))
        Assertions.assertFalse(medium.canUse(getPlayer(null)))
    }

    @Test
    fun result() {
        Assertions.assertEquals(MediumResult.WEREWOLF, medium.result(RoleType.WEREWOLF))
        Assertions.assertEquals(MediumResult.NOT_WEREWOLF, medium.result(RoleType.CITIZEN))
    }

    private fun getPlayer(itemInMainHandType: Material?): Player {
        val player = mockk<Player>()
        every { player.equipment?.itemInMainHand?.type } returns itemInMainHandType
        return player
    }

    private fun Medium.canUse(player: Player): Boolean {
        val method = this::class.java.getDeclaredMethod("canUse", Player::class.java)
        method.isAccessible = true
        return method.invoke(this, player) as Boolean
    }

    private fun Medium.result(roleType: RoleType?): MediumResult {
        val method = this::class.java.getDeclaredMethod("result", RoleType::class.java)
        method.isAccessible = true
        return method.invoke(this, roleType) as MediumResult
    }
}