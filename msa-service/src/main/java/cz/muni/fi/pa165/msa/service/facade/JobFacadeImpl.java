package cz.muni.fi.pa165.msa.service.facade;

import cz.muni.fi.pa165.monsterslayeragency.entities.Hero;
import cz.muni.fi.pa165.monsterslayeragency.entities.Job;
import cz.muni.fi.pa165.monsterslayeragency.enums.JobSeverity;
import cz.muni.fi.pa165.monsterslayeragency.enums.JobStatus;
import cz.muni.fi.pa165.msa.dto.HeroDTO;
import cz.muni.fi.pa165.msa.dto.JobCreateDTO;
import cz.muni.fi.pa165.msa.dto.JobDTO;
import cz.muni.fi.pa165.msa.facade.JobFacade;
import cz.muni.fi.pa165.msa.service.BeanMappingService;
import cz.muni.fi.pa165.msa.service.HeroService;
import cz.muni.fi.pa165.msa.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Michaela Bajanova (469166)
 */
@Service
@Transactional
public class JobFacadeImpl implements JobFacade {

    @Autowired
    private JobService jobService;

    @Autowired
    private HeroService heroService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public Long createJob(JobCreateDTO jobDto) {
        validate(jobDto, "Job DTO cannot be null.");
        Job mappedJob = beanMappingService.mapTo(jobDto, Job.class);
        Job newJob = jobService.createJob(mappedJob);
        return newJob.getId();
    }

    @Override
    public void updateJob(JobDTO jobDto) {
        validate(jobDto, "Job DTO cannot be null.");
        Job job = beanMappingService.mapTo(jobDto, Job.class);
        jobService.updateJob(job);
    }

    @Override
    public void removeJob(Long id) {
        validate(id, "Id cannot be null");
        Job job = jobService.findById(id);
        jobService.deleteJob(job);
    }

    @Override
    public JobDTO findById(Long id) {
        validate(id, "Id cannot be null");
        Job job = jobService.findById(id);
        return (job == null) ? null : beanMappingService.mapTo(job, JobDTO.class);
    }

    @Override
    public List<JobDTO> findAll() {
        return beanMappingService.mapTo(jobService.findAll(), JobDTO.class);
    }

    @Override
    public void addHero(JobDTO jobDto, HeroDTO heroDto) {
        validate(jobDto, "Job DTO cannot be null.");
        validate(heroDto, "Hero DTO cannot be null.");

        Job job = jobService.findById(jobDto.getId());
        Hero hero = heroService.findHeroById(heroDto.getId());
        Set<Hero> heroes = job.getHeroes();

        if (!heroes.contains(hero)) {
            Set<Hero> newHeroes = new HashSet<>(heroes);
            newHeroes.add(hero);
            job.setHeroes(newHeroes);
            jobService.updateJob(job);
        }
    }

    @Override
    public void removeHero(JobDTO jobDto, HeroDTO heroDto) {
        validate(jobDto, "Job DTO cannot be null.");
        validate(heroDto, "Hero DTO cannot be null.");

        Job job = jobService.findById(jobDto.getId());
        Hero hero = heroService.findHeroById(heroDto.getId());
        Set<Hero> heroes = job.getHeroes();

        if (heroes.contains(hero)) {
            Set<Hero> newHeroes = new HashSet<>(heroes);
            newHeroes.remove(hero);
            job.setHeroes(newHeroes);
            jobService.updateJob(job);
        }
    }

    @Override
    public void changeEvaluation(JobDTO jobDto, int evaluation) {
        Job job = jobService.findById(jobDto.getId());
        job.setEvaluation(evaluation);
        jobService.updateJob(job);
    }

    @Override
    public void changeJobStatus(JobDTO jobDto, JobStatus status) {
        Job job = jobService.findById(jobDto.getId());
        job.setStatus(status);
        jobService.updateJob(job);
    }

    @Override
    public void changeJobSeverity(JobDTO jobDto, JobSeverity severity) {
        Job job = jobService.findById(jobDto.getId());
        job.setSeverity(severity);
        jobService.updateJob(job);
    }

    private void validate(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
