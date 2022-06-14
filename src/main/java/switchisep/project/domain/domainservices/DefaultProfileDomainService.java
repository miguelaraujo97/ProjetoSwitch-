package switchisep.project.domain.domainservices;

import org.springframework.stereotype.Service;
import switchisep.project.domain.profile.Profile;
import switchisep.project.domain.valueobjects.Name;
import switchisep.project.domain.valueobjects.ProfileID;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultProfileDomainService {

    public DefaultProfileDomainService(){
    //Empty constructor
    }

    public Optional<ProfileID> getDefaultProfileID(List<Profile> profileList){
        Name name=Name.createName("Visitor");
        for (Profile profile: profileList) {
            if(profile.hasName(name)){
                return Optional.of(profile.getProfileID());

                //TODO empty if ???
            };
        }
        return Optional.empty();
    }
}
