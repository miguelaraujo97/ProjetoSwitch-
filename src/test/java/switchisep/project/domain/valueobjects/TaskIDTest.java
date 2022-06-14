
package switchisep.project.domain.valueobjects;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskIDTest {

    @Test
    void createValidTaskID() {

        //Arrange
        TaskID taskID = TaskID.createTaskID("T001");

        //Act
        String taskIDValue = taskID.getTaskID();

        //Assert
        assertNotNull(taskID);
        assertEquals(taskIDValue, "T001");
    }

    @Test
    void createEmptyTaskID() {

        //Arrange, Act & Assert
        assertThrows(IllegalArgumentException.class, () -> TaskID.createTaskID(""));
    }

    @Test
    void createNullTaskID() {

        //Arrange, Act & Assert
        assertThrows(NullPointerException.class, () -> TaskID.createTaskID(null));
    }

    @Test
    void bothTaskIDsAreEqual() {

        //Arrange
        String taskID = "T001";
        TaskID taskID1 = TaskID.createTaskID(taskID);
        TaskID taskID2 = TaskID.createTaskID(taskID);

        //Act
        boolean result = taskID1.equals(taskID2);

        //Assert
        assertEquals(taskID1, taskID2);
        assertTrue(result);
    }

    @Test
    void comparingTheSameObject() {

        //Arrange
        String taskID = "T001";
        TaskID taskID1 = TaskID.createTaskID(taskID);

        //Act & Assert
        assertEquals(taskID1, taskID1);
    }

    @Test
    void oneOfTheObjectsIsNull() {

        //Arrange
        String taskID = "T001";
        TaskID taskID1 = TaskID.createTaskID(taskID);
        TaskID taskID2 = null;

        //Act
        boolean result = taskID1.equals(taskID2);

        //Act & Assert
        assertNotEquals(taskID1, taskID2);
        assertFalse(result);
    }

    @Test
    void bothTaskIDsAreDifferent() {

        //Arrange
        String taskID1 = "T001";
        String taskID2 = "T002";
        TaskID taskID3 = TaskID.createTaskID(taskID1);
        TaskID taskID4 = TaskID.createTaskID(taskID2);

        //Act
        boolean result = taskID3.equals(taskID4);

        //Assert
        assertNotEquals(taskID1, taskID2);
        assertFalse(result);
    }

    @Test
    void bothHashCodesAreTheSame() {

        //Arrange
        String taskID = "T001";
        TaskID taskID1 = TaskID.createTaskID(taskID);
        TaskID taskID2 = TaskID.createTaskID(taskID);

        //Act & Assert
        assertEquals(taskID1.hashCode(), taskID2.hashCode());
    }

    @Test
    void bothHashCodesAreDifferent() {

        //Arrange
        String taskID1 = "T001";
        String taskID2 = "T002";
        TaskID taskID3 = TaskID.createTaskID(taskID1);
        TaskID taskID4 = TaskID.createTaskID(taskID2);

        //Act & Assert
        assertNotEquals(taskID3.hashCode(), taskID4.hashCode());
    }
}