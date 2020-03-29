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
        if (monster == null) {
            throw new IllegalArgumentException("Monster is null");
        }
        entityManager.persist(monster);
    }

    @Override
    public void removeMonster(Monster monster) throws IllegalArgumentException {
        if (monster == null) {
            throw new IllegalArgumentException("Monster is null");
        }
        entityManager.remove(monster);
    }

    @Override
    public void updateMonster(Monster monster) throws IllegalArgumentException {
        if (monster == null) {
            throw new IllegalArgumentException("Monster is null");
        }
        entityManager.merge(monster);
    }

    public Monster findById(Long id) throws IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("Id is null");
        }
        return entityManager.find(Monster.class, id);
    }

    @Override
    public Monster findByName(String name) throws IllegalArgumentException {
        if (name == null) {
            throw new IllegalArgumentException("Monster name is null");
        }
        try {
            return entityManager
                    .createQuery("select monster from Monster monster where monster.name = :name", Monster.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException nrf) {
            return null;
        }
    }

    @Override
    public List<Monster> findByMonsterType(MonsterType monsterType) {
        if (monsterType == null) {
            throw new IllegalArgumentException("Monster type is null");
        }
        try {
            return entityManager
                    .createQuery("select monster from Monster monster where monster.type = :type", Monster.class)
                    .setParameter("type", monsterType)
                    .getResultList();
        } catch (NoResultException nrf) {
            return null;
        }
    }

    @Override
    public List<Monster> findBySize(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Monster size is below 0");
        }
        try {
            return entityManager
                    .createQuery("select monster from Monster monster where monster.size = :size", Monster.class)
                    .setParameter("size", size)
                    .getResultList();
        } catch (NoResultException nrf) {
            return null;
        }
    }

    @Override
    public List<Monster> findByMonsterTypeAndSize(MonsterType monsterType, int size) {
        if (monsterType == null || size < 0) {
            throw new IllegalArgumentException("Monster type is null or size is below 0");
        }
        try {
            return entityManager
                    .createQuery("select monster from Monster monster where monster.type = :type and monster.size = :size", Monster.class)
                    .setParameter("type", monsterType)
                    .setParameter("size", size)
                    .getResultList();
        } catch (NoResultException nrf) {
            return null;
        }
    }

    @Override
    public List<Monster> findAll() {
        return entityManager
                .createQuery("select monster from Monster monster", Monster.class)
                .getResultList();
    }
}
