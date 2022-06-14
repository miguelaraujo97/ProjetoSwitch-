package switchisep.project.datamodel;

import org.junit.jupiter.api.Test;
import switchisep.project.domain.valueobjects.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static switchisep.project.domain.valueobjects.SprintStatus.SprintStatusEnum.STARTED;

class SprintJpaTest {


    @Test
    void getSprintID() {
        //arrange
        SprintID sprintID = SprintID.createSprintID("1");
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");
        TimePeriod timePeriod = TimePeriod.createTimePeriod(
                LocalDate.now(), LocalDate.now().plusWeeks(1));
        SprintStatus sprintStatus = SprintStatus.createSprintStatus(STARTED);
        SprintOrder sprintOrder = SprintOrder.createSprintOrder(1);
        SprintJpa sprintJPA = new SprintJpa(sprintID,
                projectCode, timePeriod, sprintStatus, sprintOrder);
        //act
        SprintID sprintID1 = sprintJPA.getSprintID();
        //assert
        assertEquals(sprintID, sprintID1);

    }
    @Test
    void getSprintStatus() {
        //arrange
        SprintID sprintID = SprintID.createSprintID("1");
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");
        TimePeriod timePeriod = TimePeriod.createTimePeriod(
                LocalDate.now(), LocalDate.now().plusWeeks(1));
        SprintStatus sprintStatus = SprintStatus.createSprintStatus(STARTED);
        SprintOrder sprintOrder = SprintOrder.createSprintOrder(1);
        SprintJpa sprintJPA = new SprintJpa(sprintID,
                projectCode, timePeriod, sprintStatus, sprintOrder);
        //act
        SprintStatus sprintStatus1 = sprintJPA.getSprintStatus();
        //assert
        assertEquals(sprintStatus, sprintStatus1);

    }

    @Test
    void getProjectCode() {
        //arrange
        SprintID sprintID = SprintID.createSprintID("1");
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");
        TimePeriod timePeriod = TimePeriod.createTimePeriod(
                LocalDate.now(), LocalDate.now().plusWeeks(1));
        SprintStatus sprintStatus = SprintStatus.createSprintStatus(STARTED);
        SprintOrder sprintOrder = SprintOrder.createSprintOrder(1);
        SprintJpa sprintJPA = new SprintJpa(sprintID,
                projectCode, timePeriod, sprintStatus, sprintOrder);
        //act
        ProjectCode projectCode1 = sprintJPA.getProjectCode();
        //assert
        assertEquals(projectCode, projectCode1);
    }

    @Test
    void getSprintOrder() {
        //arrange
        SprintID sprintID = SprintID.createSprintID("1");
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");
        TimePeriod timePeriod = TimePeriod.createTimePeriod(
                LocalDate.now(), LocalDate.now().plusWeeks(1));
        SprintStatus sprintStatus = SprintStatus.createSprintStatus(STARTED);
        SprintOrder sprintOrder = SprintOrder.createSprintOrder(1);
        SprintJpa sprintJPA = new SprintJpa(sprintID,
                projectCode, timePeriod, sprintStatus, sprintOrder);
        //act
        SprintOrder sprintOrder1 = sprintJPA.getSprintOrder();
        //assert
        assertEquals(sprintOrder, sprintOrder1);
    }

    @Test
    void getPlannedTimePeriod() {
        //arrange
        SprintID sprintID = SprintID.createSprintID("1");
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");
        TimePeriod timePeriod = TimePeriod.createTimePeriod(
                LocalDate.now(), LocalDate.now().plusWeeks(1));
        SprintStatus sprintStatus = SprintStatus.createSprintStatus(STARTED);
        SprintOrder sprintOrder = SprintOrder.createSprintOrder(1);
        SprintJpa sprintJPA = new SprintJpa(sprintID,
                projectCode, timePeriod, sprintStatus, sprintOrder);
        //act
        TimePeriod timePeriod1 = sprintJPA.getPlannedTimePeriod();
        //assert
        assertEquals(timePeriod, timePeriod1);
    }

    @Test
    void noArgsConst(){

        //Arrange
        SprintJpa sprintJPA = new SprintJpa();

        //Assert
        assertEquals(sprintJPA, sprintJPA);
    }
}