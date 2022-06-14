package switchisep.project.domain.valueobjects;

import lombok.Getter;
import org.springframework.stereotype.Component;
import java.util.Objects;

@Component
public class Description implements ValueObject<Description> {

    @Getter
    private String description;

    private Description(String description) {
        this.description = description;
    }

    public Description() {

    }

    /**
     * Static method to instantiate a Description VO
     *
     * @param description
     * @return
     */
    public static Description createDescription(String description) {
        if (description.isEmpty() || description.isBlank()) {
            throw new IllegalArgumentException("Description can't" +
                    " be empty nor blank");
        }
        return new Description(description);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Description that = (Description) o;
        return description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }

    @Override
    public boolean sameValueAs(Description other) {
        return other != null && this.description.equals(other.description);
    }
}
