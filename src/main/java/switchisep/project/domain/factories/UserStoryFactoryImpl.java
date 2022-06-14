package switchisep.project.domain.factories;

import org.springframework.stereotype.Component;
import switchisep.project.domain.userstory.UserStory;
import switchisep.project.domain.valueobjects.*;

@Component
public class UserStoryFactoryImpl implements UserStoryFactory {

    public UserStory createUserStory(Description description,
                                     UserStoryID userStoryId,
                                     ProjectCode projectCode,
                                     Priority priority,
                                     EffortEstimate effort, SprintID sprintID,UserStoryID parentUS) {
        return new UserStory.Builder(userStoryId, projectCode,
                description).statusPlanned().priority(priority).
                effort(effort).sprintID(sprintID).parentUS(parentUS).build();
    }
}
