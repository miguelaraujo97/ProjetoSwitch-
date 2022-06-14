package switchisep.project.dto.assemblers;

import org.springframework.stereotype.Service;
import switchisep.project.dto.ProfileRequestDTO;
import switchisep.project.domain.profilerequest.ProfileRequest;

@Service
public class ProfileRequestDTOAssembler {

    public ProfileRequestDTO toDTO(ProfileRequest profileRequest) {
        return new ProfileRequestDTO(profileRequest.getProfileRequestID().getProfileRequestID(),
                profileRequest.getProfileID().getProfileID(),
                profileRequest.getUserID().getUserID());
    }
}