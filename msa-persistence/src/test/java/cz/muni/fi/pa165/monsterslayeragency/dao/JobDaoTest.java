package cz.muni.fi.pa165.monsterslayeragency.dao;

import cz.muni.fi.pa165.monsterslayeragency.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.monsterslayeragency.entities.*;
import cz.muni.fi.pa165.monsterslayeragency.enums.Severity;
import cz.muni.fi.pa165.monsterslayeragency.enums.JobStatus;
import cz.muni.fi.pa165.monsterslayeragency.enums.MonsterType;
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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ludovit Kopcsanyi
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class JobDaoTest extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    JobDao jobDao;

    private Job job;
    private Request request;
    private Monster monster;
    private User user;
    private Hero hero;

    @BeforeMethod
    public void setUp() {
        monster = new Monster();
        monster.setName("Ghoul");
        monster.setSize(10);
        monster.setMonsterType(MonsterType.NECROPHAGE);

        request = new Request();
        request.setLocation("Velen");
        request.setAward(new BigDecimal(100));
        request.setMonsters(new ArrayList<>());
        request.getMonsters().add(monster);

        user = new User();
        user.setEmail("default@mail.com");
        user.setImage("test_image");
        user.setPassword("default");
        user.setUserName("Geralt");

        hero = new Hero();
        hero.setName("Witcher");
        hero.setUser(user);
        hero.setImage("test");

        entityManager.persist(user);
        entityManager.persist(hero);
        entityManager.persist(monster);
        entityManager.persist(request);

        job = new Job();
        job.setHeroes(new HashSet<>());
        job.getHeroes().add(hero);
        job.setEvaluation(5);
        job.setRequest(request);
        job.setSeverity(Severity.MODERATE);
        job.setStatus(JobStatus.DONE);
    }

    @Test
    public void addJobTest() {
        jobDao.addJob(job);

        List<Job> resultList = entityManager.createQuery("select j from Job j", Job.class).getResultList();
        assertThat(resultList).containsExactlyInAnyOrder(job);
    }

    @Test
    public void testFindJobById() {
        entityManager.persist(job);
        Job found = jobDao.findJobById(job.getId());
        Assert.assertEquals(job, found);
    }

    @Test
    public void testFindAll() {
        entityManager.persist(job);
        Job job2 = new Job();
        entityManager.persist(job2);
        List<Job> found = jobDao.findAll();
        assertThat(found).containsExactlyInAnyOrder(job, job2);    }

    @Test
    public void testUpdate() {
        entityManager.persist(job);
        Job found = jobDao.findJobById(job.getId());
        found.setStatus(JobStatus.DONE);
        jobDao.updateJob(found);
        found = jobDao.findJobById(job.getId());
        Assert.assertEquals(job.getStatus(), found.getStatus());
    }

    @Test
    public void testRemove() {
        entityManager.persist(job);
        jobDao.removeJob(job);
        Assert.assertNull(jobDao.findJobById(job.getId()));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void addNullJob() {
        jobDao.addJob(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void updateNullJob() {
        jobDao.updateJob(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void removeNullJob() {
        jobDao.removeJob(null);
    }

}