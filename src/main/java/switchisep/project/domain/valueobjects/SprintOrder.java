package switchisep.project.domain.valueobjects;

import java.util.Objects;

/**
 * DDD refactoring (Mário Dessa / Francisco Amado): Value object sprintOrder
 */
public class SprintOrder implements ValueObject<SprintOrder> {

    private int sprintOrder;

    /**
     * Private constructor that accepts an integer as an argument.
     * @param order The order of a given sprint in the project.
     */
    private SprintOrder(int order) {

        this.sprintOrder = order;
    }

    public SprintOrder() {
    }

    /**
     * Static method that allows the creation of sprintOrder objects.
     * @param sprintOrder The order of a given sprint in the project.
     * @return A sprintOrder object.
     */
    public static SprintOrder createSprintOrder(int sprintOrder) {

        if (sprintOrder <= 0) {

            throw new IllegalArgumentException("The sprint order number must be equal or superior to 1");
        }

        return new SprintOrder(sprintOrder);
    }

    public int getSprintOrder(){
        return this.sprintOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SprintOrder that = (SprintOrder) o;
        return sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sprintOrder);
    }

    @Override
    public boolean sameValueAs(SprintOrder other) {
        return other != null && this.sprintOrder == other.sprintOrder;
    }
}
