package switchisep.project.datamodel;

import org.junit.jupiter.api.Test;
import switchisep.project.domain.valueobjects.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class UserJPATest {

    @Test
    void testGetStatus(){
        //Arrange
        UserName userName = UserName.createUserName("UserNameTest");
        Email email = Email.createEmail("test@email.com");
        UserID userId = UserID.createUserID(1);
        Function function = Function.createFunction("Developer");
        PasswordHash hashedPassword = PasswordHash.createHashPassword("passwordForTesting");
        Photo photo = Photo.createPhoto("https://www.isep.ipp.pt/img/logo3.png");
        ProfileID profileIdDouble = ProfileID.generateID();

        UserJpa userJpa = new UserJpa(userId, userName, email, function, hashedPassword, photo, profileIdDouble, true);

        //Act
        boolean result = userJpa.getUserStatus();

        //Assert
        assertTrue(result);
    }
}