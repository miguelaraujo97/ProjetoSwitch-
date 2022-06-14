package switchisep.project.domain.profilerequest;

import org.junit.jupiter.api.Test;
import switchisep.project.domain.profile.Profile;
import switchisep.project.domain.valueobjects.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProfileRequestTest {

    @Test
    void testEquals() {
        Profile pAdmin = new Profile(Name.createName("Administrator"));
        ProfileID profileIDAdmin = ProfileID.generateID();
        UserID userID = UserID.createUserID(1);
        ProfileRequestID profileRequestID =
                ProfileRequestID.createProfileRequestID(1);
        ProfileRequest profileRequest =
                new ProfileRequest.Builder(profileRequestID,
                        profileIDAdmin, userID).build();
        assertEquals(profileRequest, profileRequest);
    }

    @Test
    void checkOverride() {
        //Arrange
        Profile pAdmin = new Profile(Name.createName("Administrator"));
        ProfileID profileIDAdmin = ProfileID.generateID();
        Profile pDir = new Profile(Name.createName("Director"));
        ProfileID profileIDDir = ProfileID.generateID();

        UserID userID1 = UserID.createUserID(1);
        UserID userID2 = UserID.createUserID(2);
        ProfileRequestID profileRequestID =
                ProfileRequestID.createProfileRequestID(1);
        ProfileRequest profileRequestAdmin = new ProfileRequest.Builder(
                profileRequestID, profileIDAdmin, userID1).build();
        ProfileRequest profileRequestDir = new ProfileRequest.Builder(
                profileRequestID, profileIDDir, userID2).build();
        //Act

        // Assert
        assertEquals(profileRequestAdmin, profileRequestAdmin);
        assertNotEquals(profileRequestAdmin.hashCode(), profileRequestDir.hashCode());
        assertNotEquals(profileRequestAdmin, profileRequestDir);
        assertNotEquals(profileRequestAdmin, null);
    }

    @Test
    void testEquals_return_false_different_class_objects() {
        //Arrange
        Profile pAdmin = new Profile(Name.createName("Administrator"));
        ProfileID profileIDAdmin = ProfileID.generateID();

        UserID userID1 = UserID.createUserID(1);
        ProfileRequestID profileRequestID =
                ProfileRequestID.createProfileRequestID(1);
        ProfileRequest profileRequestAdmin = new ProfileRequest.
                Builder(profileRequestID,profileIDAdmin, userID1).build();
        Object obj = new Object();
        //Act
        boolean isEquals = profileRequestAdmin.equals(obj);
        //Assert
        assertFalse(isEquals);
        assertNotEquals(profileRequestAdmin, null);
    }

    @Test
    void testEquals_return_true() {
        //Arrange
        Profile pAdmin = new Profile(Name.createName("Administrator"));
        ProfileID profileIDAdmin = ProfileID.generateID();
        UserID userID1 = UserID.createUserID(1);
        ProfileRequestID profileRequestID =
                ProfileRequestID.createProfileRequestID(1);
        ProfileRequest profileRequestAdmin =
                new ProfileRequest.Builder(profileRequestID,profileIDAdmin, userID1).build();
        ProfileRequest profileRequestAdmin2 =
                new ProfileRequest.Builder(profileRequestID,
                        profileIDAdmin, userID1).build();

        //Act
        boolean isEquals = profileRequestAdmin.equals(profileRequestAdmin2);
        //Assert
        assertTrue(isEquals);
    }

    @Test
    void testEquals_return_false() {
        //Arrange
        Profile pAdmin = new Profile(Name.createName("Administrator"));
        ProfileID profileIDAdmin = ProfileID.generateID();

        UserID userID1 = UserID.createUserID(1);
        UserID userID2 = UserID.createUserID(2);
        ProfileRequestID profileRequestID =
                ProfileRequestID.createProfileRequestID(1);
        ProfileRequest profileRequestAdmin = new ProfileRequest.Builder(profileRequestID,
                profileIDAdmin, userID1).build();
        ProfileRequest profileRequestAdmin2 = new ProfileRequest.Builder(profileRequestID,
                profileIDAdmin, userID2).build();

        //Act
        boolean isEquals = profileRequestAdmin.equals(profileRequestAdmin2);
        //Assert
        assertFalse(isEquals);
    }

    @Test
    void getProfileID() {
        //Arrange
        Profile pAdmin = new Profile(Name.createName("Administrator"));
        ProfileID profileIDAdmin = ProfileID.generateID();
        ProfileID profileIdOther = ProfileID.generateID();

        UserID userID1 = UserID.createUserID(1);


        ProfileRequestID profileRequestID =
                ProfileRequestID.createProfileRequestID(1);

        ProfileRequest profileRequestAdmin = new ProfileRequest.Builder(profileRequestID,
                profileIDAdmin, userID1).build();
        //Act
        ProfileID getProfileId = profileRequestAdmin.getProfileID();
        //Assert
        assertEquals(profileIDAdmin, getProfileId);
        assertNotEquals(profileIdOther, getProfileId);
    }

    @Test
    void getUserID() {
        //Arrange
        Profile pAdmin = new Profile(Name.createName("Administrator"));
        ProfileID profileIDAdmin = ProfileID.generateID();

        UserID userID1 = UserID.createUserID(1);
        UserID userID2 = UserID.createUserID(2);

        ProfileRequestID profileRequestID =
                ProfileRequestID.createProfileRequestID(1);

        ProfileRequest profileRequestAdmin = new ProfileRequest.Builder(profileRequestID,
                profileIDAdmin, userID1).build();
        //Act
        UserID getUserId = profileRequestAdmin.getUserID();
        //Assert
        assertEquals(userID1, getUserId);
        assertNotEquals(userID2, getUserId);
    }

    @Test
    void getProfileRequestID() {
        //Arrange
        Profile pAdmin = new Profile(Name.createName("Administrator"));
        ProfileID profileIDAdmin = ProfileID.generateID();

        UserID userID1 = UserID.createUserID(1);
        UserID userID2 = UserID.createUserID(2);

        ProfileRequestID profileRequestID =
                ProfileRequestID.createProfileRequestID(1);
        ProfileRequestID profileRequestID2 =
                ProfileRequestID.createProfileRequestID(2);

        ProfileRequest profileRequestAdmin = new ProfileRequest.Builder(profileRequestID,
                profileIDAdmin, userID1).build();
        //Act
        ProfileRequestID getRequestID = profileRequestAdmin.getProfileRequestID();
        //Assert
        assertEquals(profileRequestID, getRequestID);
        assertNotEquals(profileRequestID2, getRequestID);
    }
}