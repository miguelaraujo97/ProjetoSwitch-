package switchisep.project.domain.userstory;

import org.junit.jupiter.api.Test;
import switchisep.project.domain.valueobjects.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class UserStoryTest {

    @Test
    void checkCompleteBuilder() {

        // Arrange
        Description expectedDescription = Description.createDescription("teste");
        UserStoryID expectedUserStoryID = UserStoryID.createUserStoryID();
        ProjectCode expectedProjectCode = ProjectCode.createProjectCode("A123");
        EffortEstimate expectedEffortEstimate = EffortEstimate.createEffortEstimate(1);
        Priority expectedPriority = Priority.createPriority(1);
        //ParentUS expectedParentUS = ParentUS.createParentUS(expectedUserStoryID);
        SprintID expectedSprintID = SprintID.createSprintID("S01");
        UserStoryID parentUS = UserStoryID.createUserStoryIdWithString("0");

        // Act
        UserStory userStory = new UserStory.Builder(expectedUserStoryID, expectedProjectCode, expectedDescription)
                .effort(expectedEffortEstimate)
                .priority(expectedPriority)
                .statusPlanned()
                .parentUS(parentUS)
                .sprintID(expectedSprintID).build();

        Description resultDescription = userStory.getUserStoryDescription();
        ProjectCode resultProjectCode = userStory.getProjectCode();
        UserStoryID resultUserStoryID = userStory.getUserStoryID();
        EffortEstimate resultEffortEstimate = userStory.getEffort();
        Priority resultPriority = userStory.getPriority();
        UserStoryID resultParentUs = userStory.getParentUserStory();
        SprintID resultSprintID = userStory.getSprintID();

        // Assert
        assertEquals(expectedDescription, resultDescription);
        assertEquals(expectedProjectCode, resultProjectCode);
        assertEquals(expectedUserStoryID, resultUserStoryID);
        assertEquals(expectedEffortEstimate, resultEffortEstimate);
        assertEquals(expectedPriority, resultPriority);
        //assertEquals(expectedParentUS, resultParentUs);
        assertEquals(expectedSprintID, resultSprintID);
    }

    @Test
    void checkGetAndSet() {
        // Arrange
        Description expectedDescription = Description.createDescription("teste");
        UserStoryID expectedUserStoryID = UserStoryID.createUserStoryID();
        ProjectCode expectedProjectCode = ProjectCode.createProjectCode("A123");
        EffortEstimate expectedEffortEstimate = EffortEstimate.createEffortEstimate(1);
        Priority expectedPriority = Priority.createPriority(1);
        ParentUS expectedParentUS = ParentUS.createParentUS(expectedUserStoryID);
        SprintID expectedSprintID = SprintID.createSprintID("S01");
        UserStoryID parentUS = UserStoryID.createUserStoryIdWithString("0");

        // Act
        UserStory userStory = new UserStory.Builder(expectedUserStoryID, expectedProjectCode, expectedDescription)
                .effort(expectedEffortEstimate)
                .priority(expectedPriority)
                .statusPlanned().
                parentUS(parentUS).build();

        Priority newPriority = Priority.createPriority(2);

        userStory.setPriority(newPriority);

        SprintID newSprintID = SprintID.createSprintID("S02");

        userStory.setSprintID(newSprintID);

        EffortEstimate effortEstimate = EffortEstimate.createEffortEstimate(1);

        userStory.setEffort(effortEstimate);

        UserStoryStatus newStatus = UserStoryStatus.createUserStoryStatus(UserStoryStatus.UserStoryStatusEnum.FINISHED);

        userStory.setStatus(newStatus);
        // Assert
        assertEquals(2, userStory.getPriority().getPriority());
        assertEquals("S02", userStory.getSprintID().getTaskContainerIDString());
        assertEquals(1, effortEstimate.getEffortEstimateValue());
        assertEquals(newStatus.getuSValueOfStatus(), userStory.getStatus().getuSValueOfStatus());
    }

    @Test
    void assertNotEqualsTwoDifferenteUserStories() {

        // Arrange
        Description userStoryOneDescription = Description.createDescription("teste");

        UserStoryID userStoryOneUserStoryID = UserStoryID.createUserStoryID();

        ProjectCode userStoryOneProjectCode = ProjectCode.createProjectCode("A123");

        EffortEstimate userStoryOneEffortEstimate = EffortEstimate.createEffortEstimate(1);

        Priority userStoryOnePriority = Priority.createPriority(1);

        SprintID userStoryOneSprintID = SprintID.createSprintID("S01");

        UserStoryID userStoryOneparentUS = UserStoryID.createUserStoryIdWithString("0");

        UserStory userStoryOne = new UserStory.Builder(userStoryOneUserStoryID, userStoryOneProjectCode, userStoryOneDescription)
                .effort(userStoryOneEffortEstimate)
                .priority(userStoryOnePriority)
                .statusPlanned()
                .parentUS(userStoryOneparentUS)
                .sprintID(userStoryOneSprintID).build();

        Description userStoryTwoDescription = Description.createDescription("Teste");

        UserStoryID userStoryTwoUserStoryID = UserStoryID.createUserStoryID();

        ProjectCode userStoryTwoProjectCode = ProjectCode.createProjectCode("B123");

        EffortEstimate userStoryTwoEffortEstimate = EffortEstimate.createEffortEstimate(2);

        Priority userStoryTwoPriority = Priority.createPriority(2);

        SprintID userStoryTwoSprintID = SprintID.createSprintID("S02");

        UserStoryID userStoryTwoparentUS = UserStoryID.createUserStoryIdWithString("-1");

        // Act

        UserStory userStoryTwo = new UserStory.Builder(userStoryTwoUserStoryID, userStoryTwoProjectCode, userStoryTwoDescription)
                .effort(userStoryTwoEffortEstimate)
                .priority(userStoryTwoPriority)
                .statusPlanned()
                .parentUS(userStoryTwoparentUS)
                .sprintID(userStoryTwoSprintID).build();

        // Assert
        assertNotEquals(userStoryOne, userStoryTwo);
    }


    @Test
    void checkOverride() {
        //Arrange
        Description description = Description.createDescription("teste");
        Description description1 = Description.createDescription("teste1");
        UserStoryID userStoryID = UserStoryID.createUserStoryID();
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");

        //Act
        UserStory userStory = new UserStory.Builder(userStoryID,
                projectCode, description).build();
        UserStory userStory1 = new UserStory.Builder(userStoryID,
                projectCode, description).build();
        UserStory userStory2 = new UserStory.Builder(userStoryID,
                projectCode, description1).build();
        // Assert
        assertEquals(userStory, userStory);
        assertEquals(userStory, userStory1);
        assertEquals(userStory.hashCode(), userStory1.hashCode());
        assertNotEquals(userStory, userStory2);
        assertNotEquals(userStory, null);
        assertNotEquals(userStory.hashCode(), userStory2.hashCode());
    }

}

