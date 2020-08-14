package net.atlantis.jinrocraft.model

enum class RoleType(val key: String, val jpName: String, val groupType: GroupType) {
    CITIZEN("Citizen", "市民", GroupType.CITIZENS),
    SEER("Seer", "占い師", GroupType.CITIZENS),
    MEDIUM("Medium", "霊能者", GroupType.CITIZENS),
    HUNTER("Hunter", "狩人", GroupType.CITIZENS),
    WEREWOLF("Werewolf", "人狼", GroupType.WEREWOLVES),
    MADMAN("Madman", "狂人", GroupType.WEREWOLVES),
    FOX("Fox", "妖狐", GroupType.FOXES);

    companion object {
        fun findByKey(key: String): RoleType? {
            return values().firstOrNull { it.key == key }
        }
    }
}