package switchisep.project.domain.valueobjects;

import  org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SprintDurationTest {

    @Test
    void createValidSprintDurationObject() {

        //Arrange
        int sprintDuration = 1;

        //Act & Assert
        assertNotNull(SprintDuration.createSprintDuration(sprintDuration));
    }

    @Test
    void createZeroSprintDuration() {

        //Arrange, Act & Assert
        assertThrows(IllegalArgumentException.class, () -> SprintDuration.createSprintDuration(0));
    }

    @Test
    void createNegativeSprintDuration() {

        //Arrange, Act & Assert
        assertThrows(IllegalArgumentException.class, () -> SprintDuration.createSprintDuration(-1));
    }

    @Test
    void getSprintDuration() {

        //Arrange
        int sprintDurationValue = 1;
        SprintDuration sprintDuration1 = SprintDuration.createSprintDuration(sprintDurationValue);

        //Act
        int actual = sprintDuration1.getSprintDuration();
        int expected = 1;

        //Assert
        assertEquals(actual, expected);
    }

    @Test
    void bothObjectsAreEqual() {

        //Arrange
        int sprintDuration = 1;
        SprintDuration sprintDuration1 = SprintDuration.createSprintDuration(sprintDuration);
        SprintDuration sprintDuration2 = SprintDuration.createSprintDuration(sprintDuration);

        //Act
        boolean result = sprintDuration1.equals(sprintDuration2);

        //Assert
        assertEquals(sprintDuration1, sprintDuration2);
        assertTrue(result);
    }

    @Test
    void comparingTheSameObject() {

        //Arrange
        int sprintDuration = 1;
        SprintDuration sprintDuration1 = SprintDuration.createSprintDuration(sprintDuration);

        //Act & Assert
        assertEquals(sprintDuration1, sprintDuration1);
    }

    @Test
    void bothObjectsAreDifferent() {

        //Arrange
        int sprintDurationValue1 = 1;
        int sprintDurationValue2 = 2;
        SprintDuration sprintDuration1 = SprintDuration.createSprintDuration(sprintDurationValue1);
        SprintDuration sprintDuration2 = SprintDuration.createSprintDuration(sprintDurationValue2);

        //Act
        boolean result = sprintDuration1.equals(sprintDuration2);

        //Act & Assert
        assertNotEquals(sprintDuration1, sprintDuration2);
        assertFalse(result);
    }

    @Test
    void oneOfTheTwoObjectsIsNull() {

        //Arrange
        int sprintDurationValue1 = 1;
        SprintDuration sprintDuration1 = SprintDuration.createSprintDuration(sprintDurationValue1);
        SprintDuration sprintDuration2 = null;

        //Act
        boolean result = sprintDuration1.equals(sprintDuration2);

        //Act & Assert
        assertNotEquals(sprintDuration1, sprintDuration2);
        assertFalse(result);
    }

    @Test
    void objectsAreFromDifferentClasses(){
        //Arrange
        int sprintDurationValue1 = 1;
        SprintDuration sprintDuration1 = SprintDuration.createSprintDuration(sprintDurationValue1);
        Object nativeObject = new Object();
        //Act
        boolean result = sprintDuration1.equals(nativeObject);

        //Act & Assert
        assertFalse(result);

    }

    @Test
    void hashCodeIsTheSame() {

        //Arrange
        int sprintDuration = 1;
        SprintDuration sprintDuration1 = SprintDuration.createSprintDuration(sprintDuration);
        SprintDuration sprintDuration2 = SprintDuration.createSprintDuration(sprintDuration);

        //Act & Assert
        assertEquals(sprintDuration1.hashCode(), sprintDuration2.hashCode());
    }

    @Test
    void hashCodeIsDifferent() {

        //Arrange
        int sprintDurationValue1 = 1;
        int sprintDurationValue2 = 2;
        SprintDuration sprintDuration1 = SprintDuration.createSprintDuration(sprintDurationValue1);
        SprintDuration sprintDuration2 = SprintDuration.createSprintDuration(sprintDurationValue2);

        //Act & Assert
        assertNotEquals(sprintDuration1.hashCode(), sprintDuration2.hashCode());
    }

}