package cz.muni.fi.pa165.msa.dto;

import cz.muni.fi.pa165.monsterslayeragency.enums.JobSeverity;
import cz.muni.fi.pa165.monsterslayeragency.enums.JobStatus;

import javax.validation.constraints.NotNull;

/**
 * @author Michaela Bajanova (469166)
 */
public class JobCreateDTO {

    // UNCOMMENT WHEN RequestDTO IS CREATED.
    // private RequestDTO request;

    // UNCOMMENT WHEN HeroDTO IS CREATED.
    // private Set<HeroDTO> heroes;

    private int evaluation;

    @NotNull
    private JobStatus status;

    @NotNull
    private JobSeverity severity;

    // UNCOMMENT WHEN RequestDTO IS CREATED.
//    public RequestDTO getRequest() {
//        return request;
//    }

    // UNCOMMENT WHEN HeroDTO IS CREATED.
//    public Set<HeroDTO> getHeroes() {
//        return heroes;
//    }

    public int getEvaluation() {
        return evaluation;
    }

    public JobStatus getStatus() {
        return status;
    }

    public JobSeverity getSeverity() {
        return severity;
    }

    // UNCOMMENT WHEN RequestDTO IS CREATED.
//    public void setRequest(RequestDTO request) {
//        this.request = request;
//    }

    // UNCOMMENT WHEN HeroDTO IS CREATED.
//    public void setHeroes(Set<HeroDTO> heroes) {
//        this.heroes = heroes;
//    }

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
}
