package cz.muni.fi.pa165.msa.service;

import cz.muni.fi.pa165.monsterslayeragency.entities.Monster;
import cz.muni.fi.pa165.monsterslayeragency.enums.MonsterType;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ludovit Kopcsanyi
 */
@Service
public interface MonsterService {

    Monster create(Monster monster) throws IllegalArgumentException;

    void delete(Monster monster) throws IllegalArgumentException;

    void update(Monster monster) throws IllegalArgumentException;

    void changeName(Monster monster, String newName) throws IllegalArgumentException;

    List<Monster> findAll();

    Monster findById(Long id) throws IllegalArgumentException;

    Monster findByName(String name) throws IllegalArgumentException;

    List<Monster> findByMonsterType(MonsterType monsterType) throws IllegalArgumentException;

    List<Monster> findBySize(int size);

}
