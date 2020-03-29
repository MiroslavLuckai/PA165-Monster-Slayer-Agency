package cz.muni.fi.pa165.monsterslayeragency.dao;

import cz.muni.fi.pa165.monsterslayeragency.entities.Hero;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Miroslav Luckai
 */
@Repository
public class HeroDaoImpl implements HeroDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addHero(Hero hero) {
        validate(hero, "Hero cannot be null!");
        em.persist(hero);
    }

    @Override
    public void updateHero(Hero hero) {
        validate(hero, "Hero cannot be null!");
        em.merge(hero);
    }

    @Override
    public void removeHero(Hero hero) {
        validate(hero, "Hero cannot be null!");
        em.remove(hero);
    }

    @Override
    public Hero findHero(Long id) {
        validate(id, "Cannot search for hero with null hero ID!");
        return em.find(Hero.class, id);
    }

    @Override
    public Hero findByHeroName(String heroName) {
        return em.createQuery("select hero from Hero hero where hero.name = :name", Hero.class)
                .setParameter("name", heroName)
                .getSingleResult();
    }

    @Override
    public Hero findByUser(Long userId) {
        validate(userId, "Cannot search for hero with null user ID!");
        return em.createQuery("select hero from Hero hero where hero.user.id = :userId", Hero.class)
                .setParameter("userId", userId)
                .getSingleResult();
    }

    private void validate(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
