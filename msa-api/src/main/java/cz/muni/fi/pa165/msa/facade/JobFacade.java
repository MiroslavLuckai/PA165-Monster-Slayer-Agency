package cz.muni.fi.pa165.msa.facade;

import cz.muni.fi.pa165.monsterslayeragency.enums.JobSeverity;
import cz.muni.fi.pa165.monsterslayeragency.enums.JobStatus;
import cz.muni.fi.pa165.msa.dto.HeroDTO;
import cz.muni.fi.pa165.msa.dto.JobCreateDTO;
import cz.muni.fi.pa165.msa.dto.JobDTO;

import java.util.List;

/**
 * @author Michaela Bajanova (469166)
 */
public interface JobFacade {

    /**
     * Creates job.
     * @param job job to be created
     * @return id of the new job
     */
    Long createJob(JobCreateDTO job);

    /**
     * Updates job.
     * @param job job to be updated
     */
    void updateJob(JobDTO job);

    /**
     * Removes job.
     * @param id if of a job to be removed
     */
    void removeJob(Long id);

    /**
     * Find job by id.
     * @param id id of a job to be found
     * @return job with selected id
     */
    JobDTO findById(Long id);

    /**
     * Finds all jobs.
     * @return list of all jobs.
     */
    List<JobDTO> findAll();

    /**
     * Adds hero to a job.
     * @param hero hero to be added to the job
     */
    void addHero(HeroDTO hero);

    /**
     * Removes hero from a job.
     * @param hero hero to be removed from the job.
     */
    void removeHero(HeroDTO hero);

    /**
     * Changes evaluation of a job.
     * @param evaluation evaluation to be set to the job
     */
    void changeEvaluation(int evaluation);

    /**
     * Changes job status.
     * @param status status to be set to the job.
     */
    void changeJobStatus(JobStatus status);

    /**
     * Changes job severity.
     * @param severity severity to be set to the job.
     */
    void changeJobSeverity(JobSeverity severity);
}
