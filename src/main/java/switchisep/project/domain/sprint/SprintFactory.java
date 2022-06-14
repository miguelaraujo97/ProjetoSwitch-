package switchisep.project.domain.sprint;

import org.springframework.stereotype.Service;
import switchisep.project.domain.valueobjects.*;

@Service
public class SprintFactory implements SprintFactoryInterface {

    public Sprint createSprint(SprintID sprintID, ProjectCode projectID,
                               TimePeriod timePeriod, SprintStatus sprintStatus) {
        return new Sprint(sprintID, projectID,
                timePeriod, sprintStatus);
    }

}
