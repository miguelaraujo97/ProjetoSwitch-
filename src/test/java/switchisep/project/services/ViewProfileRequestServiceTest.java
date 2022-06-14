package switchisep.project.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchisep.project.dto.ProfileRequestDTO;
import switchisep.project.dto.assemblers.ProfileRequestDTOAssembler;
import switchisep.project.domain.profilerequest.ProfileRequest;
import switchisep.project.domain.valueobjects.ProfileRequestID;
import switchisep.project.repositories.ProfileRequestRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ViewProfileRequestServiceTest {

    @InjectMocks
    ViewProfileRequestService viewProfileRequestService;

    @Mock
    ProfileRequestRepository profileRequestRepositoryInterface;

    @Mock
    ProfileRequestDTOAssembler profileRequestDTOAssembler;

    @Test
    void shouldReturnListWithProfileRequests() {

        // Arrange

        ProfileRequest profileRequestMock = mock(ProfileRequest.class);

        List<ProfileRequest> profileRequestList = new ArrayList<>();

        profileRequestList.add(profileRequestMock);

        int profileRequestId = 1;

        String userId = "U01";

        int profileId = 1;

        ProfileRequestDTO profileRequestDTO = new ProfileRequestDTO(profileRequestId, userId, profileId);

        List<ProfileRequestDTO> profileRequestDTOSList = new ArrayList<>();

        profileRequestDTOSList.add(profileRequestDTO);

        when(profileRequestRepositoryInterface.findAll()).thenReturn(profileRequestList);

        when(profileRequestDTOAssembler.toDTO(profileRequestMock)).thenReturn(profileRequestDTO);

        // Act

        List<ProfileRequestDTO> profileRequestResultList = viewProfileRequestService.getAllProfileRequests();

        // Assert
        assertEquals(profileRequestDTOSList, profileRequestResultList);
    }

    @Test
    void shouldReturnProfileRequestById() {

        // Arrange

        ProfileRequest profileRequestMock = mock(ProfileRequest.class);

        Optional<ProfileRequest> profileRequestOptional = Optional.of(profileRequestMock);

        int profileRequestId = 1;

        String userId = "U01";

        int profileId = 1;

        ProfileRequestDTO profileRequestDTO = new ProfileRequestDTO(profileRequestId, userId, profileId);

        Optional<ProfileRequestDTO> profileRequestDTOOptional = Optional.of(profileRequestDTO);

        ProfileRequestID profileRequestID = ProfileRequestID.createProfileRequestID(profileRequestId);

        when(profileRequestRepositoryInterface.findById(profileRequestID)).thenReturn(profileRequestOptional);

        when(profileRequestDTOAssembler.toDTO(profileRequestMock)).thenReturn(profileRequestDTO);

        // Act

        Optional<ProfileRequestDTO> profileRequestDtoResult = viewProfileRequestService.getProfileRequestById(profileRequestId);

        // Assert

        assertEquals(profileRequestDTOOptional, profileRequestDtoResult);
    }

    @Test
    void shouldNotReturnProfileRequestById() {

        // Arrange

        int profileRequestIdInt = 1;

        ProfileRequestID profileRequestID = ProfileRequestID.createProfileRequestID(profileRequestIdInt);

        when(profileRequestRepositoryInterface.findById(profileRequestID)).thenReturn(Optional.empty());

        // Act

        Optional<ProfileRequestDTO> profileRequestDtoResult = viewProfileRequestService.getProfileRequestById(profileRequestIdInt);

        // Assert

        assertEquals(Optional.empty(), profileRequestDtoResult);
    }
}