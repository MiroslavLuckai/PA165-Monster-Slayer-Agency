package cz.muni.fi.pa165.msa.service.facade;

import cz.muni.fi.pa165.monsterslayeragency.entities.Monster;
import cz.muni.fi.pa165.monsterslayeragency.enums.MonsterType;
import cz.muni.fi.pa165.monsterslayeragency.enums.Resistance;
import cz.muni.fi.pa165.msa.dto.MonsterCreateDTO;
import cz.muni.fi.pa165.msa.dto.MonsterDTO;
import cz.muni.fi.pa165.msa.service.BeanMappingService;
import cz.muni.fi.pa165.msa.service.DummyObjects;
import cz.muni.fi.pa165.msa.service.MonsterServiceImpl;
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
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * @author Ludovit Kopcsanyi
 */
public class MonsterFacadeImplTest {

    @Autowired
    @InjectMocks
    private MonsterFacadeImpl monsterFacade;

    @Mock
    private BeanMappingService beanMappingService;

    @Mock
    private MonsterServiceImpl monsterService;

    private Monster monster1;
    private Monster monster2;

    private MonsterDTO monsterDTO1;
    private MonsterDTO monsterDTO2;

    private MonsterCreateDTO monsterCreateDTO;

    @BeforeClass
    void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    void setUp() {
        monsterCreateDTO = new MonsterCreateDTO();
        monsterCreateDTO.setName("Beast Monster");
        monsterCreateDTO.setSize(10);
        monsterCreateDTO.setResistances(new HashSet<>(Arrays.asList(Resistance.ICE, Resistance.ROCK)));
        monsterCreateDTO.setMonsterType(MonsterType.BEAST);

        monsterDTO1 = DummyObjects.getMonsterDTODummy1();
        monsterDTO2 = DummyObjects.getMonsterDTODummy2();

        monster1 = DummyObjects.getMonsterDummy1();
        monster2 = DummyObjects.getMonsterDummy2();
    }

    @AfterMethod
    void reset() {
        Mockito.reset(monsterService);
    }

    @Test
    void create() {
        when(beanMappingService.mapTo(monsterCreateDTO, Monster.class)).thenReturn(monster1);
        when(monsterService.create(monster1)).thenReturn(monster1);
        Long createdId = monsterFacade.createMonster(monsterCreateDTO);
        verify(monsterService).create(monster1);
        Assert.assertEquals(createdId, monster1.getId());
    }

    @Test
    void updateName() {
        doNothing().when(monsterService).update(any());
        when(monsterService.findById(monster1.getId())).thenReturn(monster1);
        monsterFacade.changeName(monsterDTO1, "Monster 1");
        monster1.setName("Monster 1");
        verify(monsterService).update(monster1);
    }

    @Test
    void delete() {
        doNothing().when(monsterService).delete(any());
        when(monsterService.findById(monster1.getId())).thenReturn(monster1);
        monsterFacade.removeMonster(monsterDTO1.getId());
        verify(monsterService).delete(monster1);
    }

    @Test
    void findMonsterById() {
        when(beanMappingService.mapTo(monster1, MonsterDTO.class)).thenReturn(monsterDTO1);
        when(monsterService.findById(monster1.getId())).thenReturn(monster1);
        MonsterDTO tempMonsterDTO = monsterFacade.findById(monster1.getId());
        Assert.assertEquals(tempMonsterDTO, monsterDTO1);
    }

    @Test
    void findMonsterByName() {
        when(beanMappingService.mapTo(monster1, MonsterDTO.class)).thenReturn(monsterDTO1);
        when(monsterService.findByName(monster1.getName())).thenReturn(monster1);
        MonsterDTO tempMonsterDTO = monsterFacade.findByName(monster1.getName());
        Assert.assertEquals(tempMonsterDTO, monsterDTO1);
    }

    @Test
    void findAllMonsters() {
        List<Monster> monsters = new ArrayList<>();
        monsters.add(monster1);
        monsters.add(monster2);
        when(monsterService.findAll()).thenReturn(monsters);

        List<MonsterDTO> monstersDTO = new ArrayList<>();
        monstersDTO.add(monsterDTO1);
        monstersDTO.add(monsterDTO2);
        when(beanMappingService.mapTo(monsters, MonsterDTO.class)).thenReturn(monstersDTO);

        List<MonsterDTO> actualMonstersDTO = monsterFacade.findAll();
        verify(monsterService).findAll();
        Assert.assertEquals(actualMonstersDTO, monstersDTO);
    }

    @Test
    void findByMonsterType() {
        List<Monster> beasts = new ArrayList<>();
        beasts.add(monster1);
        when(monsterService.findByMonsterType(MonsterType.BEAST)).thenReturn(beasts);

        List<MonsterDTO> beastsDTO = new ArrayList<>();
        beastsDTO.add(monsterDTO1);
        when(beanMappingService.mapTo(beasts, MonsterDTO.class)).thenReturn(beastsDTO);

        List<MonsterDTO> actualMonstersDTO = monsterFacade.findByMonsterType(MonsterType.BEAST);
        verify(monsterService).findByMonsterType(MonsterType.BEAST);
        Assert.assertEquals(actualMonstersDTO, beastsDTO);
    }

    @Test
    void findBySize() {
        List<Monster> monsters = new ArrayList<>();
        monsters.add(monster1);
        monsters.add(DummyObjects.getMonsterDummy3());
        when(monsterService.findBySize(10)).thenReturn(monsters);

        List<MonsterDTO> monstersDTO = new ArrayList<>();
        monstersDTO.add(monsterDTO1);
        monstersDTO.add(DummyObjects.getMonsterDTODummy3());
        when(beanMappingService.mapTo(monsters, MonsterDTO.class)).thenReturn(monstersDTO);

        List<MonsterDTO> actualMonstersDTO = monsterFacade.findBySize(10);
        verify(monsterService).findBySize(10);
        Assert.assertEquals(actualMonstersDTO, monstersDTO);
    }



}
