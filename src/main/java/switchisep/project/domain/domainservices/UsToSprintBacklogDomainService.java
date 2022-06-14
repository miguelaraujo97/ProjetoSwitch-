package switchisep.project.domain.domainservices;

import org.springframework.stereotype.Service;
import switchisep.project.domain.project.Project;
import switchisep.project.domain.sprint.Sprint;
import switchisep.project.domain.userstory.UserStory;
import switchisep.project.domain.userstory.UserStoryStatus;
import switchisep.project.domain.valueobjects.ProjectCode;
import switchisep.project.domain.valueobjects.SprintStatus;
import switchisep.project.error_handling.BusinessRulesException;


import java.util.Optional;


@Service
public class UsToSprintBacklogDomainService {


    public Optional<UserStory> addToSprintBackLog(
            Project project,
            UserStory userStory,
            Sprint sprint) {
        ProjectCode projectCode = project.getProjectCode();

        if (sprint.getSprintStatus().equals(SprintStatus.
                createSprintStatus(SprintStatus.SprintStatusEnum.FINISHED))) {
            throw new BusinessRulesException("Sprint has already " +
                    "finished");
        }
//        if (sprint.getSprintStatus().equals(SprintStatus.
//                createSprintStatus(SprintStatus.SprintStatusEnum.STARTED))) {
//            throw new BusinessRulesException("Sprint has already " +
//                    "started");
//        }

        if (userStory.getStatus().equals(
                UserStoryStatus.createUserStoryStatus(
                        UserStoryStatus.UserStoryStatusEnum.FINISHED))) {
            throw new BusinessRulesException("User story has already" +
                    " finished");
        }

        if (!userStory.getProjectCode().equals(projectCode)) {
            throw new BusinessRulesException("User story doesnt belong " +
                    "to the project");
        }

        if (!sprint.getProjectCode().equals(projectCode)) {
            throw new BusinessRulesException("Sprint doesnt belong" +
                    " to the project");
        }
        userStory.setSprintID(sprint.getSprintID());
        return Optional.of(userStory);
    }

}
