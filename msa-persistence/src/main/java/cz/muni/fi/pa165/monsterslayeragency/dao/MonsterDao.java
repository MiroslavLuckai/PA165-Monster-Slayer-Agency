package cz.muni.fi.pa165.monsterslayeragency.dao;

import cz.muni.fi.pa165.monsterslayeragency.entities.Monster;
import cz.muni.fi.pa165.monsterslayeragency.enums.MonsterType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Ludovit Kopcsanyi
 */
public interface MonsterDao {

	/**
	 * Adds a monster to the database
	 *
	 * @param monster monster do be added
	 */
	public void addMonster(Monster monster) throws IllegalArgumentException;

	/**
	 * Removes a monster from the database
	 *
	 * @param monster monster to be removed
	 */
	public void removeMonster(Monster monster) throws IllegalArgumentException;

	/**
	 * Updates a monster in the database
	 *
	 * @param monster monster to be updated
	 */
	public void updateMonster(Monster monster) throws IllegalArgumentException;

	/**
	 * Finds monster by its id
	 * @param id id of Monster
	 * @return instance of Monster
	 */
	Monster findById(Long id) throws IllegalArgumentException;

	/**
	 * Finds monster by its name
	 * @param name name of Monster
	 * @return instance of Monster
	 */
	Monster findByName(String name) throws IllegalArgumentException;

	/**
	 * Find monsters by their type
	 * @param monsterType value of MonsterType
	 * @return list of monsters
	 */
	List<Monster> findByMonsterType(MonsterType monsterType) throws IllegalArgumentException;

	/**
	 * Find monsters by their size
	 * @param size numeric size value
	 * @return list of monsters
	 */
	List<Monster> findBySize(int size) throws IllegalArgumentException;

	/**
	 * List all monsters
	 * @return list of monsters
	 */
	List<Monster> findAll();

}
