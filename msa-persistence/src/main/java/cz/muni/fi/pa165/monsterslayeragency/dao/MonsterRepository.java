package cz.muni.fi.pa165.monsterslayeragency.dao;

import cz.muni.fi.pa165.monsterslayeragency.entities.Monster;
import cz.muni.fi.pa165.monsterslayeragency.enums.MonsterType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Ludovit Kopcsanyi
 */
public interface MonsterRepository extends CrudRepository<Monster, Long> {

	/**
	 * Finds monster by its name
	 * @param name name of Monster
	 * @return instance of Monster
	 */
	Monster findByName(String name);

	/**
	 * Find monsters by their type
	 * @param monsterType value of MonsterType
	 * @return instance of Monster
	 */
	List<Monster> findByMonsterType(MonsterType monsterType);

	/**
	 * Find monsters by their size
	 * @param size numeric size value
	 * @return instance of Monster
	 */
	List<Monster> findBySize(int size);

	/**
	 * Find monsters by their type and size
	 * @param monsterType value of MonsterType
	 * @param size numeric size value
	 * @return instance of Monster
	 */
	List<Monster> findByMonsterTypeAndSize(MonsterType monsterType, int size);

}
