package cz.muni.fi.pa165.monsterslayeragency.dao;

import cz.muni.fi.pa165.monsterslayeragency.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.monsterslayeragency.entities.Job;
import cz.muni.fi.pa165.monsterslayeragency.enums.JobStatus;
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
import java.util.List;

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

    @BeforeMethod
    public void setUp() {
        job = new Job();
        jobDao.addJob(job);
    }

    @Test
    public void testFindJobById() {
        Job found = jobDao.findJobById(job.getId());
        Assert.assertEquals(job.getId(), found.getId());
    }

    @Test
    public void testFindAll() {
        Job job2 = new Job();
        jobDao.addJob(job2);
        List<Job> found = jobDao.getAllJobs();
        Assert.assertEquals(found.size(), 2);
    }

    @Test
    public void testUpdate() {
        Job found = jobDao.findJobById(job.getId());
        found.setStatus(JobStatus.DONE);
        jobDao.updateJob(found);
        found = jobDao.findJobById(job.getId());
        Assert.assertEquals(job.getStatus(), found.getStatus());
    }

    @Test
    public void testRemove() {
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