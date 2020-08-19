package net.atlantis.jinrocraft.view

import org.bukkit.ChatColor
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

abstract class Icon {
    abstract val name: String
    abstract val description: String
    abstract val itemStack: ItemStack

    private val displayName: String
        get() = ChatColor.RESET.toString() + name

    fun toItemStack(): ItemStack {
        val resultItemStack = itemStack
        val itemMeta = resultItemStack.itemMeta
        itemMeta?.setDisplayName(displayName)
        itemMeta?.lore = getLore()
        itemMeta?.isUnbreakable = true
        itemMeta?.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ENCHANTS)
        resultItemStack.itemMeta = itemMeta
        return resultItemStack
    }

    fun getLore(): List<String> {
        val result = mutableListOf<String>()
        result.add("${ChatColor.DARK_GRAY}$description")
        return result
    }
}