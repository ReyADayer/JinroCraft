package net.atlantis.jinrocraft.view.co

import net.atlantis.jinrocraft.config.PluginPreference
import net.atlantis.jinrocraft.model.ComingOut
import net.atlantis.jinrocraft.model.RoleType
import net.atlantis.jinrocraft.view.Icon
import net.atlantis.jinrocraft.view.Menu
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.koin.core.inject
import kotlin.reflect.full.primaryConstructor

class CoMenu(player: Player) : Menu(player) {
    companion object {
        const val TITLE = "正体を明かす（カミングアウト）"

        fun isMenu(name: String): Boolean {
            return name == TITLE
        }
    }

    private val pluginPreference: PluginPreference by inject()

    override fun show() {
        val inventory = Bukkit.createInventory(null, 18, TITLE).apply {
            var index = 0
            val roleTypes: List<RoleType> = pluginPreference.roles.mapNotNull {
                RoleType.findByKey(it)
            }
            roleTypes.forEach {
                val icon = it.coIcon.primaryConstructor?.call() as Icon
                setItem(index, icon.toItemStack())
                index += 1
            }
        }
        player.openInventory(inventory)
    }

    override fun clickIcon(itemStack: ItemStack) {
        val name = ChatColor.stripColor(itemStack.itemMeta?.displayName) ?: return
        val roleType = RoleType.findByJpName(name) ?: return
        ComingOut(player).send(roleType)
        player.closeInventory()
    }
}