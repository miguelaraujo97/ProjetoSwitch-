package switchisep.project.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchisep.project.dto.ProjectDto;
import switchisep.project.dto.assemblers.ProjectDtoAssembler;
import switchisep.project.domain.project.Project;
import switchisep.project.domain.project.ProjectInterfaceFactory;
import switchisep.project.domain.valueobjects.*;
import switchisep.project.repositories.ProjectRepository;
import switchisep.project.repositories.TypologyRepository;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateProjectServiceTest {


    @InjectMocks
    CreateProjectService createProjectService;

    @Mock
    ProjectRepository projectRepository;
    @Mock
    TypologyRepository typologyRepository;
    @Mock
    ProjectInterfaceFactory projectInterfaceFactory;

    @Mock
    ProjectDtoAssembler projectDTOAssembler;

    @Test
    void createAndSaveProjectSuccess() {
        //arrange
        ProjectDto projectDTO = mock(ProjectDto.class);
        projectDTO.code = "a123";
        projectDTO.name = "Things";
        projectDTO.customer = "ISEP";
        projectDTO.projectDescription = "Data";
        projectDTO.budget = 0.0;
        projectDTO.sprintDuration = 1;
        projectDTO.numberOfPlannedSprints = 0;
        projectDTO.status = "planned";
        projectDTO.startDate = LocalDate.of(2022, 02, 25);
        projectDTO.endDate = LocalDate.of(2026, 03, 22);
        projectDTO.businessSector = "Knowledge";
        projectDTO.typologyDescription = "Fixed Costs";


        ProjectCode code = ProjectCode.createProjectCode(projectDTO.code);

        ProjectName name = ProjectName.createProjectName(projectDTO.name);
        ProjectCustomer customer = ProjectCustomer.createProjectCustomer(projectDTO.customer);
        ProjectDescription description = ProjectDescription.createProjectDescription(projectDTO.projectDescription);
        ProjectBudget budget = ProjectBudget.createBudget(projectDTO.budget);
        SprintDuration sprintDuration = SprintDuration.createSprintDuration(projectDTO.sprintDuration);
        ProjectStatus status = ProjectStatus.PLANNED;
        ProjectNumberOfPlannedSprints numberOfPlannedSprints = ProjectNumberOfPlannedSprints.createNumberOfPlannedSprints(projectDTO.numberOfPlannedSprints);
        Description typologyDescription = Description.createDescription(projectDTO.typologyDescription);
        TimePeriod timePeriod = TimePeriod.createTimePeriod(projectDTO.startDate, projectDTO.endDate);
        ProjectBusinessSector businessSector = ProjectBusinessSector.createProjectBusinessSector(projectDTO.businessSector);
        Project project = mock(Project.class);

        ProjectDto outProjectDto = mock(ProjectDto.class);

        when(projectRepository.existsProjectByProjectCode(code)).thenReturn(false);
        when(typologyRepository.existsByTypologyDescription(typologyDescription)).thenReturn(true);
        when(projectInterfaceFactory.createProject(code, name, customer, description, budget, sprintDuration,
                status, numberOfPlannedSprints, typologyDescription, timePeriod, businessSector)).thenReturn(project);

        when(projectRepository.saveProject(project)).thenReturn(project);

        when(projectDTOAssembler.toNative(project)).thenReturn(outProjectDto);

        when(createProjectService.getProjectDTO(project)).thenReturn(outProjectDto);


        //act
        Optional<ProjectDto> result = createProjectService.createProjectAndSave(projectDTO);

        //assert
        assertEquals(Optional.of(outProjectDto), result);

    }


    @Test
    void createAndSaveProjectAlreadyExists() {
        //arrange
        ProjectDto projectDTO = mock(ProjectDto.class);
        projectDTO.code = "a123";
        projectDTO.name = "Things";
        projectDTO.customer = "ISEP";

        ProjectCode code = ProjectCode.createProjectCode("a123");


        when(projectRepository.existsProjectByProjectCode(code)).thenReturn(true);

        //act
        Optional<ProjectDto> result = createProjectService.createProjectAndSave(projectDTO);

        //assert
        assertEquals(Optional.empty(), result);

    }

    @Test
    void createAndSaveProjectWithSomeNullValues() {
        //arrange
        ProjectDto projectDTO = mock(ProjectDto.class);
        projectDTO.code = "a123";
        projectDTO.name = "Things";
        projectDTO.customer = "ISEP";



        ProjectCode code = ProjectCode.createProjectCode(projectDTO.code);

        ProjectName name = ProjectName.createProjectName(projectDTO.name);
        ProjectCustomer customer = ProjectCustomer.createProjectCustomer(projectDTO.customer);
        ProjectStatus status = ProjectStatus.valueOfIgnoreCase("planned");
        ProjectDescription projectDescription = ProjectDescription.createProjectDescription("Insert project description.");
        SprintDuration sprintDuration = SprintDuration.createSprintDuration(1);
        ProjectBudget budget = ProjectBudget.createBudget(0);
        ProjectNumberOfPlannedSprints numberOfPlannedSprints =  ProjectNumberOfPlannedSprints.createNumberOfPlannedSprints(0);
        Description typologyDescription = Description.createDescription("Insert typology.");
        TimePeriod timePeriod = TimePeriod.createTimePeriod(LocalDate.now(),LocalDate.now().plusYears(2));
        ProjectBusinessSector businessSector = ProjectBusinessSector.createProjectBusinessSector("Insert business sector");
        Project project = mock(Project.class);

        ProjectDto outProjectDto = mock(ProjectDto.class);

        when(projectRepository.existsProjectByProjectCode(code)).thenReturn(false);
        when(projectInterfaceFactory.createProject(code, name, customer, projectDescription, budget, sprintDuration,
                status, numberOfPlannedSprints, typologyDescription, timePeriod, businessSector)).thenReturn(project);

        when(projectRepository.saveProject(project)).thenReturn(project);

        when(projectDTOAssembler.toNative(project)).thenReturn(outProjectDto);

        when(createProjectService.getProjectDTO(project)).thenReturn(outProjectDto);


        //act
        Optional<ProjectDto> result = createProjectService.createProjectAndSave(projectDTO);

        //assert
        assertEquals(Optional.of(outProjectDto), result);

    }



    @Test
    void testGetProjectDTO_CheckConversion() {
        //arrange
        Project project = mock(Project.class);

        ProjectDto projectDTO = mock(ProjectDto.class);

        when(projectDTOAssembler.toNative(project)).thenReturn(projectDTO);
        //act
        ProjectDto result = createProjectService.getProjectDTO(project);

        //Assert
        assertEquals(projectDTO, result);
    }

//--------------------------------------------------------------------------------------------------------------------//



}

