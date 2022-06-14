package switchisep.project.datamodel;

import switchisep.project.domain.valueobjects.ProfileID;
import switchisep.project.domain.valueobjects.ProfileRequestID;
import switchisep.project.domain.valueobjects.UserID;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "profile_requests")
public class ProfileRequestJpa implements Serializable {
    @EmbeddedId
    private ProfileRequestID profileRequestID;
    @Embedded
    private ProfileID profileID;
    @Embedded
    private UserID userID;

    public ProfileRequestJpa(ProfileRequestID profileRequestID, ProfileID profileID, UserID userID) {
        this.profileRequestID = profileRequestID;
        this.profileID = profileID;
        this.userID = userID;
    }

    public ProfileRequestJpa() {
    }

    public ProfileRequestID getProfileRequestID() {
        return profileRequestID;
    }

    public ProfileID getProfileID() {
        return profileID;
    }

    public UserID getUserID() {
        return userID;
    }
}
