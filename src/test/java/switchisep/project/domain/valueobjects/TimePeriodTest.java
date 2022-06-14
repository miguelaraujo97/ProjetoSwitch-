package switchisep.project.domain.valueobjects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Testing for Time Period
 *
 */

class TimePeriodTest {

    @Test
    void shouldCreateTimePeriod() {

        // Arrange
        LocalDate startDate = LocalDate.of(2030, 1, 1);
        LocalDate endDate = LocalDate.of(2040, 1, 1);

        // Act

        TimePeriod timePeriodOne = TimePeriod.createTimePeriod(startDate, endDate);

        TimePeriod timePeriodTwo = TimePeriod.createTimePeriod(startDate, endDate);

        // Assert
        assertEquals(timePeriodOne, timePeriodTwo);

    }

    @Test
    void shouldntCreateTimePeriodEndDateBeforeStartDate() {

        // Arrange
        LocalDate startDate = LocalDate.of(2030, 1, 1);
        LocalDate endDate = LocalDate.of(2040, 1, 1);

        // Act + Assert
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->{
            TimePeriod.createTimePeriod(endDate, startDate);
        });

        assertEquals(exception.getMessage(), "End Date Can Not Be Before The Start Date");

    }



    @Test
    void testEqualsForSameObject() {

        // Arrange
        LocalDate startDate = LocalDate.of(2030, 1, 1);
        LocalDate endDate = LocalDate.of(2030, 1, 2);

        // Act
        TimePeriod timePeriod = TimePeriod.createTimePeriod(startDate, endDate);

        // Assert
        assertEquals(timePeriod, timePeriod);
    }

    @Test
    void testEqualsForEqualObject() {

        // Arrange
        LocalDate startDateOne = LocalDate.of(2030, 1, 1);
        LocalDate endDateOne = LocalDate.of(2030, 1, 2);

        LocalDate startDateTwo = LocalDate.of(2030, 1, 1);
        LocalDate endDateTwo = LocalDate.of(2030, 1, 2);

        // Act
        TimePeriod timePeriodOne = TimePeriod.createTimePeriod(startDateOne, endDateOne);
        TimePeriod timePeriodTwo = TimePeriod.createTimePeriod(startDateTwo, endDateTwo);

        // Assert
        assertEquals(timePeriodOne, timePeriodTwo);
    }

    @Test
    void testEqualsForDifferentDifferentClass() {

        // Arrange
        LocalDate startDate = LocalDate.of(2030, 1, 1);
        LocalDate endDate = LocalDate.of(2030, 1, 2);

        // Act
        TimePeriod timePeriod = TimePeriod.createTimePeriod(startDate, endDate);
        Object obj = new Object();

        // Assert
        assertNotEquals(timePeriod, obj);
    }

    @Test
    void testEqualsForDifferentObjectSameClass() {

        // Arrange
        LocalDate startDateOne = LocalDate.of(2030, 1, 1);
        LocalDate endDateOne = LocalDate.of(2030, 1, 2);

        LocalDate startDateTwo = LocalDate.of(2031, 1, 1);
        LocalDate endDateTwo = LocalDate.of(2031, 1, 2);

        // Act
        TimePeriod timePeriodOne = TimePeriod.createTimePeriod(startDateOne, endDateOne);
        TimePeriod timePeriodTwo = TimePeriod.createTimePeriod(startDateTwo, endDateTwo);

        // Assert
        assertNotEquals(timePeriodOne, timePeriodTwo);
    }

    @Test
    void testHashCodeForDifferentObjectSameClass() {

        // Arrange

        LocalDate startDateOne = LocalDate.of(2030, 1, 1);
        LocalDate endDateOne = LocalDate.of(2040, 1, 1);

        LocalDate startDateTwo = LocalDate.of(2035, 1, 1);
        LocalDate endDateTwo = LocalDate.of(2045, 1, 1);

        // Act
        TimePeriod timePeriodOne = TimePeriod.createTimePeriod(startDateOne, endDateOne);
        TimePeriod timePeriodTwo = TimePeriod.createTimePeriod(startDateTwo, endDateTwo);

        // Assert
        assertNotEquals(timePeriodOne.hashCode(), timePeriodTwo.hashCode());
    }

    @Test
    void testHashCodeForSameObject() {

        // Arrange
        LocalDate startDate = LocalDate.of(2030, 1, 1);
        LocalDate endDate = LocalDate.of(2040, 1, 1);

        // Act
        TimePeriod timePeriodOne = TimePeriod.createTimePeriod(startDate, endDate);

        // Assert
        assertEquals(timePeriodOne.hashCode(), timePeriodOne.hashCode());
    }

    @Test
    void sameValueAs_testForNull(){
        // Arrange
        LocalDate startDate = LocalDate.of(2030, 1, 1);
        LocalDate endDate = LocalDate.of(2040, 1, 1);
        TimePeriod timePeriodOne = TimePeriod.createTimePeriod(startDate, endDate);

        // Act
        boolean result = timePeriodOne.sameValueAs(null);

        // Act
        assertFalse(result);

    }

    @Test
    void sameValueAs_testForBothAttributesEqual(){
        // Arrange
        LocalDate startDate1 = LocalDate.of(2030, 1, 1);
        LocalDate endDate1 = LocalDate.of(2040, 1, 1);
        TimePeriod timePeriodOne = TimePeriod.createTimePeriod(startDate1, endDate1);

        LocalDate startDate2 = LocalDate.of(2030, 1, 1);
        LocalDate endDate2 = LocalDate.of(2040, 1, 1);
        TimePeriod timePeriodTwo = TimePeriod.createTimePeriod(startDate2, endDate2);

        // Act
        boolean result = timePeriodOne.sameValueAs(timePeriodTwo);

        // Act
        assertTrue(result);
    }

    @Test
    void sameValueAs_testForDiffEndDate(){
        // Arrange
        LocalDate startDate1 = LocalDate.of(2030, 1, 1);
        LocalDate endDate1 = LocalDate.of(2040, 1, 1);
        TimePeriod timePeriodOne = TimePeriod.createTimePeriod(startDate1, endDate1);

        LocalDate startDate2 = LocalDate.of(2030, 1, 1);
        LocalDate endDate2 = LocalDate.of(2042, 1, 1);
        TimePeriod timePeriodTwo = TimePeriod.createTimePeriod(startDate2, endDate2);

        // Act
        boolean result = timePeriodOne.sameValueAs(timePeriodTwo);

        // Act
        assertFalse(result);
    }

    @Test
    void sameValueAs_testForDiffStartDate(){
        // Arrange
        LocalDate startDate1 = LocalDate.of(2030, 1, 1);
        LocalDate endDate1 = LocalDate.of(2040, 1, 1);
        TimePeriod timePeriodOne = TimePeriod.createTimePeriod(startDate1, endDate1);

        LocalDate startDate2 = LocalDate.of(2032, 1, 1);
        LocalDate endDate2 = LocalDate.of(2040, 1, 1);
        TimePeriod timePeriodTwo = TimePeriod.createTimePeriod(startDate2, endDate2);

        // Act
        boolean result = timePeriodOne.sameValueAs(timePeriodTwo);

        // Act
        assertFalse(result);
    }

    @Test
    void sameValueAs_testForDiffStartDateAndEndDate(){
        // Arrange
        LocalDate startDate1 = LocalDate.of(2030, 1, 1);
        LocalDate endDate1 = LocalDate.of(2040, 1, 1);
        TimePeriod timePeriodOne = TimePeriod.createTimePeriod(startDate1, endDate1);

        LocalDate startDate2 = LocalDate.of(2032, 1, 1);
        LocalDate endDate2 = LocalDate.of(2042, 1, 1);
        TimePeriod timePeriodTwo = TimePeriod.createTimePeriod(startDate2, endDate2);

        // Act
        boolean result = timePeriodOne.sameValueAs(timePeriodTwo);

        // Act
        assertFalse(result);
    }

    @Test
    void testGetStartDate(){
        // Arrange
        LocalDate startDate = LocalDate.of(2030, 1, 1);
        LocalDate endDate = LocalDate.of(2040, 1, 1);
        TimePeriod timePeriodOne = TimePeriod.createTimePeriod(startDate, endDate);

        // Act
        LocalDate startDateFound = timePeriodOne.getStartDate();

        // Act
        assertEquals(startDate, startDateFound);

    }

    @Test
    void testGetEndDate(){
        // Arrange
        LocalDate startDate = LocalDate.of(2030, 1, 1);
        LocalDate endDate = LocalDate.of(2040, 1, 1);
        TimePeriod timePeriodOne = TimePeriod.createTimePeriod(startDate, endDate);

        // Act
        LocalDate endDateFound = timePeriodOne.getEndDate();

        // Act
        assertEquals(endDate, endDateFound);

    }


}