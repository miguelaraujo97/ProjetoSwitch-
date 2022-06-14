package switchisep.project.dto.assemblers;

import org.springframework.stereotype.Service;

import switchisep.project.dto.ProjectDto;
import switchisep.project.domain.project.Project;


@Service
public class ProjectDtoAssembler {


    public ProjectDto toNative(Project project) {

        ProjectDto projectDTO = new ProjectDto();

        projectDTO.code = project.getProjectCode().getCode();
        projectDTO.name = project.getName().getName();
        projectDTO.status = project.getStatus().getStatus();
        projectDTO.budget = project.getProjectBudget().getBudget();
        projectDTO.customer = project.getCustomer().getCustomerName();
        projectDTO.sprintDuration = project.getProjectSprintDuration().getSprintDuration();
        projectDTO.numberOfPlannedSprints = project.getProjectNumberOfPlannedSprints().getNumberOfPlannedSprints();
        projectDTO.projectDescription = project.getProjectDescription().getProjectDescription();
        projectDTO.typologyDescription = project.getTypologyDescription().getDescription();
        projectDTO.businessSector = project.getProjectBusinessSector().getBusinessSector();
        projectDTO.startDate = project.getTimePeriod().getStartDate();
        projectDTO.endDate = project.getTimePeriod().getEndDate();


        return projectDTO;
    }


}
