package cz.muni.fi.pa165.msa.service;

import cz.muni.fi.pa165.monsterslayeragency.dao.JobDao;
import cz.muni.fi.pa165.monsterslayeragency.entities.Hero;
import cz.muni.fi.pa165.monsterslayeragency.entities.Job;
import cz.muni.fi.pa165.monsterslayeragency.entities.User;
import cz.muni.fi.pa165.monsterslayeragency.enums.JobSeverity;
import cz.muni.fi.pa165.monsterslayeragency.enums.JobStatus;
import cz.muni.fi.pa165.msa.service.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Michaela Bajanova (469166)
 */
@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobDao jobDao;
    private String JOB_IS_NULL_MESSAGE = "Job cannot be null.";

    @Override
    public Job createJob(Job job) throws IllegalArgumentException {
        Validator.validate(job, JOB_IS_NULL_MESSAGE);
        jobDao.addJob(job);
        return job;
    }

    @Override
    public void updateJob(Job job) throws IllegalArgumentException {
        Validator.validate(job, JOB_IS_NULL_MESSAGE);
        jobDao.updateJob(job);
    }

    @Override
    public void deleteJob(Job job) throws IllegalArgumentException {
        Validator.validate(job, JOB_IS_NULL_MESSAGE);
        jobDao.removeJob(job);
    }

    @Override
    public Job findById(Long id) throws IllegalArgumentException {
        Validator.validate(id, "Id cannot be null.");
        return jobDao.findJobById(id);
    }

    @Override
    public List<Job> findAll() {
        return jobDao.findAll();
    }

    @Override
    public List<Job> findJobsBySeverity(JobSeverity severity) {
        Validator.validate(severity, "Cannot search with null severity!");
        return jobDao.findJobsBySeverity(severity);
    }

    @Override
    public List<Job> findJobsByStatus(JobStatus status) {
        Validator.validate(status, "Cannot search with null status!");
        return jobDao.findJobsByStatus(status);
    }

    @Override
    public List<Job> findHeroJobs(Hero hero) {
        List<Job> jobs = jobDao.findAll();
        return jobs.stream()
                .filter(job -> job.getHeroes().contains(hero))
                .collect(Collectors.toList());
    }
}
