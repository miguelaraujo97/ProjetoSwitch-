package switchisep.project.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpdateUserStoryDTOTest {

    @Test
    void testUpdateUserStoryDTOConst(){

        UpdateUserStoryDTO updateUserStoryDTO = new UpdateUserStoryDTO();

        assertEquals(updateUserStoryDTO, updateUserStoryDTO);
    }

}