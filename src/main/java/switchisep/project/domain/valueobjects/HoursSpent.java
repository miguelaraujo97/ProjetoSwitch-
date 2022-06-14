package switchisep.project.domain.valueobjects;

import java.util.Objects;

public class HoursSpent {
    private final int hoursSpent;

    private HoursSpent(int hours){
        this.hoursSpent=hours;
    }

    /**
     * Static method to instantiate a HoursSpent VO
     * @param hours
     * @return
     */
    public static HoursSpent createHoursSpent( int hours){
        if(hours<=0){
            throw new IllegalArgumentException("Hours spent can't be" +
                    " negative");
        }
        return new HoursSpent(hours);
    }

    public int getHoursSpent(){
        return this.hoursSpent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HoursSpent that = (HoursSpent) o;
        return hoursSpent == that.hoursSpent;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hoursSpent);
    }
}
