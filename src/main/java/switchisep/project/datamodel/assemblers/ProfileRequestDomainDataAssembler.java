package switchisep.project.datamodel.assemblers;

import org.springframework.stereotype.Service;
import switchisep.project.datamodel.ProfileRequestJpa;
import switchisep.project.domain.profilerequest.ProfileRequest;

@Service
public class ProfileRequestDomainDataAssembler {

    public ProfileRequestJpa toData(ProfileRequest profileRequest) {
        return new ProfileRequestJpa(
                profileRequest.getProfileRequestID(),
                profileRequest.getProfileID(),
                profileRequest.getUserID());
    }

    public ProfileRequest toDomain(ProfileRequestJpa profileRequestJpa) {
        return new ProfileRequest.Builder(
                profileRequestJpa.getProfileRequestID(),
                profileRequestJpa.getProfileID(),
                profileRequestJpa.getUserID()).build();
    }
}
