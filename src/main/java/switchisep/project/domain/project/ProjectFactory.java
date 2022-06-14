package switchisep.project.domain.project;

import org.springframework.stereotype.Service;
import switchisep.project.domain.valueobjects.*;

@Service
public class ProjectFactory implements ProjectInterfaceFactory {


    public Project createProject(ProjectCode code, ProjectName name, ProjectCustomer customer,
                                 ProjectDescription projectDescription, ProjectBudget budget,
                                 SprintDuration projectSprintDuration, ProjectStatus status,
                                 ProjectNumberOfPlannedSprints numberOfPlannedSprints, Description typologyDescription,
                                 TimePeriod timePeriod, ProjectBusinessSector projectBusinessSector){

        return new Project.Builder(code, name, customer).projectDescription(projectDescription).projectBudget(budget).projectSprintDuration(projectSprintDuration)
                .projectStatus(status).projectNumberOfPlannedSprints(numberOfPlannedSprints).projectTypologyDescription(typologyDescription)
                .projectTimePeriod(timePeriod).projectBusinessSector(projectBusinessSector).build();
    }


}
