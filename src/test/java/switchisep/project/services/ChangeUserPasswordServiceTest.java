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
import switchisep.project.domain.valueobjects.UserID;
import switchisep.project.repositories.interfaces.UserRepositoryInterface;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ChangeUserPasswordServiceTest {

    @InjectMocks
    ChangeUserPasswordService changeUserPasswordService;

    @Mock
    UserRepositoryInterface userRepository;

    @Mock
    UserDomainInternalDTOAssembler userDomainDtoAssembler;

    @Test
    void setNewPasswordSuccess() {

        UserUIInfoDTO changePasswordToUIDTOExpected = mock(UserUIInfoDTO.class);
        EditUserDTO editUserDTO = new EditUserDTO();
        editUserDTO.userID = 1;
        editUserDTO.oldPassword = "oldPass";
        editUserDTO.newPassword = "newPass";

        UserID userID = UserID.createUserID(editUserDTO.userID);

        User user = mock(User.class);
        User editedUser = mock(User.class);

        when(userRepository.findById(userID)).thenReturn(Optional.of(user));

        when(user.setNewPassword(editUserDTO.newPassword, editUserDTO.oldPassword)).thenReturn(true);

        when(userRepository.save(user)).thenReturn(editedUser);

        when(userDomainDtoAssembler.toNativeDTO(editedUser)).thenReturn(changePasswordToUIDTOExpected);

        //Act
        Optional<UserUIInfoDTO> result = changeUserPasswordService.setNewPassword(editUserDTO);

        //assert
        assertEquals(Optional.of(changePasswordToUIDTOExpected),result);

    }

    @Test
    void setNewPasswordNewPassNull() {

        EditUserDTO editUserDTO = new EditUserDTO();

        editUserDTO.newPassword = null;
        editUserDTO.oldPassword = "oldPass";
        editUserDTO.userID = 1;

        Optional<UserUIInfoDTO> result = changeUserPasswordService.setNewPassword(editUserDTO);

        assertEquals(Optional.empty(), result);

    }

   @Test
    void setNewPasswordNewPassBlank() {

       EditUserDTO editUserDTO = new EditUserDTO();

        editUserDTO.newPassword = "";
        editUserDTO.oldPassword = "oldPass";
        editUserDTO.userID = 1;

        Optional<UserUIInfoDTO> result = changeUserPasswordService.setNewPassword(editUserDTO);

        assertEquals(Optional.empty(), result);

    }

    @Test
    void setNewPasswordOldPassNull() {

        EditUserDTO editUserDTO = new EditUserDTO();

        editUserDTO.newPassword = "newPass";
        editUserDTO.oldPassword = null;
        editUserDTO.userID = 1;

        Optional<UserUIInfoDTO> result = changeUserPasswordService.setNewPassword(editUserDTO);

        assertEquals(Optional.empty(), result);

    }

    @Test
    void setNewPasswordOldPassBlank() {

        EditUserDTO editUserDTO = new EditUserDTO();

        editUserDTO.newPassword = "newPass";
        editUserDTO.oldPassword = "";
        editUserDTO.userID = 1;


        Optional<UserUIInfoDTO> result = changeUserPasswordService.setNewPassword(editUserDTO);

        assertEquals(Optional.empty(), result);

    }
}