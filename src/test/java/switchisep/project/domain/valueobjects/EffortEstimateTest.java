package switchisep.project.domain.valueobjects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class EffortEstimateTest {
    @Test
    void testCorrectGet() {
        //arrange
        EffortEstimate effortEstimate = EffortEstimate.
                createEffortEstimate(1);
        int expected = 1;
        //act
        int result = effortEstimate.getEffortEstimateValue();
        //assert
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1,-2,-3,-5,-8,-12,-21,4, 6, 7, 9, 10, 11, 13, 14, 15, 16, 17, 18, 19,
            20, 22})
    void is_not_a_Fibonacci_number(int values) {
        //Assert
        Exception exception =
                Assertions.assertThrows(IllegalArgumentException.class, () -> {
            EffortEstimate.createEffortEstimate(values);
        });
        String expectedMessage = "Value is not a fibonacci number";
        //Act
        String actualMessage = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void checkOverride() {
        //Arrange

        //Act
        EffortEstimate effortEstimate = EffortEstimate.
                createEffortEstimate(1);
        EffortEstimate effortEstimate1 = EffortEstimate.
                createEffortEstimate(1);
        EffortEstimate effortEstimate3 = EffortEstimate.
                createEffortEstimate(3);
        Object newObj = new Object();
        // Assert
        assertEquals(effortEstimate, effortEstimate);
        assertEquals(effortEstimate, effortEstimate1);
        assertEquals(effortEstimate.hashCode(), effortEstimate1.hashCode());

        assertNotEquals(effortEstimate, effortEstimate3);
        assertNotEquals(effortEstimate, null);
        assertNotEquals(effortEstimate.hashCode(), effortEstimate3.hashCode());
        assertNotEquals(effortEstimate, newObj);
    }

}