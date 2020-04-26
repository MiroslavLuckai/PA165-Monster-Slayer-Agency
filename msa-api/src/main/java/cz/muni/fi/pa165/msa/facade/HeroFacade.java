package cz.muni.fi.pa165.msa.facade;

import cz.muni.fi.pa165.msa.dto.HeroDTO;

import java.util.List;

public interface HeroFacade {

    /**
     * Creates new Hero in database
     * @param heroDTO new Hero to be created
     * @return id of the newly created Hero
     */
    Long createHero(HeroDTO heroDTO);

    /**
     * Update hero in database
     * @param heroDTO hero to be updated
     */
    void updateHero(HeroDTO heroDTO);

    /**
     * Remove hero from the database
     * @param heroDTO hero which should be removed
     */
    void removeHero(HeroDTO heroDTO);

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

    /**
     * finds all heroes stored in database and map them on HeroDTO class
     * @return
     */
    List<HeroDTO> findAllHeroes();

}
