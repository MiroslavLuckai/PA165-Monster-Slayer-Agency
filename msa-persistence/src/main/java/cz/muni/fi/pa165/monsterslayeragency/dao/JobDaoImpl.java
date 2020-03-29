package cz.muni.fi.pa165.monsterslayeragency.dao;

import cz.muni.fi.pa165.monsterslayeragency.entities.Job;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Michaela Bajanova (469166)
 */
@Repository
public class JobDaoImpl implements JobDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addJob(Job job) throws IllegalArgumentException {
        validate(job);
        em.persist(job);
    }

    @Override
    public void updateJob(Job job) throws IllegalArgumentException {
        validate(job);
        em.merge(job);
    }

    @Override
    public void removeJob(Job job) throws IllegalArgumentException {
        validate(job);
        em.remove(job);
    }

    @Override
    public Job findJobById(Long id) throws IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("Job id cannot be null.");
        }

        return em.find(Job.class, id);
    }

    @Override
    public List<Job> findAll() {
        return em.createQuery("select job from Job job", Job.class).getResultList();
    }

    private void validate(Job job) throws IllegalArgumentException {
        if (job == null) {
            throw new IllegalArgumentException("Job cannot be null.");
        }
    }
}
