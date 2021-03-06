package cz.muni.fi.pa165.msa.dto;

import cz.muni.fi.pa165.monsterslayeragency.enums.Severity;
import cz.muni.fi.pa165.monsterslayeragency.enums.JobStatus;

import java.util.Objects;
import java.util.Set;

/**
 * @author Michaela Bajanova (469166)
 */
public class JobDTO {

    private Long id;
  
    private RequestDTO request;
    
    private Set<HeroDTO> heroes;

    private int evaluation;
    
    private JobStatus status;
    
    private Severity severity;

    public Long getId() {
        return id;
    }

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

    public void setId(Long id) {
        this.id = id;
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
        if (!(o instanceof JobDTO)) return false;
        JobDTO jobDTO = (JobDTO) o;
        return getEvaluation() == jobDTO.getEvaluation() &&
                Objects.equals(getId(), jobDTO.getId()) &&
                Objects.equals(getRequest(), jobDTO.getRequest()) &&
                Objects.equals(getHeroes(), jobDTO.getHeroes()) &&
                getStatus() == jobDTO.getStatus() &&
                getSeverity() == jobDTO.getSeverity();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRequest(), getHeroes(), getEvaluation(), getStatus(), getSeverity());
    }

    @Override
    public String toString() {
        return "JobDTO{" +
                "id=" + id +
                ", request=" + request +
                ", heroes=" + heroes +
                ", evaluation=" + evaluation +
                ", status=" + status +
                ", severity=" + severity +
                '}';
    }
}
