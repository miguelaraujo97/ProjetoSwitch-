package switchisep.project.domain.sprint;

import org.springframework.stereotype.Service;
import switchisep.project.domain.valueobjects.*;

@Service
public interface SprintFactoryInterface {

    Sprint createSprint(SprintID sprintID, ProjectCode code,
                        TimePeriod timePeriod, SprintStatus sprintStatus);

}
