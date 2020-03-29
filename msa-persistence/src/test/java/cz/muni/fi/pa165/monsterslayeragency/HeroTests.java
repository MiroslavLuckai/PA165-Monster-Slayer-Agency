package cz.muni.fi.pa165.monsterslayeragency;


import cz.muni.fi.pa165.monsterslayeragency.dao.HeroDao;
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

/**
 * @author Filip Daniel Fedin
 */

@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class HeroTests extends AbstractTestNGSpringContextTests {

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
        Assert.assertEquals(hero.getId(), found.getId());
        Assert.assertEquals(hero.getName(), found.getName());
        Assert.assertEquals(hero.getImage(), found.getImage());
        Assert.assertEquals(hero.getUser(), found.getUser());
        Assert.assertEquals(hero.getUser(), user);
    }
}
