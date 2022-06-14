package switchisep.project.domain.profile;

import org.junit.jupiter.api.Test;
import switchisep.project.domain.valueobjects.Name;

import static org.junit.jupiter.api.Assertions.*;

class ProfileTest {

    @Test
    void areTheProfileNamesEqual() {

        //Arrange
        String profileNameOne = "Director";
        Profile profileOne = new Profile(Name.createName(profileNameOne));
        String profileNameTwo = "Director";
        Profile profileTwo = new Profile(Name.createName(profileNameTwo));

        //Act
        boolean expected = profileOne.isTheNameEqual(profileTwo.getName());

        //Assert
        assertTrue(expected);
    }

    @Test
    void areTheProfileNamesDifferent() {

        //Arrange
        String profileNameOne = "Administrator";
        Profile profileOne = new Profile(Name.createName(profileNameOne));
        String profileNameTwo = "Director";
        Profile profileTwo = new Profile(Name.createName(profileNameTwo));

        //Act
        boolean expected = profileOne.isTheNameEqual(profileTwo.getName());

        //Assert
        assertFalse(expected);
    }

    @Test
    void getProfileIdSuccessfully() {

        //Arrange
        String profileName = "Director";
        Profile profile = new Profile(Name.createName(profileName));
        String profileID = profile.getProfileID().getProfileID();

        //Act
        String expectedResult = "PF";
        String result = profileID.substring(0, 2);

        //Assert
        assertEquals(expectedResult, result);
    }

   @Test
   void getProfileNameSuccessfully() {

        //Arrange
        String actualProfileName = "Director";
        Profile profile = new Profile(Name.createName(actualProfileName));

        //Act
        String expected = profile.getName().getInputName();

        //Assert
        assertEquals(expected, actualProfileName);
    }

    @Test
    void isTheProfileCreatedEqualToItself(){

        //Arrange
        String profileNameOne = "Director";
        Profile profileOne = new Profile(Name.createName(profileNameOne));

        //Act
        boolean result = profileOne.equals(profileOne);

        //Assert
        assertTrue(result);
    }

    @Test
    void areBothProfilesEqual() {

        //Arrange
        String profileNameOne = "Director";
        String profileNameTwo = "Director";

        //Act
        Profile profileOne = new Profile(Name.createName(profileNameOne));
        Profile profileTwo = new Profile(Name.createName(profileNameTwo));

        //Assert
        assertEquals(profileOne, profileTwo);
    }

    @Test
    void areBothProfilesDifferent() {

        //Arrange
        String profileNameOne = "Director";
        String profileNameTwo = "Administrator";

        //Act
        Profile profileOne = new Profile(Name.createName(profileNameOne));
        Profile profileTwo = new Profile(Name.createName(profileNameTwo));

        //Assert
        assertNotEquals(profileOne, profileTwo);
    }

    @Test
    void isOneOfTheProfilesNull(){

        //Arrange
        String profileNameOne = "Director";

        //Act
        Profile profileOne = new Profile(Name.createName(profileNameOne));
        Profile profileTwo = null;

        //Assert
        assertNotEquals(profileOne, profileTwo);
    }

    @Test
    void isTheNameEqualToTheNameProvided() {

        //Arrange
        Name name = Name.createName("Director");
        Profile profile = new Profile(name);

        //Act
        boolean result = profile.hasName(name);

        //Assert
        assertTrue(result);
    }

    @Test
    void isTheNameDifferentFromTheNameProvided() {

        //Arrange
        Name name = Name.createName("Director");
        Name differentName = Name.createName("Administrator");
        Profile profile = new Profile(name);

        //Act
        boolean result = profile.hasName(differentName);

        //Assert
        assertFalse(result);
    }

    @Test
    void areBothHashCodesEqual() {

        //Arrange
        String profileNameOne = "Director";
        String profileNameTwo = "Director";

        //Act
        Profile profileOne = new Profile(Name.createName(profileNameOne));
        Profile profileTwo = new Profile(Name.createName(profileNameTwo));

        //Act & Assert
        assertEquals(profileOne.hashCode(), profileTwo.hashCode());
    }

    @Test
    void areBothHashCodesDifferent() {

        //Arrange
        String profileNameOne = "Director";
        String profileNameTwo = "Administrator";

        //Act
        Profile profileOne = new Profile(Name.createName(profileNameOne));
        Profile profileTwo = new Profile(Name.createName(profileNameTwo));

        //Assert
        assertNotEquals(profileOne.hashCode(), profileTwo.hashCode());
    }

    @Test
    void areBothStringRepresentationsOfProfilesDifferent() {

        //Arrange
        String profileNameOne = "Director";
        String profileNameTwo = "Administrator";

        //Act
        Profile profileOne = new Profile(Name.createName(profileNameOne));
        Profile profileTwo = new Profile(Name.createName(profileNameTwo));

        //Assert
        assertNotEquals(profileOne.toString(), profileTwo.toString());
    }
    @Test
    void areProfileDifferentFromAnotherObjType() {

        //Arrange
        String profileNameOne = "Director";
        Object obj = new Object();

        //Act
        Profile profileOne = new Profile(Name.createName(profileNameOne));


        //Assert
        assertNotEquals(profileOne, obj);
    }

    @Test
    void sameAsOtherProfile(){
        //Arrange
        String profileNameOne = "Director";
        String profileNameTwo = "Administrator";

        //Act
        Profile profileOne = new Profile(Name.createName(profileNameOne));
        Profile profileTwo = new Profile(Name.createName(profileNameTwo));

        assertFalse(profileOne.sameIdentityAs(profileTwo));
        assertTrue(profileOne.sameIdentityAs(profileOne));
    }

}