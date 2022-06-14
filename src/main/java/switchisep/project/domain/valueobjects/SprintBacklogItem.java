package switchisep.project.domain.valueobjects;

import javax.persistence.Embeddable;
import java.util.Objects;

/**
 * DDD refactoring (Mário Dessa / Francisco Amado): Value object
 * sprintBacklogItem
 */
//@Embeddable
public class SprintBacklogItem implements ValueObject<SprintBacklogItem> {

    private  UserStoryID userStoryID;
    private  ScrumBoardStatus scrumBoardStatus;

    /**
     * Private constructor that accepts an integer as an argument.
     *
     * @param userStoryID      Unique identifier for user stories.
     * @param scrumBoardStatus Can assume one of three possible values:
     *                         TO_DO, IN_PROGRESS and DONE.
     */
    private SprintBacklogItem(UserStoryID userStoryID,
                              ScrumBoardStatus scrumBoardStatus) {

        this.userStoryID = userStoryID;
        this.scrumBoardStatus = scrumBoardStatus;
    }

    public SprintBacklogItem() {
    }

    /**
     * Static method that allows the creation of sprintBacklogItem objects.
     *
     * @param userStoryID      Unique identifier for user stories.
     * @param scrumBoardStatus Can assume one of three possible values:
     *                         TO_DO, IN_PROGRESS and DONE.
     * @return A sprintBacklogItem object.
     */
    public static SprintBacklogItem createSprintBacklogItem
    (UserStoryID userStoryID, ScrumBoardStatus scrumBoardStatus) {

        return new SprintBacklogItem(userStoryID, scrumBoardStatus);
    }

    @Override
    public boolean sameValueAs(SprintBacklogItem other) {
        return other != null
                && this.userStoryID.equals(other.userStoryID)
                && this.scrumBoardStatus.equals(other.scrumBoardStatus);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SprintBacklogItem)) return false;
        SprintBacklogItem other = (SprintBacklogItem) o;
        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userStoryID, scrumBoardStatus);
    }


}
