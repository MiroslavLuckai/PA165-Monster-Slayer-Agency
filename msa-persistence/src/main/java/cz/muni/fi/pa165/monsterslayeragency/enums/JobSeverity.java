package cz.muni.fi.pa165.monsterslayeragency.enums;

/**
 * Enum describing how important the job is
 */
public enum JobSeverity {

    /**
     * Job has to be completed no matter what, and can be assigned only to the most suited heroes
     */
    CRITICAL,

    /**
     * Job is fairly important and should be assigned to capable heroes
     */
    MODERATE,

    /**
     * Job is not very important and anyone can be assigned to it
     */
    MINOR
}
