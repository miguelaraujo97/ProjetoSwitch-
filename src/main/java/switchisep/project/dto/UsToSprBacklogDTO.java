package switchisep.project.dto;

import java.util.Objects;

public class UsToSprBacklogDTO {
    public String userStoryID;
    public String projectCode;
    public String sprintID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsToSprBacklogDTO)) return false;
        UsToSprBacklogDTO that = (UsToSprBacklogDTO) o;
        return Objects.equals(userStoryID, that.userStoryID) && Objects.equals(projectCode, that.projectCode) && Objects.equals(sprintID, that.sprintID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userStoryID, projectCode, sprintID);
    }
}
