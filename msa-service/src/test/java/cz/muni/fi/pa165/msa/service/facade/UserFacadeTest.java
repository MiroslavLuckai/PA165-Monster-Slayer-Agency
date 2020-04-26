package cz.muni.fi.pa165.msa.service.facade;

import cz.muni.fi.pa165.monsterslayeragency.entities.User;
import cz.muni.fi.pa165.msa.dto.UserDTO;
import cz.muni.fi.pa165.msa.service.BeanMappingService;
import cz.muni.fi.pa165.msa.service.DummyObjects;
import cz.muni.fi.pa165.msa.service.UserService;
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

import java.util.ArrayList;
import java.util.List;

public class UserFacadeTest {

    @Mock
    private UserService service;

    @Mock
    private BeanMappingService mapper;

    @Autowired
    @InjectMocks
    private UserFacadeImpl facade;

    private User geralt;

    private UserDTO geraltDTO;

    @BeforeClass
    public void initMocks() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void setUp() {
        geralt = DummyObjects.getUserDummy1();
        geraltDTO = DummyObjects.getUserDTODummy1();
    }

    @AfterMethod
    public void tearDown() {
        Mockito.reset(service);
    }

    @Test
    public void createUserTest() {
        geralt.setId(1L);
        Mockito.when(service.registerUser(geralt, "Password1")).thenReturn(geralt);
        Mockito.when(mapper.mapTo(geraltDTO, User.class)).thenReturn(geralt);

        Assert.assertEquals(facade.registerUser(geraltDTO, "Password1"), geralt.getId());
    }

    @Test
    public void updateUserTest() {
        Mockito.when(mapper.mapTo(geraltDTO, User.class)).thenReturn(geralt);

        facade.updateUser(geraltDTO);
        Mockito.verify(service, Mockito.times(1)).updateUser(geralt);
    }

    @Test
    public void removeUserTest() {
        Mockito.when(mapper.mapTo(geraltDTO, User.class)).thenReturn(geralt);

        facade.removeUser(geraltDTO);
        Mockito.verify(service, Mockito.times(1)).removeUser(geralt);
    }

    @Test
    public void authenticateUserTest() {
        Mockito.when(mapper.mapTo(geraltDTO, User.class)).thenReturn(geralt);

        facade.authenticateUser(geraltDTO, "Password1");
        Mockito.verify(service, Mockito.times(1)).authenticate(geralt, "Password1");
    }

    @Test
    public void findUserByIdTest() {
        Mockito.when(mapper.mapTo(geralt, UserDTO.class)).thenReturn(geraltDTO);
        Mockito.when(service.findUserById(1L)).thenReturn(geralt);

        Assert.assertEquals(facade.findUserById(1L), geraltDTO);
    }

    @Test
    public void findAllUsersTest() {
        List<User> users = new ArrayList<>();
        users.add(geralt);
        List<UserDTO> usersDTO = new ArrayList<>();
        usersDTO.add(geraltDTO);

        Mockito.when(mapper.mapTo(users, UserDTO.class)).thenReturn(usersDTO);
        Mockito.when(service.findAll()).thenReturn(users);

        Assert.assertEquals(facade.getAllUsers(), usersDTO);
    }

    @Test
    public void findUserByEmail() {
        Mockito.when(mapper.mapTo(geralt, UserDTO.class)).thenReturn(geraltDTO);
        Mockito.when(service.findUserByEmail("default@mail.com")).thenReturn(geralt);

        Assert.assertEquals(facade.findUserByEmail("default@mail.com"), geraltDTO);
    }

    @Test
    public void findUserByName() {
        Mockito.when(mapper.mapTo(geralt, UserDTO.class)).thenReturn(geraltDTO);
        Mockito.when(service.findUserByUsername("Geralt")).thenReturn(geralt);

        Assert.assertEquals(facade.findUserByUsername("Geralt"), geraltDTO);
    }
}
