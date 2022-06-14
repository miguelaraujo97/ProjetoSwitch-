package switchisep.project.dto.assemblers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import switchisep.project.dto.UserInternalDTO;
import switchisep.project.dto.UserUIInfoDTO;
import switchisep.project.dto.UserUiDTO;
import switchisep.project.domain.user.User;
import switchisep.project.domain.user.UserFactory;
import switchisep.project.domain.valueobjects.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class UserDomainInternalDTOAssemblerTest {

    @InjectMocks
    UserDomainInternalDTOAssembler userDomainInternalDTOAssembler;

    @Test
    void toDTO() {
        UserFactory userFactory = new UserFactory();
        UserName userName = UserName.createUserName("UserNameTest");
        Email email = Email.createEmail("test@email.com");
        UserID userId = UserID.createUserID(1);
        Function function = Function.createFunction("Developer");
        PasswordHash hashedPassword = PasswordHash.createHashPassword(
                "passwordForTesting");
        Photo photo = Photo.createPhoto("https://www.isep.ipp.pt/img/logo3" +
                ".png");

        ProfileID profileIdDouble = mock(ProfileID.class);

        User user = userFactory.createNewUser(userId, userName, email,
                function, hashedPassword, photo, profileIdDouble);

        UserInternalDTO expected = new UserInternalDTO(user.getUserID(),
                user.getEmail(), user.getUserName(),
                user.getProfileID(), user.getFunction(),
                user.getPhoto(), user.getUserStatus());

        UserInternalDTO actual = userDomainInternalDTOAssembler.toDTO(user);

        assertEquals(actual, expected);
    }

    @Test
    void toNativeDTO() {
        UserFactory userFactory = new UserFactory();
        UserName userName = UserName.createUserName("UserNameTest");
        Email email = Email.createEmail("test@email.com");
        UserID userId = UserID.createUserID(1);
        Function function = Function.createFunction("Developer");
        PasswordHash hashedPassword = PasswordHash.createHashPassword(
                "passwordForTesting");
        Photo photo = Photo.createPhoto("https://www.isep.ipp.pt/img/logo3" +
                ".png");

        ProfileID profileIdDouble = mock(ProfileID.class);

        User user = userFactory.createNewUser(userId, userName, email,
                function, hashedPassword, photo, profileIdDouble);

        UserUIInfoDTO userUIInfoDtoExpected = new UserUIInfoDTO();
        userUIInfoDtoExpected.userId = user.getUserID().getUserID();
        userUIInfoDtoExpected.profileId = user.getProfileID().getProfileID();
        userUIInfoDtoExpected.name = user.getUserName().getUserName();
        userUIInfoDtoExpected.email = user.getEmail().getEmail();
        userUIInfoDtoExpected.function = user.getFunction().getFunction();
        userUIInfoDtoExpected.photo = user.getPhoto().getPhotoURL();

        UserUIInfoDTO userUIInfoDtoActual =
                userDomainInternalDTOAssembler.toNativeDTO(user);

        assertEquals(userUIInfoDtoActual, userUIInfoDtoExpected);


    }

    @Test
    void toOutputDTO() {
        UserFactory userFactory = new UserFactory();
        UserName userName = UserName.createUserName("UserNameTest");
        Email email = Email.createEmail("test@email.com");
        UserID userId = UserID.createUserID(1);
        Function function = Function.createFunction("Developer");
        PasswordHash hashedPassword = PasswordHash.createHashPassword(
                "passwordForTesting");
        Photo photo = Photo.createPhoto("https://www.isep.ipp.pt/img/logo3" +
                ".png");

        ProfileID profileIdDouble = mock(ProfileID.class);

        User user = userFactory.createNewUser(userId, userName, email,
                function, hashedPassword, photo, profileIdDouble);

        UserUiDTO userUIDtoExpected =
                new UserUiDTO(user.getUserID().getUserID(),
                user.getUserName().getUserName(),
                user.getEmail().getEmail(),
                user.getFunction().getFunction());


        UserUiDTO userUIDtoActual =
                userDomainInternalDTOAssembler.toOutputDTO(user);

        assertEquals(userUIDtoActual, userUIDtoExpected);
    }
}