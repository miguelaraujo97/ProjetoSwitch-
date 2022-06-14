package switchisep.project.domain.domainservices;

import org.junit.jupiter.api.Test;
import switchisep.project.domain.profile.Profile;
import switchisep.project.domain.user.User;
import switchisep.project.domain.valueobjects.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateProfileDomainServiceTest {

    @Test
    void shouldReturnFail_parametersNull() {
        //arrange
        Profile profile = null;
        User user = null;
        //act
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () ->
                        new UpdateProfileDomainService(user, profile));
        //assert
        assertEquals("Parameters can be null", exception.getMessage());
    }

    @Test
    void updateProfile_Success() {
        //arrange
        Profile profile = mock(Profile.class);
        User user = mock(User.class);
        ProfileID profileID = ProfileID.generateID();
        when(profile.getProfileID()).thenReturn(profileID);

        UpdateProfileDomainService updateProfileDomainService =
                new UpdateProfileDomainService(user, profile);
        //act
        Optional<User> userUpdated = updateProfileDomainService.updateProfile();
        //assert
        assertEquals(Optional.of(user), userUpdated);
    }

    @Test
    void updateProfile_IT_Success() {
        //arrange
        Profile profileDirector = new Profile(Name.createName("Director"));
        ProfileID profileDirectorID = profileDirector.getProfileID();
        Profile profileVisitor = new Profile(Name.createName("Visitor"));
        ProfileID profileVisitorID = profileVisitor.getProfileID();

        UserName userName = UserName.createUserName("UserNameTest");
        Email email = Email.createEmail("test@email.com");

        UserID userId = UserID.createUserID(1);
        Function function = Function.createFunction("Developer");
        PasswordHash hashedPassword = PasswordHash.createHashPassword(
                "passwordForTesting");
        Photo photo = Photo.createPhoto(
                "https://www.isep.ipp.pt/img/logo3.png");

        User user = new User(userId, userName, email,
                function, hashedPassword, photo, profileVisitorID);

        UpdateProfileDomainService updateProfileDomainService =
                new UpdateProfileDomainService(user, profileDirector);

        //act
        updateProfileDomainService.updateProfile();
        ProfileID profileIDResult = user.getProfileID();
        //assert
        assertEquals(profileDirectorID, profileIDResult);
    }
}