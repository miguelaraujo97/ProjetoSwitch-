package switchisep.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchisep.project.dto.ProjectDto;
import switchisep.project.dto.UpdateProjectDto;
import switchisep.project.dto.assemblers.ProjectDtoAssembler;
import switchisep.project.domain.project.Project;
import switchisep.project.domain.valueobjects.*;
import switchisep.project.repositories.ProjectRepository;
import switchisep.project.repositories.interfaces.IProjectRepository;

import java.util.Optional;

@Service
public class EditProjectService {


    IProjectRepository projectRepository;
    ProjectDtoAssembler projectDTOAssembler;

    @Autowired
    public EditProjectService(IProjectRepository projectRepository, ProjectDtoAssembler projectDTOAssembler) {

        this.projectRepository = projectRepository;
        this.projectDTOAssembler = projectDTOAssembler;
    }

    /**
     * Service used to set new data in a project.
     * @param newData ProjectDtoNative
     * @return Optional<ProjectDtoNative>
     */
    public Optional<ProjectDto> setNewData(UpdateProjectDto newData) {

        ProjectCode code = ProjectCode.createProjectCode(newData.code);
        Optional<Project> projectOpt = projectRepository.getProjectByProjectCode(code);


        if (projectOpt.isPresent()) {

            Project project = projectOpt.get();

            if (!(newData.startDate == null && newData.endDate == null)) {

                TimePeriod newTimePeriod = TimePeriod.createTimePeriod(newData.startDate, newData.endDate);

                project.setTimePeriod(newTimePeriod);
            }

            if (newData.sprintDuration != null) {

                SprintDuration newSprintDuration = SprintDuration.createSprintDuration(newData.sprintDuration);
                project.setProjectSprintDuration(newSprintDuration);
            }

            if (newData.numberOfPlannedSprints != null) {

                ProjectNumberOfPlannedSprints newNumberOfPlannedSprints = ProjectNumberOfPlannedSprints.
                        createNumberOfPlannedSprints(newData.numberOfPlannedSprints);

                project.setProjectNumberOfPlannedSprints(newNumberOfPlannedSprints);

            }

            if (newData.status != null) {

                ProjectStatus newStatus = ProjectStatus.valueOfIgnoreCase(newData.status);
                project.setProjectStatus(newStatus);
            }


            if (newData.projectDescription !=null) {

                ProjectDescription newDescription = ProjectDescription.createProjectDescription(newData.projectDescription);
                project.setProjectDescription(newDescription);
            }

            Project savedProject = projectRepository.saveProject(project);

            ProjectDto projectDTOUpdated = projectDTOAssembler.toNative(savedProject);

            return Optional.of(projectDTOUpdated);



        } else
            return Optional.empty();


    }


}
