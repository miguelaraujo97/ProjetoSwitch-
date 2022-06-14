package switchisep.project.domain.valueobjects;

import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
public class EffortEstimate implements ValueObject<EffortEstimate> {

    private int effortEstimateValue;

    private EffortEstimate(int effortEstimateValue) {
        this.effortEstimateValue = effortEstimateValue;
    }

    public static EffortEstimate createEffortEstimate(int effort) {
        int[] fibonacciNumber = {0, 1, 2, 3, 5, 8, 12, 21};
        for (int fibonacci : fibonacciNumber) {
            if (fibonacci == effort) {
                return new EffortEstimate(effort);
            }
        }
        throw new IllegalArgumentException("Value is not a fibonacci number");
    }

    public int getEffortEstimateValue() {
        return this.effortEstimateValue;
    }

    @Override
    public boolean sameValueAs(EffortEstimate other) {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EffortEstimate that = (EffortEstimate) o;
        return effortEstimateValue == that.effortEstimateValue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(effortEstimateValue);
    }

}



