package net.atlantis.jinrocraft.model.time

import net.atlantis.jinrocraft.factory.WorldFactory
import org.bukkit.World
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class TimeTypeTest {

    @Test
    fun findByWorld() {
        Assertions.assertEquals(TimeType.MOONING, TimeType.findByWorld(getWorld(0)))
        Assertions.assertEquals(TimeType.MOONING, TimeType.findByWorld(getWorld(5000)))
        Assertions.assertEquals(TimeType.MOONING, TimeType.findByWorld(getWorld(11999)))
        Assertions.assertEquals(TimeType.EVENING, TimeType.findByWorld(getWorld(12000)))
        Assertions.assertEquals(TimeType.EVENING, TimeType.findByWorld(getWorld(13999)))
        Assertions.assertEquals(TimeType.NIGHT, TimeType.findByWorld(getWorld(14000)))
        Assertions.assertEquals(TimeType.NIGHT, TimeType.findByWorld(getWorld(23999)))
        Assertions.assertNull(TimeType.findByWorld(getWorld(24000)))
    }

    private fun getWorld(time: Long): World {
        return WorldFactory.build(time)
    }
}