package switchisep.project.domain.valueobjects;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ProjectDescription implements ValueObject<ProjectDescription> {

    private String projectDescription;

    public ProjectDescription() {
    }

    private ProjectDescription(String projectDescription) {

        this.projectDescription = projectDescription;
    }

    /**
     * Static method to instantiate a Project Description VO
     *
     * @param projectDescription
     * @return
     */
    public static ProjectDescription createProjectDescription(String projectDescription) {
               return new ProjectDescription(projectDescription);
    }

    /**
     * necessary for the dto
     * @return
     */
    public String getProjectDescription() {
        return projectDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectDescription other = (ProjectDescription) o;
        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectDescription);
    }

    @Override
    public boolean sameValueAs(ProjectDescription other) {
        return other != null && this.projectDescription.equals(other.projectDescription);
    }
}
