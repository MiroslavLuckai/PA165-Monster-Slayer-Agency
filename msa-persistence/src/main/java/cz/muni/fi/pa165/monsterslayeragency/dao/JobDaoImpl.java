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
        validate(job, "Job cannot be null!");
        em.persist(job);
    }

    @Override
    public void updateJob(Job job) throws IllegalArgumentException {
        validate(job, "Job cannot be null!");
        em.merge(job);
    }

    @Override
    public void removeJob(Job job) throws IllegalArgumentException {
        validate(job, "Job cannot be null!");
        em.remove(job);
    }

    @Override
    public Job findJobById(Long id) throws IllegalArgumentException {
        validate(id, "ID cannot be null!");
        return em.find(Job.class, id);
    }

    @Override
    public List<Job> findAll() {
        return em.createQuery("select job from Job job", Job.class).getResultList();
    }

    private void validate(Object object, String message) throws IllegalArgumentException {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
