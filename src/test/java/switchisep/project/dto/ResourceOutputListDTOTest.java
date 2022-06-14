package switchisep.project.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResourceOutputListDTOTest {


    @Test
    void testEqualsRepresentationModel_SameObj(){

        //Arrange
        ResourceOutputListDTO resourceOutputListDTO = new ResourceOutputListDTO();
        ResourceOutputListDTO resourceOutputListDTO1 = new ResourceOutputListDTO();

        //Act && Assert
        assertEquals(resourceOutputListDTO, resourceOutputListDTO1);
    }

    @Test
    void testEqualsRepresentationModel_Null(){

        //Arrange
        ResourceOutputListDTO resourceOutputListDTO1 = new ResourceOutputListDTO();

        //Act && Assert
        assertNotEquals(null, resourceOutputListDTO1);
    }

    @Test
    void testEqualsRepresentationModel_DiffClass(){

        //Arrange
        ResourceOutputListDTO resourceOutputListDTO1 = new ResourceOutputListDTO();
        Object other = new Object();

        //Act && Assert
        assertNotEquals(other, resourceOutputListDTO1);
    }
}