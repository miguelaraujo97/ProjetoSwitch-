package switchisep.project.dto;

import org.springframework.hateoas.RepresentationModel;


public class ProfileRequestDTO extends RepresentationModel<ProfileRequestDTO>{

    public int profileRequestId;
    public String profileID;
    public int userID;

    public ProfileRequestDTO(int profileRequestId, String profileID, int userID) {
        this.profileRequestId = profileRequestId;
        this.profileID = profileID;
        this.userID = userID;
    }
}
