package switchisep.project.dto;

import org.springframework.hateoas.RepresentationModel;

import java.util.List;

public class ProfileListDTO extends RepresentationModel<ProfileListDTO> {

    public List<ProfileDTO> profileDTOList;
}
