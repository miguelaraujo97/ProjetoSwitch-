package switchisep.project.domain.valueobjects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import switchisep.project.error_handling.BusinessRulesException;
import switchisep.project.error_handling.EmptyObjectException;

import static org.junit.jupiter.api.Assertions.*;

class NameTest {

    @Test
    void getName() {
        // Arrange
        String actualName = "Celso Castro";
        Name nameOne = Name.createName(actualName);
        // Act
        String expectedResult = nameOne.getInputName();
        // Assert
        assertEquals(expectedResult, actualName);
    }

    @Test
    void emptyStringProjectName() {

        //Arrange

        String name = "";

        //Act & Assert
        Assertions.assertThrows(EmptyObjectException.class, () -> {
            Name.createName(name);
        });

    }

    @Test
    void createInvalidName_nullInput() {
        //Arrange
        String name = null;
        //Act & Assert
        Assertions.assertThrows(NullPointerException.class, () -> {
            Name.createName(name);
        });
    }


    @Test
    void createInvalidName_ContainsNumericValues() {
        // Arrange
        String name = "Celso Castro1";
        // Act
        BusinessRulesException exception =
                Assertions.assertThrows(BusinessRulesException.class, () ->
                        Name.createName(name));
        String expectedMessage = "Name must only contain letters";
        String actualMessage = exception.getMessage();
        // Assert
        assertEquals(expectedMessage, actualMessage);
    }


    @Test
    void sameValue() {
        //Arrange
        String profileName = "name to test";
        String profileName2 = "Other name to test";
        //Act
        Name name = Name.createName(profileName);
        Name name1 = Name.createName(profileName2);
        //Assert
        assertFalse(name.sameValueAs(name1));
        assertTrue(name.sameValueAs(name));
        assertFalse(name.sameValueAs(null));
    }

    @Test
    void checkOverride() {
        //Arrange
        //Act
        String profileName = "name to test";
        String profileName2 = "Other name to test";
        Name name = Name.createName(profileName);
        Name name1 = Name.createName(profileName);
        Name name2 = Name.createName(profileName2);
        Object obj = new Object();
        // Assert
        assertEquals(name, name);
        assertEquals(name, name1);
        assertEquals(name.hashCode(), name1.hashCode());

        assertNotEquals(name, name2);
        assertNotEquals(null, name);
        assertNotEquals(name, obj);

        assertNotEquals(name.hashCode(), name2.hashCode());
    }
}
