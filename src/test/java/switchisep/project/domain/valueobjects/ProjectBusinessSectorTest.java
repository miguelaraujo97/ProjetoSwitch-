package switchisep.project.domain.valueobjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectBusinessSectorTest {


    @Test
    void sameValueAsTrue() {
        //Arrange
        String finance = "Finance";
        ProjectBusinessSector businessSector = ProjectBusinessSector.createProjectBusinessSector(finance);

        //Act
        boolean result = businessSector.sameValueAs(businessSector);

        //Assert
        assertTrue(result);
    }

    @Test
    void sameValueAsFalse() {
        //Arrange
        String finance = "Finance";
        String tech = "Tech";
        ProjectBusinessSector businessSector = ProjectBusinessSector.createProjectBusinessSector(finance);
        ProjectBusinessSector businessSector1 = ProjectBusinessSector.createProjectBusinessSector(tech);

        //Act
        boolean result = businessSector.sameValueAs(businessSector1);

        //Assert
        assertFalse(result);
    }

    @Test
    void sameValueAsNull() {
        //Arrange
        String finance = "Finance";
        ProjectBusinessSector businessSector = ProjectBusinessSector.createProjectBusinessSector(finance);


        //Act
        boolean result = businessSector.sameValueAs(null);

        //Assert
        assertFalse(result);
    }

    @Test
    void checkEqualsHasCodeOverride() {
        //Arrange
        String finance = "Finance";
        String technology = "Technology";

        //Act
        ProjectBusinessSector businessSector =
                ProjectBusinessSector.createProjectBusinessSector(finance);
        ProjectBusinessSector businessSector1 =
                ProjectBusinessSector.createProjectBusinessSector(finance);
        ProjectBusinessSector businessSector2 =
                ProjectBusinessSector.createProjectBusinessSector(technology);
        Object obj = new Object();
        // Assert
        assertEquals(businessSector, businessSector);
        assertEquals(businessSector, businessSector1);
        assertEquals(businessSector.hashCode(), businessSector1.hashCode());
        assertNotEquals(businessSector, businessSector2);
        assertNotEquals(businessSector, null);
        assertNotEquals(businessSector, obj);

        assertNotEquals(businessSector.hashCode(), businessSector2.hashCode());
    }

}