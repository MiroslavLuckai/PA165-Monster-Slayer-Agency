package cz.muni.fi.pa165.msa.service;

import cz.muni.fi.pa165.monsterslayeragency.entities.Monster;
import cz.muni.fi.pa165.monsterslayeragency.enums.MonsterType;

import java.util.List;

/**
 * @author Ludovit Kopcsanyi
 */
public interface MonsterService {

    Monster create(Monster monster);

    void delete(Monster monster);

    void update(Monster monster);

    void changeName(Monster monster, String newName);

    List<Monster> findAll();

    Monster findById(Long id);

    Monster findByName(String name);

    List<Monster> findByMonsterType(MonsterType monsterType);

    List<Monster> findBySize(int size);

}
