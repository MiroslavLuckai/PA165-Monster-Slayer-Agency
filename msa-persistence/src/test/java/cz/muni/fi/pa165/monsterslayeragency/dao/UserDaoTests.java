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
import java.util.Arrays;
import java.util.List;


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
        userDao.addUser(user1);

        user2 = new User();
        user2.setEmail("normal@mail.com");
        user2.setImage("image");
        user2.setUserName("Peter");
        user2.setPassword("password");
        userDao.addUser(user2);
    }


    @Test
    public void findUserByIdTest() {
        User found = userDao.findUserById(user1.getId());
        Assert.assertEquals(user1, found);
    }

    @Test
    public void updateUserTest() {
        String oldMail = user1.getEmail();
        user1.setEmail("newMail");
        userDao.updateUser(user1);
        User found = userDao.findUserById(user1.getId());
        Assert.assertEquals(found.getEmail(), user1.getEmail());
        Assert.assertNotEquals(found.getEmail(), oldMail);
    }

    @Test
    public void removeUserTest() {
        userDao.removeUser(user1);
        Assert.assertNull(userDao.findUserById(user1.getId()));
    }

    @Test
    public void findUserByEmailTest() {
        User found = userDao.findUserByEmail(user1.getEmail());
        Assert.assertEquals(found, user1);
    }

    @Test
    public void findUserByUsernameTest() {
        User found = userDao.findUserByUsername(user1.getUserName());
        Assert.assertEquals(found, user1);
    }

    @Test
    public void findAllUsersTest() {
        List<User> found = userDao.getAllUsers();
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        Assert.assertEquals(found, users);

    }
}
