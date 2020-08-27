package net.atlantis.jinrocraft.factory

import io.mockk.every
import io.mockk.mockk
import org.bukkit.scoreboard.Objective

object ObjectiveFactory {
    fun build(): Objective {
        val objective = mockk<Objective>()
        every { objective.displaySlot = any() } returns Unit
        return objective
    }
}