package switchisep.project.domain.valueobjects;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PriorityTest {

    @ParameterizedTest
    @ValueSource(ints = {-1,-2,-3,-5,-8,-12,-21, 0})
    void testPriorityNegative(int values) {
        //Arrange
        //Act
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () ->
                        Priority.createPriority(values));
        // Assert
        assertEquals("Priority can't be negative",
                exception.getMessage());
    }


    @Test
    void checkOverride() {
        //Arrange

        //Act
        Priority priority = Priority.createPriority(1);
        Priority priority1 = Priority.createPriority(1);
        Priority priority2 = Priority.createPriority(2);
        Object obj = new Object();

        // Assert
        assertEquals(priority, priority);
        assertEquals(priority, priority1);
        assertEquals(priority.hashCode(), priority1.hashCode());

        assertNotEquals(priority, priority2);
        assertNotEquals(null, priority);
        assertNotEquals(priority, obj);
        assertNotEquals(priority.hashCode(), priority2.hashCode());
    }

    @Test
    void compareTo() {
        Priority priority = Priority.createPriority(1);
        Priority priority1 = Priority.createPriority(5);

        assertEquals(-4, priority.compareTo(priority1));
    }

}