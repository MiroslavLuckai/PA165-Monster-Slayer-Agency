package cz.muni.fi.pa165.msa.rest.controllers;

import cz.muni.fi.pa165.monsterslayeragency.enums.Severity;
import cz.muni.fi.pa165.monsterslayeragency.enums.JobStatus;
import cz.muni.fi.pa165.msa.dto.JobCreateDTO;
import cz.muni.fi.pa165.msa.dto.JobDTO;
import cz.muni.fi.pa165.msa.facade.JobFacade;
import cz.muni.fi.pa165.msa.rest.exceptions.ResourceAlreadyExistsException;
import cz.muni.fi.pa165.msa.rest.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @author Michaela Bajanova (469166)
 */
@RestController
@RequestMapping(value = "/jobs")
public class JobController {

    final static Logger logger = LoggerFactory.getLogger(JobController.class);

    @Autowired
    private JobFacade jobFacade;

    /**
     * Get all jobs stored in database
     * curl -i -X GET http://localhost:8080/pa165/rest/jobs
     * @return List of jobs stored in database
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<JobDTO> getJobs() {

        logger.debug("Get all jobs");
        return jobFacade.findAll();
    }

    /**
     * Get Job by its id
     * curl -i -X GET http://localhost:8080/pa165/rest/jobs/{id}
     * @param id id of the job we are looking for
     * @return Job with requested id, in case there is no job with this id, exception is thrown
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final JobDTO getJobById(@PathVariable("id") Long id) {

        logger.debug("Find jobs({})", id);
        JobDTO job = jobFacade.findById(id);
        if (job == null) {
            throw new ResourceNotFoundException();
        }
        return job;
    }

    /**
     * Get list of Jobs with requested status
     * curl -i -X GET http://localhost:8080/pa165/rest/jobs/status/{status}
     * @param status status of the jobs we are looking for
     * @return List of jobs
     */
    @RequestMapping(value = "/status/{status}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<JobDTO> getJobsByStatus(@PathVariable JobStatus status) {
        logger.debug("Find jobs by status: {}", status);
        return jobFacade.findJobsByStatus(status);
    }

    /**
     * Get list of Jobs with requested severity
     * curl -i -X GET http://localhost:8080/pa165/rest/jobs/severity/{severity}
     * @param severity severity of the jobs we are looking for
     * @return List of jobs
     */
    @RequestMapping(value = "/severity/{severity}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<JobDTO> getJobsBySeverity(@PathVariable Severity severity) {
        logger.debug("Find jobs by severity: {}", severity);
        return jobFacade.findJobsBySeverity(severity);
    }

    /**
     * Get list of jobs currently assigned to hero
     * curl -i -X GET http://localhost:8080/pa165/rest/jobs/hero/{heroId}
     * @param id id of the hero whose jobs we are looking for
     * @return List of jobs
     */
    @RequestMapping(value = "/hero/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<JobDTO> getHeroJobs(@PathVariable Long id) {
        logger.debug("Find jobs by hero id: {}", id);
        return jobFacade.findHeroJobs(id);
    }

    /**
     * Remove job with requested id from database
     * @param id id of the job we want to remove
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteJob(@PathVariable("id") Long id) {
        try {
            logger.debug("Delete job with id: {}", id);
            jobFacade.removeJob(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }

    /**
     * Update job in a database
     * @param id id of the job we want to update
     * @param job new version of the job instance
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final JobDTO updateJob(@PathVariable("id") Long id, @RequestBody JobDTO job) {
        logger.debug("Update job with id: {}", id);
        jobFacade.updateJob(job);
        return jobFacade.findById(id);
    }

    /**
     * Create new Job in database
     * @param id id of the request associated with a job
     * @param job job we want to create
     * @return newly created Job
     */
    @RequestMapping(value = "/create/request/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final JobDTO createJob(@PathVariable("id") Long id, @RequestBody JobCreateDTO job) {
        try {
            logger.debug("Create job");
            return jobFacade.createJobFromRequest(job, id);
        } catch (Exception ex) {
            throw new ResourceAlreadyExistsException();
        }
    }
}
