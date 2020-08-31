package net.atlantis.jinrocraft.model.role

import net.atlantis.jinrocraft.factory.PlayerFactory
import net.atlantis.jinrocraft.model.RoleType
import org.bukkit.Material
import org.bukkit.entity.Player
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class SeerTest {
    private val seer = Seer()

    @Test
    fun canUse() {
        Assertions.assertTrue(seer.canUse(getPlayer(10, Material.SHEARS)))
        Assertions.assertTrue(seer.canUse(getPlayer(11, Material.SHEARS)))
        Assertions.assertFalse(seer.canUse(getPlayer(9, Material.SHEARS)))
        Assertions.assertFalse(seer.canUse(getPlayer(10, Material.DIAMOND_AXE)))
        Assertions.assertFalse(seer.canUse(getPlayer(10, Material.COBBLESTONE)))
        Assertions.assertFalse(seer.canUse(getPlayer(0, null)))
    }

    @Test
    fun result() {
        Assertions.assertEquals(SeerResult.WEREWOLF, seer.result(RoleType.WEREWOLF))
        Assertions.assertEquals(SeerResult.FOX, seer.result(RoleType.FOX))
        Assertions.assertEquals(SeerResult.NOT_WEREWOLF, seer.result(RoleType.GREAT_WOLF))
        Assertions.assertEquals(SeerResult.NOT_WEREWOLF, seer.result(RoleType.CITIZEN))
    }

    private fun getPlayer(level: Int, itemInMainHandType: Material?): Player {
        return PlayerFactory.build(level, itemInMainHandType)
    }

    private fun Seer.canUse(player: Player): Boolean {
        val method = this::class.java.getDeclaredMethod("canUse", Player::class.java)
        method.isAccessible = true
        return method.invoke(this, player) as Boolean
    }

    private fun Seer.result(roleType: RoleType?): SeerResult {
        val method = this::class.java.getDeclaredMethod("result", RoleType::class.java)
        method.isAccessible = true
        return method.invoke(this, roleType) as SeerResult
    }
}