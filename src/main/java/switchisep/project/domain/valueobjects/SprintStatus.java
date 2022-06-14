package switchisep.project.domain.valueobjects;

import java.util.Objects;

/**
 * DDD refactoring (Mário Dessa / Francisco Amado): Value object sprintStatus
 */

public class SprintStatus implements ValueObject<SprintStatus> {

    public SprintStatus() {
    }

    private String sprintStatusDescription;

    private SprintStatus(String sprintStatusDescription) {

        this.sprintStatusDescription = sprintStatusDescription;
    }

    /**
     * Method that allows the creation of a new sprintStatus object.
     *
     * @param sprintStatusEnum One of three possible values: NOT_STARTED, STARTED and FINISHED.
     * @return A new sprintStatus object, null if a valid value is not passed.
     */
    public static SprintStatus createSprintStatus(SprintStatusEnum sprintStatusEnum) {

        return new SprintStatus(sprintStatusEnum.toString());
    }

    /**
     * Getter method for the sprint status description.
     *
     * @return The sprint status description.
     */
    public String getSprintStatusDescription() {

        return sprintStatusDescription;
    }

    @Override
    public boolean sameValueAs(SprintStatus other) {
        return other != null && this.sprintStatusDescription.equals(other.sprintStatusDescription);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SprintStatus that = (SprintStatus) o;
        return sprintStatusDescription.equals(that.sprintStatusDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sprintStatusDescription);
    }

    /**
     * Enumeration of possible sprint statuses.
     */
    public enum SprintStatusEnum {

        NOT_STARTED,
        STARTED,
        FINISHED
    }

}



