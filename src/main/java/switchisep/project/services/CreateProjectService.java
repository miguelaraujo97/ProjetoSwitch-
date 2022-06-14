package switchisep.project.services;

import org.springframework.stereotype.Service;
import switchisep.project.dto.ProjectDto;


import switchisep.project.dto.assemblers.ProjectDtoAssembler;
import switchisep.project.domain.project.Project;
import switchisep.project.domain.project.ProjectInterfaceFactory;
import switchisep.project.domain.valueobjects.*;
import switchisep.project.repositories.ProjectRepository;
import switchisep.project.repositories.TypologyRepository;
import switchisep.project.repositories.interfaces.IProjectRepository;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class CreateProjectService {

    ProjectInterfaceFactory projectInterfaceFactory;

    IProjectRepository projectRepository;

    ProjectDtoAssembler projectDTOAssembler;

    TypologyRepository typologyRepository;

    public CreateProjectService(ProjectInterfaceFactory projectInterfaceFactory, IProjectRepository projectRepository,
                                ProjectDtoAssembler projectDTOAssembler, TypologyRepository typologyRepository) {

        this.projectInterfaceFactory = projectInterfaceFactory;
        this.projectRepository = projectRepository;
        this.projectDTOAssembler = projectDTOAssembler;

        this.typologyRepository = typologyRepository;
    }


    public Optional<ProjectDto> createProjectAndSave(ProjectDto projectDTO) {

        /// mandatory values 1/2 - create and validate if project exists
        ProjectCode code = ProjectCode.createProjectCode(projectDTO.code);

        if (!(projectRepository.existsProjectByProjectCode(code))) {

            /// mandatory values 2/2
            ProjectName name = ProjectName.createProjectName(projectDTO.name);
            ProjectCustomer customer = ProjectCustomer.createProjectCustomer(projectDTO.customer);

            /// default generated values
            ProjectStatus status = ProjectStatus.PLANNED;

            /// optional values
            ProjectDescription projectDescription = ProjectDescription.createProjectDescription("Insert project description.");
            SprintDuration sprintDuration = SprintDuration.createSprintDuration(1);
            ProjectBudget budget = ProjectBudget.createBudget(0);
            ProjectNumberOfPlannedSprints numberOfPlannedSprints =  ProjectNumberOfPlannedSprints.createNumberOfPlannedSprints(0);
            Description typologyDescription = Description.createDescription("Insert typology.");
            TimePeriod timePeriod = TimePeriod.createTimePeriod(LocalDate.now(),LocalDate.now().plusYears(2));
            ProjectBusinessSector businessSector = ProjectBusinessSector.createProjectBusinessSector("Insert business sector");


            if (projectDTO.projectDescription != null) {
                projectDescription = ProjectDescription.createProjectDescription(projectDTO.projectDescription);
            }

            if (projectDTO.sprintDuration != null) {
                sprintDuration = SprintDuration.createSprintDuration(projectDTO.sprintDuration);
            }

            if (projectDTO.budget != null) {
                budget = ProjectBudget.createBudget(projectDTO.budget);
            }

            if (projectDTO.numberOfPlannedSprints != null) {
                numberOfPlannedSprints = ProjectNumberOfPlannedSprints.createNumberOfPlannedSprints(projectDTO.numberOfPlannedSprints);
            }

            if (projectDTO.typologyDescription != null) {
                Description typologyDescriptionVerification = Description.createDescription(projectDTO.typologyDescription);

                if (typologyRepository.existsByTypologyDescription(typologyDescriptionVerification)) {

                    typologyDescription = typologyDescriptionVerification;

                }
            }

            if (projectDTO.startDate != null && projectDTO.endDate != null) {
                timePeriod = TimePeriod.createTimePeriod(projectDTO.startDate, projectDTO.endDate);
            }

            if (projectDTO.businessSector != null) {
                businessSector = ProjectBusinessSector.createProjectBusinessSector(projectDTO.businessSector);
            }


            Project projectCreated = projectInterfaceFactory.createProject(code, name, customer, projectDescription, budget, sprintDuration,
                    status, numberOfPlannedSprints, typologyDescription, timePeriod, businessSector);

            Project projectSaved = projectRepository.saveProject(projectCreated);

            ProjectDto newProjectDtoSaved = getProjectDTO(projectSaved);

            return Optional.of(newProjectDtoSaved);
        }

        return Optional.empty();

    }

    protected ProjectDto getProjectDTO(Project project) {

        return projectDTOAssembler.toNative(project);
    }


}
