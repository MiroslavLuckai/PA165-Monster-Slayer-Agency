package cz.muni.fi.pa165.monsterslayeragency.dao;

import cz.muni.fi.pa165.monsterslayeragency.entities.Job;
import cz.muni.fi.pa165.monsterslayeragency.enums.JobSeverity;
import cz.muni.fi.pa165.monsterslayeragency.enums.JobStatus;
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

    @Override
    public List<Job> findJobsBySeverity(JobSeverity severity) {
        validate(severity, "Cannot search with null severity!");
        return em.createQuery("SELECT job FROM Job job WHERE job.severity = :severity", Job.class)
                .setParameter("severity", severity)
                .getResultList();
    }

    @Override
    public List<Job> findJobsByStatus(JobStatus status) {
        validate(status, "Cannot search with null status!");
        return em.createQuery("SELECT job FROM Job job WHERE job.status = :status", Job.class)
                .setParameter("status", status)
                .getResultList();
    }

    private void validate(Object object, String message) throws IllegalArgumentException {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
