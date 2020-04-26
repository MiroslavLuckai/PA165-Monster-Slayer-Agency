package cz.muni.fi.pa165.msa.service;

import cz.muni.fi.pa165.monsterslayeragency.dao.HeroDao;
import cz.muni.fi.pa165.monsterslayeragency.entities.Hero;
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

/**
 * @author Miroslav Luckai 469288
 */
public class HeroServiceTest {

    @Mock
    HeroDao heroDao;

    @Autowired
    @InjectMocks
    HeroServiceImpl service;

    Hero geralt;

    @BeforeClass
    public void initMocks() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void setUp() {
        geralt = DummyObjects.getHeroDummy1();
    }

    @AfterMethod
    public void tearDown() {
        Mockito.reset(heroDao);
    }

    @Test
    public void createHeroTest() {
        service.createHero(geralt);
        Mockito.verify(heroDao, Mockito.times(1)).addHero(geralt);
    }

    @Test
    public void updateHeroTest() {
        geralt.setName("Geralt of Rivia");
        service.updateHero(geralt);
        Mockito.verify(heroDao, Mockito.times(1)).updateHero(geralt);
    }

    @Test
    public void removeHeroTest() {
        service.removeHero(geralt);
        Mockito.verify(heroDao, Mockito.times(1)).removeHero(geralt);
    }

    @Test
    public void getHeroByIdTest() {
        geralt.setId(1L);
        Mockito.when(heroDao.findHero(1L)).thenReturn(geralt);

        Assert.assertEquals(service.findHeroById(1L), geralt);
    }

    @Test
    public void getAllHeroesTest() {
        List<Hero> heroes = new ArrayList<>();
        heroes.add(geralt);

        Mockito.when(heroDao.findAllHeroes()).thenReturn(heroes);

        Assert.assertEquals(service.findAllHeroes(), heroes);
    }

    @Test
    public void getHeroByNameTest() {
        Mockito.when(heroDao.findByHeroName("Witcher")).thenReturn(geralt);

        Assert.assertEquals(service.findHeroByName("Witcher"), geralt);
    }

    @Test
    public void getHeroByUserIdTest() {
        Mockito.when(heroDao.findByUserId(1L)).thenReturn(geralt);

        Assert.assertEquals(service.findHeroByUserId(1L), geralt);
    }
}
