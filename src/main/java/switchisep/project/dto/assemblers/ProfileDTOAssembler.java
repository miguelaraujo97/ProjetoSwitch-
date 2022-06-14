package switchisep.project.dto.assemblers;

import org.springframework.stereotype.Service;
import switchisep.project.dto.ProfileDTO;
import switchisep.project.domain.profile.Profile;

@Service
public class ProfileDTOAssembler {

    public ProfileDTO toNative(Profile profile) {

        ProfileDTO profileDTO = new ProfileDTO();

        profileDTO.name = profile.getName().getInputName();
        profileDTO.profileID = profile.getProfileID().getProfileID();
        return profileDTO;
    }

}
