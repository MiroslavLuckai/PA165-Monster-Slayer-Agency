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

    /**
     * List all monsters stored in database
     * curl -i -X GET http://localhost:8080/pa165/rest/monsters
     * @return List of monsters
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<MonsterDTO> getMonsters() {

        logger.debug("Get all requests");
        return monsterFacade.findAll();
    }

    /**
     * Get monster by its Id
     * curl -i -X GET http://localhost:8080/pa165/rest/monsters/{id}
     * @param id id of the monster we are looking for
     * @return Monster with requested id, in case there is no monster with this Id in database, exception is thrown
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final MonsterDTO getMonsterById(@PathVariable("id") Long id) {

        logger.debug("Find monster({})", id);
        MonsterDTO monster = monsterFacade.findById(id);
        if (monster == null) {
            throw new ResourceNotFoundException();
        }
        return monster;
    }

    /**
     * Get monster by its name
     * curl -i -X GET http://localhost:8080/pa165/rest/monsters/name/{name}
     * @param name name of the monster we are looking for
     * @return Monster with requested name, in case there is no monster with this name in database, exception is thrown
     */
    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final MonsterDTO getMonsterByName(@PathVariable("name") String name) {

        logger.debug("Find monster({})", name);
        return monsterFacade.findByName(name);
    }

    /**
     * Get list of monsters according their type
     * curl -i -X GET http://localhost:8080/pa165/rest/monsters/type/{monsterType}
     * @param monsterType monsterType of monsters we are looking for
     * @return List of monsters
     */
    @RequestMapping(value = "/type/{monsterType}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<MonsterDTO> getMonstersByType(@PathVariable("monsterType") MonsterType monsterType) {

        logger.debug("Find monsters({})", monsterType);
        return monsterFacade.findByMonsterType(monsterType);
    }

    /**
     * Get list of monsters according their size
     * curl -i -X GET http://localhost:8080/pa165/rest/monsters/size/{size}
     * @param size size of the monsters we are looking for
     * @return List of monsters
     */
    @RequestMapping(value = "/size/{size}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<MonsterDTO> getMonstersBySize(@PathVariable("size") int size) {

        logger.debug("Find monsters({})", size);
        return monsterFacade.findBySize(size);
    }

    /**
     * Create new monster in a database
     * @param monsterCreateDTO monster we want to create
     * @return newly created monster
     */
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

    /**
     * Remove monster with id from database
     * curl -i -X DELETE http://localhost:8080/pa165/rest/monsters/{id}
     * @param id id of the monster we want to remove
     * @throws ResourceNotFoundException in case there is no monster with requested id
     */
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
