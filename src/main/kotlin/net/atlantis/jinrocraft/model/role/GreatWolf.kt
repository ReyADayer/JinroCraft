package net.atlantis.jinrocraft.model.role

class GreatWolf : Werewolf() {
    override val name = "大狼"
    override val description = """
        |夜の間、攻撃によるダメージが2増加します。
        |占い結果は村人、霊能結果は大狼となります。
    """.trimMargin()
}