package net.atlantis.jinrocraft.model.time

import org.bukkit.World

enum class TimeType(
        private val jpName: String,
        private val description: String,
        private val startAt: Int,
        private val endedAt: Int
) {
    MOONING("朝", "おはようございます 朝になりました", 0, 11999),
    EVENING(" 夕方", "日没が迫っています 投票しましょう", 12000, 13999),
    NIGHT("夜", "恐ろしい夜がやってきました", 14000, 23999);

    companion object {
        fun findByWorld(world: World): TimeType? {
            val time = world.time
            return values().firstOrNull { it.startAt <= time && time <= it.endedAt }
        }
    }
}