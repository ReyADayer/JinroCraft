package net.atlantis.jinrocraft.packet

import com.comphenix.protocol.ProtocolManager
import com.comphenix.protocol.events.PacketContainer
import org.bukkit.entity.Player
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.lang.reflect.InvocationTargetException

abstract class Packet : KoinComponent {
    protected val protocolManager: ProtocolManager by inject()

    abstract fun send(player: Player)

    fun send(players: List<Player>) {
        players.forEach {
            send(it)
        }
    }

    protected fun sendPacket(player: Player, packetContainer: PacketContainer) {
        try {
            protocolManager.sendServerPacket(player, packetContainer)
        } catch (e: InvocationTargetException) {
            throw RuntimeException("Cannot send packet $packetContainer", e)
        }
    }
}