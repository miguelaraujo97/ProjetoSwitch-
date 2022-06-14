package switchisep.project.domain.valueobjects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PercentageOfExecutionTest {


    @ParameterizedTest
    @ValueSource(ints = {-100, -1})
    void invalid_format_negative_and_zero_values(int hours) {
        //arrange

        //act & assert
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            PercentageOfExecution.createPercentageOfExecution(-1);
        });
        //assert
        assertEquals(exception.getMessage(), "should be between " +
                "0 and 100");
    }

    @Test
    void checkBiggerThanOneHundred() {
        //arrange
        //act & assert
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            PercentageOfExecution.createPercentageOfExecution(101);
        });
        //assert
        assertEquals(exception.getMessage(), "should be between " +
                "0 and 100");
    }

    @Test
    void checkOverride(){
        //Arrange

        //Act
        PercentageOfExecution percentageOfExecution =
                PercentageOfExecution.createPercentageOfExecution(0);
        PercentageOfExecution percentageOfExecution2 =
                PercentageOfExecution.createPercentageOfExecution(0);
        PercentageOfExecution percentageOfExecution3 =
                PercentageOfExecution.createPercentageOfExecution(100);
        Object obj = new Object();
        // Assert
        assertEquals(percentageOfExecution, percentageOfExecution);
        assertEquals(percentageOfExecution, percentageOfExecution2);

        assertEquals(percentageOfExecution.hashCode(),
                percentageOfExecution2.hashCode());

        assertNotEquals(percentageOfExecution,percentageOfExecution3);
        assertNotEquals(percentageOfExecution, null);
        assertNotEquals(percentageOfExecution,obj);
        assertNotEquals(percentageOfExecution.hashCode(),
                percentageOfExecution3.hashCode());
    }
}

