package cz.muni.fi.pa165.monsterslayeragency.dao;

import cz.muni.fi.pa165.monsterslayeragency.entities.Job;

import java.util.List;

/**
 * @author Michaela Bajanova (469166)
 */
public interface JobDao {

    /***
     * Adds job to database.
     * @param job job to be added
     * @throws IllegalArgumentException when job is null
     */
    void addJob(Job job) throws IllegalArgumentException;

    /***
     * Updates job in database.
     * @param job job to be updated
     * @throws IllegalArgumentException when job is null
     */
    void updateJob(Job job) throws IllegalArgumentException;

    /***
     * Removes job from database.
     * @param job job to be removed
     * @throws IllegalArgumentException when job is null
     */
    void removeJob(Job job) throws IllegalArgumentException;

    /***
     * Finds job by id in database.
     * @param id id of job to be found
     * @return job entity found in database with id of jobId
     * @throws IllegalArgumentException when jobId is null
     */
    Job findJobById(Long id) throws IllegalArgumentException;

    /***
     * Lists all job entities in database.
     * @return List of all job entities in database.
     */
    List<Job> getAllJobs();
}
