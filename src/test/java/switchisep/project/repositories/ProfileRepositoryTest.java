package switchisep.project.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchisep.project.datamodel.ProfileJpa;
import switchisep.project.datamodel.assemblers.ProfileDomainDataAssembler;
import switchisep.project.domain.profile.Profile;
import switchisep.project.domain.valueobjects.Name;
import switchisep.project.domain.valueobjects.ProfileID;
import switchisep.project.repositories.jpa.ProfileJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProfileRepositoryTest {

    @InjectMocks
    ProfileRepository profileRepository;

    @Mock
    ProfileJpaRepository profileJpaRepository;

    @Mock
    ProfileDomainDataAssembler profileDomainDataAssembler;

    @Test
    void shouldSaveProfile() {

        // Arrange

        Profile profileMock = mock(Profile.class);

        ProfileJpa profileJpaMock = mock(ProfileJpa.class);

        when(profileDomainDataAssembler.toData(profileMock)).thenReturn(profileJpaMock);

        when(profileJpaRepository.save(profileJpaMock)).thenReturn(profileJpaMock);

        when(profileDomainDataAssembler.toDomain(profileJpaMock)).thenReturn(profileMock);

        // Act

        Profile profileResult  = profileRepository.save(profileMock);

        // Assert

        assertEquals(profileMock, profileResult);
    }

    @Test
    void shouldReturnProfileByName() {

        // Arrange

        String name = "Developer";

        Name nameVo = Name.createName(name);

        ProfileJpa profileJpaMock = mock(ProfileJpa.class);

        Optional<ProfileJpa> profileJpaOptional = Optional.of(profileJpaMock);

        Profile profileMock = mock(Profile.class);

        Optional<Profile> profileOptional = Optional.of(profileMock);

        when(profileJpaRepository.findByName(nameVo)).thenReturn(profileJpaOptional);

        when(profileDomainDataAssembler.toDomain(profileJpaMock)).thenReturn(profileMock);

        // Act

        Optional<Profile> profileOptionalResult = profileRepository.findByName(nameVo);

        // Assert

        assertEquals(profileOptional, profileOptionalResult);
    }

    @Test
    void shouldNotReturnProfileByName() {

        // Arrange

        String name = "Developer";

        Name nameVo = Name.createName(name);

        when(profileJpaRepository.findByName(nameVo)).thenReturn(Optional.empty());

        // Act

        Optional<Profile> profileOptionalResult = profileRepository.findByName(nameVo);

        // Assert

        assertEquals(Optional.empty(), profileOptionalResult);
    }

    @Test
    void shouldReturnTrueWhenCheckingIfProfileExistsByName() {

        // Arrange

        String name = "Developer";

        Name nameVo = Name.createName(name);

        when(profileJpaRepository.existsByName(nameVo)).thenReturn(true);

        // Act

        boolean booleanResult = profileRepository.existsByName(nameVo);

        // Assert

        assertTrue(booleanResult);
    }

    @Test
    void shouldReturnFalseWhenCheckingIfProfileExistsByName() {

        // Arrange

        String name = "Developer";

        Name nameVo = Name.createName(name);

        when(profileJpaRepository.existsByName(nameVo)).thenReturn(false);

        // Act

        boolean booleanResult = profileRepository.existsByName(nameVo);

        // Assert

        assertFalse(booleanResult);
    }

    @Test
    void shouldReturnListOfProfiles() {

        // Arrange

        Profile profileMock = mock(Profile.class);

        List<Profile> profileList = new ArrayList<>();

        profileList.add(profileMock);

        ProfileJpa profileJpaMock = mock(ProfileJpa.class);

        List<ProfileJpa> profileJpaList = new ArrayList<>();

        profileJpaList.add(profileJpaMock);

        when(profileJpaRepository.findAll()).thenReturn(profileJpaList);

        when(profileDomainDataAssembler.toDomain(profileJpaMock)).thenReturn(profileMock);

        // Act

        List<Profile> profileListResult = profileRepository.getAllProfiles();

        // Assert

        assertEquals(profileList, profileListResult);
    }

    @Test
    void shouldReturnProfileById() {

        // Arrange

        ProfileID profileID = ProfileID.generateIdWithString("PF1");

        Profile profileMock = mock(Profile.class);

        Optional<Profile> profileOptional = Optional.of(profileMock);

        ProfileJpa profileJpaMock = mock(ProfileJpa.class);

        Optional<ProfileJpa> profileJpaOptional = Optional.of(profileJpaMock);

        when(profileJpaRepository.findById(profileID)).thenReturn(profileJpaOptional);

        when(profileDomainDataAssembler.toDomain(profileJpaMock)).thenReturn(profileMock);

        // Act

        Optional<Profile> optionalProfileResult = profileRepository.findById(profileID);

        // Assert

        assertEquals(profileOptional, optionalProfileResult);
    }

    @Test
    void shouldNotReturnProfileById() {

        // Arrange

        ProfileID profileID = ProfileID.generateIdWithString("PF1");

        when(profileJpaRepository.findById(profileID)).thenReturn(Optional.empty());

        // Act

        Optional<Profile> optionalProfileResult = profileRepository.findById(profileID);

        // Assert

        assertEquals(Optional.empty(), optionalProfileResult);
    }
}
