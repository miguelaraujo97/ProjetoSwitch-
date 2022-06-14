package switchisep.project.domain.factories;

import org.springframework.stereotype.Component;
import switchisep.project.domain.userstory.UserStory;
import switchisep.project.domain.valueobjects.*;

@Component
public interface UserStoryFactory {

    UserStory createUserStory(Description description,
                              UserStoryID userStoryId,
                              ProjectCode projectCode,
                              Priority priority,
                              EffortEstimate effort,
                              SprintID sprintID, UserStoryID parentUS);
}
