package switchisep.project.domain.valueobjects;

import org.springframework.stereotype.Component;
import java.util.Objects;

@Component
public class Priority implements ValueObject<Priority>, Comparable<Priority> {
    private int priority;

    public Priority() {
    }

    private Priority(int priority) {
        this.priority = priority;
    }


    public static Priority createPriority(int priority) {

        if (priority <= 0) {
            throw new IllegalArgumentException("Priority can't be" +
                    " negative");
        }
        return new Priority(priority);
    }

    public int getPriority() {

        return this.priority;
    }


    @Override
    public int compareTo(Priority o) {
        return this.priority - o.priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Priority priority1 = (Priority) o;
        return sameValueAs(priority1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(priority);
    }

    @Override
    public boolean sameValueAs(Priority other) {
        return priority == other.priority;
    }
}
