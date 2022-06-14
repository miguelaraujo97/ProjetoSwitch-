package switchisep.project.domain.valueobjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SprintIDTest {

    @Test
    void createValidSprintID() {

        //Arrange
        SprintID sprintID = SprintID.createSprintID("SP001");

        //Act & Assert
        assertNotNull(sprintID);
    }

    @Test
    void createEmptySprintID() {

        //Arrange, Act & Assert
        assertThrows(IllegalArgumentException.class, () -> SprintID.createSprintID(""));
    }


    @Test
    void createEmptySpacedSprintID() {

        //Arrange, Act & Assert
        assertThrows(IllegalArgumentException.class, () -> SprintID.createSprintID(" "));
    }

    @Test
    void createNullSprintID() {

        //Arrange, Act & Assert
        assertThrows(IllegalArgumentException.class, () -> SprintID.createSprintID(null));
    }

    @Test
    void getTaskContainerID() {

        //Arrange
        SprintID sprintID = SprintID.createSprintID("SP001");

        //Act
        String actual = sprintID.getTaskContainerIDString();
        String expected = "SP001";

        //Assert
        assertEquals(actual, expected);
    }

    @Test
    void testEqualsSameObject() {

        //Arrange
        SprintID sprintID = SprintID.createSprintID("SP001");

        //Act & Assert
        assertEquals(sprintID, sprintID);
    }

    @Test
    void testEqualsEqualObjects() {

        //Arrange
        SprintID sprintID = SprintID.createSprintID("SP001");
        SprintID sprintID1 = SprintID.createSprintID("SP001");

        //Act & Assert
        assertEquals(sprintID, sprintID1);
    }

    @Test
    void testEqualsDifferentObject() {

        //Arrange
        SprintID sprintID1 = SprintID.createSprintID("SP001");
        SprintID sprintID2 = SprintID.createSprintID("SP002");

        //Act & Assert
        assertNotEquals(sprintID1, sprintID2);
    }

    @Test
    void testEqualsNullObject() {

        //Arrange
        SprintID sprintID1 = SprintID.createSprintID("SP001");
        SprintID sprintID2 = null;

        //Act & Assert
        assertNotEquals(sprintID1, sprintID2);
    }

    @Test
    void testEqualsDifferentClass() {

        //Arrange
        SprintID sprintID1 = SprintID.createSprintID("SP001");
        Object nativeObject = new Object();

        //Act & Assert
        assertNotEquals(sprintID1, nativeObject);
    }

    @Test
    void testHashCodeDifferentObjects() {
        //Arrange
        SprintID sprintID1 = SprintID.createSprintID("SP001");
        SprintID sprintID2 = SprintID.createSprintID("SP002");

        //Act & Assert
        assertNotEquals(sprintID1.hashCode(), sprintID2.hashCode());
    }

    @Test
    void testSameValueAs_Null(){

        //Arrange
        SprintID sprintID1 = SprintID.createSprintID("SP001");

        //Act
        boolean result = sprintID1.sameValueAs(null);

        //Assert
        assertFalse(result);
    }

    @Test
    void testSameValueAs_DiffValue(){

        //Arrange
        SprintID sprintID1 = SprintID.createSprintID("SP001");
        SprintID sprintID2 = SprintID.createSprintID("SP002");

        //Act
        boolean result = sprintID1.sameValueAs(sprintID2);

        //Assert
        assertFalse(result);
    }

    @Test
    void testSameValueAs_SameValue(){

        //Arrange
        SprintID sprintID1 = SprintID.createSprintID("SP001");
        SprintID sprintID2 = SprintID.createSprintID("SP001");

        //Act
        boolean result = sprintID1.sameValueAs(sprintID2);

        // Assert
        assertTrue(result);
    }
}

