package switchisep.project.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchisep.project.dto.ProfileDTO;
import switchisep.project.dto.assemblers.ProfileDTOAssembler;
import switchisep.project.domain.profile.ProfileFactoryInterface;
import switchisep.project.domain.profile.Profile;
import switchisep.project.domain.valueobjects.Name;
import switchisep.project.repositories.interfaces.ProfileRepositoryInterface;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateProfileServiceTest {

    @InjectMocks
    CreateProfileService createProfileService;

    @Mock
    ProfileRepositoryInterface profileRepository;

    @Mock
    ProfileFactoryInterface profileFactoryInterface;

    @Mock
    ProfileDTOAssembler profileDTOAssembler;

    @Test
    void createAndSaveProfile_should_succeed() {
        //Arrange
        ProfileDTO profileDto = mock(ProfileDTO.class);
        profileDto.name = ("profile name");
        Name profileName = Name.createName(profileDto.name);
        Profile profile = mock(Profile.class);
        ProfileDTO anotherProfileDTO = mock(ProfileDTO.class);
        anotherProfileDTO.name = ("profile name");
        //Act
        when(profileRepository.existsByName(profileName)).thenReturn(false);
        when(profileFactoryInterface.createProfile(profileName))
                .thenReturn(profile);
        when(profileRepository.save(profile)).thenReturn(profile);
        when(profileDTOAssembler.toNative(profile))
                .thenReturn(anotherProfileDTO);
        //Assert
        Optional<ProfileDTO> actual =
                createProfileService.createAndSaveProfile(profileDto);
        Optional<ProfileDTO> expected = Optional.of(anotherProfileDTO);
        assertEquals(expected, actual);
    }

    @Test
    void createAndSaveProfile_shouldFail_DuplicatedProfile() {
        //Arrange
        ProfileDTO profileDto = mock(ProfileDTO.class);
        profileDto.name = "Abcdef";
        when(profileRepository.existsByName(Name.createName(profileDto.name)))
                .thenReturn(true);
        Optional<ProfileDTO> expected = Optional.empty();
        //Act
        Optional<ProfileDTO> actual =
                createProfileService.createAndSaveProfile(profileDto);
        //Assert
        assertEquals(actual, expected);
    }


}
