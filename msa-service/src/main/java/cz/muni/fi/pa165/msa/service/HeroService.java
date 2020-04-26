package cz.muni.fi.pa165.msa.service;

import cz.muni.fi.pa165.monsterslayeragency.entities.Hero;

import java.util.List;

/**
 * @author Miroslav Luckai 469288
 */
public interface HeroService {

    Hero createHero(Hero hero) throws IllegalArgumentException;

    void updateHero(Hero hero) throws IllegalArgumentException;

    void removeHero(Hero hero) throws IllegalArgumentException;

    Hero findHeroById(Long id) throws IllegalArgumentException;

    Hero findHeroByName(String name) throws IllegalArgumentException;

    Hero findHeroByUserId(Long id) throws IllegalArgumentException;

    List<Hero> findAllHeroes();
}
