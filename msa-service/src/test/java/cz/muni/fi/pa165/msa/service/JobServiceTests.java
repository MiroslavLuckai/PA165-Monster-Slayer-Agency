package cz.muni.fi.pa165.msa.service;

import cz.muni.fi.pa165.monsterslayeragency.dao.JobDao;
import cz.muni.fi.pa165.monsterslayeragency.entities.Job;
import cz.muni.fi.pa165.msa.service.config.ServiceConfiguration;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * @author Michaela Bajanova (469166)
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class JobServiceTests extends AbstractTestNGSpringContextTests {

    @Mock
    private JobDao jobDao;

    @Autowired
    @InjectMocks
    private JobService jobService;

    private Job job;

    @BeforeClass
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void setUp() {
        job = DummyObjects.getJobDummy1();
    }

    @AfterMethod
    public void tearDown() {
        Mockito.reset(jobDao);
    }

    @Test
    public void testCreateJob() {
        Job createdJob = jobService.createJob(job);
        verify(jobDao, times(1)).addJob(job);
        assertThat(createdJob).isEqualTo(job);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateJobWithNull() {
        jobService.createJob(null);
        verify(jobDao, times(0)).addJob(null);
    }

    @Test
    public void testUpdateJob() {
        jobService.updateJob(job);
        verify(jobDao, times(1)).updateJob(job);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testUpdateJobWithNull() {
        jobService.updateJob(null);
        verify(jobDao, times(0)).updateJob(null);
    }

    @Test
    public void testDeleteJob() {
        jobService.deleteJob(job);
        verify(jobDao, times(1)).removeJob(job);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDeleteJobWithNull() {
        jobService.deleteJob(null);
        verify(jobDao, times(0)).removeJob(null);
    }

    @Test
    public void testFindById() {
        job.setId(1L);
        when(jobDao.findJobById(job.getId())).thenReturn(job);

        Job result = jobService.findById(job.getId());
        verify(jobDao, times(1)).findJobById(job.getId());
        assertThat(result).isEqualTo(job);
    }

    @Test
    public void testFindAll() {
        List<Job> jobs = new ArrayList<>();
        jobs.add(job);
        jobs.add(DummyObjects.getJobDummy2());
        when(jobDao.findAll()).thenReturn(jobs);

        List<Job> result = jobService.findAll();
        verify(jobDao, times(1)).findAll();
        assertThat(result).isEqualTo(jobs);
    }
}
