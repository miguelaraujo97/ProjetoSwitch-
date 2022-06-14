package switchisep.project.domain.valueobjects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class FunctionTest {

    @ParameterizedTest
    @ValueSource(strings = {" ", "     ", "\t", "\n", ""})
    void createInvalidFunction_invalidFormat(String function) {
        //Assert
        Exception exception =  //Act & Assert
                Assertions.assertThrows(IllegalArgumentException.class, () -> {
                    Function.createFunction(function);
                });
        String expectedMessage = "Function cannot be null or empty";
        //Act
        String actualMessage = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, actualMessage);
    }


    @ParameterizedTest
    @ValueSource(strings = {"Tester", "Q&A", "Developer", "Dev ", "Dev"})
    void createValid_Function(String function) {
        //Act
        Function function1 = Function.createFunction(function);
        String expectedFunction = function1.getFunction();
        //Assert
        assertEquals(expectedFunction, function);
    }
    @Test
    void testEqualsForNull() {
        //Arrange
        String function = null;
        //Assert
        Exception exception =  //Act & Assert
                Assertions.assertThrows(IllegalArgumentException.class, () -> {
                    Function.createFunction(function);
                });
        String expectedMessage = "Function cannot be null or empty";
        //Act
        String actualMessage = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, actualMessage);
    }



    //----------------------------------------------------------------------------------------------------------------//
    //Tests for equals and hashCode methods

    @Test
    void testSamValueAs_Null(){

        //Arrange
        String functionTest = "Developer";
        Function function = Function.createFunction(functionTest);

        //Act
        boolean result = function.sameValueAs(null);

        //Assert
        assertFalse(result);
    }

    @Test
    void testEqualsForSameObject() {
        //Arrange
        String functionString = "Developer";
        //Act
        Function function = Function.createFunction(functionString);
        //Assert
        assertEquals(function, function);
    }

    @Test
    void testEqualsForEqualObject() {
        //Arrange
        String functionString = "Developer";
        //Act
        Function function1 = Function.createFunction(functionString);
        Function function2 = Function.createFunction(functionString);
        //Assert
        assertEquals(function1, function2);
    }

    @Test
    void testEqualsForDifferentObject_DifferentClass() {
        //Arrange
        String functionString = "Developer";
        //Act
        Function function1 = Function.createFunction(functionString);
        Object obj = new Object();
        //Assert
        assertNotEquals(function1, obj);
    }

    @Test
    void testEqualsForDifferentObject_SameClass() {
        //Arrange
        String functionString = "Developer";
        String functionString2 = "Tester";
        //Act
        Function function1 = Function.createFunction(functionString);
        Function function2 = Function.createFunction(functionString2);
        //Assert
        assertNotEquals(function1, function2);
    }

    @Test
    void testHashCodeForDifferentObject_SameClass() {
        //Arrange
        String functionString = "Developer";
        String functionString2 = "Tester";
        //Act
        Function function1 = Function.createFunction(functionString);
        Function function2 = Function.createFunction(functionString2);
        //Assert
        assertNotEquals(function1.hashCode(), function2.hashCode());

    }

    @Test
    void testHashCodeForSameObject() {
        //Arrange
        String functionString = "Developer";
        //Act
        Function function1 = Function.createFunction(functionString);
        //Assert
        assertEquals(function1.hashCode(), function1.hashCode());
    }

}