package switchisep.project.domain.profile;


import org.springframework.stereotype.Service;
import switchisep.project.domain.valueobjects.Name;

@Service
public class ProfileFactory implements ProfileFactoryInterface {
    /**
     * Factory that implements the interface of profile to create instances
     * of profile.
     *
     * @param profileName input name of profile
     * @return new profile
     */
    public Profile createProfile(Name profileName) {
        return new Profile(profileName);
    }

}
