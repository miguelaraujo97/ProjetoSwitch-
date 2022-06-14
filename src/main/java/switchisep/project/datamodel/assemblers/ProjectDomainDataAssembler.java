package switchisep.project.datamodel.assemblers;

import org.springframework.stereotype.Service;
import switchisep.project.datamodel.ProjectJpa;
import switchisep.project.domain.project.Project;

@Service
public class ProjectDomainDataAssembler {

    public ProjectJpa toData(Project project) {

        return new ProjectJpa(project.getProjectCode(), project.getName(), project.getCustomer(),
                project.getProjectDescription(), project.getProjectBudget(), project.getProjectSprintDuration(),
                project.getStatus(), project.getProjectNumberOfPlannedSprints(), project.getTypologyDescription(),
                project.getTimePeriod(), project.getProjectBusinessSector());
    }

    public Project toDomain(ProjectJpa projectJpa){

        return new Project.Builder(projectJpa.getCode(),projectJpa.getName(),projectJpa.getCustomer()).projectDescription(
                projectJpa.getProjectDescription()).projectBudget(projectJpa.getBudget()).projectSprintDuration(projectJpa.getSprintDuration())
                .projectStatus(projectJpa.getStatus()).projectNumberOfPlannedSprints(projectJpa.getNumberOfPlannedSprints())
                .projectTypologyDescription(projectJpa.getTypologyDescription()).projectTimePeriod(projectJpa.getTimePeriod())
                .projectBusinessSector(projectJpa.getBusinessSector()).build();
    }
}
