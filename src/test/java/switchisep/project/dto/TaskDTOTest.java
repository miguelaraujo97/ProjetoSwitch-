package switchisep.project.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskDTOTest {

    @Test
    void testTaskDTOConstructor(){

        TaskDTO taskDTO = new TaskDTO();

        assertEquals(taskDTO, taskDTO);
    }

}