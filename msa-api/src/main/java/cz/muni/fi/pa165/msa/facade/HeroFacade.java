package cz.muni.fi.pa165.msa.facade;

import cz.muni.fi.pa165.msa.dto.HeroDTO;

public interface HeroFacade {

    /**
     * Creates new Hero in database
     * @param hero new Hero to be created
     * @return id of the newly created Hero
     */
    Long createHero(HeroDTO hero);

    /**
     * Update hero in database
     * @param hero hero to be updated
     */
    void updateHero(HeroDTO hero);

    /**
     * Remove hero from the database
     * @param hero hero which should be removed
     */
    void removeHero(HeroDTO hero);

    /**
     * find hero by requested id
     * @param id id of the hero we are looking for
     * @return instance of class Hero with wanted ID
     */
    HeroDTO findHero(Long id);

    /**
     * finds hero by its hero nick-name(hero nick-name have to be unique)
     * @param heroName hero nick-name of the user
     * @return instance of class Hero with wanted nick-name, hopefully null otherwise
     */
    HeroDTO findByHeroName(String heroName);

    /**
     * finds hero profile by its underlying user
     * @param userId Id of the user whose hero profile we are looking for
     * @return instance of class Hero with wanted underlying user, hopefully null otherwise
     */
    HeroDTO findByUserId(Long userId);

}
