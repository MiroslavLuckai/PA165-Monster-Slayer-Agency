package cz.muni.fi.pa165.monsterslayeragency.dao;

import cz.muni.fi.pa165.monsterslayeragency.entities.Monster;
import cz.muni.fi.pa165.monsterslayeragency.enums.MonsterType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Ludovit Kopcsanyi
 */
public interface MonsterRepository {

	/**
	 * Adds a monster to the database
	 *
	 * @param monster monster do be added
	 */
	public void addMonster(Monster monster);

	/**
	 * Removes a monster from the database
	 *
	 * @param monster monster to be removed
	 */
	public void removeMonster(Monster monster);

	/**
	 * Updates a monster in the database
	 *
	 * @param monster monster to be updated
	 */
	public void updateMonster(Monster monster);

	/**
	 * Finds monster by its id
	 * @param id id of Monster
	 * @return instance of Monster
	 */
	Monster findById(Long id);

	/**
	 * Finds monster by its name
	 * @param name name of Monster
	 * @return instance of Monster
	 */
	Monster findByName(String name);

	/**
	 * Find monsters by their type
	 * @param monsterType value of MonsterType
	 * @return list of monsters
	 */
	List<Monster> findByMonsterType(MonsterType monsterType);

	/**
	 * Find monsters by their size
	 * @param size numeric size value
	 * @return list of monsters
	 */
	List<Monster> findBySize(int size);

	/**
	 * Find monsters by their type and size
	 * @param monsterType value of MonsterType
	 * @param size numeric size value
	 * @return list of monsters
	 */
	List<Monster> findByMonsterTypeAndSize(MonsterType monsterType, int size);

	/**
	 * List all monsters
	 * @return list of monsters
	 */
	List<Monster> findAll();

}
