package switchisep.project.domain.valueobjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScrumBoardStatusTest {

    @Test
    void createNewValidScrumBoardStatusWithToDoValue() {

        //Arrange
        ScrumBoardStatus.ScrumBoardStatusEnum statusValue = ScrumBoardStatus.ScrumBoardStatusEnum.TO_DO;
        ScrumBoardStatus newStatus = ScrumBoardStatus.createScrumBoardStatus(statusValue);
        String expected = "TO_DO";

        //Act
        String actual = newStatus.getScrumBoardStatusDescription();

        //Assert
        assertNotNull(newStatus);
        assertEquals(actual, expected);
    }

    @Test
    void createNewValidScrumBoardStatusWithInProgressValue() {

        //Arrange
        ScrumBoardStatus.ScrumBoardStatusEnum statusValue = ScrumBoardStatus.ScrumBoardStatusEnum.IN_PROGRESS;
        ScrumBoardStatus newStatus = ScrumBoardStatus.createScrumBoardStatus(statusValue);
        String expected = "IN_PROGRESS";

        //Act
        String actual = newStatus.getScrumBoardStatusDescription();

        //Assert
        assertNotNull(newStatus);
        assertEquals(actual, expected);
    }

    @Test
    void createNewValidScrumBoardStatusWithDoneValue() {

        //Arrange
        ScrumBoardStatus.ScrumBoardStatusEnum statusValue = ScrumBoardStatus.ScrumBoardStatusEnum.DONE;
        ScrumBoardStatus newStatus = ScrumBoardStatus.createScrumBoardStatus(statusValue);
        String expected = "DONE";

        //Act
        String actual = newStatus.getScrumBoardStatusDescription();

        //Assert
        assertNotNull(newStatus);
        assertEquals(actual, expected);
    }

    @Test
    void bothObjectsAreEqual() {

        //Arrange
        ScrumBoardStatus.ScrumBoardStatusEnum statusValue1 = ScrumBoardStatus.ScrumBoardStatusEnum.DONE;
        ScrumBoardStatus.ScrumBoardStatusEnum statusValue2 = ScrumBoardStatus.ScrumBoardStatusEnum.DONE;
        ScrumBoardStatus newStatus1 = ScrumBoardStatus.createScrumBoardStatus(statusValue1);
        ScrumBoardStatus newStatus2 = ScrumBoardStatus.createScrumBoardStatus(statusValue2);

        //Act & Assert
        assertEquals(newStatus1, newStatus2);
    }

    @Test
    void comparingTheSameObject() {

        //Arrange
        ScrumBoardStatus.ScrumBoardStatusEnum statusValue1 = ScrumBoardStatus.ScrumBoardStatusEnum.DONE;
        ScrumBoardStatus newStatus1 = ScrumBoardStatus.createScrumBoardStatus(statusValue1);

        //Act & Assert
        assertEquals(newStatus1, newStatus1);
    }

    @Test
    void bothObjectsAreDifferent() {

        //Arrange
        ScrumBoardStatus.ScrumBoardStatusEnum statusValue1 = ScrumBoardStatus.ScrumBoardStatusEnum.DONE;
        ScrumBoardStatus.ScrumBoardStatusEnum statusValue2 = ScrumBoardStatus.ScrumBoardStatusEnum.IN_PROGRESS;
        ScrumBoardStatus newStatus1 = ScrumBoardStatus.createScrumBoardStatus(statusValue1);
        ScrumBoardStatus newStatus2 = ScrumBoardStatus.createScrumBoardStatus(statusValue2);

        //Act & Assert
        assertNotEquals(newStatus1, newStatus2);
    }

    @Test
    void oneOfTheTwoObjectsIsNull() {

        //Arrange
        ScrumBoardStatus.ScrumBoardStatusEnum statusValue1 = ScrumBoardStatus.ScrumBoardStatusEnum.DONE;
        ScrumBoardStatus newStatus1 = ScrumBoardStatus.createScrumBoardStatus(statusValue1);
        ScrumBoardStatus newStatus2 = null;

        //Act & Assert
        assertNotEquals(newStatus1, newStatus2);
    }

    @Test
    void objectsAreFromDifferentClasses(){
        //Arrange
        ScrumBoardStatus.ScrumBoardStatusEnum statusValue1 = ScrumBoardStatus.ScrumBoardStatusEnum.DONE;
        ScrumBoardStatus newStatus1 = ScrumBoardStatus.createScrumBoardStatus(statusValue1);
        Object nativeObject = new Object();

        //Act & Assert
        assertNotEquals(newStatus1, nativeObject);

    }

    @Test
    void sameValueAs_testForNull(){
        //Arrange
        ScrumBoardStatus.ScrumBoardStatusEnum statusValue1 = ScrumBoardStatus.ScrumBoardStatusEnum.DONE;
        ScrumBoardStatus newStatus1 = ScrumBoardStatus.createScrumBoardStatus(statusValue1);
        ScrumBoardStatus newStatus2 = null;

        //Act
        boolean result = newStatus1.sameValueAs(newStatus2);

        //Assert
        assertFalse(result);
    }

    @Test
    void hashCodeIsTheSame() {

        //Arrange
        ScrumBoardStatus.ScrumBoardStatusEnum statusValue1 = ScrumBoardStatus.ScrumBoardStatusEnum.DONE;
        ScrumBoardStatus.ScrumBoardStatusEnum statusValue2 = ScrumBoardStatus.ScrumBoardStatusEnum.DONE;
        ScrumBoardStatus newStatus1 = ScrumBoardStatus.createScrumBoardStatus(statusValue1);
        ScrumBoardStatus newStatus2 = ScrumBoardStatus.createScrumBoardStatus(statusValue2);

        //Act & Assert
        assertEquals(newStatus1.hashCode(), newStatus2.hashCode());
    }

    @Test
    void hashCodeIsDifferent() {

        //Arrange
        ScrumBoardStatus.ScrumBoardStatusEnum statusValue1 = ScrumBoardStatus.ScrumBoardStatusEnum.DONE;
        ScrumBoardStatus.ScrumBoardStatusEnum statusValue2 = ScrumBoardStatus.ScrumBoardStatusEnum.IN_PROGRESS;
        ScrumBoardStatus newStatus1 = ScrumBoardStatus.createScrumBoardStatus(statusValue1);
        ScrumBoardStatus newStatus2 = ScrumBoardStatus.createScrumBoardStatus(statusValue2);

        //Act & Assert
        assertNotEquals(newStatus1.hashCode(), newStatus2.hashCode());
    }

}