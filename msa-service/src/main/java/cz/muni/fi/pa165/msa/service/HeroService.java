package cz.muni.fi.pa165.msa.service;

import cz.muni.fi.pa165.monsterslayeragency.entities.Hero;

import java.util.List;

/**
 * @author Miroslav Luckai 469288
 */
public interface HeroService {

    Long createHero(Hero hero);

    void updateHero(Hero hero);

    void removeHero(Hero hero);

    Hero findHeroById(Long id);

    Hero findHeroByName(String name);

    Hero findHeroByUserId(Long id);
    List<Hero> findAllHeroes();
}
