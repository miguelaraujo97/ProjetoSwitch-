package switchisep.project.dto.assemblers;

import org.junit.jupiter.api.Test;
import switchisep.project.dto.SprintUIDTO;
import switchisep.project.domain.sprint.Sprint;
import switchisep.project.domain.valueobjects.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SprintUIDTOAssemblerTest {

    @Test
    void toDTO(){

        SprintUIDTOAssembler sprintUIDTOAssembler = new SprintUIDTOAssembler();

        SprintID sprintID = SprintID.createSprintID("S0001");
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");
        TimePeriod timePeriod = TimePeriod.createTimePeriod(LocalDate.now(),
                LocalDate.now().plusDays(1));
        SprintStatus sprintStatus = SprintStatus.createSprintStatus(SprintStatus.SprintStatusEnum.STARTED);
        SprintOrder sprintOrder = SprintOrder.createSprintOrder(1);
        Sprint sprint = new Sprint(sprintID,projectCode,timePeriod,sprintStatus);

        SprintUIDTO sprintUIDTO = new SprintUIDTO();
        sprintUIDTO.sprintID = "S0001";
        sprintUIDTO.sprintOrder = "1";
        sprintUIDTO.startDate = LocalDate.now().toString();
        sprintUIDTO.endDate = LocalDate.now().toString();
        sprintUIDTO.status = "Started";

        SprintUIDTO result = sprintUIDTOAssembler.toDTO(sprint);

        assertEquals(sprintUIDTO, result);

    }

}