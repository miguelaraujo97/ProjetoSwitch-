package switchisep.project.domain.sprint;

import org.junit.jupiter.api.Test;
import switchisep.project.domain.valueobjects.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SprintFactoryTest {


    @Test
    void sprintFactoryTest_createNewSprint(){

        //Arrange

        SprintFactory sprintFactory = new SprintFactory();

        SprintID sprintID = SprintID.createSprintID("S001");

        ProjectCode projectCode = ProjectCode.createProjectCode("A123");


        SprintDuration sprintDuration = SprintDuration.createSprintDuration(1);
        SprintOrder sprintOrder = SprintOrder.createSprintOrder(1);

        LocalDate startDate = LocalDate.of(2023, 10, 01);
        LocalDate endDate = LocalDate.of(2024, 10, 01);

        TimePeriod plannedTimePeriod = TimePeriod.createTimePeriod(startDate, endDate);

        SprintStatus sprintStatus = SprintStatus.createSprintStatus(SprintStatus.SprintStatusEnum.NOT_STARTED);

        Sprint sprintManually = new Sprint(sprintID, projectCode,
                plannedTimePeriod, sprintStatus);

        //Act
        Sprint newSprint = sprintFactory.createSprint(sprintID, projectCode,
                plannedTimePeriod, sprintStatus);

        //Assert
        assertEquals(sprintManually, newSprint);
    }

}