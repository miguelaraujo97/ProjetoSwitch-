package switchisep.project.domain.valueobjects;

import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.Objects;

@Component
public class CostPerHour {
    private double costPerHour;

    public CostPerHour() {
    }

    private CostPerHour(double costPerHour){
        this.costPerHour = costPerHour;
    }

    /**
     * @author Celso Castro 1211755
     *
     * Static method os instanciate a CostPerHour VO
     *
     * @param costPerHour primitive data type (double) that you want to convert to Value Object.
     *                    In this Case the Cost Per Hour
     * @return
     */

    public static CostPerHour createCostPerHour(double costPerHour){

        if (costPerHour <= 0){
            throw new IllegalArgumentException("Cost Per Hour Cannot be Be Negative nor Zero");

        }

        if (BigDecimal.valueOf(costPerHour).scale() >= 3) {
            throw new IllegalArgumentException("Cost Per Hour Cannot Have More Than Two Decimal");
        }

        return new CostPerHour(costPerHour);
    }

    public double getCostPerHour() {
        return costPerHour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CostPerHour that = (CostPerHour) o;
        return Double.compare(that.costPerHour, costPerHour) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(costPerHour);
    }
}
