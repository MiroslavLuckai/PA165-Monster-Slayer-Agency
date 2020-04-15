package cz.muni.fi.pa165.msa.facade;

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

//    void addHero(HeroDTO hero);
//    void removeHero(HeroDTO hero);
//    void changeEvaluation(int evaluation);
//    void changeJobStatus(JobStatus status);
//    void changeJobSeverity(JobSeverity severity);
}
