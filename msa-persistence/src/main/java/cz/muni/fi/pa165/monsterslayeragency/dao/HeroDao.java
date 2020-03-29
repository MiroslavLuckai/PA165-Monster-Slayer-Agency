package cz.muni.fi.pa165.monsterslayeragency.dao;

import cz.muni.fi.pa165.monsterslayeragency.entities.Hero;
import cz.muni.fi.pa165.monsterslayeragency.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Miroslav Luckai
 */
public interface HeroDao {
    /**
     * Adds hero into database
     * @param hero hero to be added into database
     */
    void addHero(Hero hero);

    /**
     * Update hero in database
     * @param hero hero to be updated
     */
    void updateHero(Hero hero);

    /**
     * Remove hero from the database
     * @param hero hero which should be removed
     */
    void removeHero(Hero hero);

    /**
     * find hero by requested id
     * @param id id of the hero we are looking for
     */
    void findHero(Long id);

    /**
     * finds hero by its hero nick-name(hero nick-name have to be unique)
     * @param heroName hero nick-name of the user
     * @return instance of class Hero with wanted nick-name, hopefully null otherwise
     */
    Hero findByHeroName(String heroName);

    /**
     * finds hero profile by its underlying user
     * @param userId Id of the user whose hero profile we are looking for
     * @return instance of class Hero with wanted underlying user, hopefully null otherwise
     */
    Hero findByUser(Long userId);

}
