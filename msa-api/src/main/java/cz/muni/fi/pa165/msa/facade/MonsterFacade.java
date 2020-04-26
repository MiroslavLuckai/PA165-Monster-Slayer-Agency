package cz.muni.fi.pa165.msa.facade;

import cz.muni.fi.pa165.monsterslayeragency.entities.Monster;
import cz.muni.fi.pa165.monsterslayeragency.enums.MonsterType;
import cz.muni.fi.pa165.msa.dto.MonsterCreateDTO;
import cz.muni.fi.pa165.msa.dto.MonsterDTO;

import java.util.List;

public interface MonsterFacade {

    /**
     * Creates request.
     * @param monster monster to be created
     * @return id of the new monster
     */
    Long createMonster(MonsterCreateDTO monster);


    /**
     * Removes monster.
     * @param id id of a monster to be removed
     */
    boolean removeMonster(Long id);

    /**
     * Find request by id.
     * @param id id of a request to be found
     * @return request with selected id
     */
    MonsterDTO findById(Long id);

    /**
     * Finds all monsters.
     * @return list of all monsters
     */
    List<MonsterDTO> findAll();

    /**
     * Finds a monster by type
     * @param name name of the monster
     * @return monsters with entered name
     */
    MonsterDTO findByName(String name);

    /**
     * Finds monsters by type
     * @param monsterType type of monsters
     * @return monsters with selected monster type
     */
    List<MonsterDTO> findByMonsterType(MonsterType monsterType);

    /**
     * Finds monsters by size
     * @param size size of monsters
     * @return monsters with selected size
     */
    List<MonsterDTO> findBySize(int size);

    void changeName(MonsterDTO monsterDTO, String newName);
}
