package switchisep.project.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfileListDTOTest {

    @Test
    void testEqualsRepresentationModel_SameObj(){

        //Arrange
        ProfileListDTO profileListDTO = new ProfileListDTO();
        ProfileListDTO profileListDTO1 = new ProfileListDTO();

        //Act && Assert
        assertEquals(profileListDTO, profileListDTO1);
    }

    @Test
    void testEqualsRepresentationModel_Null(){

        //Arrange
        ProfileListDTO profileListDTO = new ProfileListDTO();

        //Act && Assert
        assertNotEquals(null, profileListDTO);
    }

    @Test
    void testEqualsRepresentationModel_DiffClass(){

        //Arrange
        ProfileListDTO profileListDTO = new ProfileListDTO();
        Object other = new Object();

        //Act && Assert
        assertNotEquals(other, profileListDTO);
    }

}