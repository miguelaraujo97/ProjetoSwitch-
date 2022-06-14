package switchisep.project.domain.valueobjects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import switchisep.project.domain.valueobjects.CostPerHour;
import switchisep.project.domain.valueobjects.PercentageAllocation;

import static org.junit.jupiter.api.Assertions.*;

class PercentageAllocationTest {


    @ParameterizedTest
    @ValueSource(ints = {50, 99, 1, 100,0})
    void shouldCreateValidPercentageAllocationBetweenZeroAndOneHundred(int values) {
        // Arrange
        // Act
        PercentageAllocation percentageAllocationOne = PercentageAllocation
                .createAllocation(values);
        PercentageAllocation percentageAllocationTwo = PercentageAllocation
                .createAllocation(values);
        // Assert
        assertEquals(percentageAllocationOne, percentageAllocationTwo);
    }

    @Test
    void shouldntCreateValidPercentageAllocationBelowZero() {

        // Arrange
        int percentageNumber = -1;

        // Act
        IllegalArgumentException exception = Assertions
                .assertThrows(IllegalArgumentException.class, () -> {
            PercentageAllocation.createAllocation(percentageNumber);
        });

        // Assert
        assertEquals("Percentage Allocation must be between 0 and 100",
                exception.getMessage());
    }

    @Test
    void shouldntCreateValidPercentageAllocationGreaterThanOneHundred() {

        // Arrange
        int percentageNumber = 101;

        // Act
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class, () -> {
            PercentageAllocation.createAllocation(percentageNumber);
        });

        // Assert
        assertEquals("Percentage Allocation must be between 0 and 100",
                exception.getMessage());
    }

    @Test
    void testEqualsForSameObject() {

        // Arrange
        int percentageNumber = 50;
        // Act
        PercentageAllocation percentageAllocation = PercentageAllocation.
                createAllocation(percentageNumber);
        // Assert
        assertEquals(percentageAllocation, percentageAllocation);
    }

    @Test
    void testEqualsForEqualObject() {

        // Arrange
        int percentageNumberOne = 50;
        int percentageNumberTwo = 50;

        // Act
        PercentageAllocation percentageAllocationOne = PercentageAllocation.
                createAllocation(percentageNumberOne);
        PercentageAllocation percentageAllocationTwo = PercentageAllocation.
                createAllocation(percentageNumberTwo);

        // Assert
        assertEquals(percentageAllocationOne, percentageAllocationTwo);
    }

    @Test
    void testEqualsForDifferentObjectDifferentClass() {

        // Arrange
        int percentageNumber = 50;

        // Act
        PercentageAllocation percentageAllocation = PercentageAllocation.
                createAllocation(percentageNumber);
        CostPerHour costPerHour = CostPerHour.createCostPerHour(1.0);

        // Assert
        assertNotEquals(null, percentageAllocation);
        assertNotEquals(percentageAllocation, costPerHour);
    }

    @Test
    void testEqualsForDifferentObjectSameClass() {

        // Arrange
        int percentageNumberOne = 50;
        int percentageNumberTwo = 55;

        // Act
        PercentageAllocation percentageAllocationOne = PercentageAllocation.
                createAllocation(percentageNumberOne);
        PercentageAllocation percentageAllocationTwo = PercentageAllocation.
                createAllocation(percentageNumberTwo);

        // Assert
        assertNotEquals(percentageAllocationOne, percentageAllocationTwo);
    }

    @Test
    void testHashCodeDifferentObjectsSameClass() {

        // Arrange
        int percentageNumberOne = 1;

        int percentageNumberTwo = 2;

        // Act
        PercentageAllocation percentageAllocationOne = PercentageAllocation.
                createAllocation(percentageNumberOne);

        PercentageAllocation percentageAllocationTwo = PercentageAllocation.
                createAllocation(percentageNumberTwo);

        // Assert
        assertNotEquals(percentageAllocationOne.hashCode(),
                percentageAllocationTwo.hashCode());
    }

    @Test
    void testHashCodeSameObject() {

        // Arrange
        int percentageNumber = 0;

        // Act
        PercentageAllocation percentageAllocationOne = PercentageAllocation.
                createAllocation(percentageNumber);

        // Assert
        assertEquals(percentageAllocationOne.hashCode(),
                percentageAllocationOne.hashCode());
    }
}