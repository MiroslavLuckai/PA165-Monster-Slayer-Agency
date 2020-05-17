package cz.muni.fi.pa165.monsterslayeragency.entities;

import cz.muni.fi.pa165.monsterslayeragency.enums.Severity;
import cz.muni.fi.pa165.monsterslayeragency.enums.JobStatus;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Miroslav Luckai
 */
@Entity(name = "Job")
@Table(name = "jobs")
public class Job extends AbstractEntity {

    @OneToOne(targetEntity = Request.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id")
    private Request request;

    @OneToMany
    @JoinColumn(name = "hero_id")
    private Set<Hero> heroes = new HashSet<>();

    @Column(name = "evaluation")
    private int evaluation;

    @Column(name = "status")
    private JobStatus status;

    @Column(name = "severity")
    private Severity severity;

    public Long getId() {
        return super.id;
    }

    public void setId(Long id) {
        super.id = id;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Set<Hero> getHeroes() {
        return heroes;
    }

    public void setHeroes(Set<Hero> heroes) {
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

    public Severity getSeverity() {
        return severity;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    public boolean addHero(Hero hero) {
        return this.heroes.add(hero);
    }

    public boolean removeHero(Hero hero) {
        return this.heroes.remove(hero);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Job)) return false;
        Job job = (Job) o;
        return getEvaluation() == job.getEvaluation() &&
                Objects.equals(getId(), job.getId()) &&
                Objects.equals(getRequest(), job.getRequest()) &&
                Objects.equals(getHeroes(), job.getHeroes()) &&
                getStatus() == job.getStatus() &&
                getSeverity() == job.getSeverity();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRequest(), getHeroes(), getEvaluation(), getStatus(), getSeverity());
    }
}
