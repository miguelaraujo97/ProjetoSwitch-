package switchisep.project.domain.valueobjects;

import org.springframework.stereotype.Component;

import javax.persistence.Embeddable;
import java.util.Objects;

@Component
public class ProjectBudget implements ValueObject<ProjectBudget> {

    private double budget;

    public ProjectBudget() {
    }

    /**
     * @author João Reis /Ricardo Pereira
     * Private constructor that accepts a double as an argument.
     * @param budget Monetary amount available for spending in a determined activity or project
     */

    private ProjectBudget(double budget) {
        this.budget = budget;
    }

    /**
     * Static method to instantiate a ProjectBudget VO
     *
     * @param budget
     * @return a budget
     */

    public static ProjectBudget createBudget(double budget) {

        if (budget < 0) {
            throw new IllegalArgumentException(
                    "ProjectBudget has to be greater or equal zero.");
        }
        return new ProjectBudget(budget);
    }

    /**
     * necessary for the dtoNative.
     * @return double  budget
     */
    public double getBudget() {
        return budget;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectBudget other = (ProjectBudget) o;

        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return Objects.hash(budget);

}

    @Override
    public boolean sameValueAs(ProjectBudget other) {
        return Double.compare(other.budget, budget) == 0;
    }
}
