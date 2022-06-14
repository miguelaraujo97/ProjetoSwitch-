package switchisep.project.dto;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FinalProjectActivityStatusDTOList extends RepresentationModel<FinalProjectActivityStatusDTOList> {

    public List<ProjectActivityStatusDTO> activitiesStatusList;
}
