package cz.muni.fi.pa165.monsterslayeragency.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Michaela Bajanova (469166)
 */
@Getter
@AllArgsConstructor
public enum MonsterType {
    DRAGON(Food.MEAT, new Power[] {Power.FIRE, Power.LIGHTNING}, new Power[] {Power.ICE}),
    GHOUL(Food.CORPSE, new Power[] {Power.POISON}, new Power[] {Power.FIRE, Power.BLADE}),
    GOLEM(Food.ENERGY, new Power[] {Power.BLADE}, new Power[] {Power.LIGHTNING}),
    SIREN(Food.ENERGY, new Power[] {Power.WATER, Power.WIND}, new Power[] {Power.BLADE}),
    VAMPIRE(Food.BLOOD, new Power[] {Power.BLADE, Power.ICE, Power.POISON}, new Power[] {Power.FIRE, Power.MAGIC}),
    WEREWOLF(Food.MEAT, new Power[] {Power.BLADE}, new Power[] {Power.POISON, Power.MAGIC});

    private Food food;
    private Power[] resistances;
    private Power[] weaknesses;
}
