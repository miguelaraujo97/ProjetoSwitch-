package switchisep.project.domain.valueobjects;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PercentageAllocation {

    private int percentageNumber;

    public PercentageAllocation() {
    }

    private PercentageAllocation(int percentageNumber){
        this.percentageNumber = percentageNumber;
    }

    /**
     * @author Celso Castro 1211755
     *
     * Static Method to instanciate a Percentage Allocation VO
     *
     * @param allocationPercentage Percentage of allocation of a Resource on a ProjectDeprecated
     *                             where it must be between 0 and 100, where 100 means
     *                             Full Time.
     *
     * @return new PercentageAllocation VO
     */

    public static PercentageAllocation createAllocation(int allocationPercentage){

        if (allocationPercentage < 0 || allocationPercentage > 100) {
            throw  new IllegalArgumentException("Percentage Allocation must be between 0 and 100");
        }

        return new PercentageAllocation(allocationPercentage);
    }

    public int getPercentageNumber() {
        return percentageNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PercentageAllocation that = (PercentageAllocation) o;
        return percentageNumber == that.percentageNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(percentageNumber);
    }
}
