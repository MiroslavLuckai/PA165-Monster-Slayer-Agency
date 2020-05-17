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
     * @param jobDto job to be created
     * @return id of the new job
     * @throws IllegalArgumentException when jobDto is null
     */
    JobDTO createJob(JobCreateDTO jobDto) throws IllegalArgumentException;

    /**
     * Create job with request which is already stored in database
     * @param jobDTO job to be created
     * @param requestId request which should created job be linked with
     * @return
     */
    JobDTO createJobFromRequest(JobCreateDTO jobDTO, Long requestId);

    /**
     * Updates job.
     * @param jobDto job to be updated
     * @throws IllegalArgumentException when jobDto is null
     */
    void updateJob(JobDTO jobDto) throws IllegalArgumentException;

    /**
     * Removes job.
     * @param id if of a job to be removed
     * @throws IllegalArgumentException when id is null
     */
    void removeJob(Long id) throws IllegalArgumentException;

    /**
     * Find job by id.
     * @param id id of a job to be found
     * @return job with selected id
     * @throws IllegalArgumentException when id is null
     */
    JobDTO findById(Long id) throws IllegalArgumentException;

    /**
     * Finds all jobs.
     * @return list of all jobs.
     */
    List<JobDTO> findAll();

    /**
     * Adds hero to a job.
     * @param jobDto job to add hero to
     * @param heroDto hero to be added to the job
     * @throws IllegalArgumentException when jobDto or heroDto is null
     */
    void addHero(JobDTO jobDto, HeroDTO heroDto) throws IllegalArgumentException;

    /**
     * Removes hero from a job.
     * @param jobDto job to remove hero from
     * @param heroDto hero to be removed from the job.
     * @throws IllegalArgumentException when jobDto or heroDto is null
     */
    void removeHero(JobDTO jobDto, HeroDTO heroDto) throws IllegalArgumentException;

    /**
     * Changes evaluation of a job.
     * @param jobDto job to change evaluation
     * @param evaluation evaluation to be set to the job
     */
    void changeEvaluation(JobDTO jobDto, int evaluation);

    /**
     * Changes job status.
     * @param jobDto job to change job status
     * @param status status to be set to the job.
     */
    void changeJobStatus(JobDTO jobDto, JobStatus status);

    /**
     * Changes job severity.
     * @param jobDto job to change job severity
     * @param severity severity to be set to the job.
     */
    void changeJobSeverity(JobDTO jobDto, JobSeverity severity);

    List<JobDTO> findJobsBySeverity(JobSeverity severity);

    List<JobDTO> findJobsByStatus(JobStatus status);

    List<JobDTO> findHeroJobs(Long id);
}
