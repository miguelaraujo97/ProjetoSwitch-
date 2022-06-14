package switchisep.project.domain.valueobjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectStatusTest {

    @Test
    void sameValueAsTrue(){

        ProjectStatus status = ProjectStatus.PLANNED;
        ProjectStatus status2 = ProjectStatus.valueOfIgnoreCase("planned");

        boolean result = status.sameValueAs(status2);

        assertTrue(result);

    }

    @Test
    void sameValueAsFalse(){

        ProjectStatus status = ProjectStatus.valueOfIgnoreCase("Planned");
        ProjectStatus status1 = ProjectStatus.CLOSED;

        boolean result = status.sameValueAs(status1);

        assertFalse(result);
    }

}