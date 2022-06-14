package switchisep.project.domain.valueobjects;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class DescriptionTest {
    @Test
    void testCorrectGet() {
        //arrange
        Description description = Description.createDescription(
                "test");
        String expected = "test";
        //act
        String result = description.getDescription();
        //assert
        assertEquals(expected, result);
    }


    @ParameterizedTest
    @ValueSource(strings = {" ", "     ", "\t", "\n", ""})
    void testDescriptionNull(String description) {
        //Arrange
        //Act
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () ->
                        Description.createDescription(description));
        //Act
        String expectedMessage = "Description can't be empty nor blank";

        String actualMessage = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, actualMessage);
    }


    @Test
    void checkOverride() {
        //Arrange
        //Act
        Description description = Description.createDescription(
                "Test");
        Description description1 = Description.createDescription(
                "Test");
        Description description2 = Description.createDescription(
                "Test1");
        Object obj = new Object();
        // Assert
        assertEquals(description, description);
        assertEquals(description, description1);
        assertEquals(description.hashCode(), description1.hashCode());

        assertNotEquals(description, description2);
        assertNotEquals(description, null);
        assertNotEquals(description, obj);
        assertNotEquals(description.hashCode(),
                description2.hashCode());
    }

    @Test
    void testSamValueAs_Null(){

        //Arrange
        String descriptionString = "description";
        Description description = Description.createDescription(descriptionString);

        //Act
        boolean result = description.sameValueAs(null);

        //Assert
        assertFalse(result);
    }

    @Test
    void testSamValueAs_True(){

        //Arrange
        String descriptionString = "description";
        Description description = Description.createDescription(descriptionString);

        //Act
        boolean result = description.sameValueAs(description);

        //Assert
        assertTrue(result);
    }
}