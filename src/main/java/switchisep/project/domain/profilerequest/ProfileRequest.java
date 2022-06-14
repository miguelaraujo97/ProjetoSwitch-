package switchisep.project.domain.profilerequest;


import switchisep.project.domain.valueobjects.ProfileID;
import switchisep.project.domain.valueobjects.ProfileRequestID;
import switchisep.project.domain.valueobjects.UserID;

import java.util.Objects;

/**
 * this class aims to create objects from the type ProfileRequest
 * US003
 * @author created by José Soares (1040817) on 27/12/2021
 */

public class ProfileRequest {
    //Attributes
    private final ProfileRequestID profileRequestID;
    private final ProfileID profileID;
    private final UserID userID;


    /**
     * constructor to add profile requests
     * US003
     * @param builder of the user that requests the profile
     */

    public ProfileRequest(Builder builder) {
        this.profileRequestID = builder.profileRequestID;
        this.profileID = builder.profileID;
        this.userID = builder.userID;
    }

    /**
     * builder ProfileRequest
     * US003
     */

    public static class Builder {

        private final ProfileRequestID profileRequestID;

        private final ProfileID profileID;
        private final UserID userID;

        public Builder(ProfileRequestID profileRequestID,
                       ProfileID profileID, UserID userID) {
            //mandatory
            this.profileRequestID=profileRequestID;
            this.profileID = profileID;
            this.userID = userID;

        }

        public ProfileRequest build(){
            return new ProfileRequest(this);
        }
    }

    public ProfileID getProfileID() {
        return profileID;
    }

    public UserID getUserID() {
        return userID;
    }

    public ProfileRequestID getProfileRequestID() {
        return profileRequestID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfileRequest that = (ProfileRequest) o;
        return profileID.equals(that.profileID) && userID.equals(that.userID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profileID, userID);
    }
}
