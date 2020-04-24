package cz.muni.fi.pa165.msa.dto;

import cz.muni.fi.pa165.monsterslayeragency.enums.JobSeverity;
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
    private JobSeverity severity;

    public Long getId() {
        return id;
    }

    // UNCOMMENT WHEN RequestDTO IS CREATED.
    public RequestDTO getRequest() {
        return request;
    }

    // UNCOMMENT WHEN HeroDTO IS CREATED.
    public Set<HeroDTO> getHeroes() {
        return heroes;
    }

    public int getEvaluation() {
        return evaluation;
    }

    public JobStatus getStatus() {
        return status;
    }

    public JobSeverity getSeverity() {
        return severity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // UNCOMMENT WHEN RequestDTO IS CREATED.
    public void setRequest(RequestDTO request) {
        this.request = request;
    }

    // UNCOMMENT WHEN HeroDTO IS CREATED.
    public void setHeroes(Set<HeroDTO> heroes) {
        this.heroes = heroes;
    }

    public void setEvaluation(int evaluation) {
        this.evaluation = evaluation;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }

    public void setSeverity(JobSeverity severity) {
        this.severity = severity;
    }

    // TODO: implement equals and hash code when RequestDTO and HeroDTO are created. (maybe also toString)


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
}
