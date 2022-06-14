package switchisep.project.domain.valueobjects;

import org.springframework.stereotype.Component;

import javax.persistence.Embeddable;
import java.util.Objects;

/**
 * DDD refactoring (Mário Dessa / Francisco Amado): Value object sprintDuration
 */
@Component
public class SprintDuration implements  ValueObject<SprintDuration> {

    private int duration;

    public SprintDuration() {
    }

    /**
     * Private constructor that accepts an integer as an argument.
     * @param duration The duration of a given sprint.
     */
    private SprintDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Static method that allows the creation of sprintDuration objects.
     * @param sprintDuration The duration of a given sprint.
     * @return A sprintDuration object.
     */
    public static SprintDuration createSprintDuration(int sprintDuration) {

        if (sprintDuration < 1) {

            throw new IllegalArgumentException("The minimum sprint duration is one week");
        }

        return new SprintDuration(sprintDuration);
    }

    /**
     * Getter method for the sprint duration.
     * @return The sprint duration (in weeks).
     */
    public int getSprintDuration() {
        return duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SprintDuration that = (SprintDuration) o;
        return sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(duration);
    }

    @Override
    public boolean sameValueAs(SprintDuration other) {
        return other != null && this.duration == other.duration;
    }
}
