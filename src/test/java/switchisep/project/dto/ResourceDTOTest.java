package switchisep.project.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ResourceDTOTest {

    @Test
    void isTheProfileDTOCreatedEqualToItself(){

        //Arrange
        String projectCode = "A123";
        String email = "amado@me.com";
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusYears(3);
        int percentageOfAllocation = 50;
        double costPerHour = 1.5;
        String role = "Developer";
        ResourceDTO resourceDTOOne = new ResourceDTO(projectCode, email, startDate, endDate, percentageOfAllocation,
                costPerHour, role);

        //Act
        boolean result = resourceDTOOne.equals(resourceDTOOne);

        //Assert
        assertTrue(result);
    }

    @Test
    void areBothResourceDTOsEqual() {

        //Arrange
        String projectCode = "A123";
        String email = "amado@me.com";
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusYears(3);
        int percentageOfAllocation = 50;
        double costPerHour = 1.5;
        String role = "Developer";

        //Act
        ResourceDTO resourceDTOOne = new ResourceDTO(projectCode, email, startDate, endDate, percentageOfAllocation,
                costPerHour, role);
        ResourceDTO resourceDTOTwo = new ResourceDTO(projectCode, email, startDate, endDate, percentageOfAllocation,
                costPerHour, role);

        //Assert
        assertEquals(resourceDTOOne, resourceDTOTwo);
    }

    @Test
    void areBothResourceDTOsDifferent() {

        //Arrange
        String projectCode = "A123";
        String projectCode1 = "B123";
        String email = "amado@me.com";
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusYears(3);
        int percentageOfAllocation = 50;
        double costPerHour = 1.5;
        String role = "Developer";

        //Act
        ResourceDTO resourceDTOOne = new ResourceDTO(projectCode, email, startDate, endDate, percentageOfAllocation,
                costPerHour, role);
        ResourceDTO resourceDTOTwo = new ResourceDTO(projectCode1, email, startDate, endDate, percentageOfAllocation,
                costPerHour, role);

        //Assert
        assertNotEquals(resourceDTOOne, resourceDTOTwo);
    }

    @Test
    void isOneOfTheResourceDTOsNull(){

        //Arrange
        String projectCode = "A123";
        String email = "amado@me.com";        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusYears(3);
        int percentageOfAllocation = 50;
        double costPerHour = 1.5;
        String role = "Developer";

        //Act
        ResourceDTO resourceDTOOne = new ResourceDTO(projectCode, email, startDate, endDate, percentageOfAllocation,
                costPerHour, role);
        ResourceDTO resourceDTOTwo = null;

        //Assert
        assertNotEquals(resourceDTOOne, resourceDTOTwo);
    }

    @Test
    void areBothHashCodesEqual() {

        //Arrange
        String projectCode = "A123";
        String email = "amado@me.com";        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusYears(3);
        int percentageOfAllocation = 50;
        double costPerHour = 1.5;
        String role = "Developer";

        //Act
        ResourceDTO resourceDTOOne = new ResourceDTO(projectCode, email, startDate, endDate, percentageOfAllocation,
                costPerHour, role);
        ResourceDTO resourceDTOTwo = new ResourceDTO(projectCode, email, startDate, endDate, percentageOfAllocation,
                costPerHour, role);

        //Assert
        assertEquals(resourceDTOOne.hashCode(), resourceDTOTwo.hashCode());
    }

    @Test
    void areBothHashCodesDifferent() {

        //Arrange
        String projectCode = "A123";
        String projectCode1 = "B123";
        String email = "amado@me.com";
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusYears(3);
        int percentageOfAllocation = 50;
        double costPerHour = 1.5;
        String role = "Developer";

        //Act
        ResourceDTO resourceDTOOne = new ResourceDTO(projectCode, email, startDate, endDate, percentageOfAllocation,
                costPerHour, role);
        ResourceDTO resourceDTOTwo = new ResourceDTO(projectCode1, email, startDate, endDate, percentageOfAllocation,
                costPerHour, role);

        //Assert
        assertNotEquals(resourceDTOOne.hashCode(), resourceDTOTwo.hashCode());
    }


    @Test
    void sameObjectTestForEquals() {

        //Arrange
        String projectCode = "A123";
        String email = "amado@me.com";
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusYears(3);
        int percentageOfAllocation = 50;
        double costPerHour = 1.5;
        String role = "Developer";

        //Act
        ResourceDTO resourceDTOOne = new ResourceDTO(projectCode, email, startDate, endDate, percentageOfAllocation,
                costPerHour, role);

        //Assert
        assertEquals(resourceDTOOne, resourceDTOOne);
    }

    @Test
    void nullTestForEquals(){

        //Arrange
        String projectCode = "A123";
        String email = "amado@me.com";
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusYears(3);
        int percentageOfAllocation = 50;
        double costPerHour = 1.5;
        String role = "Developer";

        //Act
        ResourceDTO resourceDTOOne = new ResourceDTO(projectCode, email, startDate, endDate, percentageOfAllocation,
                costPerHour, role);

        //Assert
        assertNotEquals(resourceDTOOne, null);
    }

    @Test
    void diffClassTestForEquals(){

        //Arrange
        String projectCode = "A123";
        String email = "amado@me.com";
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusYears(3);
        int percentageOfAllocation = 50;
        double costPerHour = 1.5;
        String role = "Developer";

        //Act
        ResourceDTO resourceDTOOne = new ResourceDTO(projectCode, email, startDate, endDate, percentageOfAllocation,
                costPerHour, role);

        Object objectOther = new Object();

        //Assert
        assertNotEquals(resourceDTOOne, objectOther);
    }

    @Test
    void compareObjectsDiffEmail() {

        //Arrange
        String projectCode = "A123";
        String projectCode1 = "B123";
        String email = "amado@me.com";
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusYears(3);
        int percentageOfAllocation = 50;
        double costPerHour = 1.5;
        String role = "Developer";

        //Act
        ResourceDTO resourceDTOOne = new ResourceDTO(projectCode, email, startDate, endDate, percentageOfAllocation,
                costPerHour, role);
        ResourceDTO resourceDTOTwo = new ResourceDTO(projectCode1, "email", startDate, endDate, percentageOfAllocation,
                costPerHour, role);

        //Assert
        assertNotEquals(resourceDTOOne, resourceDTOTwo);
    }

    @Test
    void compareObjectsDiffRole() {

        //Arrange
        String projectCode = "A123";
        String projectCode1 = "B123";
        String email = "amado@me.com";
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusYears(3);
        int percentageOfAllocation = 50;
        double costPerHour = 1.5;
        String role = "Developer";

        //Act
        ResourceDTO resourceDTOOne = new ResourceDTO(projectCode, email, startDate, endDate, percentageOfAllocation,
                costPerHour, role);
        ResourceDTO resourceDTOTwo = new ResourceDTO(projectCode1, email, startDate, endDate, percentageOfAllocation,
                costPerHour, "dev");

        //Assert
        assertNotEquals(resourceDTOOne, resourceDTOTwo);
    }

    @Test
    void compareObjectsDiffStartDate() {

        //Arrange
        String projectCode = "A123";
        String projectCode1 = "B123";
        String email = "amado@me.com";
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusYears(3);
        int percentageOfAllocation = 50;
        double costPerHour = 1.5;
        String role = "Developer";

        //Act
        ResourceDTO resourceDTOOne = new ResourceDTO(projectCode, email, startDate, endDate, percentageOfAllocation,
                costPerHour, role);
        ResourceDTO resourceDTOTwo = new ResourceDTO(projectCode1, email, startDate.plusDays(1), endDate, percentageOfAllocation,
                costPerHour, "dev");

        //Assert
        assertNotEquals(resourceDTOOne, resourceDTOTwo);
    }

    @Test
    void compareObjectsDiffEndDate() {

        //Arrange
        String projectCode = "A123";
        String projectCode1 = "B123";
        String email = "amado@me.com";
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusYears(3);
        int percentageOfAllocation = 50;
        double costPerHour = 1.5;
        String role = "Developer";

        //Act
        ResourceDTO resourceDTOOne = new ResourceDTO(projectCode, email, startDate, endDate, percentageOfAllocation,
                costPerHour, role);
        ResourceDTO resourceDTOTwo = new ResourceDTO(projectCode1, email, startDate, endDate.plusDays(1), percentageOfAllocation,
                costPerHour, "dev");

        //Assert
        assertNotEquals(resourceDTOOne, resourceDTOTwo);
    }





}