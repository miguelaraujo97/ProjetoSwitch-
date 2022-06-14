package switchisep.project.datamodel.assemblers;

import org.springframework.stereotype.Service;
import switchisep.project.datamodel.ProfileJpa;
import switchisep.project.domain.profile.Profile;

@Service
public class ProfileDomainDataAssembler {

    public ProfileJpa toData(Profile profile) {
        return new ProfileJpa(profile.getProfileID(), profile.getName());
    }

    public Profile toDomain(ProfileJpa profileJpa) {
        return new Profile(profileJpa.getId(),profileJpa.getName());
    }
}
