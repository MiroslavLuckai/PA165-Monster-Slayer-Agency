package cz.muni.fi.pa165.msa.service;

import cz.muni.fi.pa165.monsterslayeragency.dao.MonsterDao;
import cz.muni.fi.pa165.monsterslayeragency.dao.RequestDao;
import cz.muni.fi.pa165.monsterslayeragency.dao.UserDao;
import cz.muni.fi.pa165.monsterslayeragency.entities.Monster;
import cz.muni.fi.pa165.monsterslayeragency.entities.Request;
import cz.muni.fi.pa165.monsterslayeragency.entities.User;
import cz.muni.fi.pa165.msa.service.config.ServiceConfiguration;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ContextConfiguration(classes = ServiceConfiguration.class)
public class RequestServiceImplTest extends AbstractTestNGSpringContextTests {

    @Mock
    private RequestDao requestDao;

    @Mock
    private UserDao userDao;

    @Mock
    private MonsterDao monsterDao;

    @Autowired
    @InjectMocks
    private RequestService requestService;

    private Request request;
    private User customer;
    private Monster monster1, monster2;

    @BeforeClass
    public void initMocks() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    private void setUp() {
        customer = DummyObjects.getUserDummy1();

        monster1 = DummyObjects.getMonsterDummy1();

        monster2 = DummyObjects.getMonsterDummy2();

        List<Monster> monsterList = new ArrayList<>();
        monsterList.add(monster1);
        monsterList.add(monster2);

        request = DummyObjects.getRequestDummy2();
        request.setCustomer(customer);
        request.setMonsters(monsterList);
    }

    @Test
    public void create() {
        requestService.create(request);
        verify(requestDao, times(1)).addRequest(request);
    }

    @Test
    public void delete() {
        requestService.delete(request);
        verify(requestDao, times(1)).removeRequest(request);
    }

    @Test
    public void findAll() {
        List<Request> requestList = new ArrayList<>();
        requestList.add(request);
        requestList.add(DummyObjects.getRequestDummy1());
        when(requestDao.findAll()).thenReturn(requestList);
        Assert.assertEquals(requestService.findAll(), requestList);
    }

    @Test
    public void findById() {
        when(requestDao.findRequestById(request.getId())).thenReturn(request);

        Assert.assertEquals(requestService.findById(request.getId()), request);
    }

    @Test
    public void findByCustomer() {
        when(requestDao.findRequestByCustomer(request.getCustomer())).thenReturn(request);

        Assert.assertEquals(requestService.findByCustomer(request.getCustomer()), request);
    }

    @Test
    public void addMonsters() {
        Monster addedMonster = DummyObjects.getMonsterDummy3();
        List<Monster> monsters = new ArrayList<>();
        monsters.add(addedMonster);

        when(requestDao.findRequestById(request.getId())).thenReturn(request);

        requestService.addMonsters(request, monsters);
        verify(requestDao, times(1)).updateRequest(request);
    }


    @Test
    public void removeMonsters() {
        Monster addedMonster = DummyObjects.getMonsterDummy3();
        List<Monster> monsters = new ArrayList<>();
        monsters.add(addedMonster);

        when(requestDao.findRequestById(request.getId())).thenReturn(request);

        requestService.removeMonsters(request, monsters);
        verify(requestDao, times(1)).updateRequest(request);
    }

    @Test
    public void changeLocation() {

        when(requestDao.findRequestById(request.getId())).thenReturn(request);

        requestService.changeLocation(request, "new location");
        verify(requestDao, times(1)).updateRequest(request);
    }

    @Test
    public void changeAward() {
        when(requestDao.findRequestById(request.getId())).thenReturn(request);

        BigDecimal award = new BigDecimal(5);
        requestService.changeAward(request, award);
        verify(requestDao, times(1)).updateRequest(request);
    }

    @Test
    public void managingNonExistingRequest() {
        when(requestDao.findRequestById(request.getId())).thenReturn(null);

        requestService.changeAward(request, new BigDecimal(5));
        verify(requestDao, times(0)).updateRequest(request);

        requestService.changeLocation(request, "new location");
        verify(requestDao, times(0)).updateRequest(request);

        requestService.addMonsters(request, new ArrayList<>());
        verify(requestDao, times(0)).updateRequest(request);

        requestService.removeMonsters(request, new ArrayList<>());
        verify(requestDao, times(0)).updateRequest(request);
    }
}