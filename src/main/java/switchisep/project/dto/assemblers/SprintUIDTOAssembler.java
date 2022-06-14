package switchisep.project.dto.assemblers;

import org.springframework.stereotype.Service;
import switchisep.project.dto.SprintUIDTO;
import switchisep.project.domain.sprint.Sprint;

@Service
public class SprintUIDTOAssembler {

    public SprintUIDTO toDTO(Sprint sprint){

        SprintUIDTO sprintUIDTO = new SprintUIDTO();

        sprintUIDTO.sprintID = sprint.getSprintID().getTaskContainerIDString();
        sprintUIDTO.status = sprint.getSprintStatus().getSprintStatusDescription();
        sprintUIDTO.sprintOrder = String.valueOf(sprint.getSprintOrder().getSprintOrder());
        sprintUIDTO.startDate = sprint.getPlannedTimePeriod().getStartDate().toString();
        sprintUIDTO.endDate = sprint.getPlannedTimePeriod().getEndDate().toString();

        return sprintUIDTO;
    }
}
