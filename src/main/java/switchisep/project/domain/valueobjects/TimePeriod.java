package switchisep.project.domain.valueobjects;

import org.springframework.stereotype.Component;

import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.util.Objects;

public class TimePeriod implements  ValueObject<TimePeriod>{

    private LocalDate startDate;
    private LocalDate endDate;

    public TimePeriod() {
    }

    private TimePeriod(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * @author Celso Castro 1211755
     *
     * Static Method to instantiate a TimePeriod VO
     *
     * @param startDate Start Date of the Time Period
     * @param endDate End Date of the Time Period
     * @return A new Time Period VO
     */

    public static TimePeriod createTimePeriod (LocalDate startDate, LocalDate endDate) {
        validateTimePeriod(startDate, endDate);

        return new TimePeriod(startDate, endDate);
    }

    private static void validateTimePeriod(LocalDate startDate, LocalDate endDate){

        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End Date Can Not Be Before The Start Date");
        }
    }

    /**
     * necessary for the dtoNative.
     * @return start date
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * necessary for the dtoNative.
     * @return end date
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    @Override
    public boolean sameValueAs(TimePeriod other) {
        return other != null && this.startDate.equals(other.startDate) &&
                this.endDate.equals(other.endDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimePeriod)) return false;
        TimePeriod other = (TimePeriod) o;
        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate);
    }

}
