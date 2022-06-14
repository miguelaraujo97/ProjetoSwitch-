package switchisep.project.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import switchisep.project.dto.SprintCreationInfo;
import switchisep.project.dto.SprintUIDTO;
import switchisep.project.services.CreateSprintService;
import switchisep.project.services.ViewSprintsService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SprintControllerTest {

    @InjectMocks
    SprintController sprintController;

    @Mock
    CreateSprintService createSprintService;

    @Mock
    ViewSprintsService viewSprintsService;

    @Test
    void createNewSprint_UnitTest_SuccessScenario(){
        //Arrange
        SprintCreationInfo sprintCreationInfo = new SprintCreationInfo();
        sprintCreationInfo.plannedStartDate = LocalDate.of(2022,01,01);
        String projectCodePathValue = "A123";


        SprintUIDTO sprintUIDTODouble = mock(SprintUIDTO.class);

        when(createSprintService.createAndSaveSprint(sprintCreationInfo,
                projectCodePathValue)).thenReturn(sprintUIDTODouble);

        //Act
        ResponseEntity<Object> resultResponseEntity = sprintController
                .createNewSprint(sprintCreationInfo, projectCodePathValue);

        ResponseEntity<Object>  expectedResponseEntity =
                ResponseEntity
                        .status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(sprintUIDTODouble);

        //Assert
        assertEquals(expectedResponseEntity, resultResponseEntity);
    }


    @Test
    void createNewSprint_UnitTest_FailureScenario_BadInputs_NullStartDate(){
        //Arrange
        SprintCreationInfo sprintCreationInfo = new SprintCreationInfo();
        String projectCodePathValue = "A123";

        String expectedStringResponse = "Bad data entry.";

        //Act
        ResponseEntity<Object> resultResponseEntity = sprintController
                .createNewSprint(sprintCreationInfo, projectCodePathValue);

        ResponseEntity<String>  expectedResponseEntity = new ResponseEntity<>(expectedStringResponse,
                HttpStatus.BAD_REQUEST);

        //Assert
        assertEquals(expectedResponseEntity, resultResponseEntity);
    }

    @Test
    void shouldReturnSprintById() {

        // Arrange

        String sprintId = "S-01";

        SprintUIDTO sprintUIDTOMock = mock(SprintUIDTO.class);

        Optional<SprintUIDTO> sprintUIDTOOptional = Optional.of(sprintUIDTOMock);

        when(viewSprintsService.getSprintByID(sprintId)).thenReturn(sprintUIDTOOptional);

        ResponseEntity<Object> responseEntityExpected = ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(sprintUIDTOMock);

        // Act

        ResponseEntity<Object> responseEntityResult = sprintController.getSprintByID(sprintId);

        // Assert

        assertEquals(responseEntityExpected, responseEntityResult);
    }

    @Test
    void shouldNotReturnSprintById () {

        // Arrange

        String sprintId = "S-01";

        when(viewSprintsService.getSprintByID(sprintId)).thenReturn(Optional.empty());

        ResponseEntity<Object> responseEntityExpected = ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("{\"message\":\"No sprint found with this id.\"}");

        // Act

        ResponseEntity<Object> responseEntityResult = sprintController.getSprintByID(sprintId);

        // Assert

        assertEquals(responseEntityExpected, responseEntityResult);
    }

    @Test
    void shouldReturnListOfSprints() {

        // Arrange

        String projectCode = "A123";

        List<SprintUIDTO> sprintUIDTOList = new ArrayList<>();

        when(viewSprintsService.getSprintsByProjectCode(projectCode)).thenReturn(sprintUIDTOList);

        ResponseEntity<Object> responseEntityExpected = ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(sprintUIDTOList);

        // Act

        ResponseEntity<Object> responseEntityResult = sprintController.getSprintsByProjectCode(projectCode);

        // Assert

       assertEquals(responseEntityExpected, responseEntityResult);
    }

}