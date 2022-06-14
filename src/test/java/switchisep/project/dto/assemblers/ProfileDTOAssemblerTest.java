package switchisep.project.dto.assemblers;

import org.junit.jupiter.api.Test;
import switchisep.project.dto.ProfileDTO;
import switchisep.project.domain.profile.Profile;
import switchisep.project.domain.profile.ProfileFactory;
import switchisep.project.domain.valueobjects.Name;
import switchisep.project.domain.valueobjects.ProfileID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProfileDTOAssemblerTest {
    @Test
    void toNative_success() {
        ProfileDTOAssembler profileDTOAssembler = new ProfileDTOAssembler();
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.name = "a";
        profileDTO.profileID = ProfileID.generateID().getProfileID();
        ProfileFactory profileFactory = new ProfileFactory();
        Profile profile = profileFactory.createProfile(Name.createName("a"));
        ProfileDTO result = profileDTOAssembler.toNative(profile);
        assertEquals(result, profileDTO);

    }
}