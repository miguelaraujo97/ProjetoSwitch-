package switchisep.project.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import switchisep.project.dto.*;
import switchisep.project.dto.assemblers.UserStoryDomainDTOAssembler;
import switchisep.project.services.AddUsToSprintBacklogService;
import switchisep.project.services.CreateUserStoryService;
import switchisep.project.services.GetUserStoryService;
import switchisep.project.services.ProductBacklogSortedByPriorityService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserStoryControllerTest {

    @InjectMocks
    UserStoryController userStoryController;

    @Mock
    ProductBacklogSortedByPriorityService productBacklogSortedByPriorityService;

    @Mock
    CreateUserStoryService createUserStoryService;

    @Mock
    GetUserStoryService getUserStoryService;

    @Mock
    UserStoryDomainDTOAssembler userStoryDomainDTOAssembler;

    @Mock
    AddUsToSprintBacklogService addUsToSprintbacklogService;

    /**
     * Unit Testing for createUserStory Method
     */

    @Test
    void shouldCreateUserStory() {

        // Arrange

        UserStoryInput userStoryInput = new UserStoryInput();

        userStoryInput.description = "Teste";

        NewUserStoryInfo newUserStoryInfo = new NewUserStoryInfo();

        newUserStoryInfo.description = "Teste";
        newUserStoryInfo.projectCode = "A123";

        UserStoryDTO userStoryDTO = new UserStoryDTO();

        userStoryDTO.description = "Teste";
        userStoryDTO.projectCode = "A123";
        userStoryDTO.priority = 1;
        userStoryDTO.userStoryID = "62cf56dd-64f9-419e-afd0-3d8fd1037b0c";

        Optional<UserStoryDTO> optionalUserStoryVoDTO =
                Optional.of(userStoryDTO);

        when(userStoryDomainDTOAssembler.
                toNewUserStoryInfo("Teste", "A123")).thenReturn(newUserStoryInfo);

        when(createUserStoryService.createAndSaveUserStory(newUserStoryInfo))
                .thenReturn(optionalUserStoryVoDTO);

        // Act

        ResponseEntity<Object> responseEntity =
                userStoryController.createUserStory(userStoryInput, "A123");

        // Assert
        assertEquals(201, responseEntity.getStatusCodeValue());
    }

    @Test
    void shouldNotCreateUserStory() {

        // Arrange

        UserStoryInput userStoryInput = new UserStoryInput();

        userStoryInput.description = "Teste";

        NewUserStoryInfo newUserStoryInfo = new NewUserStoryInfo();

        newUserStoryInfo.description = "Teste";
        newUserStoryInfo.projectCode = "A123";

        UserStoryDTO userStoryDTO = new UserStoryDTO();

        userStoryDTO.description = "Teste";
        userStoryDTO.projectCode = "A123";
        userStoryDTO.priority = 1;
        userStoryDTO.userStoryID = "62cf56dd-64f9-419e-afd0-3d8fd1037b0c";

        when(userStoryDomainDTOAssembler.
                toNewUserStoryInfo("Teste", "A123")).thenReturn(newUserStoryInfo);

        when(createUserStoryService.createAndSaveUserStory(newUserStoryInfo))
                .thenReturn(Optional.empty());

        // Act

        ResponseEntity<Object> responseEntity =
                userStoryController.createUserStory(userStoryInput, "A123");

        // Assert
        assertEquals(409, responseEntity.getStatusCodeValue());
    }

    @Test
    void shouldReturnProductBacklog() {

        // Arrange

        String projectCodeString = "A123";

        UserStoryDTO userStoryDTO = new  UserStoryDTO();

        List<UserStoryDTO> productBacklog = new ArrayList<>();

        productBacklog.add(userStoryDTO);

        when(productBacklogSortedByPriorityService
                .getProductBacklogSortedByPriority(projectCodeString)).thenReturn(Optional.of(productBacklog));

        // Act

        ResponseEntity<Object> responseEntity = userStoryController
                .getProductBacklogSortedByPriority(projectCodeString);

        // Assert
        assertEquals(200, responseEntity.getStatusCodeValue());
    }



    @ParameterizedTest
    @ValueSource(strings = {"A12", "c1", "1", "00", "11111"})
    void should_return_exception_ProjectDontExists(String projectCode) {

        // Arrange

        when(productBacklogSortedByPriorityService
                .getProductBacklogSortedByPriority(projectCode))
                .thenReturn(Optional.empty());

        ResponseEntity<?> expected = ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"message\":\"Project with this code: "+ projectCode+ " do not exists!\"}");

        // Act

        ResponseEntity<?> actual =
                userStoryController
                        .getProductBacklogSortedByPriority(projectCode);

        // Assert

        assertEquals(actual, expected);
    }

    @Test
    void get_the_productBacklog_project_exist_empty_list()  {

        // Arrange

        String projectCode = "A1211112";

        Optional<List<UserStoryDTO>> productBacklogDTO =
                Optional.of(new ArrayList<>());

        when(productBacklogSortedByPriorityService
                .getProductBacklogSortedByPriority(projectCode))
                .thenReturn(productBacklogDTO);

        ResponseEntity<?> expected = ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"message\":\"There are no User Stories with " +
                        "this code: " + projectCode + " \"}");

        // Act

        ResponseEntity<?> actual =
                userStoryController
                        .getProductBacklogSortedByPriority(projectCode);

        // Assert

        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnUserStoryByID() {

        // Arrange

        String userStoryID = "US-d42639c5-3f62-4c88-a441-d919ac2ae7e0";

        String projectCode = "A123";

        FullUserStoryDTO fullUserStoryDTO = new FullUserStoryDTO();

        when(getUserStoryService.getUserStoryById(userStoryID))
                .thenReturn(Optional.of(fullUserStoryDTO));

        // Act

        ResponseEntity<Object> responseEntity = userStoryController.getUserStoryByID(userStoryID, projectCode);

        // Assert

        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    void shouldNotReturnUserStoryByID() {

        // Arrange

        String userStoryID = "US-d42639c5-3f62-4c88-a441-d919ac2ae7e0";

        String projectCode = "A123";

        when(getUserStoryService.getUserStoryById(userStoryID))
                .thenReturn(Optional.empty());

        // Act

        ResponseEntity<Object> responseEntity = userStoryController.getUserStoryByID(userStoryID, projectCode);

        // Assert

        assertEquals(404, responseEntity.getStatusCodeValue());
    }

    @Test
    void shouldReturnErrorMessageWhenEditUserStoryWrongAction() {

        // Arrange

        UpdateUserStoryDTO updateUserStoryDTO = new UpdateUserStoryDTO();

        updateUserStoryDTO.description = "teste";
        updateUserStoryDTO.userStoryID = "US-01";
        updateUserStoryDTO.parentUserStory = "-1";
        updateUserStoryDTO.status = "Planned";
        updateUserStoryDTO.effort = "0";
        updateUserStoryDTO.priority = "1";
        updateUserStoryDTO.projectCode = "A123";
        updateUserStoryDTO.sprintID = "S-01";
        updateUserStoryDTO.action = "wrongAction";

        ResponseEntity<Object> responseEntityExpected = ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body("Bad entry data 1");

        // Act

        ResponseEntity<Object> responseEntityResult = userStoryController.editUserStory(updateUserStoryDTO);

        // Assert

        assertEquals(responseEntityExpected, responseEntityResult);
    }

    @Test
    void shouldReturnErrorMessageWhenEditUserStoryWrongInfo() {

        // Arrange

        UpdateUserStoryDTO updateUserStoryDTO = new UpdateUserStoryDTO();

        updateUserStoryDTO.description = "teste";
        updateUserStoryDTO.userStoryID = "";
        updateUserStoryDTO.parentUserStory = "-1";
        updateUserStoryDTO.status = "Planned";
        updateUserStoryDTO.effort = "0";
        updateUserStoryDTO.priority = "1";
        updateUserStoryDTO.projectCode = "";
        updateUserStoryDTO.sprintID = "";
        updateUserStoryDTO.action = "addToUS";

        ResponseEntity<Object> responseEntityExpected = ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body("Bad entry data 2");

        // Act

        ResponseEntity<Object> responseEntityResult = userStoryController.editUserStory(updateUserStoryDTO);

        // Assert

        assertEquals(responseEntityExpected, responseEntityResult);
    }

    @Test
    void shouldReturnEditedUserStory() {

        // Arrange

        UpdateUserStoryDTO updateUserStoryDTO = new UpdateUserStoryDTO();

        updateUserStoryDTO.description = "teste";
        updateUserStoryDTO.userStoryID = "US-01";
        updateUserStoryDTO.parentUserStory = "-1";
        updateUserStoryDTO.status = "Planned";
        updateUserStoryDTO.effort = "0";
        updateUserStoryDTO.priority = "1";
        updateUserStoryDTO.projectCode = "A123";
        updateUserStoryDTO.sprintID = "S-01";
        updateUserStoryDTO.action = "addToUS";

        UsToSprBacklogDTO usToSprBacklogDTO = new UsToSprBacklogDTO();

        usToSprBacklogDTO.sprintID = "S-01";
        usToSprBacklogDTO.userStoryID = "US-01";
        usToSprBacklogDTO.projectCode = "A123";

        UserStoryDTO userStoryDTOMock = mock(UserStoryDTO.class);

        Optional<UserStoryDTO> userStoryDTOOptional = Optional.of(userStoryDTOMock);

        when(addUsToSprintbacklogService.addUsToSprintBacklog(usToSprBacklogDTO)).thenReturn(userStoryDTOOptional);

        ResponseEntity<Object> responseEntityExpected = ResponseEntity.status(HttpStatus.ACCEPTED).
                contentType(MediaType.APPLICATION_JSON).
                body(userStoryDTOMock);

        // Act

        ResponseEntity<Object> responseEntityResult = userStoryController.editUserStory(updateUserStoryDTO);

        // Assert

        assertEquals(responseEntityExpected, responseEntityResult);
    }

    @Test
    void shouldNotReturnEditedUserStory() {

        UpdateUserStoryDTO updateUserStoryDTO = new UpdateUserStoryDTO();

        updateUserStoryDTO.description = "teste";
        updateUserStoryDTO.userStoryID = "US-01";
        updateUserStoryDTO.parentUserStory = "-1";
        updateUserStoryDTO.status = "Planned";
        updateUserStoryDTO.effort = "0";
        updateUserStoryDTO.priority = "1";
        updateUserStoryDTO.projectCode = "A123";
        updateUserStoryDTO.sprintID = "S-01";
        updateUserStoryDTO.action = "addToUS";

        UsToSprBacklogDTO usToSprBacklogDTO = new UsToSprBacklogDTO();

        usToSprBacklogDTO.sprintID = "S-01";
        usToSprBacklogDTO.userStoryID = "US-01";
        usToSprBacklogDTO.projectCode = "A123";

        when(addUsToSprintbacklogService.addUsToSprintBacklog(usToSprBacklogDTO)).thenReturn(Optional.empty());

        ResponseEntity<Object> responseEntityExpected = ResponseEntity.status(HttpStatus.NOT_FOUND).
                body("Project/Sprint/US not found with that code.");

        // Act
        ResponseEntity<Object> responseEntityResult = userStoryController.editUserStory(updateUserStoryDTO);

        // Assert

        assertEquals(responseEntityExpected, responseEntityResult);
    }

    @Test
    void addUSToSprintBacklog_BadEntry1(){

        UpdateUserStoryDTO updateUserStoryDTO = new UpdateUserStoryDTO();

        updateUserStoryDTO.description = "teste";
        updateUserStoryDTO.userStoryID = "";
        updateUserStoryDTO.parentUserStory = "-1";
        updateUserStoryDTO.status = "Planned";
        updateUserStoryDTO.effort = "0";
        updateUserStoryDTO.priority = "1";
        updateUserStoryDTO.projectCode = "A123";
        updateUserStoryDTO.sprintID = "S-01";
        updateUserStoryDTO.action = "addToUS";

        ResponseEntity expected = ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body("Bad entry data 2");

        ResponseEntity result = userStoryController.editUserStory(updateUserStoryDTO);

        assertEquals(expected, result);
    }

    @Test
    void addUSToSprintBacklog_BadEntry2(){

        UpdateUserStoryDTO updateUserStoryDTO = new UpdateUserStoryDTO();

        updateUserStoryDTO.description = "teste";
        updateUserStoryDTO.userStoryID = "id";
        updateUserStoryDTO.parentUserStory = "-1";
        updateUserStoryDTO.status = "Planned";
        updateUserStoryDTO.effort = "0";
        updateUserStoryDTO.priority = "1";
        updateUserStoryDTO.projectCode = "";
        updateUserStoryDTO.sprintID = "S-01";
        updateUserStoryDTO.action = "addToUS";

        ResponseEntity expected = ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body("Bad entry data 2");

        ResponseEntity result = userStoryController.editUserStory(updateUserStoryDTO);

        assertEquals(expected, result);
    }

    @Test
    void addUSToSprintBacklog_BadEntry3(){

        UpdateUserStoryDTO updateUserStoryDTO = new UpdateUserStoryDTO();

        updateUserStoryDTO.description = "teste";
        updateUserStoryDTO.userStoryID = "qfwf";
        updateUserStoryDTO.parentUserStory = "-1";
        updateUserStoryDTO.status = "Planned";
        updateUserStoryDTO.effort = "0";
        updateUserStoryDTO.priority = "1";
        updateUserStoryDTO.projectCode = "A123";
        updateUserStoryDTO.sprintID = "";
        updateUserStoryDTO.action = "addToUS";

        ResponseEntity expected = ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body("Bad entry data 2");

        ResponseEntity result = userStoryController.editUserStory(updateUserStoryDTO);

        assertEquals(expected, result);
    }

}