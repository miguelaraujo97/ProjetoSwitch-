package switchisep.project.domain.valueobjects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectNameTest {

    @Test
    void projectNameNull() {

        //Arrange
        String projectNameData = null;


        //Act & Assert
        Assertions.assertThrows(NullPointerException.class, () -> {
            ProjectName.createProjectName(projectNameData);
        });

    }

    @Test
    void emptyStringProjectName(){

        //Arrange

        String projectNameData = "";

        //Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () ->{
            ProjectName.createProjectName(projectNameData);
        });

    }

    @Test
    void emptyStringWithSpaceProjectName(){
        //Arrange
        String projectNameData = "   ";

        //Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () ->{
            ProjectName.createProjectName(projectNameData);
        });

    }

//---------------------------------------------------------------------------------------------------------------
    //Test To Override and hash

    @Test
    void checkOverride(){
        //Arrange

        //Act
        ProjectName projectName = ProjectName.createProjectName("ISEP");
        ProjectName projectName1 = ProjectName.createProjectName("ISEP");
        ProjectName projectName2 = ProjectName.createProjectName("ISEP1");
        Object obj = new Object();

        // Assert
        assertEquals(projectName,projectName);
        assertEquals(projectName, projectName1);
        assertEquals(projectName.hashCode(),projectName1.hashCode());
        assertNotEquals(projectName,projectName2);
        assertNotEquals(projectName, null);
        assertNotEquals(projectName,obj);
        assertNotEquals(projectName.hashCode(),projectName2.hashCode());
    }
    @Test
    void sameValue() {
        //Arrange
        ProjectName projectName = ProjectName.createProjectName("ISEP");
        ProjectName projectName1 = ProjectName.createProjectName("ISEP2");
        //Assert
        assertTrue(projectName.sameValueAs(projectName));
        assertFalse(projectName.sameValueAs(projectName1));
        assertFalse(projectName.sameValueAs(null));
    }

}