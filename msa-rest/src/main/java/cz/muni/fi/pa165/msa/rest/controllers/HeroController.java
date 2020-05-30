package cz.muni.fi.pa165.msa.rest.controllers;

import cz.muni.fi.pa165.msa.dto.HeroDTO;
import cz.muni.fi.pa165.msa.facade.HeroFacade;
import cz.muni.fi.pa165.msa.rest.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @author Miroslav Luckai
 */
@RestController
@RequestMapping("/heroes")
public class HeroController {

    final static Logger logger = LoggerFactory.getLogger(MonsterController.class);

    @Autowired
    HeroFacade heroFacade;

    /**
     * List all heroes stored in database
     * curl -i -X GET http://localhost:8080/pa165/rest/heroes
     * @return List of all heroes stored in database
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<HeroDTO> getAllHeroes() {
        logger.debug("Get all heroes");
        return heroFacade.findAllHeroes();
    }

    /**
     * Get Hero according his Id
     * curl -i -X GET http://localhost:8080/pa165/rest/requests/{id}
     * @param id id of the hero we are looking for
     * @return Hero with requested id, in case there is no here with this id, exception is thrown
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HeroDTO getHeroById(@PathVariable("id") Long id) {
        logger.debug("Find hero with id: \"{}\"", id);
        HeroDTO hero = heroFacade.findHero(id);
        if (hero == null) {
            throw new ResourceNotFoundException("Cannot find Hero with the given ID.");
        }
        return hero;
    }

    /**
     * Get hero according his name
     * curl -i -X GET http://localhost:8080/pa165/rest/requests/name/{name}
     * @param name name of the hero we are looking for
     * @return Hero with requested name, in case there is no hero with this name, exception is thrown
     */
    @RequestMapping(value = "name/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HeroDTO getHeroByName(@PathVariable("name") String name) {
        logger.debug("Find hero with name: \"{}\"", name);
        HeroDTO hero = heroFacade.findByHeroName(name);
        if (hero == null) {
            throw new ResourceNotFoundException("Cannot find Hero with the given name.");
        }
        return hero;
    }

    /**
     * Get hero according his user's is
     * curl -i -X GET http://localhost:8080/pa165/rest/heroes/user/{id}
     * @param id id of the underlying user
     * @return Hero of the user with requested id, in case there is no hero with this name, exception is thrown
     */
    @RequestMapping(value = "user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HeroDTO getHeroByUserId(@PathVariable("id") Long id) {
        logger.debug("Find hero with user id: \"{}\"", id);
        HeroDTO hero = heroFacade.findByUserId(id);
        if (hero == null) {
            throw new ResourceNotFoundException("Cannot find Hero with the given user ID.");
        }
        return hero;
    }

    /**
     * Remove hero with requested id
     * curl -i -X DELETE http://localhost:8080/pa165/rest/requests/{id}
     * @param id id of the hero we want to remove
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void removeHero(@PathVariable("id") Long id) {
        try {
            logger.info("Remove hero with id: \"{}\"", id);
            heroFacade.removeHero(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException("Cannot find Hero with the given ID." + ex.getMessage(), ex);
        }
    }

    /**
     * Create new hero
     * @param dto heroDTO which should be added to database
     * @return Newly created hero
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public HeroDTO createHero(@RequestBody HeroDTO dto) {
        logger.debug("Create hero");
        return heroFacade.createHero(dto);
    }

    /**
     * Update hero in database
     * @param id id of the hero we want to updated
     * @param dto new version of the hero instance
     * @return Hero with changes
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public HeroDTO updateHero(@PathVariable("id") Long id, @RequestBody HeroDTO dto) {
        logger.debug("Updated hero with id: \"{}\"", id);
        heroFacade.updateHero(dto);
        return heroFacade.findByUserId(id);
    }
}
