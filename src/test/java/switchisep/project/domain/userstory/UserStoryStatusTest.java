package switchisep.project.domain.userstory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static switchisep.project.domain.userstory.UserStoryStatus.UserStoryStatusEnum.*;

class UserStoryStatusTest {

    @Test
    void checkOverridePlannedFinished() {
        //Arrange

        //Act
        UserStoryStatus userStoryStatus = UserStoryStatus.createUserStoryStatus(PLANNED);
        UserStoryStatus userStoryStatus1 = UserStoryStatus.createUserStoryStatus(PLANNED);
        UserStoryStatus userStoryStatus2 = UserStoryStatus.createUserStoryStatus(FINISHED);
        // Assert
        assertEquals(userStoryStatus, userStoryStatus);
        assertEquals(userStoryStatus, userStoryStatus1);
        assertEquals(userStoryStatus.hashCode(), userStoryStatus1.hashCode());
        assertNotEquals(userStoryStatus, userStoryStatus2);
        assertNotEquals(userStoryStatus, null);
        assertNotEquals(userStoryStatus.hashCode(), userStoryStatus2.hashCode());
    }

    @Test
    void checkOverrideRunningBlocked() {
        //Arrange

        //Act
        UserStoryStatus userStoryStatus = UserStoryStatus.createUserStoryStatus(PLANNED);
        UserStoryStatus userStoryStatus1 = UserStoryStatus.createUserStoryStatus(PLANNED);
        UserStoryStatus userStoryStatus2 = UserStoryStatus.createUserStoryStatus(CANCELLED);
        // Assert
        assertEquals(userStoryStatus, userStoryStatus);
        assertEquals(userStoryStatus, userStoryStatus1);
        assertEquals(userStoryStatus.hashCode(), userStoryStatus1.hashCode());
        assertNotEquals(userStoryStatus, userStoryStatus2);
        assertNotEquals(userStoryStatus, null);
        assertNotEquals(userStoryStatus.hashCode(), userStoryStatus2.hashCode());
    }

    @Test
    void checkOverrideNotNull() {
        //Arrange

        //Act
        UserStoryStatus.UserStoryStatusEnum userStoryStatusEnum = PLANNED;

        // Assert

        assertNotNull(UserStoryStatus.createUserStoryStatus(userStoryStatusEnum));
    }

    @Test
    void checkOverrideNull() {
        //Arrange

        //Act
        UserStoryStatus.UserStoryStatusEnum userStoryStatusEnum = null;

        // Assert

        assertNull(UserStoryStatus.createUserStoryStatus(userStoryStatusEnum));
    }

}
