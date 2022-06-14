package switchisep.project.datamodel;

import org.junit.jupiter.api.Test;
import switchisep.project.domain.userstory.UserStoryStatus;
import switchisep.project.domain.valueobjects.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class UserStoryJpaTest {

    @Test
    void getUserStoryID() {
        //Arrange
        Description description = Description.createDescription("teste");
        UserStoryID userStoryID = UserStoryID.createUserStoryID();
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");
        EffortEstimate effortEstimate = EffortEstimate.createEffortEstimate(1);
        Priority priority = Priority.createPriority(1);
        UserStoryStatus userStoryStatus = UserStoryStatus
                .createUserStoryStatus(UserStoryStatus.UserStoryStatusEnum.PLANNED);

        UserStoryJpa userStoryJpa = new UserStoryJpa(userStoryID.getUserStoryID(), projectCode.getCode(), description.getDescription(), null,
                userStoryStatus.getuSValueOfStatus(), priority.getPriority(), effortEstimate.getEffortEstimateValue(), SprintID.createSprintID("SP01").getTaskContainerIDString() );

        //Act
        UserStoryID result = UserStoryID.createUserStoryIdWithString(userStoryJpa.getUserStoryID());

        //Assert
        assertEquals(userStoryID, result);
    }

    @Test
    void getProjectID() {
        //Arrange
        Description description = Description.createDescription("teste");
        UserStoryID userStoryID = UserStoryID.createUserStoryID();
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");
        EffortEstimate effortEstimate = EffortEstimate.createEffortEstimate(1);
        Priority priority = Priority.createPriority(1);
        UserStoryStatus userStoryStatus = UserStoryStatus
                .createUserStoryStatus(UserStoryStatus.UserStoryStatusEnum.PLANNED);

        UserStoryJpa userStoryJpa = new UserStoryJpa(userStoryID.getUserStoryID(), projectCode.getCode(), description.getDescription(), null,
                userStoryStatus.getuSValueOfStatus(), priority.getPriority(), effortEstimate.getEffortEstimateValue(), SprintID.createSprintID("SP01").getTaskContainerIDString() );

        //Act
        String result = userStoryJpa.getProjectCode();

        //Assert
        assertEquals("A123", result);
    }

    @Test
    void getDescription() {
        //Arrange
        Description description = Description.createDescription("teste");
        UserStoryID userStoryID = UserStoryID.createUserStoryID();
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");
        EffortEstimate effortEstimate = EffortEstimate.createEffortEstimate(1);
        Priority priority = Priority.createPriority(1);
        UserStoryStatus userStoryStatus = UserStoryStatus
                .createUserStoryStatus(UserStoryStatus.UserStoryStatusEnum.PLANNED);

        UserStoryJpa userStoryJpa = new UserStoryJpa(userStoryID.getUserStoryID(), projectCode.getCode(), description.getDescription(), null,
                userStoryStatus.getuSValueOfStatus(), priority.getPriority(), effortEstimate.getEffortEstimateValue(), SprintID.createSprintID("SP01").getTaskContainerIDString() );

        //Act
        String result = userStoryJpa.getDescription();

        //Assert
        assertEquals("teste", result);
    }

    @Test
    void getParentUS() {
        //Arrange
        Description description = Description.createDescription("teste");
        UserStoryID userStoryID = UserStoryID.createUserStoryID();
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");
        EffortEstimate effortEstimate = EffortEstimate.createEffortEstimate(1);
        Priority priority = Priority.createPriority(1);
        UserStoryStatus userStoryStatus = UserStoryStatus
                .createUserStoryStatus(UserStoryStatus.UserStoryStatusEnum.PLANNED);

        UserStoryJpa userStoryJpa = new UserStoryJpa(userStoryID.getUserStoryID(), projectCode.getCode(), description.getDescription(), null,
                userStoryStatus.getuSValueOfStatus(), priority.getPriority(), effortEstimate.getEffortEstimateValue(), SprintID.createSprintID("SP01").getTaskContainerIDString() );

        //Act
        String result = userStoryJpa.getParentUS();

        //Assert
        assertNull(result);
    }

    @Test
    void getUserStoryStatus() {
        //Arrange
        Description description = Description.createDescription("teste");
        UserStoryID userStoryID = UserStoryID.createUserStoryID();
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");
        EffortEstimate effortEstimate = EffortEstimate.createEffortEstimate(1);
        Priority priority = Priority.createPriority(1);
        UserStoryStatus userStoryStatus = UserStoryStatus
                .createUserStoryStatus(UserStoryStatus.UserStoryStatusEnum.PLANNED);

        UserStoryJpa userStoryJpa = new UserStoryJpa(userStoryID.getUserStoryID(), projectCode.getCode(), description.getDescription(), null,
                userStoryStatus.getuSValueOfStatus(), priority.getPriority(), effortEstimate.getEffortEstimateValue(), SprintID.createSprintID("SP01").getTaskContainerIDString() );

        //Act
        String result = userStoryJpa.getUserStoryStatus();

        //Assert
        assertEquals(userStoryStatus.getuSValueOfStatus(), result);
    }

    @Test
    void getPriority() {
        //Arrange
        Description description = Description.createDescription("teste");
        UserStoryID userStoryID = UserStoryID.createUserStoryID();
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");
        EffortEstimate effortEstimate = EffortEstimate.createEffortEstimate(1);
        Priority priority = Priority.createPriority(1);
        UserStoryStatus userStoryStatus = UserStoryStatus
                .createUserStoryStatus(UserStoryStatus.UserStoryStatusEnum.PLANNED);

        UserStoryJpa userStoryJpa = new UserStoryJpa(userStoryID.getUserStoryID(), projectCode.getCode(), description.getDescription(), null,
                userStoryStatus.getuSValueOfStatus(), priority.getPriority(), effortEstimate.getEffortEstimateValue(), SprintID.createSprintID("SP01").getTaskContainerIDString() );

        //Act
        int result = userStoryJpa.getPriority();

        //Assert
        assertEquals(1, result);
    }

    @Test
    void getEffortEstimate() {
        //Arrange
        Description description = Description.createDescription("teste");
        UserStoryID userStoryID = UserStoryID.createUserStoryID();
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");
        EffortEstimate effortEstimate = EffortEstimate.createEffortEstimate(1);
        Priority priority = Priority.createPriority(1);
        UserStoryStatus userStoryStatus = UserStoryStatus
                .createUserStoryStatus(UserStoryStatus.UserStoryStatusEnum.PLANNED);

        UserStoryJpa userStoryJpa = new UserStoryJpa(userStoryID.getUserStoryID(), projectCode.getCode(), description.getDescription(), null,
                userStoryStatus.getuSValueOfStatus(), priority.getPriority(), effortEstimate.getEffortEstimateValue(), SprintID.createSprintID("SP01").getTaskContainerIDString() );

        //Act
        int result = userStoryJpa.getEffortEstimate();

        //Assert
        assertEquals(1, result);
    }

    @Test
    void getSprintID() {
        //Arrange
        Description description = Description.createDescription("teste");
        UserStoryID userStoryID = UserStoryID.createUserStoryID();
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");
        EffortEstimate effortEstimate = EffortEstimate.createEffortEstimate(1);
        Priority priority = Priority.createPriority(1);
        UserStoryStatus userStoryStatus = UserStoryStatus
                .createUserStoryStatus(UserStoryStatus.UserStoryStatusEnum.PLANNED);

        UserStoryJpa userStoryJpa = new UserStoryJpa(userStoryID.getUserStoryID(), projectCode.getCode(), description.getDescription(), null,
                userStoryStatus.getuSValueOfStatus(), priority.getPriority(), effortEstimate.getEffortEstimateValue(), SprintID.createSprintID("SP01").getTaskContainerIDString() );

        //Act
        String result = userStoryJpa.getSprintID();

        //Assert
        assertEquals("SP01", result);
    }

    @Test
    void noArgsConst(){
        UserStoryJpa userStoryJpa = new UserStoryJpa();

        assertEquals(userStoryJpa, userStoryJpa);
    }

    @Test
    void testEquals_diffClass(){
        Description description = Description.createDescription("teste");
        UserStoryID userStoryID = UserStoryID.createUserStoryID();
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");
        EffortEstimate effortEstimate = EffortEstimate.createEffortEstimate(1);
        Priority priority = Priority.createPriority(1);
        UserStoryStatus userStoryStatus = UserStoryStatus
                .createUserStoryStatus(UserStoryStatus.UserStoryStatusEnum.PLANNED);

        UserStoryJpa userStoryJpa = new UserStoryJpa(userStoryID.getUserStoryID(), projectCode.getCode(), description.getDescription(), null,
                userStoryStatus.getuSValueOfStatus(), priority.getPriority(), effortEstimate.getEffortEstimateValue(), SprintID.createSprintID("SP01").getTaskContainerIDString() );

        Object o  = new Object();

        assertNotEquals(o,userStoryJpa);
    }

    @Test
    void testEquals_SameObject(){
        Description description = Description.createDescription("teste");
        UserStoryID userStoryID = UserStoryID.createUserStoryID();
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");
        EffortEstimate effortEstimate = EffortEstimate.createEffortEstimate(1);
        Priority priority = Priority.createPriority(1);
        UserStoryStatus userStoryStatus = UserStoryStatus
                .createUserStoryStatus(UserStoryStatus.UserStoryStatusEnum.PLANNED);

        UserStoryJpa userStoryJpa = new UserStoryJpa(userStoryID.getUserStoryID(), projectCode.getCode(), description.getDescription(), null,
                userStoryStatus.getuSValueOfStatus(), priority.getPriority(), effortEstimate.getEffortEstimateValue(), SprintID.createSprintID("SP01").getTaskContainerIDString() );


        assertEquals(userStoryJpa.hashCode(),userStoryJpa.hashCode());
    }

    @Test
    void testEquals_EqualObjectsSameClass(){
        Description description = Description.createDescription("teste");
        UserStoryID userStoryID = UserStoryID.createUserStoryIdWithString("id");
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");
        EffortEstimate effortEstimate = EffortEstimate.createEffortEstimate(1);
        Priority priority = Priority.createPriority(1);
        UserStoryStatus userStoryStatus = UserStoryStatus
                .createUserStoryStatus(UserStoryStatus.UserStoryStatusEnum.PLANNED);

        UserStoryJpa userStoryJpa = new UserStoryJpa(userStoryID.getUserStoryID(), projectCode.getCode(), description.getDescription(), null,
                userStoryStatus.getuSValueOfStatus(), priority.getPriority(), effortEstimate.getEffortEstimateValue(), SprintID.createSprintID("SP01").getTaskContainerIDString() );

        Description description2 = Description.createDescription("teste");
        UserStoryID userStoryID2 = UserStoryID.createUserStoryIdWithString("id");
        ProjectCode projectCode2 = ProjectCode.createProjectCode("A123");
        EffortEstimate effortEstimate2 = EffortEstimate.createEffortEstimate(1);
        Priority priority2 = Priority.createPriority(1);
        UserStoryStatus userStoryStatus2 = UserStoryStatus
                .createUserStoryStatus(UserStoryStatus.UserStoryStatusEnum.PLANNED);

        UserStoryJpa userStoryJpa2 = new UserStoryJpa(userStoryID2.getUserStoryID(), projectCode2.getCode(), description2.getDescription(), null,
                userStoryStatus2.getuSValueOfStatus(), priority2.getPriority(), effortEstimate2.getEffortEstimateValue(), SprintID.createSprintID("SP01").getTaskContainerIDString() );

        assertEquals(userStoryJpa,userStoryJpa2);
    }

    @Test
    void testEquals_DifferentObjectsSameClass(){
        Description description = Description.createDescription("teste");
        UserStoryID userStoryID = UserStoryID.createUserStoryID();
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");
        EffortEstimate effortEstimate = EffortEstimate.createEffortEstimate(1);
        Priority priority = Priority.createPriority(1);
        UserStoryStatus userStoryStatus = UserStoryStatus
                .createUserStoryStatus(UserStoryStatus.UserStoryStatusEnum.PLANNED);

        UserStoryJpa userStoryJpa = new UserStoryJpa(userStoryID.getUserStoryID(), projectCode.getCode(), description.getDescription(), null,
                userStoryStatus.getuSValueOfStatus(), priority.getPriority(), effortEstimate.getEffortEstimateValue(), SprintID.createSprintID("SP01").getTaskContainerIDString() );

        Description description2 = Description.createDescription("teste2");
        UserStoryID userStoryID2 = UserStoryID.createUserStoryIdWithString("id");
        ProjectCode projectCode2 = ProjectCode.createProjectCode("A1234");
        EffortEstimate effortEstimate2 = EffortEstimate.createEffortEstimate(2);
        Priority priority2 = Priority.createPriority(2);
        UserStoryStatus userStoryStatus2 = UserStoryStatus
                .createUserStoryStatus(UserStoryStatus.UserStoryStatusEnum.PLANNED);

        UserStoryJpa userStoryJpa2 = new UserStoryJpa(userStoryID2.getUserStoryID(), projectCode2.getCode(), description2.getDescription(), null,
                userStoryStatus2.getuSValueOfStatus(), priority2.getPriority(), effortEstimate2.getEffortEstimateValue(), SprintID.createSprintID("SP01").getTaskContainerIDString() );

        assertNotEquals(userStoryJpa,userStoryJpa2);
    }
}