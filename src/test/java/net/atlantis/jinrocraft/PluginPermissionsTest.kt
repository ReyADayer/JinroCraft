package net.atlantis.jinrocraft

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class PluginPermissionsTest {
    @Test
    fun admin() {
        Assertions.assertEquals("JinroCraft.admin", PluginPermissions.ADMIN)
    }
}