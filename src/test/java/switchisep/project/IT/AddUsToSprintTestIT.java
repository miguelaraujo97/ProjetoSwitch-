package switchisep.project.IT;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import switchisep.project.dto.UpdateUserStoryDTO;
import switchisep.project.controllers.UserStoryController;
import switchisep.project.domain.project.Project;
import switchisep.project.domain.sprint.Sprint;
import switchisep.project.domain.userstory.UserStory;
import switchisep.project.domain.userstory.UserStoryStatus;
import switchisep.project.domain.valueobjects.*;
import switchisep.project.repositories.ProjectRepository;
import switchisep.project.repositories.SprintRepository;
import switchisep.project.repositories.UserStoryRepository;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AddUsToSprintTestIT {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    UserStoryController userStoryController;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UserStoryRepository userStoryRepository;
    @Autowired
    SprintRepository sprintRepository;

    @BeforeEach
    void projectSetup() {
        ProjectCode projectCode = ProjectCode.createProjectCode("A125");
        SprintDuration sprintDuration = SprintDuration.createSprintDuration(1);
        TimePeriod timePeriod = TimePeriod.createTimePeriod(LocalDate.of(2022, 01, 01),
                LocalDate.of(2023, 01, 01));
        ProjectName projectName = ProjectName.createProjectName("TestProject");
        ProjectCustomer projectCustomer = ProjectCustomer.createProjectCustomer("Customer");

        Project projectForTests = new Project.Builder(projectCode, projectName, projectCustomer)
                .projectTimePeriod(timePeriod).projectSprintDuration(sprintDuration).build();

        projectRepository.saveProject(projectForTests);
        UserStoryID userStoryID = UserStoryID.
                createUserStoryIdWithString("1");
        Description description = Description.createDescription("sadsdasdsadasdasdasdas");

        EffortEstimate effortEstimate = EffortEstimate.createEffortEstimate(0);

        SprintID sprintIDVo = SprintID.createSprintID("1");

        UserStoryStatus userStoryStatus = UserStoryStatus.createUserStoryStatus(UserStoryStatus.UserStoryStatusEnum.PLANNED);

        Priority priority = Priority.createPriority(1);

        UserStory userStory = new UserStory.Builder(userStoryID,
                projectCode, description)
                .parentUS(userStoryID)
                .status(userStoryStatus)
                .sprintID(sprintIDVo)
                .effort(effortEstimate)
                .priority(priority).build();

        SprintID sprintID = SprintID.createSprintID("1");
        SprintStatus sprintStatus = SprintStatus.
                createSprintStatus(SprintStatus.SprintStatusEnum.STARTED);
        Sprint sprint = new Sprint(sprintID, projectCode, timePeriod,
                sprintStatus);
        userStoryRepository.save(userStory);
        sprintRepository.save(sprint);
    }

    @Test
    void addSuccess() throws Exception {
        //arrange
        UpdateUserStoryDTO updateUserStoryDTO =
                new UpdateUserStoryDTO();
        updateUserStoryDTO.userStoryID="1";
        updateUserStoryDTO.projectCode="A125";
        updateUserStoryDTO.sprintID="1";
        updateUserStoryDTO.action="addToUS";
        //act
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.patch("/projects/{id}/user-stories", "A125")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString( updateUserStoryDTO ))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted()).andReturn();
        String mvcResult = result.getResponse().getContentAsString();
    }
    @Test
    void addFail() throws Exception {
        //arrange
        UpdateUserStoryDTO updateUserStoryDTO =
                new UpdateUserStoryDTO();
        updateUserStoryDTO.userStoryID="1";
        updateUserStoryDTO.projectCode="A15";
        updateUserStoryDTO.sprintID="1";
        updateUserStoryDTO.action="addToUS";
        //act
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.patch("/projects/{id}/user-stories", "A125")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString( updateUserStoryDTO ))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andReturn();
        String mvcResult = result.getResponse().getContentAsString();
    }

}
