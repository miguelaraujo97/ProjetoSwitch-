package switchisep.project.datamodel;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import switchisep.project.domain.valueobjects.ProfileID;
import switchisep.project.domain.valueobjects.ProfileRequestID;
import switchisep.project.domain.valueobjects.UserID;

import static org.junit.jupiter.api.Assertions.*;

class ProfileRequestJpaTest {

    @Test
    void getProfileRequestID() {
        //Arrange
        ProfileRequestID profileRequestID = ProfileRequestID.createProfileRequestID(1);
        ProfileID profileID = ProfileID.generateID();
        UserID userID = UserID.createUserID(1);
        ProfileRequestJpa profileRequestJpa = new ProfileRequestJpa(profileRequestID, profileID, userID);

        //Act
        ProfileRequestID result = profileRequestJpa.getProfileRequestID();

        //Assert
        assertEquals(profileRequestID, result);
    }

    @Test
    void emptyConstructor() {
        //Arrange
        ProfileRequestJpa profileRequestJpa = new ProfileRequestJpa();


        //Assert
        assertEquals(profileRequestJpa, profileRequestJpa);
    }

    @Test
    void getProfileID() {
        //Arrange
        ProfileRequestID profileRequestID = ProfileRequestID.createProfileRequestID(1);
        ProfileID profileID = ProfileID.generateID();
        UserID userID = UserID.createUserID(1);
        ProfileRequestJpa profileRequestJpa = new ProfileRequestJpa(profileRequestID, profileID, userID);

        //Act
        ProfileID result = profileRequestJpa.getProfileID();

        //Assert
        assertEquals(profileID, result);
    }

    @Test
    void getUserID() {
        //Arrange
        ProfileRequestID profileRequestID = ProfileRequestID.createProfileRequestID(1);
        ProfileID profileID = ProfileID.generateID();
        UserID userID = UserID.createUserID(1);
        ProfileRequestJpa profileRequestJpa = new ProfileRequestJpa(profileRequestID, profileID, userID);

        //Act
        UserID result = profileRequestJpa.getUserID();

        //Assert
        assertEquals(userID, result);
    }
}