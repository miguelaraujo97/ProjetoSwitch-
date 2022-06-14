package switchisep.project.domain.valueobjects;

import java.util.Objects;

public final class ProfileRequestID implements ValueObject<ProfileRequestID> {

    private int profileRequestID;

    public ProfileRequestID() {
    }

    /**
     * Private constructor that accepts a ProfileRequestID as an argument
     *
     * @param inputProfileRequestID Alphanumeric identification of a profile request
     * @author Joao Moreira <1211773@isep.ipp.pt>
     */
    private ProfileRequestID(int inputProfileRequestID) {
        this.profileRequestID = inputProfileRequestID;
    }

    /**
     * Static method to instantiate a profileRequestID VO
     *
     * @param profileRequestID string that identifies a profile request
     * @return profile request unique identification
     * @author Joao Moreira <1211773@isep.ipp.pt>
     */
    public static ProfileRequestID createProfileRequestID(int profileRequestID) {
        return new ProfileRequestID(profileRequestID);
    }

    public int getProfileRequestID() {
        return profileRequestID;
    }

    public boolean sameValueAs(ProfileRequestID other) {
        return other != null && this.profileRequestID ==
                other.profileRequestID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProfileRequestID)) return false;
        ProfileRequestID that = (ProfileRequestID) o;
        return profileRequestID == that.profileRequestID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(profileRequestID);
    }
}
