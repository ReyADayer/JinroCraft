package net.atlantis.jinrocraft.model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class RoleTypeTest {
    @Test
    fun findByKey() {
        Assertions.assertEquals(RoleType.CITIZEN, RoleType.findByKey("Citizen"))
        Assertions.assertNull(RoleType.findByKey("Example"))
    }

    @Test
    fun findByJpName() {
        Assertions.assertEquals(RoleType.CITIZEN, RoleType.findByJpName("市民"))
        Assertions.assertNull(RoleType.findByKey("Example"))
    }
}