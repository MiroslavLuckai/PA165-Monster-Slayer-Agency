package cz.muni.fi.pa165.msa.dto;

import cz.muni.fi.pa165.monsterslayeragency.enums.Severity;
import cz.muni.fi.pa165.monsterslayeragency.enums.JobStatus;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

/**
 * @author Michaela Bajanova (469166)
 */
public class JobCreateDTO {

    private RequestDTO request;
    private Set<HeroDTO> heroes;
    private int evaluation;

    @NotNull
    private JobStatus status;

    @NotNull
    private Severity severity;

    public RequestDTO getRequest() {
        return request;
    }

    public Set<HeroDTO> getHeroes() {
        return heroes;
    }

    public int getEvaluation() {
        return evaluation;
    }

    public JobStatus getStatus() {
        return status;
    }

    public Severity getSeverity() {
        return severity;
    }

    public void setRequest(RequestDTO request) {
        this.request = request;
    }

    public void setHeroes(Set<HeroDTO> heroes) {
        this.heroes = heroes;
    }

    public void setEvaluation(int evaluation) {
        this.evaluation = evaluation;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JobCreateDTO)) return false;
        JobCreateDTO that = (JobCreateDTO) o;
        return getEvaluation() == that.getEvaluation() &&
                Objects.equals(getRequest(), that.getRequest()) &&
                Objects.equals(getHeroes(), that.getHeroes()) &&
                getStatus() == that.getStatus() &&
                getSeverity() == that.getSeverity();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRequest(), getHeroes(), getEvaluation(), getStatus(), getSeverity());
    }

    @Override
    public String toString() {
        return "JobCreateDTO{" +
                "request=" + request +
                ", heroes=" + heroes +
                ", evaluation=" + evaluation +
                ", status=" + status +
                ", severity=" + severity +
                '}';
    }
}
