package switchisep.project.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchisep.project.dto.ProfileRequestDTO;
import switchisep.project.dto.UIProfileRequestDTO;
import switchisep.project.dto.assemblers.ProfileRequestDTOAssembler;
import switchisep.project.domain.profile.Profile;
import switchisep.project.domain.profilerequest.ProfileRequest;
import switchisep.project.domain.profilerequest.ProfileRequestFactory;
import switchisep.project.domain.user.User;
import switchisep.project.domain.valueobjects.Name;
import switchisep.project.domain.valueobjects.ProfileID;
import switchisep.project.domain.valueobjects.ProfileRequestID;
import switchisep.project.domain.valueobjects.UserID;
import switchisep.project.repositories.interfaces.ProfileRepositoryInterface;
import switchisep.project.repositories.interfaces.ProfileRequestRepositoryInterface;
import switchisep.project.repositories.interfaces.UserRepositoryInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProfileRequestServiceTest {

    @InjectMocks
    ProfileRequestService profileRequestService;
    @Mock
    ProfileRepositoryInterface profileRepositoryInterface;
    @Mock
    UserRepositoryInterface userRepositoryInterface;
    @Mock
    ProfileRequestFactory profileRequestFactory;
    @Mock
    ProfileRequestDTOAssembler profileRequestDTOAssembler;
    @Mock
    ProfileRequestRepositoryInterface profileRequestRepositoryInterface;


    @Test
    void shouldCreateAndSaveProfileRequest() {
        //arrange
        UIProfileRequestDTO uiProfileRequestDTO = new
                UIProfileRequestDTO("Visitor",
                1);

        List<ProfileRequest> profileRequestList = new ArrayList<>();
        when(profileRequestRepositoryInterface.findAll()).thenReturn(profileRequestList);

        User userMock = mock(User.class);
        UserID userID = UserID.createUserID(uiProfileRequestDTO.userID);
        Name name = Name.createName(uiProfileRequestDTO.
                profileName);
        Profile profile = mock(Profile.class);
        ProfileID profileID = mock(ProfileID.class);

        when(profile.getProfileID()).thenReturn(profileID);
        ProfileRequestID profileRequestID = ProfileRequestID.createProfileRequestID(1);

        when(profileRepositoryInterface.findByName(name)).
                thenReturn(Optional.of(profile));
        when(userRepositoryInterface.findById(userID)).
                thenReturn(Optional.of(userMock));
        ProfileRequest profileRequest = mock(ProfileRequest.class);
        when(profileRequestFactory.createProfileRequest(any(ProfileRequestID.class), eq(profileID), eq(userID))).
                thenReturn(profileRequest);

        ProfileRequestDTO expected = new ProfileRequestDTO(1, "1", 1);
        when(profileRequestDTOAssembler.toDTO(profileRequest)).
                thenReturn(expected);
        when(profileRequestRepositoryInterface.save(profileRequest)).
                thenReturn(profileRequest);
        //act
        Optional<ProfileRequestDTO> result = profileRequestService.
                createAndSaveProfileRequest(uiProfileRequestDTO);

        //assert
        assertEquals(expected, result.get());
    }

    @Test
    void shouldFail_CreateAndSaveProfileRequest_ProfileDoesntExist() {
        //arrange
        UIProfileRequestDTO uiProfileRequestDTO = new
                UIProfileRequestDTO("Visitor",
                1);

        UserID userID = UserID.createUserID(1);
        Name name = Name.createName(uiProfileRequestDTO.
                profileName);
        Profile profile = mock(Profile.class);

        when(profileRepositoryInterface.findByName(name)).
                thenReturn(Optional.of(profile));

        //act
        Optional<ProfileRequestDTO> result = profileRequestService.
                createAndSaveProfileRequest(uiProfileRequestDTO);

        //assert
        assertEquals(Optional.empty(), result);
    }
}