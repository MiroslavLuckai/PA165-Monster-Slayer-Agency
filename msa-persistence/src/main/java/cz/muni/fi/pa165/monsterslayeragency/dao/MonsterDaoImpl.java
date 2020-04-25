package cz.muni.fi.pa165.monsterslayeragency.dao;

import cz.muni.fi.pa165.monsterslayeragency.entities.Monster;
import cz.muni.fi.pa165.monsterslayeragency.enums.MonsterType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Ludovit Kopcsanyi
 */
@Repository
@Transactional
public class MonsterDaoImpl implements MonsterDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addMonster(Monster monster) throws IllegalArgumentException {
        validate(monster, "Monster cannot be null!");
        entityManager.persist(monster);
    }

    @Override
    public void removeMonster(Monster monster) throws IllegalArgumentException {
        validate(monster, "Monster cannot be null!");
        entityManager.remove(monster);
    }

    @Override
    public void updateMonster(Monster monster) throws IllegalArgumentException {
        validate(monster, "Monster cannot be null!");
        entityManager.merge(monster);
    }

    public Monster findById(Long id) throws IllegalArgumentException {
        validate(id, "ID cannot be null!");
        return entityManager.find(Monster.class, id);
    }

    @Override
    public Monster findByName(String name) throws IllegalArgumentException {
        validate(name, "Monster name cannot be null!");
        return entityManager.createQuery("select monster from Monster monster where monster.name = :name", Monster.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public List<Monster> findByMonsterType(MonsterType monsterType) {
        validate(monsterType, "Monster type cannot be null!");
        return entityManager
                .createQuery("select monster from Monster monster where monster.monsterType = :type", Monster.class)
                .setParameter("type", monsterType)
                .getResultList();
    }

    @Override
    public List<Monster> findBySize(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Monster size is below 0");
        }
        return entityManager
                .createQuery("select monster from Monster monster where monster.size = :size", Monster.class)
                .setParameter("size", size)
                .getResultList();
    }

    @Override
    public List<Monster> findAll() {
        return entityManager
                .createQuery("select monster from Monster monster", Monster.class)
                .getResultList();
    }

    private void validate(Object object, String message) throws IllegalArgumentException {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
