package switchisep.project.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchisep.project.dto.UsToSprBacklogDTO;
import switchisep.project.dto.UserStoryDTO;
import switchisep.project.dto.assemblers.UserStoryDomainDTOAssembler;
import switchisep.project.domain.domainservices.UsToSprintBacklogDomainService;
import switchisep.project.domain.project.Project;
import switchisep.project.domain.sprint.Sprint;
import switchisep.project.domain.userstory.UserStory;
import switchisep.project.domain.valueobjects.ProjectCode;
import switchisep.project.domain.valueobjects.SprintID;
import switchisep.project.domain.valueobjects.UserStoryID;
import switchisep.project.repositories.ProjectRepository;
import switchisep.project.repositories.interfaces.IUserStoryRepository;
import switchisep.project.repositories.interfaces.SprintRepositoryInterface;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddUsToSprintBacklogServiceTest {

    @InjectMocks
    AddUsToSprintBacklogService addUsToSprintbacklogService;
    @Mock
    UsToSprintBacklogDomainService usToSprintBacklogDomainService;
    @Mock
    SprintRepositoryInterface sprintRepository;
    @Mock
    ProjectRepository projectRepository;
    @Mock
    IUserStoryRepository userStoryRepository;
    @Mock
    UserStoryDomainDTOAssembler userStoryDomainDTOAssembler;

    @Test
    void fail_projectInvalid(){
        //arrange
        UsToSprBacklogDTO usToSprBacklogDTO = new UsToSprBacklogDTO();
        usToSprBacklogDTO.projectCode="A123";
        usToSprBacklogDTO.sprintID="1";
        usToSprBacklogDTO.userStoryID="1";
        Project project = mock(Project.class);
        UserStory userStory = mock(UserStory.class);
        Sprint sprint = mock(Sprint.class);
        when(projectRepository.getProjectByProjectCode(any())).
                thenReturn(Optional.empty());
        when(sprintRepository.findBySprintID(any())).
                thenReturn(Optional.of(sprint));
        when(userStoryRepository.findByUserStoryID(any())).
                thenReturn(Optional.of(userStory));
        //act
        Optional<UserStoryDTO> result = addUsToSprintbacklogService.
                addUsToSprintBacklog(usToSprBacklogDTO);
        //assert
        assertEquals(Optional.empty(),
                result);
    }
    @Test
    void fail_usInvalid(){
        //arrange
        UsToSprBacklogDTO usToSprBacklogDTO = new UsToSprBacklogDTO();
        usToSprBacklogDTO.projectCode="A123";
        usToSprBacklogDTO.sprintID="1";
        usToSprBacklogDTO.userStoryID="1";
        Project project = mock(Project.class);
        UserStory userStory = mock(UserStory.class);
        Sprint sprint = mock(Sprint.class);
        when(projectRepository.getProjectByProjectCode(any())).
                thenReturn(Optional.of(project));
        when(sprintRepository.findBySprintID(any())).
                thenReturn(Optional.of(sprint));
        when(userStoryRepository.findByUserStoryID(any())).
                thenReturn(Optional.empty());
        //act
        Optional<UserStoryDTO> result = addUsToSprintbacklogService.
                addUsToSprintBacklog(usToSprBacklogDTO);
        //assert
        assertEquals(Optional.empty(),
                result);
    }

    @Test
    void fail_sprintInvalid(){
        //arrange
        UsToSprBacklogDTO usToSprBacklogDTO = new UsToSprBacklogDTO();
        usToSprBacklogDTO.projectCode="A123";
        usToSprBacklogDTO.sprintID="1";
        usToSprBacklogDTO.userStoryID="1";
        Project project = mock(Project.class);
        UserStory userStory = mock(UserStory.class);
        Sprint sprint = mock(Sprint.class);
        when(projectRepository.getProjectByProjectCode(any())).
                thenReturn(Optional.of(project));
        when(sprintRepository.findBySprintID(any())).
                thenReturn(Optional.empty());
        when(userStoryRepository.findByUserStoryID(any())).
                thenReturn(Optional.of(userStory));
        //act
        Optional<UserStoryDTO> result = addUsToSprintbacklogService.
                addUsToSprintBacklog(usToSprBacklogDTO);
        //assert
        assertEquals(Optional.empty(),
                result);
    }

    @Test
    void addUsToSprintBacklog_domainServiceReturnsEmpty(){
        //arrange
        UsToSprBacklogDTO usToSprBacklogDTO = new UsToSprBacklogDTO();
        usToSprBacklogDTO.projectCode="A123";
        usToSprBacklogDTO.sprintID="1";
        usToSprBacklogDTO.userStoryID="1";
        Project project = mock(Project.class);
        UserStory userStory = mock(UserStory.class);
        Sprint sprint = mock(Sprint.class);
        UserStoryDTO userStoryDTO = mock(UserStoryDTO.class);

        SprintID sprintID = SprintID.createSprintID("1");
        UserStoryID userStoryID = UserStoryID.createUserStoryIdWithString("1");
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");


        when(projectRepository.getProjectByProjectCode(projectCode)).
                thenReturn(Optional.of(project));
        when(userStoryRepository.findByUserStoryID(userStoryID)).
                thenReturn(Optional.of(userStory));
        when(sprintRepository.findBySprintID(sprintID)).
                thenReturn(Optional.of(sprint));
        when(usToSprintBacklogDomainService.addToSprintBackLog(any(Project.class),any(UserStory.class),
                any(Sprint.class))).thenReturn(Optional.empty());

        //act
        Optional<UserStoryDTO> result = addUsToSprintbacklogService.addUsToSprintBacklog(usToSprBacklogDTO);
        //assert
        assertEquals(Optional.empty(),
                result);
    }

    @Test
    void addUsToSprintBacklog_Success(){
        //arrange
        UsToSprBacklogDTO usToSprBacklogDTO = new UsToSprBacklogDTO();
        usToSprBacklogDTO.projectCode="A123";
        usToSprBacklogDTO.sprintID="1";
        usToSprBacklogDTO.userStoryID="1";
        Project project = mock(Project.class);
        UserStory userStory = mock(UserStory.class);
        Sprint sprint = mock(Sprint.class);
        UserStoryDTO userStoryDTO = mock(UserStoryDTO.class);

        SprintID sprintID = SprintID.createSprintID("1");
        UserStoryID userStoryID = UserStoryID.createUserStoryIdWithString("1");
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");


        when(projectRepository.getProjectByProjectCode(projectCode)).
                thenReturn(Optional.of(project));
        when(userStoryRepository.findByUserStoryID(userStoryID)).
                thenReturn(Optional.of(userStory));
        when(sprintRepository.findBySprintID(sprintID)).
                thenReturn(Optional.of(sprint));
        when(usToSprintBacklogDomainService.addToSprintBackLog(any(Project.class),any(UserStory.class),
                any(Sprint.class))).thenReturn(Optional.of(userStory));

        when(userStoryRepository.save(userStory)).thenReturn(userStory);
        when(userStoryDomainDTOAssembler.fromDomainToDTO(userStory))
                .thenReturn(userStoryDTO);
        //act
        Optional<UserStoryDTO> result = addUsToSprintbacklogService.addUsToSprintBacklog(usToSprBacklogDTO);
        //assert
        assertEquals(Optional.of(userStoryDTO),
                result);
    }
}