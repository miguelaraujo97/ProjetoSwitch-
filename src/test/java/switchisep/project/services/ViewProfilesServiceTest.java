package switchisep.project.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchisep.project.dto.ProfileDTO;
import switchisep.project.dto.assemblers.ProfileDTOAssembler;
import switchisep.project.domain.profile.Profile;
import switchisep.project.domain.valueobjects.ProfileID;
import switchisep.project.repositories.interfaces.ProfileRepositoryInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ViewProfilesServiceTest {

    @InjectMocks
    ViewProfilesService viewProfilesService;

    @Mock
    ProfileRepositoryInterface profileRepositoryInterface;

    @Mock
    ProfileDTOAssembler profileDTOAssembler;

    @Test
    void shouldReturnListOfProfiles() {

        // Arrange

        ProfileDTO profileDTO = new ProfileDTO();

        profileDTO.name = "Teste";
        profileDTO.profileID = "PF-01";

        List<ProfileDTO> profileDTOList = new ArrayList<>();

        profileDTOList.add(profileDTO);

        Profile profileMock = mock(Profile.class);

        List<Profile> profileList = new ArrayList<>();

        profileList.add(profileMock);

        when(profileRepositoryInterface.getAllProfiles()).thenReturn(profileList);

        when(profileDTOAssembler.toNative(profileMock)).thenReturn(profileDTO);

        // Act

        List<ProfileDTO> profileDTOListResult = viewProfilesService.getAllProfiles();

        // Assert

        assertEquals(profileDTOList, profileDTOListResult);
    }

    @Test
    void shouldReturnProfileById() {

        // Arrange

        String profileId = "PF-01";

        ProfileID profileIdVo = ProfileID.generateIdWithString(profileId);

        ProfileDTO profileDTO = new ProfileDTO();

        profileDTO.name = "Teste";
        profileDTO.profileID = "PF-01";

        Optional<ProfileDTO> profileDTOOptional = Optional.of(profileDTO);

        Profile profileMock = mock(Profile.class);

        Optional<Profile> profileOptional = Optional.of(profileMock);

        when(profileRepositoryInterface.findById(profileIdVo)).thenReturn(profileOptional);

        when(profileDTOAssembler.toNative(profileMock)).thenReturn(profileDTO);

        // Act

        Optional<ProfileDTO> profileDTOOptionalResult = viewProfilesService.getProfileById(profileId);

        // Assert

        assertEquals(profileDTOOptional, profileDTOOptionalResult);

    }

    @Test
    void shouldNotRetunrProfileByID() {

        // Arrange

        String profileId = "PF-01";

        ProfileID profileIdVo = ProfileID.generateIdWithString(profileId);

        when(profileRepositoryInterface.findById(profileIdVo)).thenReturn(Optional.empty());

        // Act

        Optional<ProfileDTO> profileDTOOptionalResult = viewProfilesService.getProfileById(profileId);

        // Assert

        assertEquals(Optional.empty(), profileDTOOptionalResult);
    }
}