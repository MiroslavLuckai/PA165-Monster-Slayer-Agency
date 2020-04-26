package cz.muni.fi.pa165.msa.service;

import cz.muni.fi.pa165.monsterslayeragency.dao.MonsterDao;
import cz.muni.fi.pa165.monsterslayeragency.entities.Monster;
import cz.muni.fi.pa165.monsterslayeragency.enums.MonsterType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Ludovit Kopcsanyi
 */
public class MonsterServiceImpl implements MonsterService {

    @Autowired
    MonsterDao monsterDao;


    @Override
    public Monster create(Monster monster) {
        monsterDao.addMonster(monster);
        return monster;
    }

    @Override
    public void delete(Monster monster) {
        monsterDao.removeMonster(monster);
    }

    @Override
    public void update(Monster monster) {
        monsterDao.updateMonster(monster);
    }

    @Override
    public void changeName(Monster monster, String newName) {
        Monster foundMonster = monsterDao.findById(monster.getId());
        if (foundMonster != null) {
            foundMonster.setName(newName);
            monsterDao.updateMonster(foundMonster);
        }
    }

    @Override
    public List<Monster> findAll() {
        return monsterDao.findAll();
    }

    @Override
    public Monster findById(Long id) {
        return monsterDao.findById(id);
    }

    @Override
    public Monster findByName(String name) {
        return monsterDao.findByName(name);
    }

    @Override
    public List<Monster> findByMonsterType(MonsterType monsterType) {
        return monsterDao.findByMonsterType(monsterType);
    }

    @Override
    public List<Monster> findBySize(int size) {
        return monsterDao.findBySize(size);
    }
}
