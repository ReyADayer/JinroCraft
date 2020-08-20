package net.atlantis.jinrocraft.view

import net.atlantis.jinrocraft.MinecraftServerPackage
import org.bukkit.entity.Player
import org.json.JSONObject

class Title(private val title: String, private val subtitle: String) {

    private var fadeIn: Int = 3
    private var fadeOut: Int = 50
    private var stay: Int = 50

    fun send(player: Player) {
        try {
            val handle = player.javaClass.getMethod("getHandle").invoke(player)
            val connection = handle.javaClass.getField("playerConnection").get(handle)

            val playPacket = MinecraftServerPackage.getClass("PacketPlayOutTitle")
            val genericPacket = MinecraftServerPackage.getClass("Packet")
            val chatComponent = MinecraftServerPackage.getClass("IChatBaseComponent")
            val serializer = MinecraftServerPackage.getClass("IChatBaseComponent\$ChatSerializer")
            val action = MinecraftServerPackage.getClass("PacketPlayOutTitle\$EnumTitleAction")

            val timesPacket = playPacket.getConstructor(Int::class.javaPrimitiveType, Int::class.javaPrimitiveType, Int::class.javaPrimitiveType).newInstance(fadeIn, stay, fadeOut)
            connection.javaClass.getMethod("sendPacket", genericPacket).invoke(connection, timesPacket)

            val titleComponent = serializer.getMethod("a", String::class.java).invoke(null, convert(title))
            val titlePacket = playPacket.getConstructor(action, chatComponent).newInstance(action.getField("TITLE").get(null), titleComponent)
            connection.javaClass.getMethod("sendPacket", genericPacket).invoke(connection, titlePacket)

            val subtitleComponent = serializer.getMethod("a", String::class.java).invoke(null, convert(subtitle))
            val subtitlePacket = playPacket.getConstructor(action, chatComponent).newInstance(action.getField("SUBTITLE").get(null), subtitleComponent)
            connection.javaClass.getMethod("sendPacket", genericPacket).invoke(connection, subtitlePacket)
        } catch (e: Throwable) {
            throw RuntimeException(e)
        }
    }

    private fun convert(text: String): String {
        val json = JSONObject()
        json.put("text", text)
        return json.toString()
    }
}