package switchisep.project.domain.domainservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switchisep.project.domain.userstory.UserStory;
import switchisep.project.domain.factories.UserStoryFactory;
import switchisep.project.domain.valueobjects.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class CreateUserStoryDomainService {

    UserStoryFactory userStoryFactory;

    @Autowired
    public CreateUserStoryDomainService(UserStoryFactory userStoryFactory) {
        this.userStoryFactory = userStoryFactory;
    }

    /**
     * Main method that creates a User Story
     *
     * @param description    The User Story description
     * @param userStoryIdVO  The User Story Identification Number
     * @param projectCodeVO  The projectCode that the created User Story is associated to.
     * @param productBacklog the list of User Stories in a Project, so we can check the description and priority.
     * @return Optional of a User Story when created successfully
     * Optional Empty in case the description already Exists.
     */

    public Optional<UserStory> createUserStory(String description, ProjectCode projectCodeVO,
                                               List<UserStory> productBacklog, UserStoryID userStoryIdVO) {

        if (!checkIfDescriptionAlreadyExists(productBacklog, description)) {

            Description descriptionVO = Description.createDescription(description);

            Priority priorityVO = generateNextPriorityNumber(productBacklog);

            EffortEstimate effortEstimate = EffortEstimate.createEffortEstimate(0);

            SprintID sprintID = SprintID.createSprintID("-1");

            UserStoryID parentUS = UserStoryID.createUserStoryIdWithString("-1");

            UserStory userStory = userStoryFactory.createUserStory
                    (descriptionVO, userStoryIdVO, projectCodeVO,
                            priorityVO, effortEstimate,
                            sprintID, parentUS);

            return Optional.of(userStory);
        }

        return Optional.empty();

    }

    /**
     * Auxiliary Method to aid in the main method. Here it checks if the inserted description already exists.
     *
     * @param productBacklog the list of User Stories in a Project, so we can check if the description already exists.
     * @param description    the inserted description to check if already exists
     * @return true if the description already exists.
     * false if it doesn't.
     */

    public boolean checkIfDescriptionAlreadyExists(List<UserStory> productBacklog, String description) {

        boolean descriptionAlreadyExists = false;

        for (UserStory userStory : productBacklog) {

            Description descriptionTmp = userStory.getUserStoryDescription();

            if (Objects.equals(descriptionTmp.getDescription(), description)) {

                descriptionAlreadyExists = true;

            }
        }
        return descriptionAlreadyExists;
    }

    /**
     * Auxiliary Method to aid in the main method. Here it gets the next priority number for the created User Story.
     *
     * @param productBacklog the list of User Stories in a Project to get the next Priority Number.
     * @return Priority Value Object.
     */

    public Priority generateNextPriorityNumber(List<UserStory> productBacklog) {

        int nextPriorityNumber = productBacklog.size() + 1;

        return Priority.createPriority(nextPriorityNumber);
    }
}
