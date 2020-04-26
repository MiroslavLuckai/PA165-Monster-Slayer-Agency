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

    void create(Monster monster);

    void delete(Monster monster);

    List<Monster> findAll();

    Monster findById(Long id);

    Monster findByName(String name);

    List<Monster> findByMonsterType(MonsterType monsterType);

    List<Monster> findBySize(int size);

}
