package switchisep.project.domain.valueobjects;

import java.util.Objects;

public class PercentageOfExecution {
    private int percentageOfExecution;

    private PercentageOfExecution(int percentage) {
        this.percentageOfExecution = percentage;
    }


    public static PercentageOfExecution createPercentageOfExecution(
            int percentageOfExecution) {
        if (percentageOfExecution < 0 || percentageOfExecution>100) {
            throw new IllegalArgumentException("should be between 0 " +
                    "and 100");
        }
        return new PercentageOfExecution(percentageOfExecution);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PercentageOfExecution that = (PercentageOfExecution) o;
        return percentageOfExecution == that.percentageOfExecution;
    }

    @Override
    public int hashCode() {
        return Objects.hash(percentageOfExecution);
    }
}
