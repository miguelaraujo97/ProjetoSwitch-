package switchisep.project.domain.valueobjects;

import java.util.Objects;

public class ProjectName implements ValueObject<ProjectName> {

    private String name;

    public ProjectName() {
    }

    /**
     * @author João Reis /Ricardo Pereira
     * Private constructor that accepts a string as an argument.
     * @param name name f the project
     */
    private ProjectName (String name){ this.name = name;}

    /**
     * Static method to instantiate a name VO
     *
     * @param name
     * @return a name
     */
    public static ProjectName createProjectName(String name) {


        Objects.requireNonNull(name, "A name must be inserted");
        if (name.trim().equals("")) {
            throw new IllegalArgumentException("The name can not be an empty string");
        }

       return new ProjectName(name);
    }

    /**
     * necessary to the dtoNative.
     * @return string name.
     */
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectName other = (ProjectName) o;
        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean sameValueAs(ProjectName other) {
        return other != null && this.name.equals(other.name);
    }
}
