package switchisep.project.dto;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;


@Component
public class ProjectActivityStatusDTO extends RepresentationModel<ProjectActivityStatusDTO> {
    public String activityID;
    public String activityDescription;
    public String activityStatus;

   }
