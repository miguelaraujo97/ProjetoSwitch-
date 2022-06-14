package switchisep.project.domain.valueobjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectDescriptionTest {


    @Test
    void sameValueAsTrue() {
        //Arrange
        String finance = "Finance";
       ProjectDescription projectDescription = ProjectDescription.createProjectDescription(finance);

        //Act
        boolean result = projectDescription.sameValueAs(projectDescription);

        //Assert
        assertTrue(result);
    }

    @Test
    void sameValueAsFalse() {
        //Arrange
        String finance = "Finance";
        String tech = "Tech";
        ProjectDescription projectDescription = ProjectDescription.createProjectDescription(finance);
        ProjectDescription projectDescription1 = ProjectDescription.createProjectDescription(tech);

        //Act
        boolean result = projectDescription.sameValueAs(projectDescription1);

        //Assert
        assertFalse(result);
    }

    @Test
    void sameValueAsNull() {
        //Arrange
        String finance = "Finance";
        ProjectDescription projectDescription = ProjectDescription.createProjectDescription(finance);


        //Act
        boolean result = projectDescription.sameValueAs(null);

        //Assert
        assertFalse(result);
    }

    @Test
    void checkEqualsHasCodeOverride() {
        //Arrange
        String finance = "Finance";
        String technology = "Technology";

        //Act
        ProjectDescription projectDescription = ProjectDescription.createProjectDescription(finance);
        ProjectDescription projectDescription1 = ProjectDescription.createProjectDescription(finance);
        ProjectDescription projectDescription2 = ProjectDescription.createProjectDescription(technology);
        Object obj = new Object();
        // Assert
        assertEquals(projectDescription, projectDescription);
        assertEquals(projectDescription, projectDescription1);
        assertEquals(projectDescription.hashCode(), projectDescription1.hashCode());
        assertNotEquals(projectDescription, projectDescription2);
        assertNotEquals(projectDescription, null);
        assertNotEquals(projectDescription, obj);

        assertNotEquals(projectDescription.hashCode(), projectDescription2.hashCode());
    }


}