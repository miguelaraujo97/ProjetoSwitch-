package switchisep.project.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import switchisep.project.dto.ResourceDTO;
import switchisep.project.dto.ResourceInputDTO;
import switchisep.project.dto.ResourceOutputDTO;
import switchisep.project.dto.assemblers.ResourceDTOAssembler;
import switchisep.project.domain.resource.Resource;
import switchisep.project.services.CreateResourceService;
import switchisep.project.services.EditRoleService;
import switchisep.project.services.ViewResourcesService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@ExtendWith(MockitoExtension.class)
class ResourceControllerTest {

    @InjectMocks
    ResourceController resourceController;

    @Mock
    CreateResourceService createResourceService;

    @Mock
    ResourceDTOAssembler resourceDTOAssembler;

    @Mock
    ViewResourcesService viewResourcesService;

    @Mock
    EditRoleService editRoleService;

    @Test
    void addResourceToProjectSuccessfully() throws JsonProcessingException {
        //Arrange
        ResourceInputDTO resourceInputDTO = new ResourceInputDTO();
        resourceInputDTO.email = "amado@me.com";
        resourceInputDTO.startDate = LocalDate.of(2020, 1, 12);
        resourceInputDTO.endDate = LocalDate.of(2020, 12, 25);
        resourceInputDTO.percentageAllocation = 100;
        resourceInputDTO.costPerHour = 30;
        resourceInputDTO.role = "developer";

        ResourceDTO resourceDTO = new ResourceDTO("a123", "amado@me.com",
                LocalDate.of(2020, 1, 12),
                LocalDate.of(2020, 12, 25), 100, 30, "developer");

        ResourceOutputDTO resourceOutputDTO = new ResourceOutputDTO("R01",
                "amado@me.com",
                LocalDate.of(2020, 1, 12),
                LocalDate.of(2020, 12, 25), 100, 30, "developer");

        when(createResourceService.createAndAdd(resourceDTO)).thenReturn(Optional.of(resourceOutputDTO));

        when(resourceDTOAssembler.toDomainDTO(resourceInputDTO, "a123")).thenReturn(resourceDTO);

        ResponseEntity<Object> expected =
                ResponseEntity.status(HttpStatus.CREATED).body(resourceOutputDTO);

        //Act
        ResponseEntity<Object> actual =
                resourceController.addResourceToProject(resourceInputDTO, "a123");

        //Assert
        assertEquals(actual, expected);
    }

    @Test
    void addResourceToProjectUnsuccessfully() throws JsonProcessingException {

        //Arrange
        ResourceInputDTO resourceInputDTO = new ResourceInputDTO();
        resourceInputDTO.email = "amado@me.com";
        resourceInputDTO.startDate = LocalDate.of(2020, 1, 12);
        resourceInputDTO.endDate = LocalDate.of(2020, 12, 25);
        resourceInputDTO.percentageAllocation = 100;
        resourceInputDTO.costPerHour = 30;
        resourceInputDTO.role = "developer";

        ResourceDTO resourceDTO = new ResourceDTO("a123", "amado@me.com",
                LocalDate.of(2020, 1, 12),
                LocalDate.of(2020, 12, 25), 100, 30, "developer");
        when(createResourceService.createAndAdd(resourceDTO)).thenReturn(Optional.empty());
        ResponseEntity<Object> expected =
                ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).
                        body("Resource cannot be added");

        when(resourceDTOAssembler.toDomainDTO(resourceInputDTO, "a123")).thenReturn(resourceDTO);

        //Act
        ResponseEntity<Object> actual =
                resourceController.addResourceToProject(resourceInputDTO, "a123");

        //Assert
        assertEquals(actual, expected);
    }

    @Test
    void shouldReturnResourceById() {

        // Arrange

        String resourceId = "RS-01";

        ResourceDTO resourceDTOMock = mock(ResourceDTO.class);

        Optional<ResourceDTO> resourceDTOOptional = Optional.of(resourceDTOMock);

        when(viewResourcesService.getResourceById(resourceId)).thenReturn(resourceDTOOptional);

        ResponseEntity<Object> responseEntityExpected =  ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(resourceDTOMock);

        // Act

        ResponseEntity<Object> responseEntityResult = resourceController.getResourceByID(resourceId);

        // Assert

        assertEquals(responseEntityExpected, responseEntityResult);
    }

    @Test
    void shouldNotReturnResourceById() {

        // Arrange

        String resourceId = "RS-01";

        when(viewResourcesService.getResourceById(resourceId)).thenReturn(Optional.empty());

        ResponseEntity<Object> responseEntityExpected = ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"message\":\"No Resource found with that Id.\"}");

        // Act

        ResponseEntity<Object> responseEntityResult = resourceController.getResourceByID(resourceId);

        // Assert

        assertEquals(responseEntityExpected, responseEntityResult);
    }

    @Test
    void shouldReturnListOfTypologies() {

        // Arrange

        String projectCode = "A123";

        ResourceOutputDTO resourceOutputDTOMock = mock(ResourceOutputDTO.class);

        List<ResourceOutputDTO> resourceOutputDTOList = new ArrayList<>();

        resourceOutputDTOList.add(resourceOutputDTOMock);

        when(viewResourcesService.getAllResourcesByProjectCode(projectCode)).thenReturn(resourceOutputDTOList);

        ResponseEntity<Object> responseEntityExpected =  ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(resourceOutputDTOList);

        // Act

        ResponseEntity<Object> responseEntityResult = resourceController.getResourcesOfProject(projectCode);

        // Assert

        assertEquals(responseEntityExpected, responseEntityResult);
    }

    @Test
    void shouldReturnEmptyListOfTypologies() {

        // Arrange

        String projectCode = "A123";

        List<ResourceOutputDTO> resourceOutputDTOList = new ArrayList<>();

        when(viewResourcesService.getAllResourcesByProjectCode(projectCode)).thenReturn(resourceOutputDTOList);

        ResponseEntity<Object> responseEntityExpected =  ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"message\":\"No resources are allocated to this " +
                        "project.\"}");

        // Act

        ResponseEntity<Object> responseEntityResult = resourceController.getResourcesOfProject(projectCode);

        // Assert

        assertEquals(responseEntityExpected, responseEntityResult);
    }

    @Test
    void shouldChangeResourceSpecialRole() {

        // Arrange

        String projectCode = "A123";


        String email = "teste@gmail.com";

        LocalDate startDate = LocalDate.now();

        String role = "Project Manager";

        Resource resourceMock = mock(Resource.class);

        ResourceDTO resourceDTO = new ResourceDTO("A123", email,
                startDate,
                LocalDate.of(2020, 12, 25), 100, 30, role);

        when(editRoleService.setSpecialRole(email, startDate, role, projectCode)).thenReturn(resourceMock);

        ResponseEntity<Object> responseEntityExpected = ResponseEntity.status(HttpStatus.OK).
                contentType(MediaType.APPLICATION_JSON).
                body(resourceDTO);

        // Act

        ResponseEntity<Object> responseEntityResult = resourceController.changeResourceSpecialRole(resourceDTO, projectCode);

        // Assert

        assertEquals(responseEntityExpected, responseEntityResult);
    }

}