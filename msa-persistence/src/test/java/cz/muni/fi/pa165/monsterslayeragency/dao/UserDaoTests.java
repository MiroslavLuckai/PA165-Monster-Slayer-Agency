package cz.muni.fi.pa165.monsterslayeragency.dao;


import cz.muni.fi.pa165.monsterslayeragency.PersistenceSampleApplicationContext;
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
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Filip Daniel Fedin
 */


@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class UserDaoTests extends AbstractTestNGSpringContextTests{

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private UserDao userDao;

    private User user1, user2;

    @BeforeMethod
    void setUp() {
        user1 = new User();
        user1.setEmail("default@mail.com");
        user1.setImage("test_image");
        user1.setPassword("default");
        user1.setUserName("Geralt");

        user2 = new User();
        user2.setEmail("normal@mail.com");
        user2.setImage("image");
        user2.setUserName("Peter");
        user2.setPassword("password");
    }

    @Test
    public void addUserTest() {
        userDao.addUser(user1);
        userDao.addUser(user2);

        List<User> resultList = em.createQuery("select u from User u", User.class).getResultList();
        assertThat(resultList).containsExactlyInAnyOrder(user1, user2);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void addUserWithNoUserNameTest() {
        user1.setUserName(null);
        userDao.addUser(user1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void addUserWithNoPasswordTest() {
        user1.setPassword(null);
        userDao.addUser(user1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void addUserWithNoEmailTest() {
        user1.setEmail(null);
        userDao.addUser(user1);
    }


    @Test
    public void findUserByIdTest() {
        em.persist(user1);
        User found = userDao.findUserById(user1.getId());
        Assert.assertEquals(user1, found);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findUserByNullIdTest() {
        userDao.findUserById(null);
    }

    @Test
    public void updateUserTest() {
        em.persist(user1);
        String oldMail = user1.getEmail();
        user1.setEmail("newMail");
        userDao.updateUser(user1);
        User found = userDao.findUserById(user1.getId());
        Assert.assertEquals(found.getEmail(), user1.getEmail());
        Assert.assertNotEquals(found.getEmail(), oldMail);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void updateNullUserTest() {
        userDao.updateUser(null);
    }

    @Test
    public void removeUserTest() {
        em.persist(user1);
        userDao.removeUser(user1);
        Assert.assertNull(userDao.findUserById(user1.getId()));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void removeNullUserTest() {
        userDao.removeUser(null);
    }

    @Test
    public void findUserByEmailTest() {
        em.persist(user1);
        User found = userDao.findUserByEmail(user1.getEmail());
        Assert.assertEquals(found, user1);
    }

    @Test
    public void findUserByNotExistingEmailTest() {
        User found = userDao.findUserByEmail("blabla");
        Assert.assertNull(found);
    }

    @Test
    public void findUserByUsernameTest() {
        em.persist(user1);
        User found = userDao.findUserByUsername(user1.getUserName());
        Assert.assertEquals(found, user1);
    }

    @Test
    public void findUserByNotExistingUsernameTest() {
        User found = userDao.findUserByUsername("blabal");
        Assert.assertNull(found);
    }

    @Test
    public void findAllUsersTest() {
        em.persist(user1);
        em.persist(user2);
        List<User> found = userDao.getAllUsers();
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        Assert.assertEquals(found, users);

    }
}
