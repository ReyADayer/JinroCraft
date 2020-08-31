package net.atlantis.jinrocraft.model

enum class GroupType(val key: String, val jpName: String, val victoryCondition: String) {
    CITIZENS("Citizens", "市民陣営", "「人狼」を全滅させれば勝利"),
    WEREWOLVES("Werewolves", "人狼陣営", "「市民」の数を「人狼」の数と同じかそれ以下にする"),
    FOXES("Foxes", "妖狐陣営", "「妖狐」が最後まで生存で勝利");
}