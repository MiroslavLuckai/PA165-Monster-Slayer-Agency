package cz.muni.fi.pa165.msa.service.utils;

import cz.muni.fi.pa165.monsterslayeragency.entities.Hero;
import cz.muni.fi.pa165.monsterslayeragency.entities.Monster;
import cz.muni.fi.pa165.monsterslayeragency.entities.Request;
import cz.muni.fi.pa165.monsterslayeragency.enums.Resistance;
import cz.muni.fi.pa165.monsterslayeragency.enums.Skill;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Filip Daniel Fedin
 *
 * Utility class used to match heroes to requests according to their skills
 */
public class SkillMatcher {

    /**
     * Compares the skills of the hero and the resistances of the monsters in the requests
     * @param hero having skills to be compared
     * @param request having monsters to be compared
     * @return true if hero matches the request, false else
     */
    public static boolean matchesRequest(Hero hero, Request request) {
        return request.getMonsters().stream().allMatch(m -> (matchesMonster(hero, m)));
    }

    private static boolean matchesMonster(Hero hero, Monster monster) {
        Set<Skill> skills = hero.getSkills();
        Set<Resistance> resistances = monster.getResistances();
        return skills.stream().anyMatch(s -> (matchesSkill(s, resistances)));
    }

    private static boolean matchesSkill(Skill skill, Set<Resistance> resistance) {
        Stream<Resistance> stream = resistance.stream();
        switch (skill) {
            case ARMOR: {
                return stream.anyMatch(SkillMatcher::matchArmor);
            }
            case MAGIC: {
                return stream.anyMatch(SkillMatcher::matchMagic);
            }
            case MELEE: {
                return stream.anyMatch(SkillMatcher::matchMelee);
            }
            case SNEAK: {
                return stream.anyMatch(SkillMatcher::matchSneak);
            }
            case BLADES: {
                return stream.anyMatch(SkillMatcher::matchBlades);
            }
            case RANGED: {
                return stream.anyMatch(SkillMatcher::matchRanged);
            }
            case ACROBATICS: {
                return stream.anyMatch(SkillMatcher::matchAcrobatics);
            }
            default: return false;
        }
    }

    private static boolean matchMelee(Resistance resistance) {
        return Stream
                .of(Resistance.ROCK, Resistance.ICE, Resistance.BLEEDING)
                .collect(Collectors.toSet())
                .contains(resistance);
    }
    private static boolean matchBlades(Resistance resistance) {
        return Stream
                .of(Resistance.GRASS,Resistance.FIRE, Resistance.POISON)
                .collect(Collectors.toSet())
                .contains(resistance);
    }
    private static boolean matchRanged(Resistance resistance) {
        return Stream
                .of(Resistance.POISON, Resistance.PSYCHIC, Resistance.LIGHTNING)
                .collect(Collectors.toSet())
                .contains(resistance);
    }
    private static boolean matchMagic(Resistance resistance) {
        return Stream
                .of(Resistance.DARK, Resistance.BLEEDING, Resistance.GHOST, Resistance.PSYCHIC)
                .collect(Collectors.toSet())
                .contains(resistance);
    }
    private static boolean matchArmor(Resistance resistance) {
        return Stream
                .of(Resistance.WIND, Resistance.WATER, Resistance.ROCK)
                .collect(Collectors.toSet())
                .contains(resistance);
    }
    private static boolean matchSneak(Resistance resistance) {
        return Stream.of(Resistance.PSYCHIC, Resistance.GRASS, Resistance.FIRE)
                .collect(Collectors.toSet())
                .contains(resistance);
    }
    private static boolean matchAcrobatics(Resistance resistance) {
        return Stream.of(Resistance.BLEEDING, Resistance.POISON, Resistance.ICE)
                .collect(Collectors.toSet())
                .contains(resistance);
    }
}
