package net.atlantis.jinrocraft.listener

import io.mockk.every
import io.mockk.mockk
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryAction
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.InventoryView
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class ViewListenerTest {
    companion object {
        private val viewListener = ViewListener()
        private val inventoryView = mockk<InventoryView>()

        @BeforeAll
        @JvmStatic
        internal fun beforeAll() {
            every { inventoryView.convertSlot(1) } returns 1
            every { inventoryView.getItem(1) } returns null
        }
    }

    @Nested
    inner class WhenCoMenu {
        @BeforeEach
        fun setUp() {
            every { inventoryView.title } returns "正体を明かす（カミングアウト）"
        }

        @Test
        fun onClickCoMenu() {
            val event = InventoryClickEvent(inventoryView, InventoryType.SlotType.CONTAINER, 1, ClickType.LEFT, InventoryAction.PICKUP_SOME)
            viewListener.onClickCoMenu(event)
            Assertions.assertTrue(event.isCancelled)
        }
    }

    @Nested
    inner class WhenOtherMenu {
        @BeforeEach
        fun setUp() {
            every { inventoryView.title } returns "メニュー"
        }

        @Test
        fun onClickCoMenu() {
            val event = InventoryClickEvent(inventoryView, InventoryType.SlotType.CONTAINER, 1, ClickType.LEFT, InventoryAction.PICKUP_SOME)
            viewListener.onClickCoMenu(event)
            Assertions.assertFalse(event.isCancelled)
        }
    }
}