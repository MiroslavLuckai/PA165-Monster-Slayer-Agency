package cz.muni.fi.pa165.monsterslayeragency.dao;

import cz.muni.fi.pa165.monsterslayeragency.entities.Hero;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Miroslav Luckai
 */
@Repository
public class HeroDaoImpl implements HeroDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addHero(Hero hero) throws IllegalArgumentException {
        validate(hero, "Hero cannot be null!");
        validate(hero.getName(), "Hero name cannot be null!");
        validate(hero.getUser(), "User of the hero cannot be null!");
        em.persist(hero);
    }

    @Override
    public void updateHero(Hero hero) throws IllegalArgumentException {
        validate(hero, "Hero cannot be null!");
        em.merge(hero);
    }

    @Override
    public void removeHero(Hero hero) throws IllegalArgumentException {
        validate(hero, "Hero cannot be null!");
        em.remove(hero);
    }

    @Override
    public Hero findHero(Long id) throws IllegalArgumentException {
        validate(id, "Cannot search for hero with null hero ID!");
        return em.find(Hero.class, id);
    }

    @Override
    public Hero findByHeroName(String heroName) throws IllegalArgumentException {
        validate(heroName, "Cannot search for hero with null hero name!");
        List<Hero> hero = em.createQuery("select hero from Hero hero where hero.name = :name", Hero.class)
                .setParameter("name", heroName)
                .getResultList();

        return hero.isEmpty() ? null : hero.get(0);
    }

    @Override
    public Hero findByUserId(Long userId) throws IllegalArgumentException {
        validate(userId, "Cannot search for hero with null user ID!");
        List<Hero> hero = em.createQuery("select hero from Hero hero where hero.user.id = :userId", Hero.class)
                .setParameter("userId", userId)
                .getResultList();

        return hero.isEmpty() ? null : hero.get(0);
    }

    private void validate(Object object, String message) throws IllegalArgumentException {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
