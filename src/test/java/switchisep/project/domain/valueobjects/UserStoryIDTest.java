package switchisep.project.domain.valueobjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserStoryIDTest {


    @Test
    void getTypologyID() {
        String usID = "US-001";
        String typ2 = "US-002";
        UserStoryID userStoryID = UserStoryID.createUserStoryIdWithString(
                usID);

        assertEquals(usID, userStoryID.getUserStoryID());
        assertNotEquals(typ2, userStoryID.getUserStoryID());

    }


    @Test
    void sameValueAs() {
        UserStoryID userStoryID = UserStoryID.createUserStoryIdWithString(
                "US-001");
        UserStoryID userStoryID1 = UserStoryID.createUserStoryIdWithString(
                "US-001");
        UserStoryID userStoryID2 = UserStoryID.createUserStoryIdWithString(
                "US-002");

        //Assert
        assertTrue(userStoryID.sameValueAs(userStoryID1));
        assertFalse(userStoryID.sameValueAs(userStoryID2));
        assertFalse(userStoryID.sameValueAs(null));
    }



    @Test
    void checkOverride() {
        //Arrange

        //Act
        UserStoryID userStoryID = UserStoryID.createUserStoryIdWithString(
                "US-001");
        UserStoryID userStoryID1 = UserStoryID.createUserStoryIdWithString(
                "US-001");
        UserStoryID userStoryID2 = UserStoryID.createUserStoryIdWithString(
                "US-002");
        Object obj = new Object();

        // Assert
        assertEquals(userStoryID, userStoryID1);
        assertEquals(userStoryID, userStoryID);
        assertEquals(userStoryID.hashCode(), userStoryID1.hashCode());

//        assertNotEquals(userStoryID, userStoryID2);
        assertNotEquals(null, userStoryID);
        assertNotEquals(userStoryID, obj);

        assertNotEquals(userStoryID.hashCode(), userStoryID2.hashCode());
    }


}