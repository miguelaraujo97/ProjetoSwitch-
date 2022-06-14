package switchisep.project.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class UsToSprBacklogDTOTest {

    @Test
    void testEqSameObj() {
        UsToSprBacklogDTO usToSprBacklogDTO = new UsToSprBacklogDTO();

        assertEquals(usToSprBacklogDTO, usToSprBacklogDTO);
    }

    @Test
    void testEqNull() {
        UsToSprBacklogDTO usToSprBacklogDTO = new UsToSprBacklogDTO();

        assertNotEquals(null, usToSprBacklogDTO);
    }

    @Test
    void testEqDiffClass() {
        UsToSprBacklogDTO usToSprBacklogDTO = new UsToSprBacklogDTO();

        Object o = new Object();
        assertNotEquals(o, usToSprBacklogDTO);
    }

    @Test
    void testDifferentDTOs() {
        UsToSprBacklogDTO usToSprBacklogDTO = new UsToSprBacklogDTO();
        usToSprBacklogDTO.projectCode ="a132";
        usToSprBacklogDTO.sprintID = "ahfqw";
        usToSprBacklogDTO.userStoryID = "id";

        UsToSprBacklogDTO usToSprBacklogDTO2 = new UsToSprBacklogDTO();
        usToSprBacklogDTO2.projectCode ="a133";
        usToSprBacklogDTO2.sprintID = "ahfqw";
        usToSprBacklogDTO2.userStoryID = "id";

        UsToSprBacklogDTO usToSprBacklogDTO3 = new UsToSprBacklogDTO();
        usToSprBacklogDTO3.projectCode ="a132";
        usToSprBacklogDTO3.sprintID = "ahfqw-other";
        usToSprBacklogDTO3.userStoryID = "id";

        UsToSprBacklogDTO usToSprBacklogDTO4 = new UsToSprBacklogDTO();
        usToSprBacklogDTO4.projectCode ="a132";
        usToSprBacklogDTO4.sprintID = "ahfqw";
        usToSprBacklogDTO4.userStoryID = "id-2";


        assertNotEquals(usToSprBacklogDTO, usToSprBacklogDTO2);
        assertNotEquals(usToSprBacklogDTO, usToSprBacklogDTO3);
        assertNotEquals(usToSprBacklogDTO, usToSprBacklogDTO4);


        assertNotEquals(usToSprBacklogDTO.hashCode(), usToSprBacklogDTO2.hashCode());
        assertEquals(usToSprBacklogDTO.hashCode(), usToSprBacklogDTO.hashCode());
    }

}