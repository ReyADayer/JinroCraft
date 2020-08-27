package net.atlantis.jinrocraft.factory

import io.mockk.every
import io.mockk.mockk
import org.bukkit.scoreboard.Score

object ScoreFactory {
    fun build(): Score {
        val score = mockk<Score>()
        every { score.score = any() } returns Unit
        return score
    }
}