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

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<HeroDTO> getAllHeroes() {
        logger.debug("Get all heroes");
        return heroFacade.findAllHeroes();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HeroDTO getHeroById(@PathVariable("id") Long id) {
        logger.debug("Find hero with id: \"{}\"", id);
        HeroDTO hero = heroFacade.findHero(id);
        if (hero == null) {
            throw new ResourceNotFoundException();
        }
        return hero;
    }

    @RequestMapping(value = "name/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HeroDTO getHeroByName(@PathVariable("name") String name) {
        logger.debug("Find hero with name: \"{}\"", name);
        HeroDTO hero = heroFacade.findByHeroName(name);
        if (hero == null) {
            throw new ResourceNotFoundException();
        }
        return hero;
    }

    @RequestMapping(value = "user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HeroDTO getHeroByUserId(@PathVariable("id") Long id) {
        logger.debug("Find hero with user id: \"{}\"", id);
        HeroDTO hero = heroFacade.findByUserId(id);
        if (hero == null) {
            throw new ResourceNotFoundException();
        }
        return hero;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void removeHero(@PathVariable("id") Long id) {
        try {
            logger.info("Remove hero with id: \"{}\"", id);
            heroFacade.removeHero(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public HeroDTO createHero(@RequestBody HeroDTO dto) {
        logger.debug("Create hero");
        return heroFacade.createHero(dto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public HeroDTO updateHero(@PathVariable("id") Long id, @RequestBody HeroDTO dto) {
        logger.debug("Updated hero with id: \"{}\"", id);
        heroFacade.updateHero(dto);
        return heroFacade.findByUserId(id);
    }
}
