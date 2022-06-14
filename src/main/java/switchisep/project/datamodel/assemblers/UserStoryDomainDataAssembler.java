package switchisep.project.datamodel.assemblers;

import org.springframework.stereotype.Service;
import switchisep.project.datamodel.UserStoryJpa;
import switchisep.project.domain.userstory.UserStory;
import switchisep.project.domain.userstory.UserStoryStatus;
import switchisep.project.domain.valueobjects.*;

@Service
public class UserStoryDomainDataAssembler {

    public UserStoryJpa toData( UserStory userStory ) {

        return new UserStoryJpa(userStory.getUserStoryID().getUserStoryID(),
                userStory.getProjectCode().getCode(),
                userStory.getUserStoryDescription().getDescription(),
                userStory.getParentUserStory().getUserStoryID(),
                userStory.getStatus().getuSValueOfStatus(),
                userStory.getPriority().getPriority(),
                userStory.getEffort().getEffortEstimateValue(),
                userStory.getSprintID().getTaskContainerIDString());
    }

    public UserStory toDomain( UserStoryJpa userStoryJpa ) {

        UserStoryID userStoryID = UserStoryID.createUserStoryIdWithString(userStoryJpa.getUserStoryID());

        ProjectCode projectCode = ProjectCode.createProjectCode(userStoryJpa.getProjectCode());

        Description description = Description.createDescription(userStoryJpa.getDescription());

        UserStoryStatus userStoryStatus = UserStoryStatus.createUserStoryStatus
                (UserStoryStatus.UserStoryStatusEnum.valueOf( userStoryJpa.getUserStoryStatus() ));

        Priority priority = Priority.createPriority(userStoryJpa.getPriority());

        EffortEstimate effortEstimate =  EffortEstimate.createEffortEstimate( userStoryJpa.getEffortEstimate());

        SprintID sprintID = SprintID.createSprintID( userStoryJpa.getSprintID());

        UserStoryID parentUS = UserStoryID.createUserStoryIdWithString( userStoryJpa.getParentUS());

        return new UserStory.Builder( userStoryID, projectCode, description)
                .parentUS(parentUS)
                .status(userStoryStatus)
                .priority(priority)
                .sprintID(sprintID)
                .effort(effortEstimate).build();
    }
}
