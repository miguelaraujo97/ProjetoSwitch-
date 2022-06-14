package switchisep.project.dto.assemblers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import switchisep.project.dto.FullUserStoryDTO;
import switchisep.project.dto.NewUserStoryInfo;
import switchisep.project.dto.UserStoryDTO;
import switchisep.project.domain.userstory.UserStory;
import switchisep.project.domain.valueobjects.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserStoryDomainDTOAssemblerTest {


    @Test
    void shouldConvertUserStoryToUserStoryDTO() {

        // Arrange

        UserStoryDomainDTOAssembler userStoryDomainDTOAssembler = new UserStoryDomainDTOAssembler();

        UserStoryID userStoryID = UserStoryID.createUserStoryID();

        ProjectCode projectCode = ProjectCode.createProjectCode("A123");

        Description description = Description.createDescription("Teste");

        Priority priority = Priority.createPriority(1);

        EffortEstimate effortEstimate = EffortEstimate.createEffortEstimate(0);

        SprintID sprintID = SprintID.createSprintID("S01");

        UserStoryDTO userStoryDTO = new UserStoryDTO();

        UserStory userStory = new UserStory.Builder(userStoryID, projectCode, description)
                .statusPlanned()
                .effort(effortEstimate)
                .priority(priority)
                .sprintID(sprintID)
                .parentUS(userStoryID).build();

        // Act

        UserStoryDTO userStoryDTOResult = userStoryDomainDTOAssembler.fromDomainToDTO(userStory);

        // Assert
        assertEquals(userStoryDTO, userStoryDTOResult);
    }

    @Test
    void toNewUSInfo(){

        UserStoryDomainDTOAssembler userStoryDomainDTOAssembler = new UserStoryDomainDTOAssembler();

        NewUserStoryInfo newUserStoryInfo = new NewUserStoryInfo();
        newUserStoryInfo.description = "desc";
        newUserStoryInfo.projectCode = "A123";

        NewUserStoryInfo result = userStoryDomainDTOAssembler.toNewUserStoryInfo("desc",
                "A123");

        assertNotEquals(result, newUserStoryInfo);

    }

    @Test
    void toFullInfoUSDTO(){
        UserStoryDomainDTOAssembler userStoryDomainDTOAssembler = new UserStoryDomainDTOAssembler();

        UserStoryID userStoryID = UserStoryID.createUserStoryID();

        ProjectCode projectCode = ProjectCode.createProjectCode("A123");

        Description description = Description.createDescription("Teste");

        Priority priority = Priority.createPriority(1);

        EffortEstimate effortEstimate = EffortEstimate.createEffortEstimate(0);

        SprintID sprintID = SprintID.createSprintID("S01");

        UserStoryDTO userStoryDTO = new UserStoryDTO();

        UserStory userStory = new UserStory.Builder(userStoryID, projectCode, description)
                .statusPlanned()
                .effort(effortEstimate)
                .priority(priority)
                .sprintID(sprintID)
                .parentUS(userStoryID).build();

        FullUserStoryDTO fullUserStoryDTO = new FullUserStoryDTO();
        fullUserStoryDTO.userStoryID = userStory.getUserStoryID().getUserStoryID();
        fullUserStoryDTO.description = userStory.getUserStoryDescription().getDescription();
        fullUserStoryDTO.projectCode = userStory.getProjectCode().getCode();
        fullUserStoryDTO.priority = userStory.getPriority().getPriority();
        fullUserStoryDTO.effortEstimate = userStory.getEffort().getEffortEstimateValue();
        fullUserStoryDTO.userStoryStatus = userStory.getStatus().getuSValueOfStatus();
        fullUserStoryDTO.parentUserStory = userStory.getParentUserStory().getUserStoryID();
        fullUserStoryDTO.sprintID = userStory.getSprintID().getTaskContainerIDString();

        FullUserStoryDTO result = userStoryDomainDTOAssembler.toFullInfoUserStoryDTO(userStory);

        assertEquals(result, fullUserStoryDTO);



    }


}