package switchisep.project.domain.valueobjects;

import java.util.Objects;

/**
 * DDD refactoring (Mário Dessa / Francisco Amado): Value object sprintDuration
 */
public class ScrumBoardStatus implements ValueObject<ScrumBoardStatus> {

    private final String status;

    /**
     * Private constructor that accepts a string as an argument.
     *
     * @param status The scrum board status.
     */
    private ScrumBoardStatus(String status) {

        this.status = status;
    }

    /**
     * Method that allows the creation of a new ScrumBoardStatus object.
     *
     * @param scrumBoardStatusEnum One of three possible values: TO_DO, IN_PROGRESS and DONE.
     * @return A new scrumBoardStatus
     */
    public static ScrumBoardStatus createScrumBoardStatus(ScrumBoardStatusEnum scrumBoardStatusEnum) {

        return new ScrumBoardStatus(scrumBoardStatusEnum.toString());
    }

    /**
     * Getter method for the scrum board status description.
     *
     * @return The scrum board status description.
     */
    public String getScrumBoardStatusDescription() {

        return status;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScrumBoardStatus that = (ScrumBoardStatus) o;
        return sameValueAs(that);
    }

    @Override
    public int hashCode() {

        return Objects.hash(status);
    }

    @Override
    public boolean sameValueAs(ScrumBoardStatus other) {
        return other != null && this.status.equals(other.status);
    }

    /**
     * Enumeration of possible sprint statuses.
     */
    public enum ScrumBoardStatusEnum {

        TO_DO,
        IN_PROGRESS,
        DONE
    }
}
