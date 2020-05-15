package cz.muni.fi.pa165.msa.rest.controllers;

import cz.muni.fi.pa165.msa.dto.JobCreateDTO;
import cz.muni.fi.pa165.msa.dto.JobDTO;
import cz.muni.fi.pa165.msa.dto.RequestDTO;
import cz.muni.fi.pa165.msa.facade.JobFacade;
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

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<JobDTO> getJobs() {

        logger.debug("Get all jobs");
        return jobFacade.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final JobDTO getJobById(@PathVariable("id") Long id) {

        logger.debug("Find jobs({})", id);
        return jobFacade.findById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteJob(@PathVariable("id") Long id) {
        logger.debug("Delete job with id: {}", id);
        jobFacade.removeJob(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final JobDTO updateJob(@PathVariable("id") Long id, @RequestBody JobDTO job) {
        logger.debug("Update job with id: {}", id);
        jobFacade.updateJob(job);
        return jobFacade.findById(id);
    }


    @RequestMapping(value = "/create/request/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final JobDTO createJob(@PathVariable("id") Long id, @RequestBody JobCreateDTO job) {
        logger.debug("Create job");
        return jobFacade.createJobFromRequest(job, id);
    }
}
