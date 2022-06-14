package switchisep.project.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChangePasswordDTOTest {

    @Test
    void constructorTest(){

        ChangePasswordDTO changePasswordDTO = new ChangePasswordDTO();

        assertEquals(changePasswordDTO, changePasswordDTO);

    }

}