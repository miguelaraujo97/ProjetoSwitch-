package switchisep.project.domain.domainservices;

import switchisep.project.domain.profile.Profile;
import switchisep.project.domain.user.User;
import switchisep.project.domain.valueobjects.ProfileID;

import java.util.Optional;

public class UpdateProfileDomainService {

    private User user;
    private Profile profile;

    public UpdateProfileDomainService(User user, Profile profile) {
        if (user == null || profile == null) {
            throw new IllegalArgumentException("Parameters can be null");
        }
        this.user = user;
        this.profile = profile;
    }

    public Optional<User> updateProfile() {
        ProfileID profileID = this.profile.getProfileID();
        user.setProfile(profileID);
        return Optional.of(user);
    }
}