package switchisep.project.domain.user;

import org.junit.jupiter.api.Test;
import switchisep.project.domain.profile.Profile;
import switchisep.project.domain.valueobjects.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserFactoryTest {

    @Test
    void createNewUser_IntegrationTest() {

        //Arrange
        UserFactory userFactory = new UserFactory();

        Email email = Email.createEmail("email@email.com");
        UserID userID = UserID.createUserID(1);

        PasswordHash passwordHash = PasswordHash.createHashPassword("PasswordForTesting");
        UserName userName = UserName.createUserName("Name");

        Profile profile = new Profile(Name.createName("Visitor"));
        ProfileID profileID = ProfileID.generateID();


        Function userFunction = Function.createFunction("Function");
        Photo userPhoto = Photo.createPhoto("https://portal.isep.ipp.pt/global/images/" +
                "intranet/PaginaInicial/i3_logo.png");

        User user = new User(userID, userName, email, userFunction, passwordHash,
                userPhoto, profileID);

        //Act
        User userCreated = userFactory.createNewUser(userID, userName, email, userFunction,
                passwordHash, userPhoto, profileID);

        //Assert
        assertEquals(user, userCreated);
    }
}