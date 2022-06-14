package switchisep.project.domain.valueobjects;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Component
public class ProfileID implements ValueObject<ProfileID>, Serializable {
    /**
     * Input parameter to create an identification for a certain,
     * profile.
     */
    @Getter
    private final String profileID;
    private static final String REF = "PF";

    protected ProfileID() {
        this.profileID = REF + "-" + UUID.randomUUID().toString();
    }

    protected ProfileID(String profileID) {
        this.profileID = profileID;
    }

    public static ProfileID generateID() {
        return new ProfileID();
    }

    public static ProfileID generateIdWithString(String profileID) {
        return new ProfileID(profileID);
    }

    @Override
    public boolean sameValueAs(ProfileID other) {
        return other != null && this.profileID.equals(other.profileID);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProfileID)) return false;
        ProfileID other = (ProfileID) o;
        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profileID);
    }
}
