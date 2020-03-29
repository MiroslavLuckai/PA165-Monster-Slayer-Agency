package cz.muni.fi.pa165.monsterslayeragency.dao;

import cz.muni.fi.pa165.monsterslayeragency.entities.Hero;

/**
 * @author Miroslav Luckai
 */
public interface HeroDao {
    /**
     * Adds hero into database
     * @param hero hero to be added into database
     */
    void addHero(Hero hero) throws IllegalArgumentException;

    /**
     * Update hero in database
     * @param hero hero to be updated
     */
    void updateHero(Hero hero) throws IllegalArgumentException;

    /**
     * Remove hero from the database
     * @param hero hero which should be removed
     */
    void removeHero(Hero hero) throws IllegalArgumentException;

    /**
     * find hero by requested id
     * @param id id of the hero we are looking for
     * @return instance of class Hero with wanted ID
     */
    Hero findHero(Long id) throws IllegalArgumentException;

    /**
     * finds hero by its hero nick-name(hero nick-name have to be unique)
     * @param heroName hero nick-name of the user
     * @return instance of class Hero with wanted nick-name, hopefully null otherwise
     */
    Hero findByHeroName(String heroName) throws IllegalArgumentException;

    /**
     * finds hero profile by its underlying user
     * @param userId Id of the user whose hero profile we are looking for
     * @return instance of class Hero with wanted underlying user, hopefully null otherwise
     */
    Hero findByUser(Long userId) throws IllegalArgumentException;

}
