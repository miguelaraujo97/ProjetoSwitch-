package switchisep.project.domain.valueobjects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class HoursSpentTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 250})
    void valid_format_for_hours(int hours) {
        //arrange
        HoursSpent hoursSpent = HoursSpent.createHoursSpent(hours);
        //act
        int result = hoursSpent.getHoursSpent();
        //assert
        assertEquals(hours, result);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    void invalid_format_negative_and_zero_values(int hours) {
        //Assert
        Exception exception =
                Assertions.assertThrows(IllegalArgumentException.class, () -> {
                    HoursSpent.createHoursSpent(hours);
                });
        String expectedMessage = "Hours spent can't be negative";
        //Act
        String actualMessage = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void checkOverride() {
        //Arrange

        //Act
        HoursSpent hoursSpent = HoursSpent.createHoursSpent(1);
        HoursSpent hoursSpent1 = HoursSpent.createHoursSpent(1);
        HoursSpent hoursSpent2 = HoursSpent.createHoursSpent(2);
        Object obj = new Object();
        // Assert
        assertEquals(hoursSpent, hoursSpent);
        assertEquals(hoursSpent, hoursSpent1);
        assertEquals(hoursSpent.hashCode(), hoursSpent1.hashCode());

        assertNotEquals(hoursSpent, hoursSpent2);
        assertNotEquals(hoursSpent, null);
        assertNotEquals(hoursSpent, obj);
        assertNotEquals(hoursSpent.hashCode(), hoursSpent2.hashCode());
    }

}