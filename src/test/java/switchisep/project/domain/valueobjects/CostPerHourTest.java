package switchisep.project.domain.valueobjects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import switchisep.project.domain.valueobjects.CostPerHour;
import switchisep.project.domain.valueobjects.PercentageAllocation;

import static org.junit.jupiter.api.Assertions.*;

class CostPerHourTest {

    @ParameterizedTest
    @ValueSource(doubles = {5.0,5.15,12.1,16.9})
    void shouldCreateValidCostPerHourOneDecimals(double values) {
        // Arrange
        double costPerHour = values;
        // Act
        CostPerHour costPerHourOne = CostPerHour.createCostPerHour(costPerHour);
        CostPerHour costPerHourTwo = CostPerHour.createCostPerHour(costPerHour);
        // Assert
        assertEquals(costPerHourOne, costPerHourTwo);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-0.1,-0.0,-0.10, 0})
    void shouldNotCreateValidCostPerHour(double values) {
        // Arrange
        // Act
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CostPerHour.createCostPerHour(values);
        });
        // Assert
        assertEquals("Cost Per Hour Cannot be Be Negative nor Zero", exception.getMessage());
    }

    @Test
    void shouldNotCreateValidCostPerHourMoreThanTwoDecimals() {
        // Arrange
        double costPerHour = 1.123;
        // Act
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CostPerHour.createCostPerHour(costPerHour);
        });
        // Assert
        assertEquals("Cost Per Hour Cannot Have More Than Two Decimal", exception.getMessage());
    }

    @Test
    void checkOverride() {
        //Arrange
        //Act
        double   valueOne = 1.5;
        double valueTwo = 1.2;
        CostPerHour costOne = CostPerHour.createCostPerHour(valueOne);
        CostPerHour costTwo = CostPerHour.createCostPerHour(valueOne);
        CostPerHour costThree = CostPerHour.createCostPerHour(valueTwo);

        Object obj = new Object();
        // Assert
        assertEquals(costOne, costOne);
        assertEquals(costOne, costTwo);
        assertEquals(costOne.hashCode(), costTwo.hashCode());

        assertNotEquals(costOne, costThree);
        assertNotEquals(null, costOne);
        assertNotEquals(costOne, obj);

        assertNotEquals(costOne.hashCode(), costThree.hashCode());
    }

}