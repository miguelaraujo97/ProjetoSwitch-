package switchisep.project.domain.profilerequest;

import org.junit.jupiter.api.Test;
import switchisep.project.domain.profile.Profile;
import switchisep.project.domain.valueobjects.Name;
import switchisep.project.domain.valueobjects.ProfileID;
import switchisep.project.domain.valueobjects.ProfileRequestID;
import switchisep.project.domain.valueobjects.UserID;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProfileRequestFactoryTest {

    ProfileRequestFactory profileRequestFactory = new ProfileRequestFactory();

    @Test
    void createProfileRequest (){
        //Arrange
        Profile pAdmin = new Profile(Name.createName("Administrator"));
        ProfileID profileID = ProfileID.generateID();

        UserID userID = UserID.createUserID(1);

        LocalDate date = LocalDate.of(2022,1,1);
        ProfileRequestID profileRequestID =
                ProfileRequestID.createProfileRequestID(1);
        //Act
        ProfileRequest profileRequest = profileRequestFactory.
                createProfileRequest(profileRequestID,profileID, userID);

        //Assert
        assertNotNull(profileRequest);
    }
}