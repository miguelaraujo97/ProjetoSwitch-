package switchisep.project.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchisep.project.dto.EditUserDTO;
import switchisep.project.dto.UserUIInfoDTO;
import switchisep.project.dto.assemblers.UserDomainInternalDTOAssembler;

import switchisep.project.domain.user.User;
import switchisep.project.domain.valueobjects.*;
import switchisep.project.repositories.interfaces.UserRepositoryInterface;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EditUserServiceTest {

    @InjectMocks
    EditUserService editUserService;

    @Mock
    UserRepositoryInterface userRepository;

    @Mock
    UserDomainInternalDTOAssembler userUIInfoDTOAssembler;


    @Test
    void setNewUserData_changeFunctionAndPhoto_success() {
        //Arrange

        UserUIInfoDTO userUIInfoDTOExpected = mock(UserUIInfoDTO.class);


        EditUserDTO editUserDTO = mock(EditUserDTO.class);
        editUserDTO.userEmail="joao@gmail.com";
        editUserDTO.function="new function";
        editUserDTO.photo="https://www.isep.ipp.pt/img/newPhoto.png";

        Email emailVO = Email.createEmail(editUserDTO.userEmail);

        User user = mock(User.class);

        when(userRepository.findByEmail(emailVO)).thenReturn(Optional.of(user));

        when(userUIInfoDTOAssembler.toNativeDTO(user)).thenReturn(userUIInfoDTOExpected);

        //Act
        Optional<UserUIInfoDTO> result = editUserService.setNewUserData(editUserDTO);

        //assert
        assertEquals(Optional.of(userUIInfoDTOExpected),result);

        verify(user, times(1)).editUserPhoto(editUserDTO.photo);
        verify(user, times(1)).editUserFunction(editUserDTO.function);
    }

    @Test
    void setNewUserData_changeOnlyFunction_success() {
        //Arrange
        //
        UserUIInfoDTO userUIInfoDTOExpected = mock(UserUIInfoDTO.class);

        EditUserDTO editUserDTO = mock(EditUserDTO.class);
        editUserDTO.userEmail="joao@gmail.com";
        editUserDTO.function="new function";
        editUserDTO.photo=" ";

        Email emailVO = Email.createEmail(editUserDTO.userEmail);

        User user = mock(User.class);

        when(userRepository.findByEmail(emailVO)).thenReturn(Optional.of(user));

        when(userUIInfoDTOAssembler.toNativeDTO(user)).thenReturn(userUIInfoDTOExpected);

        //Act
        Optional<UserUIInfoDTO> result = editUserService.setNewUserData(editUserDTO);

        //assert
        assertEquals(Optional.of(userUIInfoDTOExpected),result);

        verify(user, times(0)).editUserPhoto(editUserDTO.photo);
        verify(user, times(1)).editUserFunction(editUserDTO.function);
    }

    @Test
    void setNewUserData_changeOnlyFunction_photoNull_success() {
        //Arrange
        //
        UserUIInfoDTO userUIInfoDTOExpected = mock(UserUIInfoDTO.class);

        EditUserDTO editUserDTO = mock(EditUserDTO.class);
        editUserDTO.userEmail="joao@gmail.com";
        editUserDTO.function="new function";
        editUserDTO.photo=null;

        Email emailVO = Email.createEmail(editUserDTO.userEmail);

        User user = mock(User.class);

        when(userRepository.findByEmail(emailVO)).thenReturn(Optional.of(user));

        when(userUIInfoDTOAssembler.toNativeDTO(user)).thenReturn(userUIInfoDTOExpected);

        //Act
        Optional<UserUIInfoDTO> result = editUserService.setNewUserData(editUserDTO);

        //assert
        assertEquals(Optional.of(userUIInfoDTOExpected),result);

        verify(user, times(0)).editUserPhoto(editUserDTO.photo);
        verify(user, times(1)).editUserFunction(editUserDTO.function);
    }

    @Test
    void setNewUserData_changeOnlyPhoto_success() {
        //Arrange
        //
        UserUIInfoDTO userUIInfoDTOExpected = mock(UserUIInfoDTO.class);

        EditUserDTO editUserDTO = mock(EditUserDTO.class);
        editUserDTO.userEmail="joao@gmail.com";
        editUserDTO.function=" ";
        editUserDTO.photo="https://www.isep.ipp.pt/img/newPhoto.png";

        Email emailVO = Email.createEmail(editUserDTO.userEmail);

        User user = mock(User.class);

        when(userRepository.findByEmail(emailVO)).thenReturn(Optional.of(user));

        when(userUIInfoDTOAssembler.toNativeDTO(user)).thenReturn(userUIInfoDTOExpected);

        //Act
        Optional<UserUIInfoDTO> result = editUserService.setNewUserData(editUserDTO);

        //assert
        assertEquals(Optional.of(userUIInfoDTOExpected),result);

        verify(user, times(1)).editUserPhoto(editUserDTO.photo);
        verify(user, times(0)).editUserFunction(editUserDTO.function);
    }
    @Test
    void setNewUserData_changeOnlyPhoto_functionIsNull_success() {
        //Arrange
        //
        UserUIInfoDTO userUIInfoDTOExpected = mock(UserUIInfoDTO.class);

        EditUserDTO editUserDTO = mock(EditUserDTO.class);
        editUserDTO.userEmail="joao@gmail.com";
        editUserDTO.function=null;
        editUserDTO.photo="https://www.isep.ipp.pt/img/newPhoto.png";

        Email emailVO = Email.createEmail(editUserDTO.userEmail);

        User user = mock(User.class);

        when(userRepository.findByEmail(emailVO)).thenReturn(Optional.of(user));

        when(userUIInfoDTOAssembler.toNativeDTO(user)).thenReturn(userUIInfoDTOExpected);

        //Act
        Optional<UserUIInfoDTO> result = editUserService.setNewUserData(editUserDTO);

        //assert
        assertEquals(Optional.of(userUIInfoDTOExpected),result);

        verify(user, times(1)).editUserPhoto(editUserDTO.photo);
        verify(user, times(0)).editUserFunction(editUserDTO.function);
    }

    @Test
    void setNewUserData_noPhotoNorFunction_fail() {
        //Arrange
        //
        UserUIInfoDTO userUIInfoDTOExpected = mock(UserUIInfoDTO.class);

        EditUserDTO editUserDTO = mock(EditUserDTO.class);
        editUserDTO.userEmail="joao@gmail.com";
        editUserDTO.function=" ";
        editUserDTO.photo=" ";

        Email emailVO = Email.createEmail(editUserDTO.userEmail);

        User user = mock(User.class);

        when(userRepository.findByEmail(emailVO)).thenReturn(Optional.of(user));

        when(userUIInfoDTOAssembler.toNativeDTO(user)).thenReturn(userUIInfoDTOExpected);

        //Act
        Optional<UserUIInfoDTO> result = editUserService.setNewUserData(editUserDTO);

        //assert
        assertEquals(Optional.of(userUIInfoDTOExpected),result);

        verify(user, times(0)).editUserPhoto(editUserDTO.photo);
        verify(user, times(0)).editUserFunction(editUserDTO.function);
    }

    @Test
    void setNewUserData_noExistingUserWithThatEmail_fail() {
        //Arrange
        //
        UserUIInfoDTO userUIInfoDTOExpected = mock(UserUIInfoDTO.class);

        EditUserDTO editUserDTO = mock(EditUserDTO.class);
        editUserDTO.userEmail="errado@gmail.com";
        editUserDTO.function="new function";
        editUserDTO.photo="https://www.isep.ipp.pt/img/newPhoto.png";

        Email emailVO = Email.createEmail(editUserDTO.userEmail);

        User user = mock(User.class);

        when(userRepository.findByEmail(emailVO)).thenReturn(Optional.empty());

//        when(userUIInfoDTOAssembler.toNativeDTO(user)).thenReturn(userUIInfoDTOExpected);

        //Act
        Optional<UserUIInfoDTO> result = editUserService.setNewUserData(editUserDTO);

        //assert
        assertEquals(Optional.empty(),result);

        verify(user, times(0)).editUserPhoto(editUserDTO.photo);
        verify(user, times(0)).editUserFunction(editUserDTO.function);
    }
}