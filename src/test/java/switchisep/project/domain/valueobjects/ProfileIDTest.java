package switchisep.project.domain.valueobjects;

import org.junit.jupiter.api.Test;
import switchisep.project.domain.profile.Profile;

import static org.junit.jupiter.api.Assertions.*;

class ProfileIDTest {

    @Test
    void getID() {
        String nameDirector = "Director";
        Profile director = new Profile(Name.createName(nameDirector));
        ProfileID prfDirector = ProfileID.generateID();
        String id = prfDirector.getProfileID();
        assertEquals(id, prfDirector.getProfileID());
    }

    @Test
    void checkEqualsHasCodeOverride() {
        //Arrange
        String nameDirector = "Director";
        String nameVisitor = "Visitor";
        Profile director = new Profile(Name.createName(nameDirector));
        Profile otherDirector = new Profile(Name.createName(nameDirector));

        Profile visitor = new Profile(Name.createName(nameVisitor));
        Object obj = new Object();
        //Act
        ProfileID prfDirector = ProfileID.generateID();
        ProfileID prfVisitor = ProfileID.generateID();
        ProfileID prfOther = ProfileID.generateID();
        // Assert
        assertEquals(prfDirector, prfDirector);
        assertNotEquals(prfDirector, prfOther);
        assertEquals(prfDirector.hashCode(), prfDirector.hashCode());

        assertNotEquals(prfDirector, prfVisitor);
        assertNotEquals(null, prfDirector);
        assertNotEquals(prfDirector, obj);
    }

    @Test
    void sameValue(){
        String nameDirector = "Director";
        String nameVisitor = "Visitor";
        Profile director = new Profile(Name.createName(nameDirector));
        Profile visitor = new Profile(Name.createName(nameVisitor));
        //Act
        ProfileID prfDirector = ProfileID.generateID();
        ProfileID prfVisitor = ProfileID.generateID();
        //Assert
        assertFalse(prfDirector.sameValueAs(prfVisitor));
        assertTrue(prfDirector.sameValueAs(prfDirector));
        assertFalse(prfDirector.sameValueAs(null));
    }

}