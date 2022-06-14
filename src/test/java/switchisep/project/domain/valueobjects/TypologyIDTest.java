package switchisep.project.domain.valueobjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TypologyIDTest {


    @Test
    void getTypologyID() {
        String typ = "TYP-111";
        String typ2 = "TYP-112";
        TypologyID typologyID = TypologyID.createTypologyIdWithString(typ);


        assertEquals(typ, typologyID.getTypologyID());
        assertNotEquals(typ2, typologyID.getTypologyID());

    }



    @Test
    void testEquals() {
        TypologyID typologyID = TypologyID.createTypologyIdWithString("TYP-111");
        TypologyID typologyID1 = TypologyID.createTypologyIdWithString("TYP-111");
        TypologyID typologyID2 = TypologyID.createTypologyIdWithString("TYP-122");
        Object obj = new Object();
        // Assert
        assertEquals(typologyID, typologyID1);
        assertEquals(typologyID, typologyID);
        assertEquals(typologyID.hashCode(), typologyID1.hashCode());

        assertNotEquals(typologyID, typologyID2);
        assertNotEquals(null, typologyID);
        assertNotEquals(typologyID, obj);

        assertNotEquals(typologyID.hashCode(), typologyID2.hashCode());
    }

    @Test
    void sameValueAs() {
        TypologyID typologyID = TypologyID.createTypologyIdWithString("TYP-111");
        TypologyID typologyID1 = TypologyID.createTypologyIdWithString("TYP-111");
        TypologyID typologyID2 = TypologyID.createTypologyIdWithString("TYP-122");

        //Assert
        assertFalse(typologyID.sameValueAs(typologyID2));
        assertTrue(typologyID.sameValueAs(typologyID1));
        assertFalse(typologyID.sameValueAs(null));
    }
}