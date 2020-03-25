package cz.muni.fi.pa165.monsterslayeragency.dao;

import cz.muni.fi.pa165.monsterslayeragency.entities.Monster;
import org.springframework.data.repository.CrudRepository;

public interface MonsterRepository extends CrudRepository<Monster, Long> {

    /**
     * Finds monster by its name - monster names are unique
     * @param name of the monster
     * @return Monster with the specified name
     */
    Monster findByName(String name);

}
