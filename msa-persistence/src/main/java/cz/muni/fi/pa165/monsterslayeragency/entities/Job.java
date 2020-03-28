package cz.muni.fi.pa165.monsterslayeragency.entities;

import cz.muni.fi.pa165.monsterslayeragency.enums.JobStatus;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * @author Miroslav Luckai
 */
@Entity
public class Job {

    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(targetEntity = Request.class, fetch = FetchType.LAZY)
    private Request request;

    @OneToMany
    private List<Hero> heroes;
    private int evaluation;
    private JobStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public List<Hero> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<Hero> heroes) {
        this.heroes = heroes;
    }

    public int getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(int evaluation) {
        this.evaluation = evaluation;
    }

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }

        if (!(object instanceof Job)) {
            return false;
        }

        Job job = (Job) object;

        return job.request.equals(this.request);
    }

    @Override
    public int hashCode() {
        return Objects.hash(request);
    }
}
