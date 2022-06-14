package switchisep.project.IT;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import switchisep.project.dto.ProjectDto;
import switchisep.project.datamodel.ProjectJpa;
import switchisep.project.domain.project.Project;
import switchisep.project.domain.typology.Typology;
import switchisep.project.domain.valueobjects.*;

import switchisep.project.repositories.ProjectRepository;
import switchisep.project.repositories.TypologyRepository;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@AutoConfigureMockMvc
@SpringBootTest
public class ProjectIntegrationTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    TypologyRepository typologyRepository;
    @Autowired
    ProjectRepository projectRepository;


    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

    }


    //US005
    @Test
    void createProjectSuccessIntegration() throws Exception {


        // arrange

        Description description = Description.createDescription("Fixed Cost");
        Typology typology = new Typology(TypologyID.createTypologyID(),description);
        typologyRepository.save(typology);

        ProjectDto projectDto = new ProjectDto();
        projectDto.code = "A1234566";
        projectDto.name = "Integration test";
        projectDto.customer = "ISEP test";
        projectDto.projectDescription = "Data";
        projectDto.typologyDescription = "Fixed Cost";
        projectDto.budget = 1000.0;
        projectDto.sprintDuration = 2;
        projectDto.numberOfPlannedSprints = 20;
        projectDto.status = "planned";
        projectDto.startDate = LocalDate.parse("2022-02-25");
        projectDto.endDate = LocalDate.parse("2026-03-22");
        projectDto.businessSector = "Knowledge";

    //Act and Assert

        MvcResult result = mockMvc.perform(post("/projects")
                        .content(objectMapper.writeValueAsString(projectDto))
                        .accept("application/json")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();

        assertEquals(201, result.getResponse().getStatus());

    }

    @Test
    void createProjectBadRequestIntegration() throws Exception {


        // arrange

        Description description = Description.createDescription("Fixed Cost");
        Typology typology = new Typology(TypologyID.createTypologyID(),description);
        typologyRepository.save(typology);

        ProjectDto projectDto = new ProjectDto();
        projectDto.code = "";
        projectDto.name = "Integration test";
        projectDto.customer = "ISEP test";
        projectDto.projectDescription = "Data";
        projectDto.typologyDescription = "Fixed Cost";
        projectDto.budget = 1000.0;
        projectDto.sprintDuration = 2;
        projectDto.numberOfPlannedSprints = 20;
        projectDto.status = "planned";
        projectDto.startDate = LocalDate.parse("2022-02-25");
        projectDto.endDate = LocalDate.parse("2026-03-22");
        projectDto.businessSector = "Knowledge";



        //Act and Assert
        MvcResult result = mockMvc.perform(post("/projects")
                        .content(objectMapper.writeValueAsString(projectDto))
                        .accept("application/json")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andReturn();

        assertEquals(400, result.getResponse().getStatus());


    }

    @Test
    void createProjectConflictIntegration() throws Exception {


        // arrange

        ProjectDto dataProject = new ProjectDto();

        dataProject.code = "A1234";
        dataProject.name = "ISEP";
        dataProject.customer = "PTH";
        ProjectCode code = ProjectCode.createProjectCode(dataProject.code);
        ProjectName name = ProjectName.createProjectName(dataProject.name);
        ProjectCustomer customer = ProjectCustomer.createProjectCustomer(dataProject.customer);

        Project project = new Project.Builder(code,name,customer).build();

        projectRepository.saveProject(project);

        Description description = Description.createDescription("Fixed Cost");
        Typology typology = new Typology(TypologyID.createTypologyID(),description);

        typologyRepository.save(typology);

        ProjectDto projectDto = new ProjectDto();
        projectDto.code = "A1234";
        projectDto.name = "Integration test";
        projectDto.customer = "ISEP test";
        projectDto.projectDescription = "Data";
        projectDto.typologyDescription = "Fixed Cost";
        projectDto.budget = 1000.0;
        projectDto.sprintDuration = 2;
        projectDto.numberOfPlannedSprints = 20;
        projectDto.status = "planned";
        projectDto.startDate = LocalDate.parse("2022-02-25");
        projectDto.endDate = LocalDate.parse("2026-03-22");
        projectDto.businessSector = "Knowledge";



        //Act and Assert

        MvcResult result = mockMvc.perform(post("/projects")
                        .content(objectMapper.writeValueAsString(projectDto))
                        .accept("application/json")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict()).andReturn();

        assertEquals(409, result.getResponse().getStatus());

    }
    //--------------------------------------------------------------------------------------------------------//
    //US008

    @Test
    void EditTestIntegration() throws Exception {

        //Arrange

        ProjectDto dataProject = new ProjectDto();

        dataProject.code = "A1234";
        dataProject.name = "ISEP";
        dataProject.customer = "PTH";
        ProjectCode code = ProjectCode.createProjectCode(dataProject.code);
        ProjectName name = ProjectName.createProjectName(dataProject.name);
        ProjectCustomer customer = ProjectCustomer.createProjectCustomer(dataProject.customer);

        Project project = new Project.Builder(code,name,customer).build();

        projectRepository.saveProject(project);

        ProjectDto newDataOne = new ProjectDto();


        newDataOne.code = "A1234";
        newDataOne.startDate = LocalDate.of(2022, 2, 25);
        newDataOne.endDate = LocalDate.of(2026, 3, 22);
        newDataOne.sprintDuration = 10;
        newDataOne.numberOfPlannedSprints = 15;
        newDataOne.projectDescription = "Closed";


        //act

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.patch("/projects/"+dataProject.code)
                        .content(objectMapper.writeValueAsString(newDataOne))
                        .accept("application/json")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        assertEquals(200, result.getResponse().getStatus());

    }

    @Test
    void EditTestBadRequestIntegration() throws Exception {

        //Arrange

        ProjectDto newData = new ProjectDto();

        newData.code = "";
        newData.startDate = LocalDate.of(2022, 2, 25);
        newData.endDate = LocalDate.of(2026, 3, 22);
        newData.sprintDuration = 10;
        newData.numberOfPlannedSprints = 15;
        newData.status = "CLOSED";
        newData.projectDescription = "change description";


        //act && assert

       MvcResult result =  mockMvc.perform(MockMvcRequestBuilders.patch("/projects")
                        .content(objectMapper.writeValueAsString(newData))
                        .accept("application/json")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed()).andReturn();

        assertEquals(405, result.getResponse().getStatus());

    }

    @Test
    void EditTestNotFoundIntegration() throws Exception {

        //Arrange

        ProjectDto newData = new ProjectDto();

        newData.code = "A12525";
        newData.startDate = LocalDate.of(2022, 2, 25);
        newData.endDate = LocalDate.of(2026, 3, 22);
        newData.sprintDuration = 10;
        newData.numberOfPlannedSprints = 15;
        newData.status = "CLOSED";
        newData.projectDescription = "change description";


        //act && assert

       MvcResult result =  mockMvc.perform(MockMvcRequestBuilders.patch("/projects/"+newData.code)
                        .content(objectMapper.writeValueAsString(newData))
                        .accept("application/json")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andReturn();

        assertEquals(404, result.getResponse().getStatus());

    }

    //----------------------------------------------------------------------------------------------------------//
    //US015

    @Test
    void shouldReturnStatusOK_getAllProjectsEmptyList() throws Exception {

        // Arrange & Act
        projectRepository.deleteAll();
        Link link = Link.of("/projects");

        MvcResult result = mockMvc.perform(get(link.getHref()))
                .andExpect(status().isOk())
                .andReturn();

        String responseEntity = result.getResponse().getContentAsString();

        ResponseEntity<Object> expected = ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(result.getResponse().getContentAsString());

        // Assert
        assertTrue(expected.getStatusCode().is2xxSuccessful());
        assertEquals(expected.getStatusCode().value(), result.getResponse().getStatus());
        assertEquals(expected.getBody(), responseEntity);

    }

    @Test
    void shouldReturnStatusFail_getAllProjectsEmptyList() throws Exception {

        // Arrange & Act
        projectRepository.deleteAll();

        MvcResult result = mockMvc.perform(get("/projects"))
                .andExpect(status().isOk())
                .andReturn();

        String responseEntity = result.getResponse().getContentAsString();

        ResponseEntity<Object> expected = ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(result.getResponse().getContentAsString());

        //System.out.println(responseEntity);
        // Assert
        assertTrue(expected.getStatusCode().is2xxSuccessful());
        assertEquals(expected.getStatusCode().value(), result.getResponse().getStatus());
        assertEquals(expected.getBody(), responseEntity);

    }


    @Test
    void shouldReturnStatusOK_getAllProjectsWithDB_empty() throws Exception {

        // Arrange & Act
        projectRepository.deleteAll();

        Iterable<ProjectJpa> projectJpa = projectRepository.findAll();
        //System.out.println(projectJpa);

        List<ProjectJpa> projectListResult = new ArrayList<>();

        // Assert
        assertEquals(projectListResult, projectJpa);

    }


    @Test
    void shouldReturnEmpty_findByIdWithDB_empty() throws Exception {

        // Arrange & Act
        projectRepository.deleteAll();

        Optional<Project> projectJpa = projectRepository.getProjectByProjectCode(ProjectCode.createProjectCode("A111125"));
        //System.out.println(projectJpa);

        // Assert
        assertEquals(Optional.empty(), projectJpa);

    }


    @Test
    @Sql("/projects.sql")
    void shouldReturnStatusOK_getAllProjectsFromDb_withData() throws Exception {

        // Arrange & Act
        Iterable<ProjectJpa> projectJpa = projectRepository.findAll();
        long projectJpaSize = projectRepository.count();

        //long projectExpectedSize = StreamSupport.stream(projectJpa.spliterator(), false).count();
        long projectExpectedSize = projectJpa.spliterator().estimateSize();
        System.out.println(projectJpa);

        // Assert
        assertTrue(projectJpaSize > 0);
        assertEquals(projectExpectedSize, projectJpaSize);

        // Clean
        projectRepository.deleteAll();
    }


    @Test
    void shouldReturnEmpty_findByWrongIdWithDB_withData() throws Exception {

        // Arrange & Act
        Optional<Project> projectJpa = projectRepository.getProjectByProjectCode(ProjectCode.createProjectCode("A11112558547847"));

        // Assert
        assertTrue(projectJpa.isEmpty());

    }


    //------------------------------------------------------------------------------------------------------------//
    //Get a project By Code



    @Test
    void getProjectByCodeSuccess() throws Exception {
        //Arrange

        String projectCode = "A1234";
        String projectName = "ISEP";
        String projectCustomer = "PTH";
        ProjectCode code = ProjectCode.createProjectCode(projectCode);
        ProjectName name = ProjectName.createProjectName(projectName);
        ProjectCustomer customer = ProjectCustomer.createProjectCustomer(projectCustomer);

        Project project = new Project.Builder(code,name,customer).build();

        projectRepository.saveProject(project);


        //act

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/projects/{projectCode}",projectCode)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        assertEquals(200, result.getResponse().getStatus());


    }

    @Test
    void getProjectByCodeNotFound() throws Exception {
        //Arrange

        String projectCode = "A1234";

        //act

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/projects/{projectCode}",projectCode)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andReturn();

        assertEquals(404, result.getResponse().getStatus());

    }


}
