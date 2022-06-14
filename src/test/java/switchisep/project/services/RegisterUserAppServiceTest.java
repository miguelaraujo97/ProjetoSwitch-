package switchisep.project.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchisep.project.dto.UserInternalDTO;
import switchisep.project.dto.UserRegistrationRequest;
import switchisep.project.dto.UserUIInfoDTO;
import switchisep.project.dto.assemblers.UserDomainInternalDTOAssembler;
import switchisep.project.domain.domainservices.DefaultProfileDomainService;
import switchisep.project.domain.profile.Profile;
import switchisep.project.domain.user.User;
import switchisep.project.domain.user.UserFactoryInterface;
import switchisep.project.domain.valueobjects.*;
import switchisep.project.repositories.interfaces.ProfileRepositoryInterface;
import switchisep.project.repositories.interfaces.UserRepositoryInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegisterUserAppServiceTest {

    @InjectMocks
    RegisterUserAppService registerUserAppService;

    @Mock
    UserRepositoryInterface userRepositoryInterface;

    @Mock
    UserDomainInternalDTOAssembler userDTOAssembler;

    @Mock
    UserFactoryInterface userFactoryInterface;

    @Mock
    ProfileRepositoryInterface profileRepository;

    @Mock
    DefaultProfileDomainService defaultProfileDomainService;

    @Mock
    UserDomainInternalDTOAssembler userUIInfoDTOAssembler;


    @Test
    void testFindById_FailureScenario_UserDoesntExist() {
        //arrange
        //act
        Optional<UserInternalDTO> opUserDTO = registerUserAppService.
                findByEmail(any());
        //assert
        assertEquals(Optional.empty(), opUserDTO);
    }

    @Test
    void testFindByEmail_FailureScenario_EmailDoesntExists() {       //arrange

        Email email = Email.createEmail("xxx@xxx.xxx");
        when(userRepositoryInterface.findByEmail(email)).
                thenReturn(Optional.empty());

        //act
        Optional<UserInternalDTO> opUserDTO = registerUserAppService.
                findByEmail(email);
        //assert
        assertEquals(Optional.empty(), opUserDTO);
    }

    @Test
    void testFindByEmail_SuccessScenario_EmailExists() {

        //arrange
        Email email = Email.createEmail("xxx@xxx.xxx");
        User user = mock(User.class);
        UserInternalDTO userInternalDTO = mock(UserInternalDTO.class);
        when(userRepositoryInterface.findByEmail(email)).
                thenReturn(Optional.of(user));
        //  doReturn(userInternalDTO).when(registerUserAppService).getUserDTO(user);
        when(userDTOAssembler.toDTO(any())).thenReturn(userInternalDTO);
        //act
        Optional<UserInternalDTO> opUserDTO = registerUserAppService.
                findByEmail(email);
        //assert
        assertEquals(Optional.of(userInternalDTO), opUserDTO);
    }

    @Test
    void testFindByEmail_SuccessScenario_UserIDExists() {       //arrange

        User user = mock(User.class);
        UserInternalDTO userInternalDTO = mock(UserInternalDTO.class);
        when(userRepositoryInterface.findByEmail(any())).
                thenReturn(Optional.of(user));
        // doReturn(userInternalDTO).when(registerUserAppService).getUserDTO(user);
        when(userDTOAssembler.toDTO(any())).thenReturn(userInternalDTO);

        //act
        Optional<UserInternalDTO> opUserDTO = registerUserAppService.
                findByEmail(any());
        //assert
        assertEquals(Optional.of(userInternalDTO), opUserDTO);
    }

    @Test
    void testCreateAndSaveUser_SuccessCase() {
        //arrange
        UserRegistrationRequest userRegistrationRequest =
                new UserRegistrationRequest();

        userRegistrationRequest.userName = "User Name";
        userRegistrationRequest.email = "teste@email.com";
        userRegistrationRequest.function = "Dev";
        userRegistrationRequest.password = "passwordTest";
        userRegistrationRequest.photo = "https://www.isep.ipp.pt/img/logo3.png";

        List<User> listToGenerateID = new ArrayList<>();
        User userInList = mock(User.class);
        listToGenerateID.add(userInList);


        when(userRepositoryInterface.findAll()).thenReturn(listToGenerateID);

        UserID userID = UserID.createUserID(2);
        UserName userName = UserName.createUserName("User Name");
        Email email = Email.createEmail("teste@email.com");
        Function function = Function.createFunction("Dev");
        Photo photo = Photo.createPhoto("https://www.isep.ipp.pt/img/logo3.png");

        User userToBeSaved = mock(User.class);
        List<Profile> profileList = new ArrayList<>();
        ProfileID profileID = ProfileID.generateID();

        UserUIInfoDTO userUIDTO = mock(UserUIInfoDTO.class);

        when(profileRepository.getAllProfiles()).thenReturn(profileList);
        when(defaultProfileDomainService.getDefaultProfileID(profileList)).thenReturn(Optional.of(profileID));
        when(userRepositoryInterface.findByEmail(email)).thenReturn(Optional.empty());
        when(userFactoryInterface.createNewUser(eq(userID), eq(userName), eq(email), eq(function), any(PasswordHash.class), eq(photo), eq(profileID))).
                thenReturn(userToBeSaved);
        when(userRepositoryInterface.save(userToBeSaved)).thenReturn(userToBeSaved);

        when(userUIInfoDTOAssembler.toNativeDTO(userToBeSaved)).thenReturn(userUIDTO);

        //act
        Optional<UserUIInfoDTO> result = registerUserAppService.
                createAndSaveUser(userRegistrationRequest);

        assertEquals(Optional.of(userUIDTO), result);

    }

    @Test
    void testCreateAndSaveUser_FailureCase_UserEmailExists() {
        //arrange
        UserRegistrationRequest userRegistrationRequest =
                new UserRegistrationRequest();

        userRegistrationRequest.userName = "User Name";
        userRegistrationRequest.email = "teste@email.com";
        userRegistrationRequest.function = "Dev";
        userRegistrationRequest.password = "passwordTest";
        userRegistrationRequest.photo = "https://www.isep.ipp.pt/img/logo3.png";

        Email email = Email.createEmail("teste@email.com");
        User userMock = mock(User.class);
        UserInternalDTO userDTO = mock(UserInternalDTO.class);
        List<Profile> profileList = new ArrayList<>();
        ProfileID profileID = ProfileID.generateID();

        when(profileRepository.getAllProfiles()).thenReturn(profileList);
        when(defaultProfileDomainService.getDefaultProfileID(profileList)).thenReturn(Optional.of(profileID));
        when(userRepositoryInterface.findByEmail(email)).thenReturn(Optional.of(userMock));
        when(userDTOAssembler.toDTO(userMock)).thenReturn(userDTO);

        //act
        Optional<UserUIInfoDTO> result = registerUserAppService.
                createAndSaveUser(userRegistrationRequest);
        //assert
        assertEquals(Optional.empty(), result);
    }

}