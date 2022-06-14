package switchisep.project.domain.valueobjects;

import org.junit.jupiter.api.Test;
import switchisep.project.domain.valueobjects.Role;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    @Test
    void createValidRoleProductOwner() {

        // Arrange
        Role.RoleEnum roleString = Role.RoleEnum.ProductOwner;
        // Act
        Role roleOne = Role.createRole(roleString);
        Role roleTwo = Role.createRole(roleString);
        // Assert
        assertEquals(roleOne, roleTwo);
    }

    @Test
    void createValidRoleProjectManager() {

        // Arrange
        Role.RoleEnum roleString = Role.RoleEnum.ProjectManager;
        // Act
        Role roleOne = Role.createRole(roleString);
        Role roleTwo = Role.createRole(roleString);

        // Assert
        assertEquals(roleOne, roleTwo);
    }

    @Test
    void createValidRoleScrumMaster() {

        // Arrange
        Role.RoleEnum roleString = Role.RoleEnum.ScrumMaster;

        // Act
        Role roleOne = Role.createRole(roleString);
        Role roleTwo = Role.createRole(roleString);

        // Assert
        assertEquals(roleOne, roleTwo);
    }

    @Test
    void createValidRoleDeveloper() {

        // Arrange
        Role.RoleEnum roleString = Role.RoleEnum.Developer;

        // Act
        Role roleOne = Role.createRole(roleString);
        Role roleTwo = Role.createRole(roleString);

        // Assert
        assertEquals(roleOne, roleTwo);
    }

    @Test
    void createValidRoleCheckNotNull() {

        // Arrange
        Role.RoleEnum roleString = Role.RoleEnum.ProductOwner;

        // Act
        Role roleOne = Role.createRole(roleString);

        // Assert
        assertNotNull(roleOne);
    }

    @Test
    void testEqualsForSameObject() {

        // Arrange
        Role.RoleEnum roleString = Role.RoleEnum.ProductOwner;

        // Act
        Role role = Role.createRole(roleString);

        // Assert
        assertEquals(role, role);
    }

    @Test
    void testEqualsForEqualObject() {

        // Arrange
        Role.RoleEnum roleStringOne = Role.RoleEnum.ProductOwner;
        Role.RoleEnum roleStringTwo = Role.RoleEnum.ProductOwner;

        // Act
        Role roleOne = Role.createRole(roleStringOne);
        Role roleTwo = Role.createRole(roleStringTwo);

        // Assert
        assertEquals(roleOne, roleTwo);
    }

    @Test
    void testEqualsForDifferentObjectDifferentClass() {

        // Arrange
        Role.RoleEnum roleString = Role.RoleEnum.ProductOwner;

        // Act
        Role role = Role.createRole(roleString);
        Object obj = new Object();

        // Assert
        assertNotEquals(null, role);
        assertNotEquals(role, obj);
    }

    @Test
    void testEqualsForDifferentObjectSameClass() {

        // Arrange
        Role.RoleEnum roleStringOne = Role.RoleEnum.ProductOwner;
        Role.RoleEnum roleStringTwo = Role.RoleEnum.ProjectManager;

        // Act
        Role roleOne = Role.createRole(roleStringOne);
        Role roleTwo = Role.createRole(roleStringTwo);

        // Assert
        assertNotEquals(roleOne, roleTwo);
    }

    @Test
    void testHashCodeForDifferentObjectSameClass() {

        // Arrange
        Role.RoleEnum roleStringOne = Role.RoleEnum.ProductOwner;
        Role.RoleEnum roleStringTwo = Role.RoleEnum.ProjectManager;

        // Act
        Role roleOne = Role.createRole(roleStringOne);
        Role roleTwo = Role.createRole(roleStringTwo);

        // Assert
        assertNotEquals(roleOne.hashCode(), roleTwo.hashCode());
    }

    @Test
    void testHashCodeForSameObject() {

        // Arrange
        Role.RoleEnum roleStringOne = Role.RoleEnum.ProductOwner;

        // Act
        Role roleOne = Role.createRole(roleStringOne);

        // Assert
        assertEquals(roleOne.hashCode(), roleOne.hashCode());
    }
}