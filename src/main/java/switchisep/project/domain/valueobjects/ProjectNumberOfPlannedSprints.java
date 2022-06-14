package switchisep.project.domain.valueobjects;

import org.springframework.stereotype.Component;

import javax.persistence.Embeddable;
import java.util.Objects;

@Component
public class ProjectNumberOfPlannedSprints implements ValueObject<ProjectNumberOfPlannedSprints> {

    private int numberOfPlannedSprints;

    public ProjectNumberOfPlannedSprints() {
    }

    private ProjectNumberOfPlannedSprints(int numberOfPlannedSprints){

        this.numberOfPlannedSprints = numberOfPlannedSprints;

    }

    public static ProjectNumberOfPlannedSprints createNumberOfPlannedSprints(int numberOfPlannedSprints){
        if (numberOfPlannedSprints < 0) {

            throw new IllegalArgumentException(
                    "The number of sprints has to be greater or equal zero");
        }
        return new ProjectNumberOfPlannedSprints(numberOfPlannedSprints);
    }

    /**
     * necessary for the dtoNative
     * @return int number of planned sprints.
     */
    public int getNumberOfPlannedSprints() {
        return numberOfPlannedSprints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectNumberOfPlannedSprints other = (ProjectNumberOfPlannedSprints) o;
        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfPlannedSprints);
    }

    @Override
    public boolean sameValueAs(ProjectNumberOfPlannedSprints other) {
        return numberOfPlannedSprints == other.numberOfPlannedSprints;
    }
}
