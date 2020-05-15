package cz.muni.fi.pa165.msa.rest.controllers;

import cz.muni.fi.pa165.msa.dto.JobDTO;
import cz.muni.fi.pa165.msa.dto.RequestDTO;
import cz.muni.fi.pa165.msa.facade.JobFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
