package switchisep.project.domain.valueobjects;

import org.springframework.stereotype.Component;
import java.util.Objects;

@Component
public class Role {

    private String role;

    public Role() {
    }

    private Role(String role) {
        this.role = role;
    }

    public enum RoleEnum {

        ProductOwner,
        ProjectManager,
        ScrumMaster,
        Developer

    }

    /**
     * Method to instantiate a new Role for a Resource
     *
     * @param role to add to Resource. This Role must coincide with the enum values or else
     *             it will throw an IllegalArgumentException
     *
     * @return a new Role to Add when a Resource is created
     *
     */

    public static Role createRole(RoleEnum roleEnum) {

        return new Role(roleEnum.toString());
    }

    public String getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role1 = (Role) o;
        return Objects.equals(role, role1.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role);
    }
}
