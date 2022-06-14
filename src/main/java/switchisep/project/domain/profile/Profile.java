package switchisep.project.domain.profile;

import switchisep.project.domain.valueobjects.Entity;
import switchisep.project.domain.valueobjects.Name;
import switchisep.project.domain.valueobjects.ProfileID;

import java.util.Objects;

/**
 * this class aims to create objects from the type Profile.
 *
 * @author created by José Soares (1040817) on 22/12/2021
 * Edited by Luis Alves (1211777) VO aplication
 */
public class Profile implements Entity<Profile> {

    public static final Name DEFAULT_PROFILE_NAME = Name.createName("Visitor");
    private final ProfileID profileID;
    private final Name name;

    /**
     * General constructor.
     *
     * @param profileName profile name
     */
    public Profile(Name profileName) {

        this.profileID = ProfileID.generateID();
        this.name = profileName;
    }
    public Profile(ProfileID profileID, Name name){
        this.profileID=profileID;
        this.name=name;
    }

    /**
     * check if an indicated name is equal to the object profileName.
     *
     * @param profileName indicated name to match the object profileName.
     * @return true if its equal.
     */
    public boolean isTheNameEqual(Name profileName) {
        return getName().equals(profileName);
    }

    /**
     * Method to return the String correspondent to the profile Name
     *
     * @return string of profile Name
     */
    public Name getName() {
        return this.name;
    }

    public ProfileID getProfileID() {
        return this.profileID;
    }

    public boolean hasName(Name name){
        return Objects.equals(this.name, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return sameIdentityAs(profile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Profile{" +
                "profileID=" + profileID +
                ", name=" + name +
                '}';
    }

    @Override
    public boolean sameIdentityAs(Profile other) {
        return other != null && name.sameValueAs(other.name);
    }

}
