package net.atlantis.jinrocraft.metadata

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class MetadataKeysTest {
    @Test
    fun keyTest(){
        Assertions.assertEquals("ROLE", MetadataKeys.ROLE)
    }
}