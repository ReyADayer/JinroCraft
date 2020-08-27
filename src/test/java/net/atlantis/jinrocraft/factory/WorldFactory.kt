package net.atlantis.jinrocraft.factory

import io.mockk.every
import io.mockk.mockk
import org.bukkit.World

object WorldFactory {
    fun build(time: Long): World {
        val world = mockk<World>()
        every { world.time } returns time
        return world
    }
}