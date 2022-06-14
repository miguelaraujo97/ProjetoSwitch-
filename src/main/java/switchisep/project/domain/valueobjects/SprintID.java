package switchisep.project.domain.valueobjects;

import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * DDD refactoring (Mário Dessa / Francisco Amado): Value object sprintID
 */
public class SprintID implements TaskContainerID, ValueObject<SprintID> {

    private String sprintID;

    /**
     * Private constructor that accepts a string as an argument.
     *
     * @param sprintID The unique alphanumerical identifier a given sprint.
     */
    private SprintID(String sprintID) {

        this.sprintID = sprintID;
    }

    /**
     * Static method that allows the creation of sprintID objects.
     *
     * @param sprintID The unique alphanumerical identifier a given sprint.
     * @return A sprintID object.
     */
    public static SprintID createSprintID(String sprintID) {

        if (!validateTaskContainerID(sprintID)) {

            throw new IllegalArgumentException("SprintID can't be empty or null");
        }

        return new SprintID(sprintID);
    }

    public SprintID() {
    }

    /**
     * Static method that allows the creation of sprintID objects.
     *
     * @param taskContainerID Identifies if a task belongs to a sprint or to a user story.
     * @return True if the taskContainerID is valid, false otherwise.
     */
    private static boolean validateTaskContainerID(String taskContainerID) {
        if (taskContainerID == null || taskContainerID.isEmpty() || taskContainerID.isBlank()) {
            return false;
        }
        return true;
    }

    /**
     * Getter method for the taskContainerID.
     *
     * @return The sprintID, given that the task invoking this method belongs to a sprint.
     */
    @Override
    public String getTaskContainerIDString() {

        return this.sprintID;
    }

    @Override
    public boolean sameValueAs(SprintID other) {
        return other != null && this.sprintID.equals(other.sprintID);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SprintID sprintID1 = (SprintID) o;
        return sprintID.equals(sprintID1.sprintID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sprintID);
    }
}
