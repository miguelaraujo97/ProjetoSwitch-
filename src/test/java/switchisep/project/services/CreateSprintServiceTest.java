package switchisep.project.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchisep.project.dto.SprintCreationInfo;
import switchisep.project.dto.SprintUIDTO;
import switchisep.project.dto.assemblers.SprintUIDTOAssembler;
import switchisep.project.domain.domainservices.CreateSprintDomainService;
import switchisep.project.domain.project.Project;
import switchisep.project.domain.sprint.Sprint;
import switchisep.project.domain.sprint.SprintFactoryInterface;
import switchisep.project.domain.valueobjects.ProjectCode;
import switchisep.project.domain.valueobjects.SprintID;
import switchisep.project.error_handling.BusinessRulesException;
import switchisep.project.repositories.ProjectRepository;
import switchisep.project.repositories.interfaces.SprintRepositoryInterface;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CreateSprintServiceTest {

    @InjectMocks
    CreateSprintService createSprintService;

    @Mock
    SprintRepositoryInterface sprintRepositoryInterface;

    @Mock
    ProjectRepository projectRepository;

    @Mock
    SprintFactoryInterface sprintFactoryInterface;

    @Mock
    CreateSprintDomainService createSprintDomainService;

    @Mock
    SprintUIDTOAssembler sprintUIDTOAssembler;


    @Test
    void createAndSaveSprint_UnitTestSuccessScenario_SprintIsCreated() {
        //Arrange
        Project projectDouble = mock(Project.class);
        SprintCreationInfo sprintCreationInfo = new SprintCreationInfo();
        String projectCodePathValue = "A123";

        sprintCreationInfo.plannedStartDate = LocalDate.of(2023, 10, 01);

        SprintUIDTO sprintUIDTO = mock(SprintUIDTO.class);

        ProjectCode projectCode = ProjectCode.createProjectCode(projectCodePathValue);
        when(projectRepository.getProjectByProjectCode(projectCode))
                .thenReturn(Optional.of(projectDouble));

        List<Sprint> sprintListByProjectID = new ArrayList<>();

        when(sprintRepositoryInterface.findSprintsByProjectCode(projectDouble.getProjectCode())).thenReturn(sprintListByProjectID);

        Sprint sprintDouble = mock(Sprint.class);

        SprintID sprintID = SprintID.createSprintID("S001");

        when(createSprintDomainService.createSprint(sprintCreationInfo, projectDouble, sprintListByProjectID,
                sprintFactoryInterface)).thenReturn(sprintDouble);

        when(sprintRepositoryInterface.save(sprintDouble)).thenReturn(sprintDouble);

        when(sprintUIDTOAssembler.toDTO(sprintDouble)).thenReturn(sprintUIDTO);

        //Act
        SprintUIDTO result = createSprintService.createAndSaveSprint(sprintCreationInfo, projectCodePathValue);

        //Assert
        assertEquals(sprintUIDTO, result);
    }

    @Test
    void createAndSaveSprint_UnitTestFailureScenario_ProjectNotFound() {
        //Arrange

        SprintCreationInfo sprintCreationInfo = new SprintCreationInfo();
        String projectCodePathValue = "A123";
        sprintCreationInfo.plannedStartDate = LocalDate.of(2023, 10, 01);


        ProjectCode projectCode = ProjectCode.createProjectCode(projectCodePathValue);
        when(projectRepository.getProjectByProjectCode(projectCode))
                .thenReturn(Optional.empty());
        //Act
        BusinessRulesException exception =
                assertThrows(BusinessRulesException.class, () -> {
                    createSprintService.createAndSaveSprint(sprintCreationInfo, projectCodePathValue);
                });
        //Assert
        assertEquals("No project was found with this project code",
                exception.getMessage());
    }


}