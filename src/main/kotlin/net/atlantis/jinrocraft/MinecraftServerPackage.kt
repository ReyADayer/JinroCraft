package net.atlantis.jinrocraft

import org.bukkit.Bukkit

object MinecraftServerPackage {
    @Throws(ClassNotFoundException::class)
    fun getClass(className: String): Class<*> {
        val version = Bukkit.getServer().javaClass.`package`.name.replace("org.bukkit.craftbukkit.", "")
        val path = "net.minecraft.server." + version
        return Class.forName("$path.$className")
    }
}