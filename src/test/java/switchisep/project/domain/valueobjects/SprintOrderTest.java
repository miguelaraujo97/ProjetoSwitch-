package switchisep.project.domain.valueobjects;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SprintOrderTest {

    @Test
    void createValidSprintOrderObject() {

        //Arrange
        int sprintOrder = 1;

        //Act & Assert
        assertNotNull(SprintOrder.createSprintOrder(sprintOrder));
    }

    @Test
    void createZeroSprintOrder() {

        //Arrange, Act & Assert
        assertThrows(IllegalArgumentException.class, () -> SprintOrder.createSprintOrder(0));
    }

    @Test
    void createNegativeSprintOrder() {

        //Arrange, Act & Assert
        assertThrows(IllegalArgumentException.class, () -> SprintOrder.createSprintOrder(-1));
    }


    @Test
    void bothObjectsAreEqual() {

        //Arrange
        int sprintOrder = 1;
        SprintOrder sprintOrder1 = SprintOrder.createSprintOrder(sprintOrder);
        SprintOrder sprintOrder2 = SprintOrder.createSprintOrder(sprintOrder);

        //Act
        boolean result = sprintOrder1.equals(sprintOrder2);

        //Assert
        assertEquals(sprintOrder1, sprintOrder2);
        assertTrue(result);
    }

    @Test
    void comparingTheSameObject() {

        //Arrange
        int sprintOrder = 1;
        SprintOrder sprintOrder1 = SprintOrder.createSprintOrder(sprintOrder);

        //Act & Assert
        assertEquals(sprintOrder1, sprintOrder1);
    }

    @Test
    void bothObjectsAreDifferent() {

        //Arrange
        int sprintOrderValue1 = 1;
        int sprintOrderValue2 = 2;
        SprintOrder sprintOrder1 = SprintOrder.createSprintOrder(sprintOrderValue1);
        SprintOrder sprintOrder2 = SprintOrder.createSprintOrder(sprintOrderValue2);

        //Act
        boolean result = sprintOrder1.equals(sprintOrder2);

        //Assert
        assertNotEquals(sprintOrder1, sprintOrder2);
        assertFalse(result);
    }

    @Test
    void oneOfTheObjectsIsNull() {

        //Arrange
        int sprintOrderValue1 = 1;
        SprintOrder sprintOrder1 = SprintOrder.createSprintOrder(sprintOrderValue1);
        Object nativeObject = new Object();

        //Act
        boolean result = sprintOrder1.equals(nativeObject);

        //Assert
        assertFalse(result);
    }

    @Test
    void oneOfTheObjectsIFromDifferentClass() {

        //Arrange
        int sprintOrderValue1 = 1;
        SprintOrder sprintOrder1 = SprintOrder.createSprintOrder(sprintOrderValue1);
        SprintOrder sprintOrder2 = null;

        //Act
        boolean result = sprintOrder1.equals(sprintOrder2);

        //Assert
        assertNotEquals(sprintOrder1, sprintOrder2);
        assertFalse(result);
    }

    @Test
    void hashCodeIsTheSame() {

        //Arrange
        int sprintOrder = 1;
        SprintOrder sprintOrder1 = SprintOrder.createSprintOrder(sprintOrder);
        SprintOrder sprintOrder2 = SprintOrder.createSprintOrder(sprintOrder);

        //Act & Assert
        assertEquals(sprintOrder1.hashCode(), sprintOrder2.hashCode());
    }

    @Test
    void hashCodeIsDifferent() {

        //Arrange
        int sprintOrderValue1 = 1;
        int sprintOrderValue2 = 2;
        SprintOrder sprintOrder1 = SprintOrder.createSprintOrder(sprintOrderValue1);
        SprintOrder sprintOrder2 = SprintOrder.createSprintOrder(sprintOrderValue2);

        //Act & Assert
        assertNotEquals(sprintOrder1.hashCode(), sprintOrder2.hashCode());
    }

    @Test
    void sameValueAs() {

        //Arrange
        int sprintOrderValue1 = 1;
        SprintOrder sprintOrder1 = SprintOrder.createSprintOrder(sprintOrderValue1);
        SprintOrder sprintOrder2 = null;

        //Act
        boolean result = sprintOrder1.sameValueAs(sprintOrder2);

        //Assert
        assertFalse(result);
    }

}