package switchisep.project.dto;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;

@Component
public class FullUserStoryDTO extends RepresentationModel<FullUserStoryDTO> {

    public String userStoryID;
    public String description;
    public String projectCode;
    public int priority;
    public int effortEstimate;
    public String userStoryStatus;
    public String parentUserStory;
    public String sprintID;
}
