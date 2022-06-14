package switchisep.project.dto;

import org.springframework.hateoas.RepresentationModel;


/**
 * DTO to transfer info to the UI in order to show -> relevant information to the
 * intended user
 *
 * We have omitted the userStatus, profileID, hashedPassword and UserID, as is not
 * relevant to show to the user.
 *
 * However...
 * should we pass all info, and leave to the UI to only show the relevant info??
 *
 */
public class UserUIInfoDTO extends RepresentationModel<UserUIInfoDTO> {

    public int userId;
    public String profileId;
    public String name;
    public String email;
    public String function;
    public String photo;

}
