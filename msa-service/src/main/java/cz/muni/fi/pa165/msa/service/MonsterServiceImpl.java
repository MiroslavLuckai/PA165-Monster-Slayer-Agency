package cz.muni.fi.pa165.msa.service;

import cz.muni.fi.pa165.monsterslayeragency.dao.MonsterDao;
import cz.muni.fi.pa165.monsterslayeragency.entities.Monster;
import cz.muni.fi.pa165.monsterslayeragency.enums.MonsterType;
import cz.muni.fi.pa165.msa.service.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ludovit Kopcsanyi
 */
@Service
public class MonsterServiceImpl implements MonsterService {

    @Autowired
    MonsterDao monsterDao;

    private String MONSTER_IS_NULL_MESSAGE = "Monster cannot be null.";

    @Override
    public Monster create(Monster monster) {
        Validator.validate(monster, MONSTER_IS_NULL_MESSAGE);
        monsterDao.addMonster(monster);
        return monster;
    }

    @Override
    public void delete(Monster monster) {
        Validator.validate(monster, MONSTER_IS_NULL_MESSAGE);
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
        Validator.validate(id, "Id cannot be null.");
        return monsterDao.findById(id);
    }

    @Override
    public Monster findByName(String name) {
        Validator.validate(name, "Name cannot be null");
        return monsterDao.findByName(name);
    }

    @Override
    public List<Monster> findByMonsterType(MonsterType monsterType) {
        Validator.validate(monsterType, "Monster type cannot be null.");
        return monsterDao.findByMonsterType(monsterType);
    }

    @Override
    public List<Monster> findBySize(int size) {
        return monsterDao.findBySize(size);
    }
}
