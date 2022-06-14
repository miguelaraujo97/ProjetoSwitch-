package switchisep.project.domain.userstory;

import switchisep.project.domain.valueobjects.*;

import java.util.*;

public class UserStory {

    // Attributes of User Story
    private final Description description;
    private final UserStoryID userStoryID;
    private final UserStoryID parentUserStory;
    private UserStoryStatus status; // Planned, Running, Finished or Blocked
    private EffortEstimate effort;
    private Priority priority;
    private final ProjectCode projectCode;
    private SprintID sprintID;

    //Constructor of UserStory


    private UserStory(Builder builder) {
        this.status = builder.status;
        this.userStoryID = builder.userStoryID;
        this.description = builder.description;
        this.effort = builder.effort;
        this.priority = builder.priority;
        this.parentUserStory = builder.parentUserStory;
        this.projectCode = builder.projectCode;
        this.sprintID=builder.sprintID;
    }

    public static class Builder {

        private final Description description;
        private final UserStoryID userStoryID;
        private UserStoryID parentUserStory;
        private UserStoryStatus status;
        private EffortEstimate effort;
        private Priority priority;
        private final ProjectCode projectCode;
        private SprintID sprintID;

        public Builder(UserStoryID userStoryID, ProjectCode projectCode,
                           Description description) {
            this.userStoryID = userStoryID;
            this.projectCode = projectCode;
            this.description = description;
        }

        public Builder statusPlanned() {
            this.status = UserStoryStatus.createUserStoryStatus(
                    UserStoryStatus.UserStoryStatusEnum.PLANNED);
            return this;
        }

        public Builder status(UserStoryStatus userStoryStatus) {
            this.status = userStoryStatus;
            return this;
        }

        public Builder effort(EffortEstimate effort) {
            this.effort = effort;
            return this;
        }

        public Builder parentUS(UserStoryID userStoryId) {
            this.parentUserStory = userStoryId;
            return this;
        }

        public Builder priority(Priority priority) {
            this.priority = priority;
            return this;
        }
        public Builder sprintID(SprintID sprintID){
            this.sprintID=sprintID;
            return this;
        }

        public UserStory build() {
            return new UserStory(this);
        }
    }


    /**
     * Method used to show the User Story description.
     *
     * @return the user story description
     */
    public Description getUserStoryDescription() {
        return this.description;
    }

    /**
     * Method to get the priority Number.
     *
     * @return User Story priority
     */
    public Priority getPriority() {
        return this.priority;
    }


    /**
     * Method to get the User Story ID.
     *
     * @return the User Story ID
     */
    public UserStoryID getUserStoryID() {
        return this.userStoryID;
    }

    /**
     * Method to return effort on a user story.
     *
     * @return effort value
     */
    public EffortEstimate getEffort() {
        return this.effort;
    }

    /**
     * Method to set effort to a user story in the current
     * Sprint Backlog.
     *
     * @param usEffort value to add on the us
     * @return true for added.
     */
    public void setEffort(EffortEstimate usEffort) {
        this.effort = usEffort;
    }

    /**
     * Method to return the status of a User Story.
     *
     * @return The User Story Status
     */
    public UserStoryStatus getStatus() {
        return this.status;
    }

    public ProjectCode getProjectCode() {
        return this.projectCode;
    }

    /**
     * Method to change the status of a User Story.
     *
     * @param usStatus string to set the status of the User Story
     */
    public void setStatus(UserStoryStatus usStatus) {
        this.status = usStatus;
    }

    /**
     * Method to set the correct priority.
     *
     * @param priority integer
     */
    public boolean setPriority(Priority priority) {
        this.priority = priority;
        return true;
    }

    /**
     * return the parent ID.
     *
     * @return the user story parent ID
     */
    public UserStoryID getParentUserStory() {
        return this.parentUserStory;
    }

    public SprintID getSprintID() {
        return sprintID;
    }

    public void setSprintID(SprintID sprintID) {
        this.sprintID=sprintID;
    }

    /**
     * Override to equals.
     *
     * @param o new object
     * @return true for equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserStory userStory = (UserStory) o;
        return Objects.equals(description, userStory.description)
                && Objects.equals(userStoryID, userStory.userStoryID)
                && Objects.equals(parentUserStory, userStory.parentUserStory)
                && Objects.equals(status, userStory.status)
                && Objects.equals(effort, userStory.effort)
                && Objects.equals(priority, userStory.priority)
                && Objects.equals(projectCode, userStory.projectCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, userStoryID, parentUserStory, status,
                effort, priority, projectCode);
    }
}
