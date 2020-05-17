package cz.muni.fi.pa165.msa.service.facade;

import cz.muni.fi.pa165.monsterslayeragency.entities.Hero;
import cz.muni.fi.pa165.msa.dto.HeroDTO;
import cz.muni.fi.pa165.msa.service.BeanMappingService;
import cz.muni.fi.pa165.msa.service.DummyObjects;
import cz.muni.fi.pa165.msa.service.HeroService;
import cz.muni.fi.pa165.msa.service.UserService;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Miroslav Luckai 469288
 */
public class HeroFacadeTest {

    @Mock
    private HeroService service;

    @Mock
    private UserService userService;

    @Mock
    private BeanMappingService mapper;

    @Autowired
    @InjectMocks
    private HeroFacadeImpl facade;

    private Hero geralt;

    private HeroDTO geraltDTO;

    @BeforeClass
    public void initMocks() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void setUp() {
        geralt = DummyObjects.getHeroDummy1();
        geraltDTO = DummyObjects.getHeroDTODummy1();
    }

    @Test
    public void createHeroTest() {
        geralt.setId(1L);
        Mockito.when(service.createHero(geralt)).thenReturn(geralt);
        Mockito.when(mapper.mapTo(geraltDTO, Hero.class)).thenReturn(geralt);
        Mockito.when(mapper.mapTo(geralt, HeroDTO.class)).thenReturn(geraltDTO);
        Mockito.when(userService.findUserById(geralt.getUser().getId())).thenReturn(geralt.getUser());

        Assert.assertEquals(facade.createHero(geraltDTO), geraltDTO);
        Mockito.verify(service, Mockito.times(1)).createHero(geralt);
    }

    @Test
    public void updateHeroTest() {
        Mockito.when(mapper.mapTo(geraltDTO, Hero.class)).thenReturn(geralt);

        facade.updateHero(geraltDTO);
        Mockito.verify(service, Mockito.times(1)).updateHero(geralt);
    }

    @Test
    public void removeHeroTest() {
        Mockito.when(service.findHeroByUserId(1L)).thenReturn(geralt);

        facade.removeHero(1L);
        Mockito.verify(service, Mockito.times(1)).removeHero(geralt);
    }

    @Test void findHeroByIdTest() {
        Mockito.when(service.findHeroById(1L)).thenReturn(geralt);
        Mockito.when(mapper.mapTo(geralt, HeroDTO.class)).thenReturn(geraltDTO);

        Assert.assertEquals(facade.findHero(1L), geraltDTO);
    }

    @Test void findHeroByNameTest() {
        Mockito.when(service.findHeroByName("Witcher")).thenReturn(geralt);
        Mockito.when(mapper.mapTo(geralt, HeroDTO.class)).thenReturn(geraltDTO);

        Assert.assertEquals(facade.findByHeroName("Witcher"), geraltDTO);
    }

    @Test
    void findHeroByUserIdTest() {
        Mockito.when(service.findHeroByUserId(1L)).thenReturn(geralt);
        Mockito.when(mapper.mapTo(geralt, HeroDTO.class)).thenReturn(geraltDTO);

        Assert.assertEquals(facade.findByUserId(1L), geraltDTO);
    }

    @Test
    void findAllHeroesTest() {
        List<HeroDTO> heroesDTO = new ArrayList<>();
        heroesDTO.add(geraltDTO);

        List<Hero> heroes = new ArrayList<>();
        heroes.add(geralt);

        Mockito.when(service.findAllHeroes()).thenReturn(heroes);
        Mockito.when(mapper.mapTo(heroes, HeroDTO.class)).thenReturn(heroesDTO);

        Assert.assertEquals(facade.findAllHeroes(), heroesDTO);

    }
}
