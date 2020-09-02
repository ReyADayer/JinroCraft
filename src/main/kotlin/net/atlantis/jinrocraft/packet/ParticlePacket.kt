package net.atlantis.jinrocraft.packet

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.events.PacketContainer
import com.comphenix.protocol.wrappers.WrappedParticle
import org.bukkit.Location
import org.bukkit.Particle
import org.bukkit.entity.Player

open class ParticlePacket private constructor(
        private val location: Location,
        private val particle: Particle,
        private val dustOptions: Particle.DustOptions? = null
) : Packet() {
    companion object {
        fun create(
                location: Location,
                particle: Particle,
                dustOptions: Particle.DustOptions? = null
        ): ParticlePacket {
            return if (particle == Particle.REDSTONE) {
                ParticlePacket(location, particle, dustOptions)
            } else {
                ParticlePacket(location, particle)
            }
        }
    }

    override fun send(player: Player) {
        val packetContainer = createParticlePacket()
        packetContainer.doubles
                .write(0, location.x)
                .write(1, location.y)
                .write(2, location.z)
        packetContainer.float.write(0, 3.0f)
        sendPacket(player, packetContainer)
    }

    protected open fun createParticlePacket(): PacketContainer {
        val packetContainer = protocolManager.createPacket(PacketType.Play.Server.WORLD_PARTICLES)
        val wrappedParticle = WrappedParticle.create<Any>(particle, dustOptions)
        packetContainer.newParticles.write(0, wrappedParticle)
        return packetContainer
    }
}