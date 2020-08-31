package net.atlantis.jinrocraft.factory

import io.mockk.every
import io.mockk.mockk
import org.bukkit.Location
import org.bukkit.World

object LocationFactory {
    fun build(): Location {
        return mockk()
    }

    fun build(world: World): Location {
        val location = build()
        every { location.world } returns world
        return location
    }
}