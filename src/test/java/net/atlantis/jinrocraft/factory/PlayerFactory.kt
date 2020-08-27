package net.atlantis.jinrocraft.factory

import io.mockk.every
import io.mockk.mockk
import net.atlantis.jinrocraft.ext.getStringMetadata
import net.atlantis.jinrocraft.metadata.MetadataKeys
import net.atlantis.jinrocraft.model.RoleService
import net.atlantis.jinrocraft.model.RoleType
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.entity.Player
import java.util.*

object PlayerFactory {
    fun build(
            name: String = getRandomName(),
            uuid: UUID = UUID.randomUUID()
    ): Player {
        val player = mockk<Player>()
        every { player.gameMode } returns GameMode.SURVIVAL
        every { player.name } returns name
        every { player.uniqueId } returns uuid
        every { player.scoreboard = any() } returns Unit
        every { player.sendMessage(any() as String) } returns Unit
        return player
    }

    fun build(
            roleType: RoleType,
            roleService: RoleService,
            name: String = getRandomName(),
            uuid: UUID = UUID.randomUUID()
    ): Player {
        val player = build(name, uuid)
        every { roleService.getRole(player) } returns roleType
        every { player.getStringMetadata(MetadataKeys.ROLE) } returns roleType.key
        return player
    }

    fun build(
            itemInMainHandType: Material?,
            name: String = getRandomName(),
            uuid: UUID = UUID.randomUUID()
    ): Player {
        val player = build(name, uuid)
        every { player.equipment?.itemInMainHand?.type } returns itemInMainHandType
        return player
    }

    fun build(
            level: Int,
            itemInMainHandType: Material?,
            name: String = getRandomName(),
            uuid: UUID = UUID.randomUUID()
    ): Player {
        val player = build(itemInMainHandType, name, uuid)
        every { player.level } returns level
        return player
    }

    private fun getRandomName(): String {
        return UUID.randomUUID().toString()
    }
}