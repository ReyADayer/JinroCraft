package net.atlantis.jinrocraft.view.co

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

internal class CoMenuTest {
    @Test
    fun isMenu() {
        Assertions.assertTrue(CoMenu.isMenu("正体を明かす（カミングアウト）"));
        Assertions.assertFalse(CoMenu.isMenu("正体を明かす"));
    }
}