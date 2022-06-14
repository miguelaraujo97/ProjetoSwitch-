package switchisep.project.datamodel;


import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;


@Entity
@Table(name="userStories")
public class UserStoryJpa implements Serializable {

    @Id
    private String userStoryID;

    private String projectCode;

    private String description;

    private String parentUS;

    private String userStoryStatus;

    private int priority;

    private int effortEstimate;

    private String sprintID;

    public UserStoryJpa(String userStoryID, String projectCode,
                        String description, String parentUS,
                        String userStoryStatus, int priority,
                        int effortEstimate, String sprintID) {

        this.userStoryID = userStoryID;
        this.projectCode = projectCode;
        this.description = description;
        this.parentUS = parentUS;
        this.userStoryStatus = userStoryStatus;
        this.priority = priority;
        this.effortEstimate = effortEstimate;
        this.sprintID = sprintID;
    }

    public UserStoryJpa() {

    }

    public String getUserStoryID() {
        return userStoryID;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public String getDescription() {
        return description;
    }

    public String getParentUS() {
        return parentUS;
    }

    public String getUserStoryStatus() {
        return userStoryStatus;
    }

    public int getPriority() {
        return priority;
    }

    public int getEffortEstimate() {
        return effortEstimate;
    }

    public String getSprintID() {
        return sprintID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserStoryJpa)) return false;
        UserStoryJpa that = (UserStoryJpa) o;
        return priority == that.priority &&
                effortEstimate == that.effortEstimate &&
                Objects.equals(userStoryID, that.userStoryID) &&
                Objects.equals(projectCode, that.projectCode) &&
                Objects.equals(description, that.description) &&
                Objects.equals(parentUS, that.parentUS) &&
                Objects.equals(userStoryStatus, that.userStoryStatus) &&
                Objects.equals(sprintID, that.sprintID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userStoryID, projectCode, description, parentUS, userStoryStatus, priority, effortEstimate, sprintID);
    }
}


