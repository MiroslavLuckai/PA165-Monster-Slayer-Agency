package cz.muni.fi.pa165.msa.service;

import cz.muni.fi.pa165.monsterslayeragency.dao.HeroDao;
import cz.muni.fi.pa165.monsterslayeragency.entities.*;
import cz.muni.fi.pa165.monsterslayeragency.enums.*;
import cz.muni.fi.pa165.msa.dto.*;
import cz.muni.fi.pa165.msa.service.config.ServiceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

@ContextConfiguration(classes = ServiceConfiguration.class)
public class BeanMappingServiceImplTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private BeanMappingService beanMappingService;

    private Hero hero;
    private User user;
    private Job job;
    private Request request;
    private Monster monster;
    private HeroDTO heroDTO;
    private UserDTO userDTO;

    @BeforeMethod
    private void setUp() {
        user = new User();
        user.setId(1L);
        user.setEmail("pepe@gmail.com");
        user.setPassword("123456");
        user.setUserName("Pepe");
        user.setImage("/images/pepe.jpg");

        hero = new Hero();
        hero.setUser(user);
        hero.setName("Superpepe");
        hero.setImage("/images/super.jpg");
        hero.setSkills(new HashSet<>());
        hero.addSkill(Skill.ACROBATICS);
        hero.addSkill(Skill.ARMOR);

        monster = new Monster();
        monster.setFood(Food.ANIMALS);
        monster.setName("Dragon");
        monster.setImage("/images/dragon.jpg");
        monster.setMonsterType(MonsterType.DRACONID);
        monster.setSize(5);
        monster.setResistances(new HashSet<>());
        monster.addResistance(Resistance.FIRE);
        monster.addResistance(Resistance.DARK);

        request = new Request();
        request.setCustomer(user);
        request.setLocation("Venice");
        request.setAward(new BigDecimal(1));
        request.setId(1L);
        request.setMonsters(new ArrayList<>());

        job = new Job();
        job.setHeroes(new HashSet<>());
        job.addHero(hero);
        job.setStatus(JobStatus.DONE);
        job.setSeverity(JobSeverity.MODERATE);
        job.setRequest(request);
        job.setEvaluation(5);
        job.setId(2L);

        userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setEmail("pepe@gmail.com");
        userDTO.setUserName("Pepe");
        userDTO.setImage("/images/pepe.jpg");

        heroDTO = new HeroDTO();
        heroDTO.setId(1L);
        heroDTO.setUser(userDTO);
        heroDTO.setName("Superpepe");
        heroDTO.setImage("/images/super.jpg");
        heroDTO.setSkills(new HashSet<>());

    }

    @Test
    public void mapToUser() {
        UserDTO userDTO = beanMappingService.mapTo(user, UserDTO.class);
        assertEqualsUserDTOtoEntity(userDTO, user);
    }

    @Test
    public void mapFromCreateDTOTest() {
        RequestDTO dummy = DummyObjects.getRequestDTODummy1();
        RequestCreateDTO requestCreateDTO = new RequestCreateDTO();
        requestCreateDTO.setLocation(dummy.getLocation());
        requestCreateDTO.setMonsters(dummy.getMonsters());
        requestCreateDTO.setCustomer(dummy.getCustomer());
        requestCreateDTO.setAward(dummy.getAward());
        Request request = beanMappingService.mapTo(requestCreateDTO, Request.class);
        Request dummyRequest = DummyObjects.getRequestDummy1();
        dummyRequest.setId(null);
        dummyRequest.getCustomer().setPassword(null);
        Assert.assertEquals(request, dummyRequest);
    }

    @Test
    public void mapToHero() {
        HeroDTO heroDTO = beanMappingService.mapTo(hero, HeroDTO.class);
        assertEqualsHeroDTOtoEntity(heroDTO, hero);
    }

    @Test
    public void mapFromHeroDTOtoHero() {
        Hero hero = beanMappingService.mapTo(heroDTO, Hero.class);
        assertEqualsHeroDTOtoEntity(heroDTO, hero);
    }

    @Test
    public void mapToMonster() {
        MonsterDTO monsterDTO = beanMappingService.mapTo(monster, MonsterDTO.class);
        assertEqualsMonsterDTOtoEntity(monsterDTO, monster);
    }

    @Test
    public void mapToRequest() {
        RequestDTO requestDTO = beanMappingService.mapTo(request, RequestDTO.class);
        assertEqualsRequestDTOtoEntity(requestDTO, request);
    }


    @Test
    public void mapToJob() {
        JobDTO jobDTO = beanMappingService.mapTo(job, JobDTO.class);
        assertEqualsJobDTOtoEntity(jobDTO, job);
    }

    @Test
    public void mapToList() {
        User user2 = new User();
        user2.setId(5L);
        user2.setImage("test");
        user2.setUserName("testname");
        user2.setPassword("password");
        user2.setEmail("testmail");
        List<User> entityList = new ArrayList<>();
        entityList.add(user);
        entityList.add(user2);

        List<UserDTO> userDTOList = beanMappingService.mapTo(entityList, UserDTO.class);

        Iterator<UserDTO> dtoIterator = userDTOList.iterator();
        Iterator<User> userIterator = entityList.iterator();
        Assert.assertEquals(userDTOList.size(), entityList.size());
        while (userIterator.hasNext() && dtoIterator.hasNext()) {
            assertEqualsUserDTOtoEntity(dtoIterator.next(), userIterator.next());
        }
    }

    private void assertEqualsUserDTOtoEntity(UserDTO userDTO, User user) {
        Assert.assertEquals(userDTO.getEmail(), user.getEmail());
        Assert.assertEquals(userDTO.getImage(), user.getImage());
        Assert.assertEquals(userDTO.getUserName(), user.getUserName());
        Assert.assertEquals(userDTO.getId(), user.getId());
    }

    private void assertEqualsHeroDTOtoEntity(HeroDTO heroDTO, Hero hero) {
        Assert.assertEquals(heroDTO.getSkills(), hero.getSkills());
        Assert.assertEquals(heroDTO.getImage(), hero.getImage());
        Assert.assertEquals(heroDTO.getName(), hero.getName());
        Assert.assertEquals(heroDTO.getId(), hero.getId());
        assertEqualsUserDTOtoEntity(heroDTO.getUser(), hero.getUser());
    }

    private void assertEqualsMonsterDTOtoEntity(MonsterDTO monsterDTO, Monster monster) {
        Assert.assertEquals(monsterDTO.getResistances(), monster.getResistances());
        Assert.assertEquals(monsterDTO.getFood(), monster.getFood());
        Assert.assertEquals(monsterDTO.getImage(), monster.getImage());
        Assert.assertEquals(monsterDTO.getMonsterType(), monster.getMonsterType());
        Assert.assertEquals(monsterDTO.getName(), monster.getName());
        Assert.assertEquals(monsterDTO.getId(), monster.getId());
    }

    private void assertEqualsRequestDTOtoEntity(RequestDTO requestDTO, Request request) {
        Assert.assertEquals(requestDTO.getAward(), request.getAward());
        Assert.assertEquals(requestDTO.getLocation(), request.getLocation());
        Assert.assertEquals(requestDTO.getId(), request.getId());
        assertEqualsUserDTOtoEntity(requestDTO.getCustomer(), request.getCustomer());

        Iterator<MonsterDTO> dtoIterator = requestDTO.getMonsters().iterator();
        Iterator<Monster> monsterIterator = request.getMonsters().iterator();
        Assert.assertEquals(requestDTO.getMonsters().size(), request.getMonsters().size());
        while (monsterIterator.hasNext() && dtoIterator.hasNext()) {
            assertEqualsMonsterDTOtoEntity(dtoIterator.next(), monsterIterator.next());
        }
    }



    private void assertEqualsJobDTOtoEntity(JobDTO jobDTO, Job job) {
        Assert.assertEquals(jobDTO.getStatus(), job.getStatus());
        Assert.assertEquals(jobDTO.getEvaluation(), job.getEvaluation());
        Assert.assertEquals(jobDTO.getId(), job.getId());
        Assert.assertEquals(jobDTO.getSeverity(), job.getSeverity());
        assertEqualsRequestDTOtoEntity(jobDTO.getRequest(), job.getRequest());

        Iterator<HeroDTO> dtoIterator = jobDTO.getHeroes().iterator();
        Iterator<Hero> heroIterator = job.getHeroes().iterator();
        Assert.assertEquals(jobDTO.getHeroes().size(), job.getHeroes().size());
        while (heroIterator.hasNext() && dtoIterator.hasNext()) {
            assertEqualsHeroDTOtoEntity(dtoIterator.next(), heroIterator.next());
        }
    }

}