package switchisep.project.dto;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;

@Component
public class UserStoryDTO extends RepresentationModel<UserStoryDTO> {

        public String userStoryID;
        public String projectCode;
        public String description;
        public int priority;
        public String status;
}
