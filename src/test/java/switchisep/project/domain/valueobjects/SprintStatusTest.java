package switchisep.project.domain.valueobjects;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SprintStatusTest {

    @Test
    void createNewValidSprintStatusWithNotStartedValue() {

        //Arrange
        SprintStatus.SprintStatusEnum statusValue = SprintStatus.SprintStatusEnum.NOT_STARTED;
        SprintStatus newStatus = SprintStatus.createSprintStatus(statusValue);
        String expected = "NOT_STARTED";

        //Act
        String actual = newStatus.getSprintStatusDescription();

        //Assert
        assertNotNull(newStatus);
        assertEquals(actual, expected);
    }

    @Test
    void createNewValidSprintStatusWithStartedValue() {

        //Arrange
        SprintStatus.SprintStatusEnum statusValue = SprintStatus.SprintStatusEnum.STARTED;
        SprintStatus newStatus = SprintStatus.createSprintStatus(statusValue);
        String expected = "STARTED";

        //Act
        String actual = newStatus.getSprintStatusDescription();

        //Assert
        assertNotNull(newStatus);
        assertEquals(actual, expected);
    }

    @Test
    void createNewValidSprintStatusWithFinishedValue() {

        //Arrange
        SprintStatus.SprintStatusEnum statusValue = SprintStatus.SprintStatusEnum.FINISHED;
        SprintStatus newStatus = SprintStatus.createSprintStatus(statusValue);
        String expected = "FINISHED";

        //Act
        String actual = newStatus.getSprintStatusDescription();

        //Assert
        assertNotNull(newStatus);
        assertEquals(actual, expected);
    }

    @Test
    void bothObjectsAreEqual() {

        //Arrange
        SprintStatus.SprintStatusEnum statusValue1 = SprintStatus.SprintStatusEnum.NOT_STARTED;
        SprintStatus.SprintStatusEnum statusValue2 = SprintStatus.SprintStatusEnum.NOT_STARTED;
        SprintStatus newStatus1 = SprintStatus.createSprintStatus(statusValue1);
        SprintStatus newStatus2 = SprintStatus.createSprintStatus(statusValue2);

        //Act & Assert
        assertEquals(newStatus1, newStatus2);
    }

    @Test
    void comparingTheSameObject() {

        //Arrange
        SprintStatus.SprintStatusEnum statusValue1 = SprintStatus.SprintStatusEnum.NOT_STARTED;
        SprintStatus newStatus1 = SprintStatus.createSprintStatus(statusValue1);

        //Act & Assert
        assertEquals(newStatus1, newStatus1);
    }

    @Test
    void bothObjectsAreDifferent() {

        //Arrange
        SprintStatus.SprintStatusEnum statusValue1 = SprintStatus.SprintStatusEnum.NOT_STARTED;
        SprintStatus.SprintStatusEnum statusValue2 = SprintStatus.SprintStatusEnum.FINISHED;
        SprintStatus newStatus1 = SprintStatus.createSprintStatus(statusValue1);
        SprintStatus newStatus2 = SprintStatus.createSprintStatus(statusValue2);

        //Act & Assert
        assertNotEquals(newStatus1, newStatus2);
    }

    @Test
    void oneOfTheTwoObjectsIsNull() {

        //Arrange
        SprintStatus.SprintStatusEnum statusValue1 = SprintStatus.SprintStatusEnum.NOT_STARTED;
        SprintStatus newStatus1 = SprintStatus.createSprintStatus(statusValue1);
        SprintStatus newStatus2 = null;

        //Act & Assert
        assertNotEquals(newStatus1, newStatus2);
    }

    @Test
    void hashCodeIsTheSame() {

        //Arrange
        SprintStatus.SprintStatusEnum statusValue1 = SprintStatus.SprintStatusEnum.NOT_STARTED;
        SprintStatus.SprintStatusEnum statusValue2 = SprintStatus.SprintStatusEnum.NOT_STARTED;
        SprintStatus newStatus1 = SprintStatus.createSprintStatus(statusValue1);
        SprintStatus newStatus2 = SprintStatus.createSprintStatus(statusValue2);

        //Act & Assert
        assertEquals(newStatus1.hashCode(), newStatus2.hashCode());
    }

    @Test
    void hashCodeIsDifferent() {

        //Arrange
        SprintStatus.SprintStatusEnum statusValue1 = SprintStatus.SprintStatusEnum.NOT_STARTED;
        SprintStatus.SprintStatusEnum statusValue2 = SprintStatus.SprintStatusEnum.FINISHED;
        SprintStatus newStatus1 = SprintStatus.createSprintStatus(statusValue1);
        SprintStatus newStatus2 = SprintStatus.createSprintStatus(statusValue2);

        //Act & Assert
        assertNotEquals(newStatus1.hashCode(), newStatus2.hashCode());
    }

    @Test
    void testSameValueAs() {

        //Arrange
        SprintStatus.SprintStatusEnum statusValue1 = SprintStatus.SprintStatusEnum.NOT_STARTED;
        SprintStatus.SprintStatusEnum statusValue2 = SprintStatus.SprintStatusEnum.STARTED;
        SprintStatus newStatus1 = SprintStatus.createSprintStatus(statusValue1);
        SprintStatus newStatus2 = SprintStatus.createSprintStatus(statusValue1);
        SprintStatus newStatus3 = SprintStatus.createSprintStatus(statusValue2);
        //Assert
        assertTrue(newStatus1.sameValueAs(newStatus2));
        assertFalse(newStatus1.sameValueAs(newStatus3));
        assertFalse(newStatus1.sameValueAs(null));
    }


}