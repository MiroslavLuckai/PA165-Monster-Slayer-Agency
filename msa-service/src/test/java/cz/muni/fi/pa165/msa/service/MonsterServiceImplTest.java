package cz.muni.fi.pa165.msa.service;

import cz.muni.fi.pa165.monsterslayeragency.dao.MonsterDao;
import cz.muni.fi.pa165.monsterslayeragency.entities.Monster;
import cz.muni.fi.pa165.monsterslayeragency.enums.MonsterType;
import cz.muni.fi.pa165.msa.service.config.ServiceConfiguration;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * @author Ludovit Kopcsanyi
 */

@ContextConfiguration(classes = ServiceConfiguration.class)
public class MonsterServiceImplTest extends AbstractTestNGSpringContextTests {

    @Mock
    private MonsterDao monsterDao;

    @Autowired
    @InjectMocks
    private MonsterService monsterService;

    private Monster monster;

    @BeforeClass
    public void initMocks() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    private void setUp() {
        monster = DummyObjects.getMonsterDummy1();
    }

    @AfterMethod
    public void tearDown() {
        Mockito.reset(monsterDao);
    }

    @Test
    public void create() {
        monsterService.create(monster);
        verify(monsterDao, times(1)).addMonster(monster);
    }

    @Test
    public void delete() {
        monsterService.delete(monster);
        verify(monsterDao, times(1)).removeMonster(monster);
    }

    @Test
    public void findAll() {
        List<Monster> monsters = new ArrayList<>();
        monsters.add(monster);
        monsters.add(DummyObjects.getMonsterDummy2());
        when(monsterDao.findAll()).thenReturn(monsters);
        Assert.assertEquals(monsterService.findAll(), monsters);
    }

    @Test
    public void findById() {
        when(monsterDao.findById(1L)).thenReturn(monster);
        Assert.assertEquals(monsterService.findById(1L), monster);
    }

    @Test
    public void findByName() {
        when(monsterDao.findByName(monster.getName())).thenReturn(monster);
        Assert.assertEquals(monsterService.findByName(monster.getName()), monster);
    }

    @Test
    public void findByMonsterType() {
        List<Monster> beasts = new ArrayList<>();
        List<Monster> hybrids = new ArrayList<>();
        beasts.add(monster);
        hybrids.add(DummyObjects.getMonsterDummy2());
        when(monsterDao.findByMonsterType(MonsterType.BEAST)).thenReturn(beasts);
        Assert.assertEquals(monsterService.findByMonsterType(MonsterType.BEAST), beasts);
        when(monsterDao.findByMonsterType(MonsterType.HYBRID)).thenReturn(hybrids);
        Assert.assertEquals(monsterService.findByMonsterType(MonsterType.HYBRID), hybrids);
    }

    @Test
    public void findBySize() {
        List<Monster> monsters = new ArrayList<>();
        monsters.add(monster);
        monsters.add(DummyObjects.getMonsterDummy3());
        when(monsterDao.findBySize(10)).thenReturn(monsters);
        Assert.assertEquals(monsterService.findBySize(10), monsters);
    }

}
