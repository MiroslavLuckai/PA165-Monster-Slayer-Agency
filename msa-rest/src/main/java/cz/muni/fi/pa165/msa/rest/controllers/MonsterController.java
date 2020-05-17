package cz.muni.fi.pa165.msa.rest.controllers;

import cz.muni.fi.pa165.monsterslayeragency.enums.MonsterType;
import cz.muni.fi.pa165.msa.dto.MonsterCreateDTO;
import cz.muni.fi.pa165.msa.dto.MonsterDTO;
import cz.muni.fi.pa165.msa.facade.MonsterFacade;
import cz.muni.fi.pa165.msa.rest.exceptions.ResourceAlreadyExistsException;
import cz.muni.fi.pa165.msa.rest.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * @author Ludovit Kopcsanyi
 */
@RestController
@RequestMapping(value = "/monsters")
public class MonsterController {

    final static Logger logger = LoggerFactory.getLogger(MonsterController.class);

    @Autowired
    private MonsterFacade monsterFacade;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<MonsterDTO> getMonsters() {

        logger.debug("Get all requests");
        return monsterFacade.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final MonsterDTO getMonsterById(@PathVariable("id") Long id) {

        logger.debug("Find monster({})", id);
        MonsterDTO monster = monsterFacade.findById(id);
        if (monster == null) {
            throw new ResourceNotFoundException();
        }
        return monster;
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final MonsterDTO getMonsterByName(@PathVariable("name") String name) {

        logger.debug("Find monster({})", name);
        return monsterFacade.findByName(name);
    }

    @RequestMapping(value = "/type/{monsterType}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<MonsterDTO> getMonstersByType(@PathVariable("monsterType") MonsterType monsterType) {

        logger.debug("Find monsters({})", monsterType);
        return monsterFacade.findByMonsterType(monsterType);
    }

    @RequestMapping(value = "/size/{size}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<MonsterDTO> getMonstersBySize(@PathVariable("size") int size) {

        logger.debug("Find monsters({})", size);
        return monsterFacade.findBySize(size);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final MonsterDTO createMonster(@RequestBody MonsterCreateDTO monsterCreateDTO) {
        logger.debug("rest createMonster()");

        try {
            Long id = monsterFacade.createMonster(monsterCreateDTO);
            return monsterFacade.findById(id);
        } catch (Exception e) {
            throw new ResourceAlreadyExistsException();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public final void removeMonster(@PathVariable("id") Long id) throws ResourceNotFoundException {
        logger.debug("rest removeMonster(monsterId={})", id);

        try {
            monsterFacade.removeMonster(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }
}
