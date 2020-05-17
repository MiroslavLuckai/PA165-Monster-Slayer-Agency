package cz.muni.fi.pa165.msa.service;

import cz.muni.fi.pa165.monsterslayeragency.entities.Hero;
import cz.muni.fi.pa165.monsterslayeragency.entities.Job;
import cz.muni.fi.pa165.monsterslayeragency.enums.Severity;
import cz.muni.fi.pa165.monsterslayeragency.enums.JobStatus;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Michaela Bajanova (469166)
 */
@Service
public interface JobService {

    Job createJob(Job job) throws IllegalArgumentException;

    void updateJob(Job job) throws IllegalArgumentException;

    void deleteJob(Job job) throws IllegalArgumentException;

    Job findById(Long id) throws IllegalArgumentException;

    List<Job> findAll();

    /**
     * List all jobs with requested severity
     * @param severity severity of the jobs we are looking for
     * @return List of jobs with requested severity
     */
    List<Job> findJobsBySeverity(Severity severity);

    /**
     * List all jobs with requested status
     * @param status status of the jobs we are looking for
     * @return List of jobs with requested status
     */
    List<Job> findJobsByStatus(JobStatus status);

    /**
     * Find all jobs in which are assigned to hero
     * @param hero hero whose assigned jobs we are looking for
     * @return List of jobs assigned to hero
     */
    List<Job> findHeroJobs(Hero hero);
}
