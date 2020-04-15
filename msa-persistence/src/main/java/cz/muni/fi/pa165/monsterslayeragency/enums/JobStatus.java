package cz.muni.fi.pa165.monsterslayeragency.enums;


/**
 * Enum describing the current status of the job
 */
public enum JobStatus {

    /**
     * Job is assigned to heroes, but has not started yet
     */
    ASSIGNED,

    /**
     * Job is in progress
     */
    IN_PROGRESS,

    /**
     * Job has not been assigned yet
     */
    NOT_ASSIGNED,

    /**
     * Job has been cancelled
     */
    CANCELED,

    /**
     * Job has successfully finished
     */
    DONE,
}
