package cz.muni.fi.pa165.msa.service;

import cz.muni.fi.pa165.monsterslayeragency.dao.MonsterDao;
import cz.muni.fi.pa165.monsterslayeragency.dao.RequestDao;
import cz.muni.fi.pa165.monsterslayeragency.dao.UserDao;
import cz.muni.fi.pa165.monsterslayeragency.entities.Monster;
import cz.muni.fi.pa165.monsterslayeragency.entities.Request;
import cz.muni.fi.pa165.monsterslayeragency.entities.User;
import cz.muni.fi.pa165.monsterslayeragency.enums.MonsterType;
import cz.muni.fi.pa165.monsterslayeragency.enums.Resistance;
import cz.muni.fi.pa165.msa.service.config.ServiceConfiguration;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.then;
import static org.testng.Assert.*;

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
        then(requestDao).should().addRequest(request);
    }

    @Test
    public void delete() {

    }

    @Test
    public void findAll() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void findByCustomer() {
    }
}