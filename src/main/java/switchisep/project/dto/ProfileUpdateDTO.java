package switchisep.project.dto;

import io.swagger.annotations.ApiModel;
import org.springframework.stereotype.Component;


@Component
@ApiModel(description = "Model to update a profile of an user")
public class ProfileUpdateDTO {
    public int userID;
    public String profileID;
}
