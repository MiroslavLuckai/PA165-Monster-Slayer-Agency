package cz.muni.fi.pa165.monsterslayeragency.dao;


import cz.muni.fi.pa165.monsterslayeragency.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.monsterslayeragency.entities.Hero;
import cz.muni.fi.pa165.monsterslayeragency.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Filip Daniel Fedin
 */

@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class HeroDaoTests extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private HeroDao heroDao;

    @Autowired
    private UserDao userDao;

    private Hero hero;
    private User user;

    @BeforeMethod
    void setUp() {
        user = new User();
        user.setEmail("default@mail.com");
        user.setImage("test_image");
        user.setPassword("default");
        user.setUserName("Geralt");

        hero = new Hero();
        hero.setName("Witcher");
        hero.setUser(user);
        hero.setImage("test");

    }

    @Test
    public void addHeroTest() {
        em.persist(user);
        heroDao.addHero(hero);

        List<Hero> resultList = em.createQuery("select h from Hero h", Hero.class).getResultList();
        assertThat(resultList).containsExactlyInAnyOrder(hero);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void addHeroWithNoNameTest() {
        em.persist(user);
        hero.setName(null);
        heroDao.addHero(hero);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void addHeroWithNoUserTest() {
        hero.setUser(null);
        heroDao.addHero(hero);
    }


    @Test
    public void findHeroByIdTest() {
        em.persist(user);
        em.persist(hero);

        Hero found = heroDao.findHero(hero.getId());
        Assert.assertEquals(hero, found);
    }

    @Test
    public void findHeroByIdNotInDatabase() {
        Hero found = heroDao.findHero(Long.valueOf("123"));
        Assert.assertNull(found);
    }

    @Test
    public void updateHeroTest() {
        em.persist(user);
        em.persist(hero);

        String oldName = hero.getName();
        hero.setName("Jojo");
        heroDao.updateHero(hero);
        Hero found = heroDao.findHero(hero.getId());
        Assert.assertEquals(found.getName(), hero.getName());
        Assert.assertNotEquals(found.getName(), oldName);
    }

    @Test
    public void removeHeroTest() {
        em.persist(user);
        em.persist(hero);

        Long removedId = hero.getId();
        heroDao.removeHero(hero);
        Assert.assertNull(heroDao.findHero(removedId));
    }

    @Test
    public void findHeroByNameTest() {
        em.persist(user);
        em.persist(hero);

        Hero found = heroDao.findByHeroName(hero.getName());
        Assert.assertEquals(hero, found);
    }

    @Test
    public void findHeroByNameNotInDatabase() {
        Hero found = heroDao.findByHeroName("pepe");
        Assert.assertNull(found);
    }

    @Test
    public void findHeroByUserTest() {
        em.persist(user);
        em.persist(hero);

        Hero found = heroDao.findByUserId(user.getId());
        Assert.assertEquals(hero, found);
    }

    @Test
    public void findHeroByUserNotInDatabase() {
        Hero found = heroDao.findByUserId(Long.valueOf("5"));
        Assert.assertNull(found);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void addNullHero() {
        heroDao.addHero(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void updateNullHero() {
        heroDao.updateHero(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void removeNullHero() {
        heroDao.removeHero(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findHeroByNullName() {
        heroDao.findByHeroName(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findHeroByNullId() {
        heroDao.findByUserId(null);
    }

}
