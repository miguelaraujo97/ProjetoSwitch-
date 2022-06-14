package switchisep.project.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import switchisep.project.dto.ProjectDto;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import switchisep.project.dto.UpdateProjectDto;
import switchisep.project.services.CreateProjectService;
import switchisep.project.services.EditProjectService;
import switchisep.project.services.ViewProjectService;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ProjectControllerTest {

    // Autowired
    @InjectMocks
    ProjectController projectController;

    @InjectMocks
    ProjectDto projectDto;

    // MockBean
    @Mock
    CreateProjectService createProjectService;

    @Mock
    EditProjectService editProjectService;

    @Mock
    ViewProjectService viewProjectService;

    // Setup
    @BeforeEach
    void init() {

        projectDto.code = "A123456789";
        projectDto.name = "Things";
        projectDto.customer = "ISEP";
        projectDto.projectDescription = "Data";
        projectDto.typologyDescription = "Fixed Cost";
        projectDto.budget = 1000.0;
        projectDto.sprintDuration = 2;
        projectDto.numberOfPlannedSprints = 20;
        projectDto.status = "planned";
        projectDto.startDate = LocalDate.parse("2022-02-25");
        projectDto.endDate = LocalDate.parse("2026-03-22");
        projectDto.businessSector = "Knowledge";

    }

    //US005
    @Test
    void createProjectSuccess() throws JsonProcessingException {

        // Arrange
        Optional<ProjectDto> optionalProjectDTO = Optional.of(projectDto);

        when(createProjectService.createProjectAndSave(projectDto)).thenReturn(optionalProjectDTO);

        // Act
        ResponseEntity<Object> responseEntity = projectController.createProject(projectDto);
        ResponseEntity<Object> expected = ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(projectDto);

        // Assert
        assertEquals(expected, responseEntity);
    }


    @Test
    void createProjectBadEntryData() throws JsonProcessingException {

        // Arrange
        // Change code to empty field
        projectDto.code = "";

        // Act
        ResponseEntity<?> result = projectController.createProject(projectDto);
        ResponseEntity<?> expected = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad entry data");

        // Assert
        assertEquals(expected, result);

    }

    @Test
    void createProjectAlreadyExists() throws JsonProcessingException {

        // Arrange
        ProjectDto outProjectDto = mock(ProjectDto.class);
        Optional<ProjectDto> optionalProjectDTO = Optional.of(outProjectDto);

        when(createProjectService.createProjectAndSave(projectDto)).thenReturn(Optional.empty());

        System.out.println(optionalProjectDTO.get().code);

        // Act
        ResponseEntity<?> result = projectController.createProject(projectDto);
        ResponseEntity<?> expected = ResponseEntity.status(HttpStatus.CONFLICT).body("Project code already exists");

        // Assert
        assertEquals(expected, result);

    }



    //----------------------------------------------////--------------------------------------------------------//

    //US008

    @Test
    void editProjectSuccess(){

        //Arrange
        ProjectDto projectDTO = mock(ProjectDto.class);


        UpdateProjectDto newData = mock(UpdateProjectDto.class);
        newData.code = "A123";

        Optional<ProjectDto> optProjectDTONative = Optional.of(projectDTO);

        when(editProjectService.setNewData(newData)).thenReturn(optProjectDTONative);

        //Act
        ResponseEntity<Object> result = projectController.editProject(newData.code, newData);
        ResponseEntity<Object> expected = ResponseEntity.status(HttpStatus.OK).
                contentType(MediaType.APPLICATION_JSON).body(projectDTO);

        //Assert
        assertEquals(expected,result);

    }

    @Test
    void editProjectBadEntry(){

        //Arrange


        UpdateProjectDto newData = mock(UpdateProjectDto.class);
        newData.code = "";

        //Act
        ResponseEntity<Object> result = projectController.editProject(newData.code, newData);
        ResponseEntity<Object> expected = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad entry data");

        //Assert
        assertEquals(expected,result);

    }

    @Test
    void editProjectNotFound(){

        //Arrange
        UpdateProjectDto newData = mock(UpdateProjectDto.class);

        newData.code = "A123";

        when(editProjectService.setNewData(newData)).thenReturn(Optional.empty());

        //Act
        ResponseEntity<Object> result = projectController.editProject(newData.code, newData);
        ResponseEntity<Object> expected = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No project found with that code.");


        //Assert
        assertEquals(expected,result);

    }

    //----------------------------------------------////--------------------------------------------------------//

    //US015

    /*@Test
    void getAllProjectsNoContent(){

        //Arrange
        List<ProjectDto> projectDtoList = new ArrayList<>();

        AllProjectsListDto allProjectsListDto = new AllProjectsListDto();

        Pageable paging = PageRequest.of(0, 2);
        when(viewProjectService.getAllProjects(paging)).thenReturn(projectDtoList);
        Link selfGET = linkTo(WebMvcLinkBuilder.methodOn(ProjectController.class).getAllProjects(paging)).withSelfRel().withType("GET");
        Link selfPOST = linkTo(WebMvcLinkBuilder.methodOn(ProjectController.class).getAllProjects(paging)).withSelfRel().withType("POST");
        allProjectsListDto.add(selfGET);
        allProjectsListDto.add(selfPOST);

        //Act
        ResponseEntity<Object> result = projectController.getAllProjects(paging);
        ResponseEntity<Object> expected = ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(allProjectsListDto);

        //Assert
        assertEquals(expected,result);

    }

    @Test
    void getAllProjectsWithContent(){
        //arrange

        List<ProjectDto> projectDtoList = new ArrayList<>();
        AllProjectsListDto allProjectsListDto = new AllProjectsListDto();

        ProjectDto projectDto1 = mock(ProjectDto.class);
        ProjectDto projectDto2 = mock(ProjectDto.class);

       projectDtoList.add(projectDto1);
       projectDtoList.add(projectDto2);
        Pageable paging = PageRequest.of(0, 2);
        when(viewProjectService.getAllProjects(paging)).thenReturn(projectDtoList);

        allProjectsListDto.projects = projectDtoList;

        Link selfGET = linkTo(WebMvcLinkBuilder.methodOn(ProjectController.class).getAllProjects(paging)).withSelfRel().withType("GET");
        Link selfPOST = linkTo(WebMvcLinkBuilder.methodOn(ProjectController.class).getAllProjects(paging)).withSelfRel().withType("POST");
        allProjectsListDto.add(selfGET);
        allProjectsListDto.add(selfPOST);

        //act

        ResponseEntity<Object> result = projectController.getAllProjects(paging);
        ResponseEntity<Object> expected = ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(allProjectsListDto);

        //assert

        assertEquals(expected,result);


    }
*/
    //------------------------------------------------------------------------------------------------------------//

    @Test
    void getProjectByProjectCode() {

        //arrange
        String projectCode = "A123";
        ProjectDto projectDto = mock(ProjectDto.class);
        Optional<ProjectDto> optionalProjectDto = Optional.of(projectDto);

        when(viewProjectService.getProjectByCode(projectCode)).thenReturn(optionalProjectDto);

        //act

        ResponseEntity<Object> result = projectController.getProjectByCode(projectCode);
        ResponseEntity<Object> expected = ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(projectDto);

        //assert

        assertEquals(expected, result);
    }

    @Test
    void getProjectByProjectCodeNotFound() {

        //arrange
        String projectCode = "A123";
        Optional<ProjectDto> optionalProjectDto = Optional.empty();

        when(viewProjectService.getProjectByCode(projectCode)).thenReturn(optionalProjectDto);

        //act

        ResponseEntity<Object> result = projectController.getProjectByCode(projectCode);
        ResponseEntity<Object> expected = ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"No project found with that code.\"}");

        //assert

        assertEquals(expected, result);
    }

}