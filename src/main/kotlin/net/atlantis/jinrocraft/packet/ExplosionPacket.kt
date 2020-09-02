package net.atlantis.jinrocraft.packet

import com.comphenix.protocol.PacketType
import org.bukkit.Location
import org.bukkit.entity.Player

class ExplosionPacket(private val location: Location) : Packet() {
    override fun send(player: Player) {
        val packetContainer = protocolManager.createPacket(PacketType.Play.Server.EXPLOSION)
        packetContainer.doubles
                .write(0, location.x)
                .write(1, location.y)
                .write(2, location.z)
        packetContainer.float.write(0, 3.0f)
        sendPacket(player, packetContainer)
    }
}