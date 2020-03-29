package cz.muni.fi.pa165.monsterslayeragency.dao;


import cz.muni.fi.pa165.monsterslayeragency.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.monsterslayeragency.dao.HeroDao;
import cz.muni.fi.pa165.monsterslayeragency.entities.Hero;
import cz.muni.fi.pa165.monsterslayeragency.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
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
    private HeroDao dao;

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

        dao.addHero(hero);
    }


    @Test
    public void findHeroByIdTest() {
        Hero found = dao.findHero(hero.getId());
        Assert.assertEquals(hero, found);
    }

    @Test
    public void updateHeroTest() {
        String oldName = hero.getName();
        hero.setName("Jojo");
        dao.updateHero(hero);
        Hero found = dao.findHero(hero.getId());
        Assert.assertEquals(found.getName(), hero.getName());
        Assert.assertNotEquals(found.getName(), oldName);
    }

    @Test
    public void removeHeroTest() {
        Long removedId = hero.getId();
        dao.removeHero(hero);
        Assert.assertNull(dao.findHero(removedId));
    }

    @Test
    public void findHeroByNameTest() {
        Hero found = dao.findByHeroName(hero.getName());
        Assert.assertEquals(hero, found);
    }

    @Test
    public void findHeroByUserTest() {
        Hero found = dao.findByUser(user.getId());
        Assert.assertEquals(hero, found);
    }

    @Test(expectedExceptions = InvalidDataAccessApiUsageException.class)
    public void addNullHero() {
        dao.addHero(null);
    }

    @Test(expectedExceptions = InvalidDataAccessApiUsageException.class)
    public void updateNullHero() {
        dao.updateHero(null);
    }

    @Test(expectedExceptions = InvalidDataAccessApiUsageException.class)
    public void removeNullHero() {
        dao.removeHero(null);
    }


}
