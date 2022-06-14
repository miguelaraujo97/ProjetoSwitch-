package switchisep.project.dto;


public class UpdateUserStoryDTO {

    public String description;
    public String userStoryID;
    public String parentUserStory;
    public String status; // Planned, Running, Finished or Blocked
    public String effort;
    public String priority;
    public String projectCode;
    public String sprintID;
    public String action; //addToUS /otherAction
}
