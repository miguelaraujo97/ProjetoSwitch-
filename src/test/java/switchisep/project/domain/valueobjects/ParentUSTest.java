package switchisep.project.domain.valueobjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ParentUSTest {
//    @Test
//    void testObjectNull() {
//        //Arrange
//        //Act
//        IllegalArgumentException exception =
//                assertThrows(IllegalArgumentException.class, () ->
//                        ParentUS.createParentUS(null));
//        // Assert
//        assertEquals("UserStory can't be null",
//                exception.getMessage());
//    }

    @Test
    void checkOverride(){
        //Arrange
        UserStoryID userStory1 = mock(UserStoryID.class);
        UserStoryID userStory2 = mock(UserStoryID.class);
        //Act
        ParentUS parentUS1 = ParentUS.createParentUS(userStory1);
        ParentUS parentUS2 = ParentUS.createParentUS(userStory1);
        ParentUS parentUS3 = ParentUS.createParentUS(userStory2);
        Object obj = new Object();
        // Assert
        assertEquals(parentUS1, parentUS1);
        assertEquals(parentUS1, parentUS2);
        assertEquals(parentUS1.hashCode(),parentUS1.hashCode());

        assertNotEquals(parentUS1,parentUS3);
        assertNotEquals(parentUS1, null);
        assertNotEquals(parentUS1,obj);
        assertNotEquals(parentUS1.hashCode(),parentUS3.hashCode());
    }


}