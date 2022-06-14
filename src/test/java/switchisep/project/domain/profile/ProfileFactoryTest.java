package switchisep.project.domain.profile;

import org.junit.jupiter.api.Test;
import switchisep.project.domain.valueobjects.Name;

import static org.junit.jupiter.api.Assertions.*;

class ProfileFactoryTest {

    ProfileFactory profileFactory = new ProfileFactory();

    @Test
    void createProfile_different_objects_same_name() {
        //Arrange
        String profileName = "Director";
        //Act
        Profile profileOne = profileFactory.createProfile(Name.createName(profileName));
        Profile profileTwo = profileFactory.createProfile(Name.createName(profileName));


        //Assert
        assertEquals(profileOne, profileTwo);
    }

    @Test
    void createProfile_different_objects_different_name() {
        //Arrange
        String profileName = "Director";
        String profileNameTwo = "Administrator";
        //Act
        Profile profileOne = profileFactory.createProfile(Name.createName(profileName));
        Profile profileTwo = profileFactory.createProfile(Name.createName(profileNameTwo));
        //Assert
        assertNotEquals(profileOne, profileTwo);
    }
}