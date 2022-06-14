package switchisep.project.dto.assemblers;

import org.springframework.stereotype.Service;
import switchisep.project.dto.FullUserStoryDTO;
import switchisep.project.dto.NewUserStoryInfo;
import switchisep.project.dto.UserStoryDTO;
import switchisep.project.domain.userstory.UserStory;

@Service
public class UserStoryDomainDTOAssembler {

    public UserStoryDomainDTOAssembler() {
        // Empty constructor
    }

    /**
     * Method that converts the incoming payload from the User Interface into
     * a DTO, so it can be
     * transferred from the controller layer to the Application Layer
     *
     * @param userStory The User Story we want to convert to DTO.
     * @return userStoryDTO
     */

    public UserStoryDTO fromDomainToDTO(UserStory userStory) {

        UserStoryDTO userStoryDto = new UserStoryDTO();

        userStoryDto.userStoryID = userStory.getUserStoryID().getUserStoryID();
        userStoryDto.projectCode = userStory.getProjectCode().getCode();
        userStoryDto.description =
                userStory.getUserStoryDescription().getDescription();
        userStoryDto.priority = userStory.getPriority().getPriority();
        userStoryDto.status = userStory.getStatus().getuSValueOfStatus();

        return userStoryDto;
    }

    /**
     * Method that converts the description and projectCode data into a single DTO
     * to be transferred to the Application Layer of the Arquitecture.
     * This method is used when creating a new User Story, do to the description
     * being a user input and the projectCode being a path variable
     *
     * @param description the description of the User Story.
     * @param projectCode the projectCode that the User Story is going to be associated to.
     * @return NewUserStoryInfo
     */

    public NewUserStoryInfo toNewUserStoryInfo(String description, String projectCode) {

        NewUserStoryInfo newUserStoryInfo = new NewUserStoryInfo();

        newUserStoryInfo.description = description;
        newUserStoryInfo.projectCode = projectCode;

        return newUserStoryInfo;
    }

    /**
     * Method that converts a User Story in to a DTO with all the attributes of
     * the class User Story. This method is user when there is a HTTP GET Request to
     * a specific User Story.
     *
     * @param userStory User Story to be converted
     * @return FullInfoUserStoryDTO
     */

    public FullUserStoryDTO toFullInfoUserStoryDTO(UserStory userStory) {

        FullUserStoryDTO fullUserStoryDTO = new FullUserStoryDTO();

        fullUserStoryDTO.userStoryID = userStory.getUserStoryID().getUserStoryID();
        fullUserStoryDTO.description = userStory.getUserStoryDescription().getDescription();
        fullUserStoryDTO.projectCode = userStory.getProjectCode().getCode();
        fullUserStoryDTO.priority = userStory.getPriority().getPriority();
        fullUserStoryDTO.effortEstimate = userStory.getEffort().getEffortEstimateValue();
        fullUserStoryDTO.userStoryStatus = userStory.getStatus().getuSValueOfStatus();
        fullUserStoryDTO.parentUserStory = userStory.getParentUserStory().getUserStoryID();
        fullUserStoryDTO.sprintID = userStory.getSprintID().getTaskContainerIDString();

        return fullUserStoryDTO;
    }

}
