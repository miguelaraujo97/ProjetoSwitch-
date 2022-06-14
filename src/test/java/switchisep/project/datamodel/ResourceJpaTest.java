package switchisep.project.datamodel;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ResourceJpaTest {

    @Test
    void getProjectCode() {
        //Arrange
        ResourceJpa resourceJpa = new ResourceJpa("code", "email@email.com",
                "id", LocalDate.of(2022, 2, 2),
                LocalDate.of(2023, 2, 2), 50, 2, "Role");
        //Act
        String result = resourceJpa.getProjectCode();

        //Assert
        assertEquals("code", result);
    }

    @Test
    void getEmail() {
        //Arrange
        ResourceJpa resourceJpa = new ResourceJpa("code", "email@email.com",
                "id", LocalDate.of(2022, 2, 2),
                LocalDate.of(2023, 2, 2), 50, 2, "Role");
        //Act
        String result = resourceJpa.getEmail();

        //Assert
        assertEquals("email@email.com", result);
    }

    @Test
    void getResourceID() {
        //Arrange
        ResourceJpa resourceJpa = new ResourceJpa("code", "email@email.com",
                "id", LocalDate.of(2022, 2, 2),
                LocalDate.of(2023, 2, 2), 50, 2, "Role");
        //Act
        String result = resourceJpa.getResourceID();

        //Assert
        assertEquals("id", result);
    }

    @Test
    void getStartDate() {
        //Arrange
        ResourceJpa resourceJpa = new ResourceJpa("code", "email@email.com",
                "id", LocalDate.of(2022, 2, 2),
                LocalDate.of(2023, 2, 2), 50, 2, "Role");
        //Act
        LocalDate result = resourceJpa.getStartDate();

        //Assert
        assertEquals(LocalDate.of(2022, 2, 2), result);
    }

    @Test
    void getEndDate() {
        //Arrange
        ResourceJpa resourceJpa = new ResourceJpa("code", "email@email.com",
                "id", LocalDate.of(2022, 2, 2),
                LocalDate.of(2023, 2, 2), 50, 2, "Role");
        //Act
        LocalDate result = resourceJpa.getEndDate();

        //Assert
        assertEquals(LocalDate.of(2023, 2, 2), result);
    }

    @Test
    void getPercentageAllocation() {
        //Arrange
        ResourceJpa resourceJpa = new ResourceJpa("code", "email@email.com",
                "id", LocalDate.of(2022, 2, 2),
                LocalDate.of(2023, 2, 2), 50, 2, "Role");
        //Act
        int result = resourceJpa.getPercentageAllocation();

        //Assert
        assertEquals(50, result);
    }

    @Test
    void getCostPerHour() {
        //Arrange
        ResourceJpa resourceJpa = new ResourceJpa("code", "email@email.com",
                "id", LocalDate.of(2022, 2, 2),
                LocalDate.of(2023, 2, 2), 50, 2, "Role");
        //Act
        double result = resourceJpa.getCostPerHour();

        //Assert
        assertEquals(2, result);
    }

    @Test
    void getRole() {
        //Arrange
        ResourceJpa resourceJpa = new ResourceJpa("code", "email@email.com",
                "id", LocalDate.of(2022, 2, 2),
                LocalDate.of(2023, 2, 2), 50, 2, "Role");
        //Act
        String result = resourceJpa.getRole();

        //Assert
        assertEquals("Role", result);
    }

    @Test
    void setStartDate() {
        //Arrange
        ResourceJpa resourceJpa = new ResourceJpa("code", "email@email.com",
                "id", LocalDate.of(2022, 2, 2),
                LocalDate.of(2023, 2, 2), 50, 2, "Role");
        //Act
        resourceJpa.setStartDate(LocalDate.of(2022,2,3));

        //Assert
        assertEquals(LocalDate.of(2022,2,3), resourceJpa.getStartDate());
    }

    @Test
    void setEndDate() {
        //Arrange
        ResourceJpa resourceJpa = new ResourceJpa("code", "email@email.com",
                "id", LocalDate.of(2022, 2, 2),
                LocalDate.of(2023, 2, 2), 50, 2, "Role");
        //Act
        resourceJpa.setEndDate(LocalDate.of(2023,2,3));

        //Assert
        assertEquals(LocalDate.of(2023,2,3), resourceJpa.getEndDate());
    }

    @Test
    void setRole() {
        //Arrange
        ResourceJpa resourceJpa = new ResourceJpa("code", "email@email.com",
                "id", LocalDate.of(2022, 2, 2),
                LocalDate.of(2023, 2, 2), 50, 2, "Role");
        //Act
        resourceJpa.setRole("newRole");

        //Assert
        assertEquals("newRole", resourceJpa.getRole());
    }

    @Test
    void areTheObjectsTheSame() {

        //Arrange
        ResourceJpa resourceJpa = new ResourceJpa();

        //Act & Assert
        assertEquals(resourceJpa, resourceJpa);
    }

    @Test
    void testEqualsForEqualObject() {

        // Arrange
        ResourceJpa resourceJpa1 = new ResourceJpa("code", "email@email.com",
                "id", LocalDate.of(2022, 2, 2),
                LocalDate.of(2023, 2, 2), 50, 2, "Role");

        ResourceJpa resourceJpa2 = new ResourceJpa("code", "email@email.com",
                "id", LocalDate.of(2022, 2, 2),
                LocalDate.of(2023, 2, 2), 50, 2, "Role");

        // Act & Assert
        assertEquals(resourceJpa1, resourceJpa2);
    }

    @Test
    void testEqualsForDifferentObjectDifferentClass() {

        // Arrange
        ResourceJpa resourceJpa1 = new ResourceJpa("code", "email@email.com",
                "id", LocalDate.of(2022, 2, 2),
                LocalDate.of(2023, 2, 2), 50, 2, "Role");

        // Act
        Object obj = new Object();

        // Assert
        assertNotEquals(resourceJpa1, obj);
    }

    @Test
    void testEqualsForDifferentObjectSameClass() {

        // Arrange
        ResourceJpa resourceJpa1 = new ResourceJpa("code", "email@email.com",
                "id", LocalDate.of(2022, 2, 2),
                LocalDate.of(2023, 2, 2), 50, 2, "Role");

        ResourceJpa resourceJpa2 = new ResourceJpa("code", "newemail@email.com",
                "id", LocalDate.of(2022, 2, 2),
                LocalDate.of(2023, 2, 2), 100, 2, "Role");

        // Act & Assert
        assertNotEquals(resourceJpa1, resourceJpa2);
    }

    @Test
    void testHashCodeForEqualObject() {

        // Arrange
        ResourceJpa resourceJpa1 = new ResourceJpa("code", "email@email.com",
                "id", LocalDate.of(2022, 2, 2),
                LocalDate.of(2023, 2, 2), 50, 2, "Role");

        ResourceJpa resourceJpa2 = new ResourceJpa("code", "email@email.com",
                "id", LocalDate.of(2022, 2, 2),
                LocalDate.of(2023, 2, 2), 50, 2, "Role");

        // Act & Assert
        assertEquals(resourceJpa1.hashCode(), resourceJpa2.hashCode());
    }

    @Test
    void testHashCodeForDifferentObjectSameClass() {

        // Arrange
        ResourceJpa resourceJpa1 = new ResourceJpa("code", "email@email.com",
                "id", LocalDate.of(2022, 2, 2),
                LocalDate.of(2023, 2, 2), 50, 2, "Role");

        ResourceJpa resourceJpa2 = new ResourceJpa("code", "newemail@email.com",
                "id", LocalDate.of(2022, 2, 2),
                LocalDate.of(2023, 2, 2), 100, 2, "Role");

        // Act & Assert
        assertNotEquals(resourceJpa1.hashCode(), resourceJpa2.hashCode());
    }

}