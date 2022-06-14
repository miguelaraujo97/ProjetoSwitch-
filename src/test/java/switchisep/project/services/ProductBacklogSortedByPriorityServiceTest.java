package switchisep.project.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchisep.project.dto.UserStoryDTO;
import switchisep.project.dto.assemblers.UserStoryDomainDTOAssembler;
import switchisep.project.domain.userstory.UserStory;
import switchisep.project.domain.valueobjects.ProjectCode;
import switchisep.project.repositories.ProjectRepository;
import switchisep.project.repositories.interfaces.IUserStoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductBacklogSortedByPriorityServiceTest {

    @InjectMocks
    ProductBacklogSortedByPriorityService productBacklogSortedByPriorityService;

    @Mock
    IUserStoryRepository userStoryRepository;

    @Mock
    ProjectRepository projectRepository;

    @Mock
    UserStoryDomainDTOAssembler userStoryDomainDTOAssembler;

    @Test
    void shouldReturnProductBacklogOrderedByPriority() {

        // Arrange

        String projectCodeString = "A123";

        UserStory userStory = mock(UserStory.class);

        List<UserStory> productBacklog = new ArrayList<>();

        productBacklog.add(userStory);

        UserStoryDTO userStoryDTO = mock(UserStoryDTO.class);

        List<UserStoryDTO> sortedProductBacklog = new ArrayList<>();

        sortedProductBacklog.add(userStoryDTO);

        ProjectCode projectCode = ProjectCode.createProjectCode( projectCodeString );

        when(projectRepository.existsProjectByProjectCode(projectCode)).thenReturn( true );

        when(userStoryRepository.findAllByProjectCode(projectCode)).thenReturn( productBacklog );

        when(userStoryDomainDTOAssembler.fromDomainToDTO(userStory)).thenReturn( userStoryDTO );

        // Act

        Optional<List<UserStoryDTO>> result = productBacklogSortedByPriorityService
                .getProductBacklogSortedByPriority( projectCodeString );

        // Assert

        assertEquals(Optional.of(sortedProductBacklog), result);
    }

    @Test
    void shouldNotReturnProductBacklogOrderedByPriority() {

        // Arrange

        String projectCodeString = "A123";

        ProjectCode projectCode = ProjectCode.createProjectCode( projectCodeString );

        when(projectRepository.existsProjectByProjectCode(projectCode)).thenReturn( false );

        // Act

        Optional<List<UserStoryDTO>> result = productBacklogSortedByPriorityService
                .getProductBacklogSortedByPriority( projectCodeString );

        // Assert

        assertEquals(Optional.empty(), result);
    }
}
