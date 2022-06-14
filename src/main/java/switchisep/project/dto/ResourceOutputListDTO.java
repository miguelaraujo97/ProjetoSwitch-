package switchisep.project.dto;

import org.springframework.hateoas.RepresentationModel;

import java.util.List;

public class ResourceOutputListDTO extends RepresentationModel<ResourceOutputListDTO> {

    public List<ResourceOutputDTO> resourceOutputDTOList;
}
