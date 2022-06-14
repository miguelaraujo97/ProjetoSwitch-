package switchisep.project.services;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchisep.project.dto.EditUserDTO;
import switchisep.project.dto.UserUiDTO;
import switchisep.project.dto.assemblers.UserDomainInternalDTOAssembler;
import switchisep.project.domain.domainservices.UpdateProfileDomainService;
import switchisep.project.domain.profile.Profile;
import switchisep.project.domain.user.User;
import switchisep.project.repositories.ProfileRepository;
import switchisep.project.repositories.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateUserProfileServiceTest {
    @Mock
    ProfileRepository profileRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    UserDomainInternalDTOAssembler userToUiDTOAssembler;

    @InjectMocks
    UpdateUserProfileService updateUserProfileService;

    @Test
    void shouldReturnOptionalEmpty_noProfile(){
        //arrange
        Profile profile = mock(Profile.class);
        User user = mock(User.class);
        when(profileRepository.findByName(any())).
                thenReturn(Optional.empty());
        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        EditUserDTO editUserDTO = new EditUserDTO();
        editUserDTO.profileID = "teste";
        //act
        Optional<UserUiDTO> opUserDTO =
                updateUserProfileService.profileUpdate(editUserDTO);
        //assert
        assertEquals(Optional.empty(),opUserDTO);
    }

    @Test
    void shouldReturnOptionalEmpty_noUser(){
        //arrange
        Profile profile = mock(Profile.class);
        when(profileRepository.findByName(any())).
                thenReturn(Optional.of(profile));
        when(userRepository.findById(any())).thenReturn(Optional.empty());
        EditUserDTO editUserDTO = new EditUserDTO();
        editUserDTO.profileID = "teste";
        //act
        Optional<UserUiDTO> opUserDTO =
                updateUserProfileService.profileUpdate(editUserDTO);
        //assert
        assertEquals(Optional.empty(),opUserDTO);
    }

    @Test
    void shouldSuccess_validUserAndProfile(){
        //arrange
        Profile profile = mock(Profile.class);
        User user = mock(User.class);
        when(profileRepository.findByName(any())).
                thenReturn(Optional.of(profile));
        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        EditUserDTO editUserDTO = new EditUserDTO();
        editUserDTO.profileID = "teste";
        UpdateProfileDomainService updateProfileDomainService =
                mock(UpdateProfileDomainService.class);

        UserUiDTO userUiDTO = mock(UserUiDTO.class);
        when(userToUiDTOAssembler.toOutputDTO(any())).thenReturn(userUiDTO);
        //act
        Optional<UserUiDTO> opUserDTO =
                updateUserProfileService.profileUpdate(editUserDTO);
        //assert
        assertEquals(Optional.of(userUiDTO),opUserDTO);
//        verify(userToUiDTOAssembler, times(1)).
//                toDTO(user);

    }
}