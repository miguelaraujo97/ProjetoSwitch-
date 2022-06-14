package switchisep.project.domain.valueobjects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import switchisep.project.domain.user.User;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

class  UserIDTest {

    @Test
    void createUserID_SuccessCase_TestWithEquals(){
        //Arrange

        //Act
        UserID userID1 = UserID.createUserID(1);
        UserID userID2 = UserID.createUserID(1);

        //Assert
        assertEquals(userID1, userID2);
    }


    //---------------------------------------------------//---------------------------------------------------------//
    //Test for equals and hashCode methods

    @Test
    void testSameValueAs_Null(){
        //Arrange
        UserID userID = UserID.createUserID(1);

        //Act
        boolean result = userID.sameValueAs(null);

        //Assert
        assertFalse(result);
    }

    @Test
    void testEquals_SameObject(){
        //Arrange

        UserID userID = UserID.createUserID(1);

        //Act & Assert
        assertEquals(userID, userID);
    }

    @Test
    void testEquals_DifferentClass(){
        //Arrange

        UserID userID = UserID.createUserID(1);

        Object object = new Object();

        //Act & Assert
        assertNotEquals(userID, object);
    }

    @Test
    void testEquals_DifferentObjectSameClass(){
        //Arrange
        UserID userID1 = UserID.createUserID(1);

        UserID userID2 = UserID.createUserID(2);

        //Act & Assert
        assertNotEquals(userID1, userID2);
    }

    @Test
    void testHashCode_SameObject(){
        //Arrange

        UserID userID = UserID.createUserID(1);

        //Act & Assert
        assertEquals(userID.hashCode(), userID.hashCode());
    }


}