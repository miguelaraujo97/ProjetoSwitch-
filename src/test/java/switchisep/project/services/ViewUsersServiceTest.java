package switchisep.project.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchisep.project.dto.UserUIInfoDTO;
import switchisep.project.dto.UserUiDTO;
import switchisep.project.dto.assemblers.UserDomainInternalDTOAssembler;
import switchisep.project.domain.profile.Profile;
import switchisep.project.domain.user.User;
import switchisep.project.domain.valueobjects.Email;
import switchisep.project.domain.valueobjects.Name;
import switchisep.project.domain.valueobjects.ProfileID;
import switchisep.project.domain.valueobjects.UserID;
import switchisep.project.repositories.interfaces.ProfileRepositoryInterface;
import switchisep.project.repositories.interfaces.UserRepositoryInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ViewUsersServiceTest {

    @InjectMocks
    ViewUsersService viewUsersService;

    @Mock
    UserRepositoryInterface userRepository;

    @Mock
    UserDomainInternalDTOAssembler userDomainDtoAssembler;

    @Mock
    ProfileRepositoryInterface profileRepository;

    @Mock
    User user;

    @Test
    void getUserByID() {
        //Arrange

        UserUIInfoDTO userDTO = mock(UserUIInfoDTO.class);
        int id = 1;

        when(userRepository.findById(any())).thenReturn(Optional.of(user));

        when(userDomainDtoAssembler.toNativeDTO(user)).thenReturn(userDTO);

        Optional<UserUIInfoDTO> expected = Optional.of(userDTO);

        //Act

        Optional<UserUIInfoDTO> result = viewUsersService.getUserByID(id);



        //Assert
        assertEquals(expected, result);

    }

    @Test
    void getUserByIDNotFound() {
        //Arrange
        UserUiDTO userToUiDTO = mock(UserUiDTO.class);

        User user = mock(User.class);
        int id = 1;
        UserID userID = UserID.createUserID(id);

        when(userRepository.findById(userID)).thenReturn(Optional.empty());

        Optional<UserUIInfoDTO> expected = Optional.empty();

        Optional<UserUIInfoDTO> result = viewUsersService.getUserByID(id);

        assertEquals(expected, result);
    }

    @Test
    void getUserByEmailSuccessfully() {

        //Arrange
        User user = mock(User.class);
        UserUiDTO userUiDTO = mock(UserUiDTO.class);
        String emailToFind = "amado@me.com";
        Email email = Email.createEmail(emailToFind);
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        when(userDomainDtoAssembler.toOutputDTO(user)).thenReturn(userUiDTO);

        Optional<UserUiDTO> result = Optional.of(userUiDTO);

        //Act
        Optional<UserUiDTO> expected = viewUsersService.getUserByEmail(emailToFind);

        //Assert
        assertEquals(result, expected);
    }

    @Test
    void getUserByEmailMegaFailure() {

        //Arrange
        User user = mock(User.class);
        UserUiDTO userUiDTO = mock(UserUiDTO.class);
        String emailToFind = "amado@me.com";
        Email email = Email.createEmail(emailToFind);
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());
        Optional<UserUiDTO> result = Optional.empty();

        //Act
        Optional<UserUiDTO> expected = viewUsersService.getUserByEmail(emailToFind);

        //Assert
        assertEquals(result, expected);
    }

    @Test
    void getUsersByProfileSuccessfully() {

        //Arrange
        User user = mock(User.class);
        UserUiDTO userUiDTO = mock(UserUiDTO.class);
        ProfileID profileID = mock(ProfileID.class);
        String profileNameToFind = "Director";
        Name profileName = Name.createName(profileNameToFind);
        Profile profile = mock(Profile.class);

        List<User> userList = new ArrayList<>();
        userList.add(user);
        when(profile.getProfileID()).thenReturn(profileID);
        when(profileRepository.findByName(profileName)).thenReturn(Optional.of(profile));
        when(userRepository.findByProfileID(profileID)).thenReturn(userList);
        when(userDomainDtoAssembler.toOutputDTO(user)).thenReturn(userUiDTO);
        List<UserUiDTO> result = new ArrayList<>();
        result.add(userUiDTO);

        //Act
        List<UserUiDTO> expected = viewUsersService.getUserByProfile(profileNameToFind);

        //Assert
        assertEquals(result, expected);
    }

    @Test
    void getUsersByProfileMegaFailureNoUsersWithThatProfile() {

        //Arrange
        User user = mock(User.class);
        ProfileID profileID = mock(ProfileID.class);
        ProfileID profileID1 = mock(ProfileID.class);
        String profileNameToFind = "Director";
        Name profileName = Name.createName(profileNameToFind);
        Profile profile = mock(Profile.class);
        List<User> userList = new ArrayList<>();
        when(profile.getProfileID()).thenReturn(profileID);
        when(profileRepository.findByName(profileName)).thenReturn(Optional.of(profile));

        //Act
        List<UserUiDTO> expected = viewUsersService.getUserByProfile(profileNameToFind);

        //Assert
        assertEquals(Collections.emptyList(), expected);
    }

    @Test
    void shouldGetListOfAllUsers() {

        // Arrange

        UserUiDTO userUiDTOMock = mock(UserUiDTO.class);

        List<UserUiDTO> userUiDTOSList = new ArrayList<>();

        userUiDTOSList.add(userUiDTOMock);

        User userMock = mock(User.class);

        List<User> userList = new ArrayList<>();

        userList.add(userMock);

        when(userRepository.findAll()).thenReturn(userList);

        when(userDomainDtoAssembler.toOutputDTO(userMock)).thenReturn(userUiDTOMock);

        // Act

        List<UserUiDTO> userUiDTOListResult = viewUsersService.getAllUsers();

        // Assert

        assertEquals(userUiDTOSList, userUiDTOListResult);
    }

}