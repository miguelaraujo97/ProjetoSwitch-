package switchisep.project.domain.valueobjects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import static org.junit.jupiter.api.Assertions.*;

class ProjectCodeTest {

    @Test
    void projectCodeNull() {
        //Arrange
        String projectCode = null;
        //Act & Assert
        Exception exception =
                Assertions.assertThrows(NullPointerException.class, () -> {
                    ProjectCode.createProjectCode(projectCode);
                });
        String expectedMessage = "A code must be inserted";
        String actualMessage = exception.getMessage();
        // Assert
        assertEquals(expectedMessage, actualMessage);
    }



    @ParameterizedTest
    @ValueSource(strings = {"A", "1"})
    void projectCodeWrong(String code) {
        //Assert
        Exception exception =
                Assertions.assertThrows(IllegalArgumentException.class, () -> {
                    ProjectCode.createProjectCode(code);
                });
        String expectedMessage = "The codes must be alphanumerical";
        //Act
        String actualMessage = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, actualMessage);
    }



    @ParameterizedTest
    @ValueSource(strings = {"", " ", "     ", "\t", "\n"})
    void emptyStringWithMultipleSpacesProjectCode(String code) {
        //Assert
        Exception exception =
                Assertions.assertThrows(IllegalArgumentException.class, () -> {
            ProjectCode.createProjectCode(code);
        });
        String expectedMessage = "The code can not be an empty string";
        //Act
        String actualMessage = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, actualMessage);
    }

    //--------------------------------------------------------------------------
    //Tests for equals and hashCode methods
    @Test
    void checkEqualsHasCodeOverride() {
        //Arrange
        String projectCodeData = "A123";
        String projectCodeData1 = "A122";
        String projectCodeData2 = "A123";
        Object obj = new Object();
        //Act
        ProjectCode projectCode =
                ProjectCode.createProjectCode(projectCodeData);
        ProjectCode projectCode1 =
                ProjectCode.createProjectCode(projectCodeData1);
        ProjectCode projectCode2 =
                ProjectCode.createProjectCode(projectCodeData2);
        // Assert
        //Equals
        assertEquals(projectCode, projectCode);
        assertEquals(projectCode, projectCode2);
        //Not Equals
        assertNotEquals(projectCode, projectCode1);
        assertNotEquals(projectCode, null);
        assertNotEquals(projectCode, obj);
        //Hash code
        assertEquals(projectCode.hashCode(), projectCode2.hashCode());
        assertNotEquals(projectCode.hashCode(), projectCode1.hashCode());
    }

}