package switchisep.project.dto;

import org.springframework.hateoas.RepresentationModel;

public class SprintUIDTO extends RepresentationModel<SprintUIDTO>  {

    public String sprintID;
    public String status;
    public String sprintOrder;
    public String startDate;
    public String endDate;
}
