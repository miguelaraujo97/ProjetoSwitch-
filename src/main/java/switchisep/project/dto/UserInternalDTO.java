package switchisep.project.dto;

import org.springframework.hateoas.RepresentationModel;
import switchisep.project.domain.valueobjects.*;

public class UserInternalDTO extends RepresentationModel<UserInternalDTO> {

    public final UserID userID;
    public final Email email;
    public final UserName userName;
    public final ProfileID profileID;
    public final Function function;
    public final Photo photo;
    public final boolean userStatus;

    public UserInternalDTO(UserID userID, Email email, UserName userName,
                           ProfileID profileID, Function function,
                           Photo photo, boolean status){
        this.userID = userID;
        this.email = email;
        this.userName = userName;
        this.profileID = profileID;
        this.function = function;
        this.photo = photo;
        this.userStatus=status;
    }

}
