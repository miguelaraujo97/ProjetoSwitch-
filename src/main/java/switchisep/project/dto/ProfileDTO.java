package switchisep.project.dto;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;

@Component
public class ProfileDTO extends RepresentationModel<ProfileDTO> {

    public String name;
    public String profileID;

}