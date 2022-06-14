package switchisep.project.domain.valueobjects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ResourceIDTest {

    @Test
    void shouldCreateValidResourceID() {

        // Arrange
        String resourceIDString = "R1";

        // Act
        ResourceID resourceIDOne = ResourceID.createResourceID(resourceIDString);
        ResourceID resourceIDTwo = ResourceID.createResourceID(resourceIDString);

        // Assert
        assertEquals(resourceIDOne, resourceIDTwo);
    }

    @Test
    void shouldntCreateValidResourceIDIsBlank() {

        // Arrange
        String resourceIDString = "";

        // Act
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            ResourceID resourceID = ResourceID.createResourceID(resourceIDString);
        });

        // Assert
        assertEquals(exception.getMessage(), "Resource ID Cannot Be Null Or Blank");
    }

    @Test
    void shouldntCreateValidResourceIDisNull() {

        // Arrange
        String resourceIDString = null;

        // Act
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            ResourceID resourceID = ResourceID.createResourceID(resourceIDString);
        });

        // Assert
        assertEquals(exception.getMessage(), "Resource ID Cannot Be Null Or Blank");
    }

    @Test
    void testEqualsForSameObject() {

        // Arrange
        String resourceIDString = "R1";

        // Act
        ResourceID resourceID = ResourceID.createResourceID(resourceIDString);

        // Assert
        assertEquals(resourceID, resourceID);
    }

    @Test
    void testEqualsForEqualObject() {

        // Arrange
        String resourceIDStringOne = "R1";
        String resourceIDStringTwo = "R1";

        // Act
        ResourceID resourceIDOne = ResourceID.createResourceID(resourceIDStringOne);
        ResourceID resourceIDTwo = ResourceID.createResourceID(resourceIDStringTwo);

        // Assert
        assertEquals(resourceIDOne, resourceIDTwo);
    }

    @Test
    void testEqualsForDifferentObjectDifferentClass() {

        // Arrange
        String resourceIDString = "R1";

        // Act
        ResourceID resourceID = ResourceID.createResourceID(resourceIDString);
        Object obj = new Object();

        // Assert
        assertNotEquals(resourceID, obj);
    }

    @Test
    void testEqualsForDifferentObjectSameClass() {

        // Arrange
        String resourceIDStringOne = "R1";
        String resourceIDStringTwo = "R2";

        // Act
        ResourceID resourceIDOne = ResourceID.createResourceID(resourceIDStringOne);
        ResourceID resourceIDTwo = ResourceID.createResourceID(resourceIDStringTwo);

        // Assert
        assertNotEquals(resourceIDOne, resourceIDTwo);
    }

    @Test
    void testHashCodeForDifferentObjectSameClass() {

        // Arrange
        String resourceIDStringOne = "R1";
        String resourceIDStringTwo = "R2";

        // Act
        ResourceID resourceIDOne = ResourceID.createResourceID(resourceIDStringOne);
        ResourceID resourceIDTwo = ResourceID.createResourceID(resourceIDStringTwo);

        // Assert
        assertNotEquals(resourceIDOne.hashCode(), resourceIDTwo.hashCode());
    }

    @Test
    void testHashCodeForSameObject() {

        // Arrange
        String resourceIDStringOne = "R1";

        // Act
        ResourceID resourceIDOne = ResourceID.createResourceID(resourceIDStringOne);

        // Assert
        assertEquals(resourceIDOne.hashCode(), resourceIDOne.hashCode());
    }

}