package switchisep.project.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SprintUIDTOTest {

    @Test
    void testEqualsRepresentationModel_SameObj(){

        //Arrange
        SprintUIDTO sprintUIDTO = new SprintUIDTO();
        SprintUIDTO sprintUIDTO1 = new SprintUIDTO();

        //Act && Assert
        assertEquals(sprintUIDTO, sprintUIDTO1);
    }

    @Test
    void testEqualsRepresentationModel_Null(){

        //Arrange
        SprintUIDTO sprintUIDTO = new SprintUIDTO();

        //Act && Assert
        assertNotEquals(null, sprintUIDTO);
    }

    @Test
    void testEqualsRepresentationModel_DiffClass(){

        //Arrange
        SprintUIDTO sprintUIDTO = new SprintUIDTO();
        Object other = new Object();

        //Act && Assert
        assertNotEquals(other, sprintUIDTO);
    }

}