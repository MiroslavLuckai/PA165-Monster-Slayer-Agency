package cz.muni.fi.pa165.msa.service.facade;

import cz.muni.fi.pa165.monsterslayeragency.entities.Hero;
import cz.muni.fi.pa165.monsterslayeragency.entities.Job;
import cz.muni.fi.pa165.monsterslayeragency.enums.Severity;
import cz.muni.fi.pa165.monsterslayeragency.enums.JobStatus;
import cz.muni.fi.pa165.msa.dto.HeroDTO;
import cz.muni.fi.pa165.msa.dto.JobCreateDTO;
import cz.muni.fi.pa165.msa.dto.JobDTO;
import cz.muni.fi.pa165.msa.service.BeanMappingService;
import cz.muni.fi.pa165.msa.service.DummyObjects;
import cz.muni.fi.pa165.msa.service.HeroService;
import cz.muni.fi.pa165.msa.service.JobService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * @author Michaela Bajanova (469166)
 */
public class JobFacadeTest {

    @Mock
    private JobService jobService;

    @Mock
    private HeroService heroService;

    @Mock
    private BeanMappingService beanMappingService;

    @Autowired
    @InjectMocks
    private JobFacadeImpl jobFacade;

    private Job job;
    private JobCreateDTO jobCreateDTO;
    private JobDTO jobDTO;

    private Hero hero;

    @BeforeClass
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void setUp() {
        job = DummyObjects.getJobDummy1();
        jobCreateDTO = DummyObjects.getJobCreateDTODummy();
        jobDTO = DummyObjects.getJobDTODummy1();
        hero = DummyObjects.getHeroDummy1();
    }

    @AfterMethod
    public void tearDown() {
        Mockito.reset(jobService);
        Mockito.reset(heroService);
        Mockito.reset(beanMappingService);
    }

    @Test
    public void testCreateJob() {
        when(jobService.createJob(job)).thenReturn(job);
        when(beanMappingService.mapTo(jobCreateDTO, Job.class)).thenReturn(job);
        when(beanMappingService.mapTo(job, JobDTO.class)).thenReturn(jobDTO);

        JobDTO createdJob = jobFacade.createJob(jobCreateDTO);

        verify(jobService, times(1)).createJob(job);
        assertThat(createdJob.getId()).isEqualTo(job.getId());
    }

    @Test
    public void testUpdateJob() {
        doNothing().when(jobService).updateJob(any());
        when(beanMappingService.mapTo(jobDTO, Job.class)).thenReturn(job);

        jobFacade.updateJob(jobDTO);

        verify(jobService, times(1)).updateJob(job);
    }

    @Test
    public void testRemoveJob() {
        jobDTO.setId(1L);
        job.setId(1L);

        doNothing().when(jobService).deleteJob(any());
        when(beanMappingService.mapTo(jobDTO, Job.class)).thenReturn(job);
        when(jobService.findById(job.getId())).thenReturn(job);

        jobFacade.removeJob(jobDTO.getId());

        verify(jobService, times(1)).deleteJob(job);
    }

    @Test
    public void testFindById() {
        jobDTO.setId(1L);
        job.setId(1L);

        when(beanMappingService.mapTo(job, JobDTO.class)).thenReturn(jobDTO);
        when(jobService.findById(job.getId())).thenReturn(job);

        JobDTO result = jobFacade.findById(job.getId());

        verify(jobService, times(1)).findById(job.getId());
        assertThat(result).isEqualTo(jobDTO);
    }

    @Test
    public void testFindAll() {
        List<Job> jobs = new ArrayList<>();
        jobs.add(job);
        jobs.add(DummyObjects.getJobDummy2());

        List<JobDTO> jobsDto = new ArrayList<>();
        jobsDto.add(jobDTO);
        jobsDto.add(DummyObjects.getJobDTODummy2());

        when(jobService.findAll()).thenReturn(jobs);
        when(beanMappingService.mapTo(jobs, JobDTO.class)).thenReturn(jobsDto);

        List<JobDTO> result = jobFacade.findAll();

        verify(jobService, times(1)).findAll();
        assertThat(result).contains(jobDTO);
        assertThat(result).contains(DummyObjects.getJobDTODummy2());
    }

    @Test
    public void testAddHero() {
        Set<Hero> heroes = new HashSet<>();
        heroes.add(DummyObjects.getHeroDummy2());
        job.setId(1L);
        job.setHeroes(heroes);
        hero.setId(2L);

        Set<HeroDTO> heroesDto = new HashSet<>();
        heroesDto.add(DummyObjects.getHeroDTODummy2());
        jobDTO.setId(1L);
        jobDTO.setHeroes(heroesDto);
        HeroDTO heroDTO = DummyObjects.getHeroDTODummy1();
        heroDTO.setId(2L);

        when(jobService.findById(job.getId())).thenReturn(job);
        when(heroService.findHeroById(hero.getId())).thenReturn(hero);

        jobFacade.addHero(jobDTO, heroDTO);

        verify(jobService, times(1)).updateJob(job);
    }

    @Test
    public void testRemoveHero() {
        hero.setId(2L);
        Set<Hero> heroes = new HashSet<>();
        heroes.add(hero);
        heroes.add(DummyObjects.getHeroDummy2());
        job.setId(1L);
        job.setHeroes(heroes);

        HeroDTO heroDTO = DummyObjects.getHeroDTODummy1();
        heroDTO.setId(2L);
        Set<HeroDTO> heroesDto = new HashSet<>();
        heroesDto.add(heroDTO);
        heroesDto.add(DummyObjects.getHeroDTODummy2());
        jobDTO.setId(1L);
        jobDTO.setHeroes(heroesDto);

        when(jobService.findById(job.getId())).thenReturn(job);
        when(heroService.findHeroById(hero.getId())).thenReturn(hero);

        jobFacade.removeHero(jobDTO, heroDTO);

        verify(jobService, times(1)).updateJob(job);
    }

    @Test
    public void testChangeEvaluation() {
        job.setId(1L);
        jobDTO.setId(1L);

        when(jobService.findById(job.getId())).thenReturn(job);

        jobFacade.changeEvaluation(jobDTO, 10);

        verify(jobService, times(1)).updateJob(job);
    }

    @Test
    public void testChangeJobStatus() {
        job.setId(1L);
        jobDTO.setId(1L);

        when(jobService.findById(job.getId())).thenReturn(job);

        jobFacade.changeJobStatus(jobDTO, JobStatus.NOT_ASSIGNED);

        verify(jobService, times(1)).updateJob(job);
    }

    @Test
    public void testChangeJobSeverity() {
        job.setId(1L);
        jobDTO.setId(1L);

        when(jobService.findById(job.getId())).thenReturn(job);

        jobFacade.changeJobSeverity(jobDTO, Severity.CRITICAL);

        verify(jobService, times(1)).updateJob(job);
    }
}
