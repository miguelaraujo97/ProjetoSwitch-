package switchisep.project.domain.domainservices;

import org.junit.jupiter.api.Test;
import switchisep.project.domain.profile.Profile;
import switchisep.project.domain.valueobjects.ProfileID;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DefaultProfileDomainServiceTest {

    @Test
    void getDefaultProfile_Success() {
        //arrange
        Profile profile = mock(Profile.class);
        ProfileID expected = ProfileID.generateID();
        List<Profile> list = new ArrayList<>();
        list.add(profile);
        when(profile.hasName(any())).thenReturn(true);
        when(profile.getProfileID()).thenReturn(expected);
        DefaultProfileDomainService defaultProfileDomainService =
                new DefaultProfileDomainService();
        //act
        Optional<ProfileID> result = defaultProfileDomainService.
                getDefaultProfileID(list);
        //assert
        assertEquals(Optional.of(expected), result);
    }

    @Test
    void getDefaultProfile_Failure_ProfileDoesntExists() {
        //arrange
        Profile profile = mock(Profile.class);
        ProfileID expected = ProfileID.generateID();
        List<Profile> list = new ArrayList<>();
        list.add(profile);
        when(profile.hasName(any())).thenReturn(false);
        when(profile.getProfileID()).thenReturn(expected);
        DefaultProfileDomainService defaultProfileDomainService =
                new DefaultProfileDomainService();
        //act
        Optional<ProfileID> result = defaultProfileDomainService.
                getDefaultProfileID(list);
        //assert
        assertEquals(Optional.empty(), result);
    }


}