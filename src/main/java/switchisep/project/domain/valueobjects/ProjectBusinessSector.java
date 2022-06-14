package switchisep.project.domain.valueobjects;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ProjectBusinessSector implements ValueObject<ProjectBusinessSector> {
    String businessSector;

    public ProjectBusinessSector() {
    }

    /**
     * @param businessSector business sector the project fits.
     * @author João Reis /Ricardo Pereira
     * Private constructor that accepts a string as an argument.
     */
    private ProjectBusinessSector(String businessSector) {

        this.businessSector = businessSector;
    }

    public static ProjectBusinessSector createProjectBusinessSector(String projectBusinessSector) {

        return new ProjectBusinessSector(projectBusinessSector);
    }

    /**
     * necessary for the dtoNative.
     *
     * @return string business Sector
     */

    public String getBusinessSector() {
        return businessSector;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectBusinessSector that = (ProjectBusinessSector) o;
        return Objects.equals(businessSector, that.businessSector);
    }

    @Override
    public int hashCode() {
        return Objects.hash(businessSector);
    }

    @Override
    public boolean sameValueAs(ProjectBusinessSector other) {
        return other != null && this.businessSector.equals(other.businessSector);
    }
}
