package switchisep.project.domain.typology;

import org.junit.jupiter.api.Test;
import switchisep.project.domain.valueobjects.Description;
import switchisep.project.domain.valueobjects.TypologyID;


import static org.junit.jupiter.api.Assertions.*;

class TypologyTest {

    @Test
    void checkTypologyID_isCorrectID() {
        //Arrange
        Description typologyDescription = Description.createDescription("TYPOLOGY A");
        //Act
        Typology typology = new Typology(TypologyID.createTypologyID(),typologyDescription);
        boolean result = typology.checkTypologyDescription(typologyDescription);
        //Assert
        assertTrue(result);
    }

    @Test
    void checkTypologyDescription_notSameDescription() {
        //Arrange
        Description typologyDescription2 = Description.createDescription("TYPOLOGY B");
        Description typologyDescription = Description.createDescription("TYPOLOGY A");
        //Act
        Typology typology = new Typology(TypologyID.createTypologyID(), typologyDescription);
        boolean result = typology.checkTypologyDescription(typologyDescription2);
        //Assert
        assertFalse(result);
    }

    @Test
    void getTypologyID_sameID() {
        //Arrange
        Description typologyDescription = Description.createDescription("TYPOLOGY A");
        TypologyID typologyID = TypologyID.createTypologyID();
        //Act
        Typology typology = new Typology(typologyID,typologyDescription);
        TypologyID typologyID_1 = typology.getTypologyID();
        //Assert
        assertEquals(typologyID,typologyID_1);
    }



    @Test
    void getTypologyDescription_SameDescription() {
        //Arrange
        Description typologyDescription = Description.createDescription("TYPOLOGY A");
        //Act
        Typology typology = new Typology(TypologyID.createTypologyID(),typologyDescription);
        Description result = typology.getTypologyDescription();
        //Assert
        assertEquals(typologyDescription, result);
    }

    @Test
    void sameIdentityAs_sameValues() {
        Description typologyDescription2 = Description.createDescription("TYPOLOGY A");
        Description typologyDescription = Description.createDescription("TYPOLOGY A");
        //Act
        Typology typology = new Typology(TypologyID.createTypologyID(),typologyDescription);
        Typology typology2 = new Typology(TypologyID.createTypologyID(),typologyDescription2);
        boolean result = typology.sameIdentityAs(typology2);
        //Assert
        assertTrue(result);
    }

    @Test
    void sameIdentityAs_differentValues() {
        Description typologyDescription2 = Description.createDescription("TYPOLOGY B");
        Description typologyDescription = Description.createDescription("TYPOLOGY A");
        //Act
        Typology typology = new Typology(TypologyID.createTypologyID(),typologyDescription);
        Typology typology2 = new Typology(TypologyID.createTypologyID(),typologyDescription2);
        boolean result = typology.sameIdentityAs(typology2);
        //Assert
        assertFalse(result);
    }

    @Test
    void sameIdentityAs_OtherIsNull() {

        Description typologyDescription = Description.createDescription("TYPOLOGY A");

        //Act
        Typology typology = new Typology(TypologyID.createTypologyID(),typologyDescription);
        Typology typology2 = null;

        boolean result = typology.sameIdentityAs(typology2);

        //Assert
        assertFalse(result);
        assertNull(typology2);
    }

    @Test
    void testHashCode_sameTypology() {
        Description typologyDescription = Description.createDescription("TYPOLOGY A");

        //Act
        Typology typology = new Typology(TypologyID.createTypologyID(),typologyDescription);


        assertEquals(typology.hashCode(), typology.hashCode());
    }

    @Test
    void testHashCode_differentTypology() {
        //Act
        Description typologyDescription2 = Description.createDescription("TYPOLOGY B");

        Description typologyDescription = Description.createDescription("TYPOLOGY A");

        //Act
        Typology typology = new Typology(TypologyID.createTypologyID(),typologyDescription);
        Typology typology2 = new Typology(TypologyID.createTypologyID(),typologyDescription2);

        assertNotEquals(typology.hashCode(), typology2.hashCode());
    }

    @Test
    void testEquals_sameTypology() {
        //Act
        Description typologyDescription2 = Description.createDescription("TYPOLOGY A");
        TypologyID typologyID = TypologyID.createTypologyID();
        Description typologyDescription = Description.createDescription("TYPOLOGY A");

        //Act
        Typology typology = new Typology(typologyID,typologyDescription);
        Typology typology2 = new Typology(typologyID, typologyDescription2);
        assertEquals(typology, typology2);
        assertEquals(typology,typology);
    }

    @Test
    void testEquals_differentTypology() {
        //Act
        Description typologyDescription2 = Description.createDescription("TYPOLOGY B");

        Description typologyDescription = Description.createDescription("TYPOLOGY A");

        //Act
        Typology typology = new Typology(TypologyID.createTypologyID(),typologyDescription);
        Typology typology2 = new Typology(TypologyID.createTypologyID(), typologyDescription2);
        Object obj = new Object();
        assertNotEquals(typology, typology2);
        assertNotEquals(typology, obj);
    }
}