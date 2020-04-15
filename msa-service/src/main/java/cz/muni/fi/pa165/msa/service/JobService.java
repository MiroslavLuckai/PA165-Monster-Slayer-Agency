package cz.muni.fi.pa165.msa.service;

import cz.muni.fi.pa165.monsterslayeragency.entities.Job;

import java.util.List;

/**
 * @author Michaela Bajanova (469166)
 */
public interface JobService {

    Job createJob(Job job) throws IllegalArgumentException;

    void updateJob(Job job) throws IllegalArgumentException;

    void deleteJob(Job job) throws IllegalArgumentException;

    Job findById(Long id) throws IllegalArgumentException;

    List<Job> findAll();
}
