package net.atlantis.jinrocraft.model

import net.atlantis.jinrocraft.model.role.Citizen
import net.atlantis.jinrocraft.model.role.Fox
import net.atlantis.jinrocraft.model.role.Hunter
import net.atlantis.jinrocraft.model.role.Madman
import net.atlantis.jinrocraft.model.role.Medium
import net.atlantis.jinrocraft.model.role.Role
import net.atlantis.jinrocraft.model.role.Seer
import net.atlantis.jinrocraft.model.role.Werewolf
import net.atlantis.jinrocraft.model.role.WhisperMadman
import net.atlantis.jinrocraft.model.role.Zealot
import net.atlantis.jinrocraft.view.Icon
import net.atlantis.jinrocraft.view.co.CitizenCoIcon
import net.atlantis.jinrocraft.view.co.FoxCoIcon
import net.atlantis.jinrocraft.view.co.HunterCoIcon
import net.atlantis.jinrocraft.view.co.MadmanCoIcon
import net.atlantis.jinrocraft.view.co.MediumCoIcon
import net.atlantis.jinrocraft.view.co.SeerCoIcon
import net.atlantis.jinrocraft.view.co.WerewolfCoIcon
import net.atlantis.jinrocraft.view.co.WhisperMadmanCoIcon
import net.atlantis.jinrocraft.view.co.ZealotCoIcon
import kotlin.reflect.KClass

enum class RoleType(val key: String, val jpName: String, val roleClass: KClass<out Role>, val coIcon: KClass<out Icon>, val countType: CountType) {
    CITIZEN("Citizen", "市民", Citizen::class, CitizenCoIcon::class, CountType.CITIZEN),
    SEER("Seer", "占い師", Seer::class, SeerCoIcon::class, CountType.CITIZEN),
    MEDIUM("Medium", "霊能者", Medium::class, MediumCoIcon::class, CountType.CITIZEN),
    HUNTER("Hunter", "狩人", Hunter::class, HunterCoIcon::class, CountType.CITIZEN),
    WEREWOLF("Werewolf", "人狼", Werewolf::class, WerewolfCoIcon::class, CountType.WEREWOLF),
    MADMAN("Madman", "狂人", Madman::class, MadmanCoIcon::class, CountType.CITIZEN),
    ZEALOT("Zealot", "狂信者", Zealot::class, ZealotCoIcon::class, CountType.CITIZEN),
    WHISPER_MADMAN("WhisperMadman", "ささやく狂人", WhisperMadman::class, WhisperMadmanCoIcon::class, CountType.CITIZEN),
    FOX("Fox", "妖狐", Fox::class, FoxCoIcon::class, CountType.NOTHING);

    companion object {
        fun findByKey(key: String?): RoleType? {
            return values().firstOrNull { it.key == key }
        }

        fun findByJpName(jpName: String): RoleType? {
            return values().firstOrNull { it.jpName == jpName }
        }
    }
}