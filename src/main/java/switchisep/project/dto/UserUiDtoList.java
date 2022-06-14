package switchisep.project.dto;

import org.springframework.hateoas.RepresentationModel;

import java.util.List;

public class UserUiDtoList extends RepresentationModel<UserUiDtoList> {

    public List<UserUiDTO> userOutputDtoList;
}
