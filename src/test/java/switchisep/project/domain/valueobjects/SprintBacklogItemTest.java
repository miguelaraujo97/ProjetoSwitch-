package switchisep.project.domain.valueobjects;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SprintBacklogItemTest {

    @Test
    void createValidSprintBacklogItemObject() {

        //Arrange
        UserStoryID userStoryID = UserStoryID.createUserStoryID();
        ScrumBoardStatus scrumBoardStatus =
                ScrumBoardStatus.createScrumBoardStatus(ScrumBoardStatus.ScrumBoardStatusEnum.IN_PROGRESS);

        //Act & Assert
        assertNotNull(SprintBacklogItem.createSprintBacklogItem(userStoryID, scrumBoardStatus));
    }

    @Test
    void bothObjectsAreEqual() {

        //Arrange
        UserStoryID userStoryID1 = UserStoryID.createUserStoryID();
        ScrumBoardStatus scrumBoardStatus1 =
                ScrumBoardStatus.createScrumBoardStatus(ScrumBoardStatus.ScrumBoardStatusEnum.IN_PROGRESS);
        ScrumBoardStatus scrumBoardStatus2 =
                ScrumBoardStatus.createScrumBoardStatus(ScrumBoardStatus.ScrumBoardStatusEnum.IN_PROGRESS);
        SprintBacklogItem sprintBacklogItem1 =
                SprintBacklogItem.createSprintBacklogItem(userStoryID1, scrumBoardStatus1);

        SprintBacklogItem sprintBacklogItem2 =
                SprintBacklogItem.createSprintBacklogItem(userStoryID1, scrumBoardStatus2);

        //Act & Assert
        assertEquals(sprintBacklogItem1, sprintBacklogItem2);
    }

    @Test
    void comparingTheSameObject() {

        //Arrange
        UserStoryID userStoryID1 = UserStoryID.createUserStoryID();
        ScrumBoardStatus scrumBoardStatus1 =
                ScrumBoardStatus.createScrumBoardStatus(ScrumBoardStatus.ScrumBoardStatusEnum.IN_PROGRESS);
        SprintBacklogItem sprintBacklogItem1 =
                SprintBacklogItem.createSprintBacklogItem(userStoryID1, scrumBoardStatus1);

        //Act & Assert
        assertEquals(sprintBacklogItem1, sprintBacklogItem1);
    }

    @Test
    void bothObjectsAreDifferent() {

        //Arrange
        UserStoryID userStoryID1 = UserStoryID.createUserStoryID();
        UserStoryID userStoryID2 = UserStoryID.createUserStoryID();
        ScrumBoardStatus scrumBoardStatus1 =
                ScrumBoardStatus.createScrumBoardStatus(ScrumBoardStatus.ScrumBoardStatusEnum.IN_PROGRESS);
        ScrumBoardStatus scrumBoardStatus2 =
                ScrumBoardStatus.createScrumBoardStatus(ScrumBoardStatus.ScrumBoardStatusEnum.DONE);
        SprintBacklogItem sprintBacklogItem1 =
                SprintBacklogItem.createSprintBacklogItem(userStoryID1, scrumBoardStatus1);
        SprintBacklogItem sprintBacklogItem2 =
                SprintBacklogItem.createSprintBacklogItem(userStoryID2, scrumBoardStatus2);

        //Act
        boolean result = sprintBacklogItem1.equals(sprintBacklogItem2);

        //Assert
        assertNotEquals(sprintBacklogItem1, sprintBacklogItem2);
        assertFalse(result);
    }

    @Test
    void oneOfTheTwoObjectsIsNull() {

        //Arrange
        UserStoryID userStoryID1 = UserStoryID.createUserStoryID();
        ScrumBoardStatus scrumBoardStatus1 =
                ScrumBoardStatus.createScrumBoardStatus(ScrumBoardStatus.ScrumBoardStatusEnum.IN_PROGRESS);
        SprintBacklogItem sprintBacklogItem1 =
                SprintBacklogItem.createSprintBacklogItem(userStoryID1, scrumBoardStatus1);
        SprintBacklogItem sprintBacklogItem2 = null;

        //Act
        boolean result = sprintBacklogItem1.equals(sprintBacklogItem2);

        //Assert
        assertNotEquals(sprintBacklogItem1, sprintBacklogItem2);
        assertFalse(result);
    }

    @Test
    void hashCodeIsTheSame() {

        //Arrange
        UserStoryID userStoryID1 = UserStoryID.createUserStoryID();
        ScrumBoardStatus scrumBoardStatus1 =
                ScrumBoardStatus.createScrumBoardStatus(ScrumBoardStatus.ScrumBoardStatusEnum.IN_PROGRESS);
        ScrumBoardStatus scrumBoardStatus2 =
                ScrumBoardStatus.createScrumBoardStatus(ScrumBoardStatus.ScrumBoardStatusEnum.IN_PROGRESS);
        SprintBacklogItem sprintBacklogItem1 =
                SprintBacklogItem.createSprintBacklogItem(userStoryID1, scrumBoardStatus1);
        SprintBacklogItem sprintBacklogItem2 =
                SprintBacklogItem.createSprintBacklogItem(userStoryID1, scrumBoardStatus2);

        //Act & Assert
        assertEquals(sprintBacklogItem1.hashCode(), sprintBacklogItem2.hashCode());
    }

    @Test
    void hashCodeIsDifferent() {

        //Arrange
        UserStoryID userStoryID1 = UserStoryID.createUserStoryID();
        UserStoryID userStoryID2 = UserStoryID.createUserStoryID();
        ScrumBoardStatus scrumBoardStatus1 =
                ScrumBoardStatus.createScrumBoardStatus(ScrumBoardStatus.ScrumBoardStatusEnum.IN_PROGRESS);
        ScrumBoardStatus scrumBoardStatus2 =
                ScrumBoardStatus.createScrumBoardStatus(ScrumBoardStatus.ScrumBoardStatusEnum.DONE);
        SprintBacklogItem sprintBacklogItem1 =
                SprintBacklogItem.createSprintBacklogItem(userStoryID1, scrumBoardStatus1);
        SprintBacklogItem sprintBacklogItem2 =
                SprintBacklogItem.createSprintBacklogItem(userStoryID2, scrumBoardStatus2);

        //Act & Assert
        assertNotEquals(sprintBacklogItem1.hashCode(), sprintBacklogItem2.hashCode());
    }

    @Test
    void testSameValueAs_TestForNull(){
        //Arrange
        UserStoryID userStoryID1 = UserStoryID.createUserStoryID();
        ScrumBoardStatus scrumBoardStatus1 =
                ScrumBoardStatus.createScrumBoardStatus(ScrumBoardStatus.ScrumBoardStatusEnum.IN_PROGRESS);
        SprintBacklogItem sprintBacklogItem1 =
                SprintBacklogItem.createSprintBacklogItem(userStoryID1, scrumBoardStatus1);

        SprintBacklogItem sprintBacklogItem2 = null;
        //Act
        boolean result = sprintBacklogItem1.sameValueAs(sprintBacklogItem2);

        //Assert
        assertFalse(result);
    }

    @Test
    void testSameValueAs_BothAttributesAreDifferent() {

        //Arrange
        UserStoryID userStoryID1 = UserStoryID.createUserStoryID();
        UserStoryID userStoryID2 = UserStoryID.createUserStoryID();
        ScrumBoardStatus scrumBoardStatus1 =
                ScrumBoardStatus.createScrumBoardStatus(ScrumBoardStatus.ScrumBoardStatusEnum.IN_PROGRESS);
        ScrumBoardStatus scrumBoardStatus2 =
                ScrumBoardStatus.createScrumBoardStatus(ScrumBoardStatus.ScrumBoardStatusEnum.DONE);
        SprintBacklogItem sprintBacklogItem1 =
                SprintBacklogItem.createSprintBacklogItem(userStoryID1, scrumBoardStatus1);
        SprintBacklogItem sprintBacklogItem2 =
                SprintBacklogItem.createSprintBacklogItem(userStoryID2, scrumBoardStatus2);

        //Act
        boolean result = sprintBacklogItem1.sameValueAs(sprintBacklogItem2);

        //Assert
        assertFalse(result);
    }

//    @Test
//    void testSameValueAs_OneAttributeIsDifferent_USIDisDiff() {
//
//        //Arrange
//        UserStoryID userStoryID1 = UserStoryID.createUserStoryID("US01", "PJ01");
//        UserStoryID userStoryID2 = UserStoryID.createUserStoryID("US02", "PJ01");
//        ScrumBoardStatus scrumBoardStatus1 =
//                ScrumBoardStatus.createScrumBoardStatus(ScrumBoardStatus.ScrumBoardStatusEnum.IN_PROGRESS);
//        ScrumBoardStatus scrumBoardStatus2 =
//                ScrumBoardStatus.createScrumBoardStatus(ScrumBoardStatus.ScrumBoardStatusEnum.IN_PROGRESS);
//        SprintBacklogItem sprintBacklogItem1 =
//                SprintBacklogItem.createSprintBacklogItem(userStoryID1, scrumBoardStatus1);
//        SprintBacklogItem sprintBacklogItem2 =
//                SprintBacklogItem.createSprintBacklogItem(userStoryID2, scrumBoardStatus2);
//
//        //Act
//        boolean result = sprintBacklogItem1.sameValueAs(sprintBacklogItem2);
//
//        //Assert
//        assertFalse(result);
//    }

    @Test
    void testSameValueAs_OneAttributeIsDifferent_SBSisDiff() {

        //Arrange
        UserStoryID userStoryID1 = UserStoryID.createUserStoryID();
        UserStoryID userStoryID2 = UserStoryID.createUserStoryID();
        ScrumBoardStatus scrumBoardStatus1 =
                ScrumBoardStatus.createScrumBoardStatus(ScrumBoardStatus.ScrumBoardStatusEnum.IN_PROGRESS);
        ScrumBoardStatus scrumBoardStatus2 =
                ScrumBoardStatus.createScrumBoardStatus(ScrumBoardStatus.ScrumBoardStatusEnum.TO_DO);
        SprintBacklogItem sprintBacklogItem1 =
                SprintBacklogItem.createSprintBacklogItem(userStoryID1, scrumBoardStatus1);
        SprintBacklogItem sprintBacklogItem2 =
                SprintBacklogItem.createSprintBacklogItem(userStoryID2, scrumBoardStatus2);

        //Act
        boolean result = sprintBacklogItem1.sameValueAs(sprintBacklogItem2);

        //Assert
        assertFalse(result);
    }

    @Test
    void testSameValueAs_TestForSameObject() {

        //Arrange
        UserStoryID userStoryID1 = UserStoryID.createUserStoryID();
        ScrumBoardStatus scrumBoardStatus1 =
                ScrumBoardStatus.createScrumBoardStatus(ScrumBoardStatus.ScrumBoardStatusEnum.IN_PROGRESS);
        SprintBacklogItem sprintBacklogItem1 =
                SprintBacklogItem.createSprintBacklogItem(userStoryID1, scrumBoardStatus1);
        SprintBacklogItem sprintBacklogItem2 =
                SprintBacklogItem.createSprintBacklogItem(userStoryID1, scrumBoardStatus1);

        //Act
        boolean result = sprintBacklogItem1.sameValueAs(sprintBacklogItem2);

        //Assert
        assertTrue(result);
    }

}