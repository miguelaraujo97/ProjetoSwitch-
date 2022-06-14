package switchisep.project.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchisep.project.dto.NewUserStoryInfo;
import switchisep.project.dto.UserStoryDTO;
import switchisep.project.dto.assemblers.UserStoryDomainDTOAssembler;
import switchisep.project.domain.domainservices.CreateUserStoryDomainService;
import switchisep.project.domain.userstory.UserStory;
import switchisep.project.domain.valueobjects.*;
import switchisep.project.repositories.ProjectRepository;
import switchisep.project.repositories.interfaces.IUserStoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * Unit Tests For UserStoryService Class
 */


@ExtendWith(MockitoExtension.class)
class UserStoryServiceTest {

    @Mock
    IUserStoryRepository userStoryRepository;

    @Mock
    ProjectRepository projectRepositoryStore;

    @Mock
    CreateUserStoryDomainService createUserStoryDomainService;

    @Mock
    UserStory userStory;

    @Mock
    UserStoryDomainDTOAssembler userStoryAssembler;

    @Test
    void createAndSaveUserStorySuccess() {

        // Arrange

        NewUserStoryInfo newUserStoryInfo = new NewUserStoryInfo();

        newUserStoryInfo.projectCode = "A123";
        newUserStoryInfo.description = "Teste";

        List<UserStory> productBacklog = new ArrayList<>();

        UserStoryID userStoryID = UserStoryID.createUserStoryID();

        ProjectCode projectCode = ProjectCode.createProjectCode("A123");


        UserStoryDTO userStoryDTO = new UserStoryDTO();

        userStoryDTO.description = "Teste";
        userStoryDTO.projectCode = "A123";
        userStoryDTO.priority = 1;
        userStoryDTO.userStoryID = "62cf56dd-64f9-419e-afd0-3d8fd1037b0c";

        CreateUserStoryService createUserStoryService =
                new CreateUserStoryService(userStoryRepository, projectRepositoryStore,
                        userStoryAssembler, createUserStoryDomainService);

        when(projectRepositoryStore.existsProjectByProjectCode(projectCode)).thenReturn(true);

        when(userStoryRepository.findAllByProjectCode(projectCode)).thenReturn(productBacklog);

        when(createUserStoryDomainService.createUserStory
                ("Teste", projectCode, productBacklog, userStoryID)).thenReturn(Optional.of(userStory));

        when(userStoryAssembler.fromDomainToDTO(Optional.of(userStory).get())).thenReturn(userStoryDTO);

        // Act

        Optional<UserStoryDTO> result =
                createUserStoryService.createAndSaveUserStory(newUserStoryInfo);

        // Assert
        assertEquals(Optional.of(userStoryDTO), result);
    }

    @Test
    void shouldNotCreateUserStoryProjectDoesntExists() {

        // Arrange

        NewUserStoryInfo newUserStoryInfo = new NewUserStoryInfo();

        newUserStoryInfo.projectCode = "A123";
        newUserStoryInfo.description = "Teste";

        List<UserStory> productBacklog = new ArrayList<>();

        UserStoryID userStoryID = UserStoryID.createUserStoryID();

        ProjectCode projectCode = ProjectCode.createProjectCode("A123");


        UserStoryDTO userStoryDTO = new UserStoryDTO();

        userStoryDTO.description = "Teste";
        userStoryDTO.projectCode = "A123";
        userStoryDTO.priority = 1;
        userStoryDTO.userStoryID = "62cf56dd-64f9-419e-afd0-3d8fd1037b0c";

        CreateUserStoryService createUserStoryService =
                new CreateUserStoryService(userStoryRepository, projectRepositoryStore,
                        userStoryAssembler, createUserStoryDomainService);

        when(projectRepositoryStore.existsProjectByProjectCode(projectCode)).thenReturn(false);

        // Act

        Optional<UserStoryDTO> result =
                createUserStoryService.createAndSaveUserStory(newUserStoryInfo);

        // Assert
        assertEquals(Optional.empty(), result);
    }

    @Test
    void shouldNotCreateUserStoryUsAlreadyExists() {

        // Arrange

        NewUserStoryInfo newUserStoryInfo = new NewUserStoryInfo();

        newUserStoryInfo.projectCode = "A123";
        newUserStoryInfo.description = "Teste";

        List<UserStory> productBacklog = new ArrayList<>();

        UserStoryID userStoryID = UserStoryID.createUserStoryID();

        ProjectCode projectCode = ProjectCode.createProjectCode("A123");


        UserStoryDTO userStoryDTO = new UserStoryDTO();

        userStoryDTO.description = "Teste";
        userStoryDTO.projectCode = "A123";
        userStoryDTO.priority = 1;
        userStoryDTO.userStoryID = "62cf56dd-64f9-419e-afd0-3d8fd1037b0c";

        CreateUserStoryService createUserStoryService =
                new CreateUserStoryService(userStoryRepository, projectRepositoryStore,
                        userStoryAssembler, createUserStoryDomainService);

        when(projectRepositoryStore.existsProjectByProjectCode(projectCode)).thenReturn(true);

        when(userStoryRepository.findAllByProjectCode(projectCode)).thenReturn(productBacklog);

        when(createUserStoryDomainService.createUserStory
                ("Teste", projectCode, productBacklog, userStoryID)).thenReturn(Optional.empty());

        // Act

        Optional<UserStoryDTO> result =
                createUserStoryService.createAndSaveUserStory(newUserStoryInfo);

        // Assert
        assertEquals(Optional.empty(), result);
    }

}