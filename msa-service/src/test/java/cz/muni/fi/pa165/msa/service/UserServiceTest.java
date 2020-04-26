package cz.muni.fi.pa165.msa.service;

import cz.muni.fi.pa165.monsterslayeragency.dao.UserDao;
import cz.muni.fi.pa165.monsterslayeragency.entities.User;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author Miroslav Luckai 469288
 */
public class UserServiceTest {

    @Mock
    private UserDao userDao;

    @Autowired
    @InjectMocks
    private UserServiceImpl service;

    private User geralt;

    @BeforeClass
    public void initMocks() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void setUp() {
        geralt = DummyObjects.getUserDummy1();
    }

    @AfterMethod
    public void tearDown() {
        Mockito.reset(userDao);
    }

    @Test
    public void createUser() {
        User user = service.registerUser(geralt, "Password1");

        Mockito.verify(userDao, Mockito.times(1)).addUser(geralt);

        Assert.assertEquals(user, geralt);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void updateUserWithNullIdTest() {
        service.updateUser(geralt);
        Mockito.verify(userDao, Mockito.times(0)).updateUser(geralt);
    }

    @Test
    public void updateUserTest() {
        geralt.setId(1L);
        geralt.setEmail("geralt.of.rivia@gmail.com");
        service.updateUser(geralt);
        Mockito.verify(userDao, Mockito.times(1)).updateUser(geralt);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void removeUserWithNullIdTest() {
        service.removeUser(geralt);
        Mockito.verify(userDao, Mockito.times(0)).removeUser(geralt);
    }

    @Test
    public void removeUserTest() {
        geralt.setId(1L);
        service.removeUser(geralt);
        Mockito.verify(userDao, Mockito.times(1)).removeUser(geralt);
    }

    @Test
    public void authenticateUserTest() {
        service.registerUser(geralt, "Password1");

        Assert.assertNotEquals(geralt.getPassword(), "password");

        Assert.assertTrue(service.authenticate(geralt, "Password1"));
    }

    @Test
    public void findUserByIdTest() {
        Mockito.when(userDao.findUserById(1L)).thenReturn(geralt);

        Assert.assertEquals(service.findUserById(1L), geralt);
    }

    @Test
    public void findUserByEmailTest() {
        Mockito.when(userDao.findUserByEmail("default@mail.com")).thenReturn(geralt);

        Assert.assertEquals(service.findUserByEmail("default@mail.com"), geralt);
    }

    @Test
    public void findUserByNameTest() {
        Mockito.when(userDao.findUserByUsername("Geralt")).thenReturn(geralt);

        Assert.assertEquals(service.findUserByUsername("Geralt"), geralt);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findUserWithNullEmail() {
        service.findUserByEmail(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findUserWithNullName() {
        service.findUserByUsername(null);
    }
}
