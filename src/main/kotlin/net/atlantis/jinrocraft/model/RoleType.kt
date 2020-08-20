package net.atlantis.jinrocraft.model

import net.atlantis.jinrocraft.view.Icon
import net.atlantis.jinrocraft.view.co.CitizenCoIcon
import net.atlantis.jinrocraft.view.co.FoxCoIcon
import net.atlantis.jinrocraft.view.co.HunterCoIcon
import net.atlantis.jinrocraft.view.co.MadmanCoIcon
import net.atlantis.jinrocraft.view.co.MediumCoIcon
import net.atlantis.jinrocraft.view.co.SeerCoIcon
import net.atlantis.jinrocraft.view.co.WerewolfCoIcon
import kotlin.reflect.KClass

enum class RoleType(val key: String, val jpName: String, val groupType: GroupType, val coIcon: KClass<out Icon>) {
    CITIZEN("Citizen", "市民", GroupType.CITIZENS, CitizenCoIcon::class),
    SEER("Seer", "占い師", GroupType.CITIZENS, SeerCoIcon::class),
    MEDIUM("Medium", "霊能者", GroupType.CITIZENS, MediumCoIcon::class),
    HUNTER("Hunter", "狩人", GroupType.CITIZENS, HunterCoIcon::class),
    WEREWOLF("Werewolf", "人狼", GroupType.WEREWOLVES, WerewolfCoIcon::class),
    MADMAN("Madman", "狂人", GroupType.WEREWOLVES, MadmanCoIcon::class),
    FOX("Fox", "妖狐", GroupType.FOXES, FoxCoIcon::class);

    companion object {
        fun findByKey(key: String?): RoleType? {
            return values().firstOrNull { it.key == key }
        }

        fun findByJpName(jpName: String): RoleType? {
            return values().firstOrNull { it.jpName == jpName }
        }
    }
}