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
import switchisep.project.dto.UserStoryInput;
import switchisep.project.controllers.ProjectController;
import switchisep.project.controllers.UserStoryController;
import switchisep.project.domain.project.Project;
import switchisep.project.domain.userstory.UserStory;
import switchisep.project.domain.valueobjects.*;
import switchisep.project.repositories.ProjectRepository;
import switchisep.project.repositories.UserStoryRepository;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserStoryIT {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ProjectController ProjectController;

    @Autowired
    UserStoryController userStoryController;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UserStoryRepository userStoryRepository;

    /**
     *  Integration Tests for createUserStoryMethod ( POST )
     */

    @Test
    public void shouldReturnResponseStatusCreated() throws Exception {

        // Arrange
        UserStoryInput userStoryInput = new UserStoryInput();

        userStoryInput.description = "Teste";

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

        // Act

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post("/projects/{id}/user-stories", "A12300")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString( userStoryInput ))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();

        // Assert
        assertEquals(201, result.getResponse().getStatus());
    }

    @Test
    public void shouldNotCreateUserStoryAlreadyExists() throws Exception {

        // Arrange
        UserStoryInput userStoryInput = new UserStoryInput();

        userStoryInput.description = "Teste";


        ProjectCode code = ProjectCode.createProjectCode("A123005");
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

        userStoryController.createUserStory(userStoryInput, "A123005");

        // Act

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post("/projects/{id}/user-stories", "A123005")
                        .contentType("application/json")
                        .content( objectMapper.writeValueAsString( userStoryInput ))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict()).andReturn();

        // Assert
        assertEquals(409, result.getResponse().getStatus());
    }

    @Test
    public void shouldNotCreateUserStoryProjectDoesntExist() throws Exception {

        // Arrange
        UserStoryInput userStoryInput = new UserStoryInput();

        userStoryInput.description = "Teste";

        ProjectCode code = ProjectCode.createProjectCode("A123");
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

        // Act

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post("/projects/{id}/user-stories", "B123")
                        .contentType("application/json")
                        .content( objectMapper.writeValueAsString( userStoryInput ))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict()).andReturn();

        // Assert
        assertEquals(409, result.getResponse().getStatus());
    }

    /**
     *  Integration Tests for getUserStoryByID ( GET )
     */

    @Test
    public void shouldReturnUserStory() throws Exception {

        // Arrange

        UserStoryInput userStoryInput = new UserStoryInput();

        userStoryInput.description = "Teste";

        ProjectCode code = ProjectCode.createProjectCode("A123");
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

        UserStoryID userStoryID = UserStoryID.createUserStoryIdWithString("US-f1a6e522-a305-479c-8263-04515e51fb2a");

        Description descriptionOne = Description.createDescription("Teste");

        Priority priority = Priority.createPriority(1);

        EffortEstimate effortEstimate = EffortEstimate.createEffortEstimate(0);

        SprintID sprintID = SprintID.createSprintID("-1");
        UserStoryID parentUS =UserStoryID.createUserStoryIdWithString("0");

        UserStory userStory = new UserStory.Builder(userStoryID, code, descriptionOne).statusPlanned()
                .priority(priority).effort(effortEstimate).parentUS(parentUS).sprintID(sprintID).build();

        userStoryRepository.save(userStory);

        // Act

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/projects/{id}/user-stories/{userStoryId}", "A123", "US-f1a6e522-a305-479c-8263-04515e51fb2a")
                        .contentType("application/json")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        // Assert
        assertEquals(200, result.getResponse().getStatus());
    }



}
