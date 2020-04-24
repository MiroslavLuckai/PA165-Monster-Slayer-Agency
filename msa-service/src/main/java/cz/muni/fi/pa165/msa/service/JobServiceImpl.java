package cz.muni.fi.pa165.msa.service;

import cz.muni.fi.pa165.monsterslayeragency.dao.JobDao;
import cz.muni.fi.pa165.monsterslayeragency.entities.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        validate(job, JOB_IS_NULL_MESSAGE);
        jobDao.addJob(job);
        return job;
    }

    @Override
    public void updateJob(Job job) throws IllegalArgumentException {
        validate(job, JOB_IS_NULL_MESSAGE);
        jobDao.updateJob(job);
    }

    @Override
    public void deleteJob(Job job) throws IllegalArgumentException {
        validate(job, JOB_IS_NULL_MESSAGE);
        jobDao.removeJob(job);
    }

    @Override
    public Job findById(Long id) throws IllegalArgumentException {
        validate(id, "Id cannot be null.");
        return jobDao.findJobById(id);
    }

    @Override
    public List<Job> findAll() {
        return jobDao.findAll();
    }

    private void validate(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
