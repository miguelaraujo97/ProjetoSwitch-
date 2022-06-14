package switchisep.project.IT;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import switchisep.project.dto.SprintCreationInfo;
import switchisep.project.domain.project.Project;
import switchisep.project.domain.sprint.Sprint;
import switchisep.project.domain.valueobjects.*;
import switchisep.project.repositories.ProjectRepository;
import switchisep.project.repositories.SprintRepository;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class CreateSprintIT {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    SprintRepository sprintRepository;

    @Test
    void createSprint_SuccessCase() throws Exception {
        //Arrange

        ProjectCode code = ProjectCode.createProjectCode("A12300");
        ProjectName name = ProjectName.createProjectName("Test");
        ProjectCustomer customer = ProjectCustomer.createProjectCustomer("ISEP");
        ProjectDescription description = ProjectDescription.createProjectDescription("CENAS");
        ProjectBudget projectBudget = ProjectBudget.createBudget(10);
        SprintDuration projectSprintDuration = SprintDuration.createSprintDuration(10);
        ProjectNumberOfPlannedSprints projectNumberOfPlannedSprints = ProjectNumberOfPlannedSprints.createNumberOfPlannedSprints(10);
        ProjectStatus status = ProjectStatus.PLANNED;
        LocalDate startDate = LocalDate.of(2022, 05, 10);
        LocalDate endDate = LocalDate.of(2025, 06, 15);
        TimePeriod timePeriod = TimePeriod.createTimePeriod(startDate, endDate);
        ProjectBusinessSector projectBusinessSector = ProjectBusinessSector.createProjectBusinessSector("School");
        Description typologyDescription = Description.createDescription("Insert typology");


        Project project = new Project.Builder(code, name, customer).projectTypologyDescription(typologyDescription).projectStatus(status)
                .projectSprintDuration(projectSprintDuration).projectDescription(description).projectBudget(projectBudget)
                .projectNumberOfPlannedSprints(projectNumberOfPlannedSprints).projectTimePeriod(timePeriod).projectBusinessSector(projectBusinessSector).build();

        projectRepository.saveProject(project);


        SprintCreationInfo sprintCreationInfo = new SprintCreationInfo();
        sprintCreationInfo.plannedStartDate = LocalDate.of(2025, 03, 10);

        //Act
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post("/projects/{projectCode}/sprints", "A12300")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(sprintCreationInfo))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();

        //Assert
        assertEquals(201, result.getResponse().getStatus());

        projectRepository.deleteAll();
    }

    @Test
    void createSprint_FailureScenario_ProjectNotFound() throws Exception {
        //Arrange
        SprintCreationInfo sprintCreationInfo = new SprintCreationInfo();
        sprintCreationInfo.plannedStartDate = LocalDate.of(2024, 10, 10);

        projectRepository.deleteAll();

        //Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/projects/{projectCode}/sprints", "A12300")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(sprintCreationInfo))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    void createSprint_FailureScenario_SprintDatesOverlap() throws Exception {
        //Arrange

        ProjectCode code = ProjectCode.createProjectCode("A12300");
        ProjectName name = ProjectName.createProjectName("Test");
        ProjectCustomer customer = ProjectCustomer.createProjectCustomer("ISEP");
        ProjectDescription description = ProjectDescription.createProjectDescription("CENAS");
        ProjectBudget projectBudget = ProjectBudget.createBudget(10);
        SprintDuration projectSprintDuration = SprintDuration.createSprintDuration(10);
        ProjectNumberOfPlannedSprints projectNumberOfPlannedSprints = ProjectNumberOfPlannedSprints.createNumberOfPlannedSprints(10);
        ProjectStatus status = ProjectStatus.PLANNED;
        LocalDate startDate = LocalDate.of(2021, 05, 10);
        LocalDate endDate = LocalDate.of(2025, 06, 15);
        TimePeriod timePeriod = TimePeriod.createTimePeriod(startDate, endDate);
        ProjectBusinessSector projectBusinessSector = ProjectBusinessSector.createProjectBusinessSector("School");
        Description typologyDescription = Description.createDescription("Insert typology");


        Project project = new Project.Builder(code, name, customer).projectTypologyDescription(typologyDescription).projectStatus(status)
                .projectSprintDuration(projectSprintDuration).projectDescription(description).projectBudget(projectBudget)
                .projectNumberOfPlannedSprints(projectNumberOfPlannedSprints).projectTimePeriod(timePeriod).projectBusinessSector(projectBusinessSector).build();

        projectRepository.saveProject(project);

        //Setting up an already existent sprint
        SprintID sprintID = SprintID.createSprintID("S001PA12300");
        TimePeriod timePeriodSprint = TimePeriod.createTimePeriod(LocalDate.of(2022, 01, 02),
                LocalDate.of(2022, 01, 10));
        SprintStatus sprintStatus = SprintStatus.createSprintStatus(SprintStatus.SprintStatusEnum.NOT_STARTED);

        Sprint sprintAlreadyExistent = new Sprint(sprintID, code, timePeriodSprint, sprintStatus);
        sprintRepository.save(sprintAlreadyExistent);

        //Performing post of sprint with overlapping dates
        SprintCreationInfo sprintCreationInfo = new SprintCreationInfo();
        sprintCreationInfo.plannedStartDate = LocalDate.of(2022, 01, 8);

        //Act & Assert
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/projects/{projectCode}/sprints", "A12300")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(sprintCreationInfo))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andReturn();

        // Assert
        assertEquals(400, result.getResponse().getStatus());

        projectRepository.deleteAll();
        sprintRepository.deleteAll();
    }
}