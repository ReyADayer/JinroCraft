package net.atlantis.jinrocraft

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class PluginCommandsTest{
    @Test
    fun jinroChat() {
        Assertions.assertEquals("jc", PluginCommands.JINRO_CHAT)
    }
}