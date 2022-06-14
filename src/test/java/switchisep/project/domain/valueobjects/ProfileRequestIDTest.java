package switchisep.project.domain.valueobjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfileRequestIDTest {

    @Test
    void createProfileRequestID() {
        //Arrange
        //Act

        ProfileRequestID profileRequestID =
                ProfileRequestID.createProfileRequestID(22);

        //Assert
        assertNotNull(profileRequestID);
    }

    @Test
    void checkOverride() {
        //Arrange

        //Act
        ProfileRequestID profileRequestID = ProfileRequestID.createProfileRequestID(1);
        ProfileRequestID profileRequestID1 = ProfileRequestID.createProfileRequestID(1);
        ProfileRequestID profileRequestID2 = ProfileRequestID.createProfileRequestID(2);
        Object obj = new Object();

        // Assert
        assertEquals(profileRequestID, profileRequestID);
        assertEquals(profileRequestID, profileRequestID1);
        assertEquals(profileRequestID.hashCode(), profileRequestID1.hashCode());

        assertNotEquals(profileRequestID, profileRequestID2);
        assertNotEquals(null, profileRequestID);
        assertNotEquals(profileRequestID, obj);
        assertNotEquals(profileRequestID.hashCode(), profileRequestID2.hashCode());
    }

    @Test
    void sameValue() {
        //Arrange
        ProfileRequestID profileRequestID = ProfileRequestID.createProfileRequestID(1);
        ProfileRequestID profileRequestID1 = ProfileRequestID.createProfileRequestID(1);
        ProfileRequestID profileRequestID2 = ProfileRequestID.createProfileRequestID(2);
        //Assert
        assertFalse(profileRequestID.sameValueAs(profileRequestID2));
        assertTrue(profileRequestID.sameValueAs(profileRequestID1));
        assertFalse(profileRequestID.sameValueAs(null));
    }
}