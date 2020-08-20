package net.atlantis.jinrocraft.command

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


internal class CommandArgsTest {
    @Test
    fun get() {
        val args = CommandArgs(arrayOf("example", "minecraft"))
        Assertions.assertEquals("example", args[0])
        Assertions.assertEquals("minecraft", args[1])
        Assertions.assertNull(args[2])
    }
}