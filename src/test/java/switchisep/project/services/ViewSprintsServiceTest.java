package switchisep.project.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchisep.project.dto.SprintUIDTO;
import switchisep.project.dto.assemblers.SprintUIDTOAssembler;
import switchisep.project.domain.sprint.Sprint;
import switchisep.project.domain.valueobjects.ProjectCode;
import switchisep.project.domain.valueobjects.SprintID;
import switchisep.project.repositories.interfaces.SprintRepositoryInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ViewSprintsServiceTest {

    @InjectMocks
    ViewSprintsService viewSprintsService;
    @Mock
    SprintRepositoryInterface sprintRepositoryInterface;
    @Mock
    SprintUIDTOAssembler sprintUIDTOAssembler;

    @Test
    void testGetSprintsByCode() {

        ProjectCode projectCode = ProjectCode.createProjectCode("A123");

        List<SprintUIDTO> expectedList = new ArrayList<>();
        SprintUIDTO sprintUIDTO = new SprintUIDTO();

        expectedList.add(sprintUIDTO);

        List<Sprint> sprintList = new ArrayList<>();

        Sprint sprint = mock(Sprint.class);

        when(sprint.getProjectCode()).thenReturn(projectCode);
        sprintList.add(sprint);

        when(sprintRepositoryInterface.findAll()).thenReturn(sprintList);

        when(sprintUIDTOAssembler.toDTO(sprint)).thenReturn(sprintUIDTO);

        List<SprintUIDTO> resultList = viewSprintsService.getSprintsByProjectCode("A123");

        assertEquals(expectedList, resultList);
    }

    @Test
    void testGetSprintBySprintID() {

        SprintID sprintID = SprintID.createSprintID("S0001");

        SprintUIDTO sprintUIDTO = new SprintUIDTO();
        Optional<SprintUIDTO> expectedOp = Optional.of(sprintUIDTO);

        Sprint sprint = mock(Sprint.class);

        when(sprintRepositoryInterface.findBySprintID(sprintID)).thenReturn(Optional.of(sprint));

        when(sprintUIDTOAssembler.toDTO(sprint)).thenReturn(sprintUIDTO);

        Optional<SprintUIDTO> resultOp = viewSprintsService.getSprintByID("S0001");

        assertEquals(expectedOp, resultOp);
    }
}