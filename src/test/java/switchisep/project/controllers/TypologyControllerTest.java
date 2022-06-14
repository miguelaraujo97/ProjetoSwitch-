package switchisep.project.controllers;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import switchisep.project.dto.TypologyCreationDTO;
import switchisep.project.dto.TypologyDTO;
import switchisep.project.services.CreateTypologyService;
import switchisep.project.services.ViewTypologiesService;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
class TypologyControllerTest {

    @InjectMocks
    TypologyController typologyController;

    @Mock
    CreateTypologyService createTypologyService;

    @Mock
    ViewTypologiesService viewTypologiesService;

    @Test
    void createTypology_success() {

        TypologyCreationDTO typologyCreationDTO = new TypologyCreationDTO();
        typologyCreationDTO.description = "TYPOLOGY A";
        TypologyDTO typologyDTO = new TypologyDTO();
        Optional<TypologyDTO> opTypologyDTO = Optional.of(typologyDTO);

        when(createTypologyService.createTypologyAndSave(typologyCreationDTO)).thenReturn(opTypologyDTO);
        ResponseEntity<Object> result = typologyController.createTypology(typologyCreationDTO);

        ResponseEntity<Object> expected = ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(opTypologyDTO);

        assertEquals(result, expected);
    }

    @Test
    void createTypology_fail_entryDataNull() {

        TypologyCreationDTO typologyCreationDTO = null;
        TypologyDTO typologyDTO = new TypologyDTO();
        Optional<TypologyDTO> opTypologyDTO = Optional.of(typologyDTO);

        ResponseEntity<Object> result = typologyController.createTypology(typologyCreationDTO);

        ResponseEntity<Object> expected = ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body("Empty or null data");

        assertEquals(result, expected);
    }

    @Test
    void createTypology_fail_entryDataEmpty() {

        TypologyCreationDTO typologyCreationDTO = new TypologyCreationDTO();
        typologyCreationDTO.description="";
        TypologyDTO typologyDTO = new TypologyDTO();
        Optional<TypologyDTO> opTypologyDTO = Optional.of(typologyDTO);


        ResponseEntity<Object> result = typologyController.createTypology(typologyCreationDTO);

        ResponseEntity<Object> expected = ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body("Empty or null data");

        assertEquals(result, expected);
    }

    @Test
    void createTypology_fail_entryDataBlank() {

        TypologyCreationDTO typologyCreationDTO = new TypologyCreationDTO();
        typologyCreationDTO.description="   ";
        TypologyDTO typologyDTO = new TypologyDTO();
        Optional<TypologyDTO> opTypologyDTO = Optional.of(typologyDTO);


        ResponseEntity<Object> result = typologyController.createTypology(typologyCreationDTO);

        ResponseEntity<Object> expected = ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body("Empty or null data");

        assertEquals(result, expected);
    }

    @Test
    void createTypology_fail_typologyAlreadyExists() {

        TypologyCreationDTO typologyCreationDTO = new TypologyCreationDTO();
        typologyCreationDTO.description="TYPOLOGY A";

        Optional<TypologyDTO> opTypologyDTO = Optional.empty();


        when(createTypologyService.createTypologyAndSave(typologyCreationDTO)).thenReturn(opTypologyDTO);
        ResponseEntity<Object> result = typologyController.createTypology(typologyCreationDTO);

        ResponseEntity<Object> expected = ResponseEntity.status(HttpStatus.CONFLICT).contentType(MediaType.APPLICATION_JSON).body("Typology Already Exists");

        assertEquals(result, expected);
    }

    @Test
    void shouldReturnTypologyById() {

        // Arrange

        String typologyId = "TY-01";

        TypologyDTO typologyDTOMock = mock(TypologyDTO.class);

        Optional<TypologyDTO> typologyDTOOptional = Optional.of(typologyDTOMock);

        when(viewTypologiesService.getTypologyById(typologyId)).thenReturn(typologyDTOOptional);

        ResponseEntity<Object> responseEntityExpected =  ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(typologyDTOMock);

        // Act

        ResponseEntity<Object> responseEntityResult = typologyController.getTypologyById(typologyId);

        // Assert

        assertEquals(responseEntityExpected, responseEntityResult);
    }

    @Test
    void shouldNotReturnTypologyId() {

        // Arrange

        String typologyId = "TY-01";

        when(viewTypologiesService.getTypologyById(typologyId)).thenReturn(Optional.empty());

        ResponseEntity<Object> responseEntityExpected =  ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(("{\"message\":\"No Typology found with that Id.\"}"));

        // Act

        ResponseEntity<Object> responseEntityResult = typologyController.getTypologyById(typologyId);

        // Assert

        assertEquals(responseEntityExpected, responseEntityResult);
    }

    @Test
    void shouldReturnListOfTypologies() {

        // Arrange

        TypologyDTO typologyDTOMock = mock(TypologyDTO.class);

        List<TypologyDTO> typologyDTOList = new ArrayList<>();

        typologyDTOList.add(typologyDTOMock);

        when(viewTypologiesService.getAllTypologies()).thenReturn(typologyDTOList);

        ResponseEntity<Object> responseEntityExpected =  ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(typologyDTOList);

        // Act

        ResponseEntity<Object> responseEntityResult = typologyController.getAllTypologies();

        // Assert

        assertEquals(responseEntityExpected, responseEntityResult);
    }

    @Test
    void shouldReturnEmptyListOfTypologies() {

        // Arrange

        List<TypologyDTO> typologyDTOList = new ArrayList<>();

        when(viewTypologiesService.getAllTypologies()).thenReturn(typologyDTOList);

        ResponseEntity<Object> responseEntityExpected =  ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"message\":\"There are no created Typologies \"}");

        // Act

        ResponseEntity<Object> responseEntityResult = typologyController.getAllTypologies();

        // Assert

        assertEquals(responseEntityExpected, responseEntityResult);
    }
}