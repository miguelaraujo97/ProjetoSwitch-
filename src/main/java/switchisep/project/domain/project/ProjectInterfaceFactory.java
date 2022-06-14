package switchisep.project.domain.project;

import org.springframework.stereotype.Service;
import switchisep.project.domain.valueobjects.*;

@Service
public interface ProjectInterfaceFactory {


    Project createProject(ProjectCode code, ProjectName name,
                          ProjectCustomer customer,
                          ProjectDescription projectDescription,
                          ProjectBudget budget,
                          SprintDuration projectSprintDuration,
                          ProjectStatus status,
                          ProjectNumberOfPlannedSprints numberOfPlannedSprints,
                          Description typologyDescription,
                          TimePeriod timePeriod,
                          ProjectBusinessSector projectBusinessSector)
            throws IllegalArgumentException;
}
