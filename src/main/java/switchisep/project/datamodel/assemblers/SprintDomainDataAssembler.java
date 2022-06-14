package switchisep.project.datamodel.assemblers;

import org.springframework.stereotype.Service;
import switchisep.project.datamodel.SprintJpa;
import switchisep.project.domain.sprint.Sprint;

@Service
public class SprintDomainDataAssembler {
    public SprintJpa toData(Sprint sprint) {
        return new SprintJpa(sprint.getSprintID(),
                sprint.getProjectCode(), sprint.getPlannedTimePeriod(),
                sprint.getSprintStatus(),sprint.getSprintOrder());
    }

    public Sprint toDomain(SprintJpa sprintJPA) {
        return new Sprint(sprintJPA.getSprintID(),
                sprintJPA.getProjectCode(),
                sprintJPA.getPlannedTimePeriod(),
                sprintJPA.getSprintStatus());

    }
}
