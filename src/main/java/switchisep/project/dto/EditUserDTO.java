package switchisep.project.dto;


public class EditUserDTO {

    public int userID;
    public String oldPassword;
    public String newPassword;
    public String profileID;
    public String userEmail;
    public String function;
    public String photo;
    public boolean activation;
    public String action;
    // Action can either be: PasswordUpdate, ProfileUpdate,
    // ActivateUser, EditPhoto, EditFunction

}
