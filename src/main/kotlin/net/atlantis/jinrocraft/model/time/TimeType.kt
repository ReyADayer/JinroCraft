package net.atlantis.jinrocraft.model.time

import org.bukkit.Sound
import org.bukkit.World

enum class TimeType(
        val jpName: String,
        val description: String,
        val sound: Sound,
        private val startAt: Int,
        private val endedAt: Int
) {
    MOONING("朝", "おはようございます 朝になりました", Sound.BLOCK_BELL_USE, 0, 11999),
    EVENING(" 夕方", "日没が迫っています 投票しましょう", Sound.BLOCK_BELL_USE, 12000, 13999),
    NIGHT("夜", "恐ろしい夜がやってきました", Sound.ENTITY_WOLF_HOWL, 14000, 23999);

    companion object {
        fun findByWorld(world: World): TimeType? {
            val time = world.time
            return values().firstOrNull { it.startAt <= time && time <= it.endedAt }
        }
    }
}