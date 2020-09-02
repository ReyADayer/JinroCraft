package net.atlantis.jinrocraft.packet

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.events.PacketContainer
import com.comphenix.protocol.wrappers.EnumWrappers
import org.bukkit.WorldBorder
import org.bukkit.entity.Player

class WorldBorderPacket(private val enable: Boolean) : Packet() {
    override fun send(player: Player) {
        val packetContainer = getPacketContainer(player, enable)
        sendPacket(player, packetContainer)
    }

    private fun getPacketContainer(player: Player, enable: Boolean): PacketContainer {
        val container = protocolManager.createPacket(PacketType.Play.Server.WORLD_BORDER)
        val wb: WorldBorder = player.world.worldBorder
        container.worldBorderActions.write(0, EnumWrappers.WorldBorderAction.INITIALIZE)
        container.integers.write(0, 29999984)

        container.doubles.write(0, player.location.x)
        container.doubles.write(1, player.location.z)
        container.doubles.write(3, wb.size)
        container.doubles.write(2, wb.size)

        container.integers.write(2, (if (enable) wb.size.toInt() else wb.warningDistance))
        container.integers.write(1, 0)

        container.longs.write(0, 0.toLong())
        return container
    }
}