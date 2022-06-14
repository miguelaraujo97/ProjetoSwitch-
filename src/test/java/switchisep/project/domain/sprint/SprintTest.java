package switchisep.project.domain.sprint;

import org.junit.jupiter.api.Test;
import switchisep.project.domain.valueobjects.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SprintTest {

    @Test
    void testEquals_SameObject(){
        //Arrange
        SprintID sprintID = SprintID.createSprintID("S001");
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");


        SprintDuration sprintDuration = SprintDuration.createSprintDuration(1);
        SprintOrder sprintOrder = SprintOrder.createSprintOrder(1);

        LocalDate startDate = LocalDate.of(2023, 10, 01);
        LocalDate endDate = LocalDate.of(2024, 10, 01);

        TimePeriod plannedTimePeriod = TimePeriod.createTimePeriod(startDate, endDate);

        SprintStatus sprintStatus = SprintStatus.createSprintStatus(SprintStatus.SprintStatusEnum.NOT_STARTED);

        Sprint sprint = new Sprint(sprintID, projectCode,
                plannedTimePeriod, sprintStatus);

        //Act & Assert
        assertEquals(sprint, sprint);
    }

    @Test
    void testEquals_DiffObjectSameClass(){
        //Arrange
        SprintID sprintID1 = SprintID.createSprintID("S001");
        SprintID sprintID2 = SprintID.createSprintID("S002");

        ProjectCode projectCode = ProjectCode.createProjectCode("A123");


        SprintDuration sprintDuration = SprintDuration.createSprintDuration(1);
        SprintOrder sprintOrder = SprintOrder.createSprintOrder(1);

        LocalDate startDate = LocalDate.of(2023, 10, 01);
        LocalDate endDate = LocalDate.of(2024, 10, 01);

        TimePeriod plannedTimePeriod = TimePeriod.createTimePeriod(startDate, endDate);

        SprintStatus sprintStatus = SprintStatus.createSprintStatus(SprintStatus.SprintStatusEnum.NOT_STARTED);

        Sprint sprint1 = new Sprint(sprintID1, projectCode,
                plannedTimePeriod, sprintStatus);

        Sprint sprint2 = new Sprint(sprintID2, projectCode,
                plannedTimePeriod, sprintStatus);

        //Act & Assert
        assertNotEquals(sprint1, sprint2);
    }

    @Test
    void testEquals_DiffObjectDiffClass(){
        //Arrange
        SprintID sprintID1 = SprintID.createSprintID("S001");

        ProjectCode projectCode = ProjectCode.createProjectCode("A123");


        SprintDuration sprintDuration = SprintDuration.createSprintDuration(1);
        SprintOrder sprintOrder = SprintOrder.createSprintOrder(1);

        LocalDate startDate = LocalDate.of(2023, 10, 01);
        LocalDate endDate = LocalDate.of(2024, 10, 01);

        TimePeriod plannedTimePeriod = TimePeriod.createTimePeriod(startDate, endDate);

        SprintStatus sprintStatus = SprintStatus.createSprintStatus(SprintStatus.SprintStatusEnum.NOT_STARTED);

        Sprint sprint = new Sprint(sprintID1, projectCode,
                plannedTimePeriod, sprintStatus);

        Object nativaObject = new Object();

        //Act & Assert
        assertNotEquals(sprint, nativaObject);
    }

    @Test
    void testEquals_NullObject(){
        //Arrange
        SprintID sprintID1 = SprintID.createSprintID("S001");

        ProjectCode projectCode = ProjectCode.createProjectCode("A123");


        SprintDuration sprintDuration = SprintDuration.createSprintDuration(1);
        SprintOrder sprintOrder = SprintOrder.createSprintOrder(1);

        LocalDate startDate = LocalDate.of(2023, 10, 01);
        LocalDate endDate = LocalDate.of(2024, 10, 01);

        TimePeriod plannedTimePeriod = TimePeriod.createTimePeriod(startDate, endDate);

        SprintStatus sprintStatus = SprintStatus.createSprintStatus(SprintStatus.SprintStatusEnum.NOT_STARTED);

        Sprint sprint = new Sprint(sprintID1, projectCode,
                plannedTimePeriod, sprintStatus);

        Sprint sprintNull = null;

        //Act & Assert
        assertNotEquals(sprint, sprintNull);
    }

    @Test
    void testHashCode_SameObject(){
        //Arrange
        SprintID sprintID1 = SprintID.createSprintID("S001");

        ProjectCode projectCode = ProjectCode.createProjectCode("A123");


        SprintDuration sprintDuration = SprintDuration.createSprintDuration(1);
        SprintOrder sprintOrder = SprintOrder.createSprintOrder(1);

        LocalDate startDate = LocalDate.of(2023, 10, 01);
        LocalDate endDate = LocalDate.of(2024, 10, 01);

        TimePeriod plannedTimePeriod = TimePeriod.createTimePeriod(startDate, endDate);

        SprintStatus sprintStatus = SprintStatus.createSprintStatus(SprintStatus.SprintStatusEnum.NOT_STARTED);

        Sprint sprint1 = new Sprint(sprintID1, projectCode,
                plannedTimePeriod, sprintStatus);

        //Act & Assert
        assertEquals(sprint1.hashCode(), sprint1.hashCode());
    }

    @Test
    void testSameIdentityAs_testForNull(){
        //Arrange
        SprintID sprintID1 = SprintID.createSprintID("S001");

        ProjectCode projectCode = ProjectCode.createProjectCode("A123");


        SprintDuration sprintDuration = SprintDuration.createSprintDuration(1);
        SprintOrder sprintOrder = SprintOrder.createSprintOrder(1);

        LocalDate startDate = LocalDate.of(2023, 10, 01);
        LocalDate endDate = LocalDate.of(2024, 10, 01);

        TimePeriod plannedTimePeriod = TimePeriod.createTimePeriod(startDate, endDate);

        SprintStatus sprintStatus = SprintStatus.createSprintStatus(SprintStatus.SprintStatusEnum.NOT_STARTED);

        Sprint sprint1 = new Sprint(sprintID1, projectCode,
                plannedTimePeriod, sprintStatus);

        Sprint sprintNull = null;

        //Act
        boolean result = sprint1.sameIdentityAs(sprintNull);

        //Assert
        assertFalse(result);
    }

    @Test
    void testGetSprintID(){
        //Arrange
        SprintID sprintID = SprintID.createSprintID("S001");

        ProjectCode projectCode = ProjectCode.createProjectCode("A123");


        SprintDuration sprintDuration = SprintDuration.createSprintDuration(1);
        SprintOrder sprintOrder = SprintOrder.createSprintOrder(1);

        LocalDate startDate = LocalDate.of(2023, 10, 01);
        LocalDate endDate = LocalDate.of(2024, 10, 01);

        TimePeriod plannedTimePeriod = TimePeriod.createTimePeriod(startDate, endDate);

        SprintStatus sprintStatus = SprintStatus.createSprintStatus(SprintStatus.SprintStatusEnum.NOT_STARTED);

        Sprint sprint1 = new Sprint(sprintID, projectCode,
                plannedTimePeriod, sprintStatus);

        //Act
        SprintID sprintIDFound = sprint1.getSprintID();

        //Assert
        assertEquals(sprintID, sprintIDFound);
    }

    @Test
    void testGetProjectD(){
        //Arrange
        SprintID sprintID = SprintID.createSprintID("S001");

        ProjectCode projectCode = ProjectCode.createProjectCode("A123");


        SprintDuration sprintDuration = SprintDuration.createSprintDuration(1);
        SprintOrder sprintOrder = SprintOrder.createSprintOrder(1);

        LocalDate startDate = LocalDate.of(2023, 10, 01);
        LocalDate endDate = LocalDate.of(2024, 10, 01);

        TimePeriod plannedTimePeriod = TimePeriod.createTimePeriod(startDate, endDate);

        SprintStatus sprintStatus = SprintStatus.createSprintStatus(SprintStatus.SprintStatusEnum.NOT_STARTED);

        Sprint sprint1 = new Sprint(sprintID, projectCode,
                plannedTimePeriod, sprintStatus);

        //Act
        ProjectCode projectCode1Found = sprint1.getProjectCode();

        //Assert
        assertEquals(projectCode, projectCode1Found);
    }
    @Test
    void getSprintStatus(){
        SprintID sprintID = SprintID.createSprintID("S001");

        ProjectCode projectCode = ProjectCode.createProjectCode("A123");


        SprintDuration sprintDuration = SprintDuration.createSprintDuration(1);
        SprintOrder sprintOrder = SprintOrder.createSprintOrder(1);

        LocalDate startDate = LocalDate.of(2023, 10, 01);
        LocalDate endDate = LocalDate.of(2024, 10, 01);

        TimePeriod plannedTimePeriod = TimePeriod.createTimePeriod(startDate, endDate);

        SprintStatus sprintStatus = SprintStatus.createSprintStatus(SprintStatus.SprintStatusEnum.NOT_STARTED);

        Sprint sprint1 = new Sprint(sprintID, projectCode,
                plannedTimePeriod, sprintStatus);

        //Act
        SprintStatus sprintStatusResult = sprint1.getSprintStatus();
        //Assert
        assertEquals(sprintStatus,sprintStatusResult);

    }

}