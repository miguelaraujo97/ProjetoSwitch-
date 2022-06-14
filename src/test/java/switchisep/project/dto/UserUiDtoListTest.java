package switchisep.project.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserUiDtoListTest {

    @Test
    void testEqualsRepresentationModel_SameObj(){

        //Arrange
        UserUiDtoList userUiDTOList = new UserUiDtoList();
        UserUiDtoList userUiDtoList1 = new UserUiDtoList();

        //Act && Assert
        assertEquals(userUiDTOList, userUiDtoList1);
    }

    @Test
    void testEqualsRepresentationModel_Null(){

        //Arrange
        UserUiDtoList userUiDTOList = new UserUiDtoList();

        //Act && Assert
        assertNotEquals(null, userUiDTOList);
    }

    @Test
    void testEqualsRepresentationModel_DiffClass(){

        //Arrange
        UserUiDtoList userUiDTOList = new UserUiDtoList();
        Object other = new Object();

        //Act && Assert
        assertNotEquals(other, userUiDTOList);
    }


}