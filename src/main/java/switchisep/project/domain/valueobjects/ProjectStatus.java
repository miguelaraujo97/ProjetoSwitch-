package switchisep.project.domain.valueobjects;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.Enumerated;

/**
 * @author João Reis /Ricardo Pereira
 * Enum class with the valid status values: Planned, Inception, Elaboration, Construction, Transition,
 * Warranty and Closed.
 */
@NoArgsConstructor
public enum ProjectStatus implements ValueObject<ProjectStatus> {

    PLANNED("PLANNED"),
    INCEPTION("INCEPTION"),
    ELABORATION("ELABORATION"),
    CONSTRUCTION("CONSTRUCTION"),
    TRANSITION("TRANSITION"),
    WARRANTY("WARRANTY"),
    CLOSED("CLOSED");

    private String status ;


    ProjectStatus(String status) {

        this.status = status;
    }

    public static ProjectStatus valueOfIgnoreCase(String status) {

        return valueOf(status.toUpperCase());
    }

    /**
     * necessary for the dtoNative
     *
     * @return string status
     */
    public String getStatus() {
        return status;
    }

    @Override
    public boolean sameValueAs(ProjectStatus other) {
        return this.equals(other);
    }
}
